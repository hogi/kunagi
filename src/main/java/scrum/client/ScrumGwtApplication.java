package scrum.client;

import ilarkesto.gwt.client.GwtLogger;
import scrum.client.workspace.Workspace;

import com.google.gwt.user.client.ui.RootPanel;

public class ScrumGwtApplication extends GScrumGwtApplication {

	private final GwtLogger log = GwtLogger.createLogger(getClass());

	private ComponentManager cm;
	private ApplicationInfo applicationInfo;

	public void onModuleLoad() {
		log.debug("onModuleLoad()");
		cm = new ComponentManager();

		final Workspace workspace = cm.getUi().getWorkspace();
		workspace.lock("Loading...");

		RootPanel rootPanel = RootPanel.get();
		rootPanel.getElement().getStyle().setProperty("position", "relative");
		rootPanel.add(workspace);
		callStartSession(new Runnable() {

			public void run() {
				cm.getPublicContext().activate();
				cm.getPinger().reschedule();
			}
		});

		cm.getRichtextConverter();
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

	@Override
	protected void handleCommunicationError(Throwable ex) {
		GwtLogger.ERROR("Communication Error:", ex);
		cm.getUi().getWorkspace().abort("Lost connection to server.");
	}

	public final void callStartSession(Runnable callback) {
		getScrumService().startSession(new DefaultCallback<DataTransferObject>(callback));
	}

}
