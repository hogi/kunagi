package scrum.client.collaboration;

import ilarkesto.gwt.client.DateAndTime;

import java.util.Comparator;
import java.util.Map;

import scrum.client.ScrumGwtApplication;
import scrum.client.project.Requirement;

public class Comment extends GComment {

	public Comment(Requirement parent) {
		setParent(parent);
		setText("");
		setDateAndTime(DateAndTime.now());
		setAuthor(ScrumGwtApplication.get().getUser());
	}

	public Comment(Map data) {
		super(data);
	}

	public static final Comparator<Comment> DATEANDTIME_COMPARATOR = new Comparator<Comment>() {

		public int compare(Comment a, Comment b) {
			return a.getDateAndTime().compareTo(b.getDateAndTime());
		}
	};

	public static final Comparator<Comment> REVERSE_DATEANDTIME_COMPARATOR = new Comparator<Comment>() {

		public int compare(Comment a, Comment b) {
			return b.getDateAndTime().compareTo(a.getDateAndTime());
		}
	};

}
