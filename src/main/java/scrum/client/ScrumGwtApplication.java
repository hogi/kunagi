package scrum.client;

import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.GwtLogger;

import java.util.LinkedList;

import scrum.client.admin.User;
import scrum.client.collaboration.ChatMessage;
import scrum.client.collaboration.ChatWidget;
import scrum.client.communication.UsersStatusWidget;
import scrum.client.context.ProjectContext;
import scrum.client.context.StartContext;
import scrum.client.dnd.DndManager;
import scrum.client.project.Project;
import scrum.client.workspace.Ui;

import com.google.gwt.user.client.ui.RootPanel;

public class ScrumGwtApplication extends GScrumGwtApplication {

	private Dao dao = new Dao();

	private ApplicationInfo applicationInfo;
	private User user;
	private Project project;
	private LinkedList<ChatMessage> chatMessages;
	private DndManager dndManager;
	private Ui ui;
	private long lastDataReceiveTime = System.currentTimeMillis();
	private PingTimer pingTimer;

	public void onModuleLoad() {
		// TODO remove workaround if issue is fixed
		// workaround for GWT issue 1813
		// http://code.google.com/p/google-web-toolkit/issues/detail?id=1813
		RootPanel.get().getElement().getStyle().setProperty("position", "relative");

		ui = Ui.get();
		ui.update();
		RootPanel.get("ScrumGwtApplication").add(ui);
		ui.lock("Loading...");
		callStartSession(new Runnable() {

			public void run() {
				ui.activatePublicView();
				pingTimer = new PingTimer();
			}
		});

		ARichtextViewEditWidget.setDefaultRichtextFormater(new ScrumRichtextFormater());
		ScrumJs.initialize();
	}

	@Override
	protected void onServerData(DataTransferObject dto) {
		if (dto.containsEntities()) {
			lastDataReceiveTime = System.currentTimeMillis();
			if (pingTimer != null) pingTimer.schedule();
		}

		if (dto.usersSelectedEntitysIds != null) {
			lastDataReceiveTime = System.currentTimeMillis();
			ProjectContext.get().updateUsersSelectedEntitysIds(dto.usersSelectedEntitysIds);
			Ui.get().update();
		}

		if (dto.onlineTeamMembersIds != null) {
			lastDataReceiveTime = System.currentTimeMillis();
			GwtLogger.DEBUG("onlineTeamMembersIds:", dto.onlineTeamMembersIds);
			project.setOnlineTeamMembersIds(dto.onlineTeamMembersIds);
			if (ProjectContext.isActive()) UsersStatusWidget.get().update();
		}

		if (dto.entityIdBase != null) {
			getDao().setEntityIdBase(dto.entityIdBase);
			GwtLogger.DEBUG("entityIdBase:", dto.entityIdBase);
		}

		if (dto.applicationInfo != null) {
			this.applicationInfo = dto.applicationInfo;
			GwtLogger.DEBUG("applicationInfo:", dto.applicationInfo);
		} else {
			assert this.applicationInfo != null;
		}

		if (dto.isUserSet()) {
			user = getDao().getUser(dto.getUserId());
		}

	}

	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}

	public String richtextToHtml(String text) {
		if (Gwt.isEmpty(text)) return text;
		String html = ScrumJs.regexTextToHtml(text);
		html = html.replace("\r\n", "<br>\n");
		html = html.replace("\n", "<br>\n");
		return html;
	}

	public ChatMessage postSystemMessage(String text, boolean distribute) {
		return postMessage(null, text, distribute);
	}

	public ChatMessage postMessage(String text) {
		return postMessage(user, text, true);
	}

	private ChatMessage postMessage(User author, String text, boolean distribute) {
		ChatMessage msg = new ChatMessage(getProject(), author, text);
		addChatMessage(msg);
		if (distribute) getDao().createChatMessage(msg);
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

	public long getLastDataReceiveTime() {
		return lastDataReceiveTime;
	}

	@Override
	protected void handleCommunicationError(Throwable ex) {
		GwtLogger.ERROR("Communication Error:", ex);
		if (project != null) {
			postSystemMessage("Communication Error: " + ex.getMessage(), false);
		} else {
			ui.showError(ex.getMessage());
		}
	}

	public void openProject(Project project) {
		Ui.get().lock("Loading project...");
		this.project = project;
		this.chatMessages = new LinkedList<ChatMessage>();
		callSelectProject(project.getId(), new Runnable() {

			public void run() {
				Ui.get().unlock();
				Ui.get().activateProjectView();
			}
		});
	}

	public void closeProject() {
		callCloseProject();
		this.project = null;
		Dao dao = getDao();
		dao.clearChatMessages();
		dao.clearImpediments();
		dao.clearQualitys();
		dao.clearRequirements();
		dao.clearRisks();
		dao.clearSprints();
		dao.clearTasks();
		Ui.get().activateStartView();
	}

	public void showEntityByReference(final String reference) {
		GwtLogger.DEBUG("Showing entity:", reference);

		if (reference.startsWith("[")) {
			String page = reference.substring(1, reference.length() - 1);
			ProjectContext.get().showWiki(page);
			return;
		}

		AGwtEntity entity = getDao().getEntityByReference(reference);
		if (entity != null) {
			ProjectContext.get().showEntity(entity);
			return;
		}
		ui.lock("Searching for " + reference);
		callRequestEntityByReference(reference, new Runnable() {

			public void run() {
				AGwtEntity entity = getDao().getEntityByReference(reference);
				if (entity == null) {
					ui.unlock();
					postSystemMessage("Object does not exist: " + reference, false);
					return;
				}
				ui.unlock();
				ProjectContext.get().showEntity(entity);
			}
		});
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
		closeProject();
		ScrumGwtApplication.get().callLogout();
		user = null;
		StartContext.destroy();
		ProjectContext.destroy();
		getDao().clearAllEntities();
		Ui.get().activatePublicView();
	}

	public final void callStartSession(Runnable callback) {
		getScrumService().startSession(new DefaultCallback<DataTransferObject>(callback));
	}

}
