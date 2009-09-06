package scrum.client;

import ilarkesto.gwt.client.ADataTransferObject;
import ilarkesto.gwt.client.GwtLogger;

import java.util.LinkedList;

import scrum.client.admin.User;
import scrum.client.collaboration.ChatMessage;
import scrum.client.collaboration.ChatWidget;
import scrum.client.dnd.DndManager;
import scrum.client.project.Project;
import scrum.client.workspace.Ui;
import scrum.client.workspace.WorkareaWidget;
import scrum.client.workspace.WorkspaceWidget;

import com.google.gwt.user.client.ui.RootPanel;

public class ScrumGwtApplication extends GScrumGwtApplication {

	private Dao dao = new Dao();

	private User user;
	private Project project;
	private LinkedList<ChatMessage> chatMessages;
	private DndManager dndManager;
	private Ui ui;
	private boolean developmentMode;
	private long lastDataReceiveTime = System.currentTimeMillis();
	private PingTimer pingTimer;

	/**
	 * Application entry point.
	 */
	public void onModuleLoad() {

		// workaround for GWT issue 1813
		// http://code.google.com/p/google-web-toolkit/issues/detail?id=1813
		RootPanel.get().getElement().getStyle().setProperty("position", "relative");

		ui = Ui.get();
		ui.update();
		RootPanel.get("workspace").add(ui);
		ui.lock("Loading...");
		callPing(new Runnable() {

			public void run() {
				ui.showLogin();
			}
		});

		pingTimer = new PingTimer();
		pingTimer.scheduleRepeating(15000);
	}

	public ChatMessage postSystemMessage(String text) {
		return postMessage(null, text);
	}

	public ChatMessage postMessage(String text) {
		return postMessage(user, text);
	}

	public ChatMessage postMessage(User author, String text) {
		ChatMessage msg = new ChatMessage(getProject(), author, text);
		getDao().createChatMessage(msg);
		addChatMessage(msg);
		return msg;
	}

	public LinkedList<ChatMessage> getChatMessages() {
		return chatMessages;
	}

	void addChatMessage(ChatMessage msg) {
		if (project == null || !msg.isProject(project)) return;
		if (chatMessages.contains(msg)) return;
		chatMessages.add(msg);
		if (chatMessages.size() > 50) chatMessages.remove(0);
		ChatWidget.get().update();
	}

	public User getUser() {
		return user;
	}

	public Ui getUi() {
		return ui;
	}

	public boolean isDevelopmentMode() {
		return developmentMode;
	}

	public long getLastDataReceiveTime() {
		return lastDataReceiveTime;
	}

	@Override
	protected void handleDataFromServer(ADataTransferObject adata) {
		super.handleDataFromServer(adata);

		DataTransferObject data = (DataTransferObject) adata;

		if (data.containsEntities()) {
			lastDataReceiveTime = System.currentTimeMillis();
			pingTimer.schedule();
		}

		if (data.entityIdBase != null) {
			getDao().setEntityIdBase(data.entityIdBase);
			GwtLogger.DEBUG("entityIdBase:", data.entityIdBase);
		}

		if (data.developmentMode != null) {
			developmentMode = data.developmentMode;
		}

		if (data.isUserSet()) {
			user = getDao().getUser(data.getUserId());
		}

	}

	@Override
	protected void handleCommunicationError(Throwable ex) {
		ex.printStackTrace();
		ui.lock("Error: " + ex.getMessage());
	}

	public void openProject(Project project) {
		Ui.get().lock("Loading project...");
		this.project = project;
		this.chatMessages = new LinkedList<ChatMessage>();
		callSelectProject(project.getId(), new Runnable() {

			public void run() {
				Ui.get().unlock();
				Ui.get().showWorkspace();
				WorkareaWidget.get().showProjectOverview();
			}
		});

		postSystemMessage(user.getName() + " logged in.");
	}

	public void closeProject() {
		this.project = null;
		Ui.get().showStartPage();
	}

	public Project getProject() {
		return project;
	}

	@Override
	public Dao getDao() {
		return dao;
	}

	public static ScrumGwtApplication get() {
		return (ScrumGwtApplication) singleton;
	}

	public DndManager getDndManager() {
		if (dndManager == null) {
			dndManager = new DndManager();
		}
		return dndManager;
	}

	public void logout() {
		Ui.get().lock("logging out");
		user = null;
		project = null;
		getDao().clearAllEntities();
		ScrumGwtApplication.get().callLogout();
		Ui.get().showLogin();
	}

	// --- helper ---

	public WorkspaceWidget getWorkspaceWidget() {
		return getUi().getWorkspace();
	}

}
