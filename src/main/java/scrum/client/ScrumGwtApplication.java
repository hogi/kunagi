package scrum.client;

import ilarkesto.core.base.Str;
import ilarkesto.core.logging.Log;
import ilarkesto.core.scope.NonConcurrentScopeManager;
import ilarkesto.core.scope.Scope;
import scrum.client.collaboration.Chat;
import scrum.client.collaboration.Subject;
import scrum.client.communication.Pinger;
import scrum.client.files.File;
import scrum.client.impediments.Impediment;
import scrum.client.issues.Issue;
import scrum.client.journal.ChangeHistoryManager;
import scrum.client.project.Quality;
import scrum.client.project.Requirement;
import scrum.client.risks.Risk;
import scrum.client.sprint.Task;
import scrum.client.workspace.WorkspaceWidget;

import com.google.gwt.user.client.ui.RootPanel;

public class ScrumGwtApplication extends GScrumGwtApplication {

	public static final String[] REFERENCE_PREFIXES = new String[] { Requirement.REFERENCE_PREFIX,
			Task.REFERENCE_PREFIX, Quality.REFERENCE_PREFIX, Issue.REFERENCE_PREFIX, Impediment.REFERENCE_PREFIX,
			Risk.REFERENCE_PREFIX, File.REFERENCE_PREFIX, Subject.REFERENCE_PREFIX };

	private final Log log = Log.get(getClass());

	private ComponentManager cm;
	private ApplicationInfo applicationInfo;

	public void onModuleLoad() {
		System.out.println("ScrumGwtApplication.onModuleLoad()");

		cm = new ComponentManager();

		NonConcurrentScopeManager scopeManager = NonConcurrentScopeManager.createCascadingScopeInstance("app",
			new ScrumComponentsReflector());

		final Scope appScope = scopeManager.getScope();
		appScope.putComponent("app", this);
		appScope.putComponent(cm.getDao());
		appScope.putComponent(new ChangeHistoryManager());
		appScope.putComponent(new Pinger());
		appScope.putComponent(new Chat());

		appScope.getComponent("pinger");

		final WorkspaceWidget workspace = cm.getUi().getWorkspace();
		workspace.lock("Loading...");

		RootPanel rootPanel = RootPanel.get();
		rootPanel.getElement().getStyle().setProperty("position", "relative");
		rootPanel.add(workspace);
		callStartSession(new Runnable() {

			public void run() {
				cm.getPublicContext().activate();
				cm.getEventBus().fireApplicationStart();
			}
		});

		cm.getWiki();
		ScrumJs.initialize();
	}

	@Override
	protected void onServerData(DataTransferObject data) {
		if (data.usersStatus != null) cm.getUsersStatus();

		if (data.applicationInfo != null) {
			this.applicationInfo = data.applicationInfo;
			log.debug("applicationInfo:", data.applicationInfo);
		} else {
			assert this.applicationInfo != null;
		}

		cm.getEventBus().fireServerDataReceived(data);
	}

	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}

	@Override
	protected void handleCommunicationError(Throwable ex) {
		log.error("Communication error:", ex);
		cm.getUi().getWorkspace().abort("Communication error: " + Str.formatException(ex));
	}

	@Override
	protected void handleUnexpectedError(Throwable ex) {
		log.error("Unexpected error:", ex);
		cm.getUi().getWorkspace().abort("Unexpected error: " + Str.formatException(ex));
	}

	public final void callStartSession(Runnable callback) {
		getScrumService().startSession(new DefaultCallback<DataTransferObject>(callback));
	}

}
