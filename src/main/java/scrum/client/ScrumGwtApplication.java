package scrum.client;

import ilarkesto.core.base.Str;
import ilarkesto.core.logging.Log;
import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.AGwtDao;
import scrum.client.admin.Auth;
import scrum.client.calendar.SimpleEvent;
import scrum.client.collaboration.Subject;
import scrum.client.common.AScrumWidget;
import scrum.client.communication.Pinger;
import scrum.client.communication.ServerDataReceivedEvent;
import scrum.client.core.ApplicationStartedEvent;
import scrum.client.files.File;
import scrum.client.impediments.Impediment;
import scrum.client.issues.Issue;
import scrum.client.project.Quality;
import scrum.client.project.Requirement;
import scrum.client.release.Release;
import scrum.client.risks.Risk;
import scrum.client.sprint.Task;
import scrum.client.workspace.Navigator;
import scrum.client.workspace.Ui;
import scrum.client.workspace.WorkspaceWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

public class ScrumGwtApplication extends GScrumGwtApplication {

	public static final String[] REFERENCE_PREFIXES = new String[] { Requirement.REFERENCE_PREFIX,
			Task.REFERENCE_PREFIX, Quality.REFERENCE_PREFIX, Issue.REFERENCE_PREFIX, Impediment.REFERENCE_PREFIX,
			Risk.REFERENCE_PREFIX, File.REFERENCE_PREFIX, Subject.REFERENCE_PREFIX, SimpleEvent.REFERENCE_PREFIX,
			Release.REFERENCE_PREFIX };

	private final Log log = Log.get(getClass());

	private ApplicationInfo applicationInfo;
	private AScrumWidget statusWidget;

	public void onModuleLoad() {
		System.out.println("ScrumGwtApplication.onModuleLoad()");

		ScrumScopeManager.initialize();

		final WorkspaceWidget workspace = Scope.get().getComponent(Ui.class).getWorkspace();
		workspace.lock("Loading...");

		RootPanel rootPanel = RootPanel.get();
		rootPanel.getElement().getStyle().setProperty("position", "relative");
		rootPanel.add(workspace);
		// rootPanel.add(new WidgetsTesterWidget().update());
		ScrumJs.initialize();

		callStartConversation(new Runnable() {

			public void run() {
				new ApplicationStartedEvent().fireInCurrentScope();
				Scope.get().getComponent(Navigator.class).gotoStart();
			}
		});

	}

	@Override
	public void resetConversation() {
		super.resetConversation();
	}

	@Override
	protected void onServerData(DataTransferObject data) {
		if (data.applicationInfo != null) {
			this.applicationInfo = data.applicationInfo;
			log.debug("applicationInfo:", data.applicationInfo);
			// Scope.get().putComponent(data.applicationInfo);
		} else {
			assert this.applicationInfo != null;
		}

		new ServerDataReceivedEvent(data).fireInCurrentScope();
	}

	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}

	public void logout() {
		log.info("Logging out");

		Scope.get().getComponent(Ui.class).lock("Logging out...");
		Scope.get().getComponent(Auth.class).logout();
		Scope.get().getComponent(Pinger.class).shutdown();
		Scope.get().getComponent(Dao.class).clearAllEntities();

		callLogout(new Runnable() {

			public void run() {
				String url = GWT.getHostPageBaseURL();
				if (!GWT.isScript()) url += "index.html?gwt.codesvr=localhost:9997";
				Window.Location.replace(url);
			}
		});

	}

	@Override
	protected void handleCommunicationError(Throwable ex) {
		Scope.get().getComponent(Ui.class).getWorkspace().abort(Str.formatException(ex));
	}

	@Override
	protected void handleUnexpectedError(Throwable ex) {
		log.error("Unexpected error:", ex);
		Scope.get().getComponent(Ui.class).getWorkspace().abort("Unexpected error: " + Str.formatException(ex));
	}

	public final void callStartConversation(Runnable callback) {
		getScrumService().startConversation(new DefaultCallback<DataTransferObject>(callback, "startConversation"));
	}

	@Override
	protected AGwtDao getDao() {
		return Dao.get();
	}

	public void setStatusWidget(AScrumWidget statusWidget) {
		this.statusWidget = statusWidget;
	}

	@Override
	protected void onServiceCall() {
		super.onServiceCall();
		if (statusWidget != null) statusWidget.update();
	}

	@Override
	protected void onServiceCallReturn() {
		super.onServiceCallReturn();
		if (statusWidget != null) statusWidget.update();
	}

}
