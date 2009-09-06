package scrum.client.collaboration;

import java.util.Map;

import scrum.client.admin.User;
import scrum.client.project.Project;

public class ChatMessage extends GChatMessage {

	public ChatMessage(Project project, User author, String text) {
		setProject(project);
		setAuthor(author);
		setText(text);
	}

	public ChatMessage(Map data) {
		super(data);
	}

}
