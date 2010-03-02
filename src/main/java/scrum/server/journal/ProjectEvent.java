package scrum.server.journal;

import scrum.server.admin.User;
import scrum.server.collaboration.ChatMessage;
import scrum.server.collaboration.ChatMessageDao;

public class ProjectEvent extends GProjectEvent {

	// --- dependencies ---

	private static ChatMessageDao chatMessageDao;

	public static void setChatMessageDao(ChatMessageDao chatMessageDao) {
		ProjectEvent.chatMessageDao = chatMessageDao;
	}

	// --- ---

	public ChatMessage createChatMessage() {
		ChatMessage msg = chatMessageDao.newEntityInstance();
		msg.setProject(getProject());
		msg.setDateAndTime(getDateAndTime());
		msg.setText(getLabel());
		return msg;
	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	public boolean isEditableBy(User user) {
		return false;
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();

	}

	@Override
	public String toString() {
		return getLabel();
	}

}