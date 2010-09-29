package scrum.client.collaboration;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.DateAndTime;

import java.util.Comparator;
import java.util.Map;

import scrum.client.admin.User;
import scrum.client.common.AScrumGwtEntity;
import scrum.client.project.Project;

public class Comment extends GComment {

	public Comment(AGwtEntity parent, User author, String text) {
		setParent(parent);
		setAuthor(author);
		setAuthorName(author.getName());
		setText(text);
		setDateAndTime(DateAndTime.now());
	}

	public Comment(Map data) {
		super(data);
	}

	@Override
	public boolean isEditable() {
		User currentUser = Scope.get().getComponent(User.class);
		if (isAuthorSet()) {
			if (!isAuthor(currentUser)) return false;
			if (getDateAndTime().getPeriodFromNow().abs().toHours() > 6) return false;
			AScrumGwtEntity parent = (AScrumGwtEntity) getParent();
			if (parent.getLatestComment() != this) return false;
		} else {
			// public comment
			Project project = Scope.get().getComponent(Project.class);
			if (!project.isProductOwnerOrScrumMasterOrTeamMember(currentUser)) return false;
		}
		return true;
	}

	public static final Comparator<Comment> DATEANDTIME_COMPARATOR = new Comparator<Comment>() {

		@Override
		public int compare(Comment a, Comment b) {
			return a.getDateAndTime().compareTo(b.getDateAndTime());
		}
	};

	public static final Comparator<Comment> REVERSE_DATEANDTIME_COMPARATOR = new Comparator<Comment>() {

		@Override
		public int compare(Comment a, Comment b) {
			return b.getDateAndTime().compareTo(a.getDateAndTime());
		}
	};

}
