package scrum.client.collaboration;

import java.util.Map;

import scrum.client.project.Requirement;

public class Comment extends GComment {

	public Comment(Requirement parent) {
		setParent(parent);
	}

	public Comment(Map data) {
		super(data);
	}

}
