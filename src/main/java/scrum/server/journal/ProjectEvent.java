package scrum.server.journal;

import scrum.server.ScrumWebApplication;
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

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();

		// renaming: req -> sto
		if (ScrumWebApplication.REQ_RENAMING_DATE.isAfter(getLastModified())) {
			setLabel(ScrumWebApplication.convertReqToSto(getLabel()));
		}
	}

	@Override
	public String toString() {
		return getLabel();
	}

}