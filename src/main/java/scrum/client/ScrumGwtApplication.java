package scrum.client;

import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.GwtLogger;
import scrum.client.workspace.Workspace;

import com.google.gwt.user.client.ui.RootPanel;

public class ScrumGwtApplication extends GScrumGwtApplication {

	private final GwtLogger log = GwtLogger.createLogger(getClass());

	private ComponentManager cm;
	private ApplicationInfo applicationInfo;

	public void onModuleLoad() {

		// TODO remove workaround if issue is fixed
		// workaround for GWT issue 1813
		// http://code.google.com/p/google-web-toolkit/issues/detail?id=1813
		// RootPanel.get().getElement().getStyle().setProperty("position", "relative");

		cm = new ComponentManager();

		final Workspace workspace = cm.getUi().getWorkspace();
		workspace.lock("Loading...");

		RootPanel.get().add(workspace);
		callStartSession(new Runnable() {

			public void run() {
				cm.getPublicContext().activate();
				cm.getPinger().reschedule();
			}
		});

		ARichtextViewEditWidget.setDefaultRichtextFormater(new ScrumRichtextFormater());
		ScrumJs.initialize();
	}

	@Override
	protected void onServerData(DataTransferObject data) {
		cm.getEventBus().fireServerDataReceived(data);

		if (data.applicationInfo != null) {
			this.applicationInfo = data.applicationInfo;
			log.debug("applicationInfo:", data.applicationInfo);
		} else {
			assert this.applicationInfo != null;
		}

	}

	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}

	public String richtextToHtml(String text) {
		if (Gwt.isEmpty(text)) return text;
		String html = ScrumJs.regexTextToHtml(text);
		html = html.replace("\n", "<br>\n");
		html = html.replace("\r", "");
		return html;
	}

	@Override
	protected void handleCommunicationError(Throwable ex) {
		GwtLogger.ERROR("Communication Error:", ex);
		cm.getUi().getWorkspace().abort("Lost connection to server, please reload.");
	}

	public void showEntityByReference(final String reference) {
		GwtLogger.DEBUG("Showing entity by reference:", reference);

		if (reference.startsWith("[")) {
			String page = reference.substring(1, reference.length() - 1);
			cm.getProjectContext().showWiki(page);
			return;
		}

		AGwtEntity entity = cm.getDao().getEntityByReference(reference);
		if (entity != null) {
			cm.getProjectContext().showEntity(entity);
			return;
		}
		cm.getUi().getWorkspace().lock("Searching for " + reference);
		callRequestEntityByReference(reference, new Runnable() {

			public void run() {
				AGwtEntity entity = cm.getDao().getEntityByReference(reference);
				if (entity == null) {
					cm.getUi().getWorkspace().unlock();
					cm.getChat().postSystemMessage("Object does not exist: " + reference, false);
					return;
				}
				cm.getUi().getWorkspace().unlock();
				cm.getProjectContext().showEntity(entity);
			}
		});
	}

	public final void callStartSession(Runnable callback) {
		getScrumService().startSession(new DefaultCallback<DataTransferObject>(callback));
	}

	public static ScrumGwtApplication get() {
		return (ScrumGwtApplication) singleton;
	}

}
