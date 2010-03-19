package scrum.client.collaboration;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import scrum.client.ComponentManager;
import scrum.client.admin.User;

public class Chat extends GChat {

	private LinkedList<ChatMessage> chatMessages = new LinkedList<ChatMessage>();

	public LinkedList<ChatMessage> getChatMessages() {
		return chatMessages;
	}

	public ChatMessage postSystemMessage(String text, boolean distribute) {
		return postMessage(null, text, distribute);
	}

	public ChatMessage postMessage(String text) {
		return postMessage(auth.getUser(), text, true);
	}

	private ChatMessage postMessage(User author, String text, boolean distribute) {
		ChatMessage msg = new ChatMessage(project, author, text);
		addChatMessage(msg);
		if (distribute) dao.createChatMessage(msg);
		return msg;
	}

	public void addChatMessage(ChatMessage msg) {
		if (project == null || !msg.isProject(project)) return;
		if (chatMessages.contains(msg)) return;
		chatMessages.add(msg);
		cleanupChatMessages();
		ComponentManager.get().getProjectContext().getSidebar().getChat().update();
	}

	private void cleanupChatMessages() {
		for (Iterator<ChatMessage> it = chatMessages.iterator(); it.hasNext();) {
			ChatMessage message = it.next();
			if (message.isOld()) it.remove();
		}
		while (chatMessages.size() > 50)
			chatMessages.remove(0);
		Collections.sort(chatMessages);
	}
}
