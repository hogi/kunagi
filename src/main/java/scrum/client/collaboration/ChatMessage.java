package scrum.client.collaboration;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.DateAndTime;
import ilarkesto.gwt.client.Time;

import java.util.Map;

import scrum.client.admin.User;
import scrum.client.project.Project;

public class ChatMessage extends GChatMessage implements Comparable<ChatMessage> {

	public ChatMessage(Project project, User author, String text) {
		setProject(project);
		setAuthor(author);
		setText(text);
		setDateAndTime(DateAndTime.now());
	}

	public ChatMessage(Map data) {
		super(data);
	}

	@Override
	public String toString() {
		return getAuthor() + ": " + getText();
	}

	public boolean isOld() {
		DateAndTime dt = getDateAndTime();

		Date today = Date.today();
		if (!dt.getDate().equals(today)) return true;

		return Time.now().toSeconds() - dt.getTime().toSeconds() > 900;
	}

	public int compareTo(ChatMessage o) {
		return getDateAndTime().compareTo(o.getDateAndTime());
	}

}
