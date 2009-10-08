package scrum.client;

import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.GwtLogger;
import scrum.client.admin.User;
import scrum.client.project.Project;
import scrum.client.workspace.Workspace;

import com.google.gwt.user.client.ui.RootPanel;

public class ScrumGwtApplication extends GScrumGwtApplication {

	private final GwtLogger log = GwtLogger.createLogger(getClass());

	private Components components;
	private ApplicationInfo applicationInfo;

	public void onModuleLoad() {

		// TODO remove workaround if issue is fixed
		// workaround for GWT issue 1813
		// http://code.google.com/p/google-web-toolkit/issues/detail?id=1813
		RootPanel.get().getElement().getStyle().setProperty("position", "relative");

		components = new Components();

		final Workspace workspace = components.getUi().getWorkspace();
		workspace.lock("Loading...");
		RootPanel.get("ScrumGwtApplication").add(workspace);
		callStartSession(new Runnable() {

			public void run() {
				components.getPublicContext().activate();
				components.getPinger().reschedule();
			}
		});

		ARichtextViewEditWidget.setDefaultRichtextFormater(new ScrumRichtextFormater());
		ScrumJs.initialize();
	}

	@Override
	protected void onServerData(DataTransferObject data) {
		components.getEventBus().fireServerDataReceived(data);

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

	public User getUser() {
		return components.getAuth().getUser();
	}

	@Override
	protected void handleCommunicationError(Throwable ex) {
		GwtLogger.ERROR("Communication Error:", ex);
		components.getUi().getWorkspace().abort("Lost connection to server, please reload.");
	}

	public void showEntityByReference(final String reference) {
		GwtLogger.DEBUG("Showing entity by reference:", reference);

		if (reference.startsWith("[")) {
			String page = reference.substring(1, reference.length() - 1);
			components.getProjectContext().showWiki(page);
			return;
		}

		AGwtEntity entity = getDao().getEntityByReference(reference);
		if (entity != null) {
			components.getProjectContext().showEntity(entity);
			return;
		}
		components.getUi().getWorkspace().lock("Searching for " + reference);
		callRequestEntityByReference(reference, new Runnable() {

			public void run() {
				AGwtEntity entity = getDao().getEntityByReference(reference);
				if (entity == null) {
					components.getUi().getWorkspace().unlock();
					components.getChat().postSystemMessage("Object does not exist: " + reference, false);
					return;
				}
				components.getUi().getWorkspace().unlock();
				components.getProjectContext().showEntity(entity);
			}
		});
	}

	@Deprecated
	public Project getProject() {
		return components.getProjectContext().getProject();
	}

	@Override
	public Dao getDao() {
		return components.getDao();
	}

	public static ScrumGwtApplication get() {
		return (ScrumGwtApplication) singleton;
	}

	public final void callStartSession(Runnable callback) {
		getScrumService().startSession(new DefaultCallback<DataTransferObject>(callback));
	}

}
