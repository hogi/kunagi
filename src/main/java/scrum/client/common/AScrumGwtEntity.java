package scrum.client.common;

import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.Gwt;

import java.util.List;
import java.util.Map;

import scrum.client.ComponentManager;
import scrum.client.collaboration.Comment;

public abstract class AScrumGwtEntity extends AGwtEntity {

	protected static final ComponentManager cm = ComponentManager.get();

	public AScrumGwtEntity() {}

	public List<Comment> getComments() {
		return cm.getDao().getCommentsByParent(this);
	}

	public Comment getLatestComment() {
		Comment latest = null;
		for (Comment comment : getComments()) {
			if (latest == null || comment.getDateAndTime().isAfter(latest.getDateAndTime())) latest = comment;
		}
		return latest;
	}

	@Override
	public boolean matchesKey(String key) {
		if (this instanceof ReferenceSupport) {
			if (matchesKey(((ReferenceSupport) this).getReference(), key)) return true;
		}
		return super.matchesKey(key);
	}

	public AScrumGwtEntity(Map data) {
		super(data);
	}

	public String toHtml() {
		return Gwt.escapeHtml(toString());
	}

}
