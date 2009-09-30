package scrum.client.collaboration;

import ilarkesto.gwt.client.DateAndTime;

import java.util.Map;

import scrum.client.ScrumGwtApplication;
import scrum.client.project.Requirement;

public class Comment extends GComment {

	public Comment(Requirement parent) {
		setParent(parent);
		setText("no comment");
		setDateAndTime(DateAndTime.now());
		setAuthor(ScrumGwtApplication.get().getUser());
	}

	public Comment(Map data) {
		super(data);
	}

}
