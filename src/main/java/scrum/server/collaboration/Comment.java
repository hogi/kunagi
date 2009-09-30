package scrum.server.collaboration;

public class Comment extends GComment {

	@Override
	public String toString() {
		return "[" + getAuthor() + "@" + getDateAndTime() + "] " + getText();
	}
}