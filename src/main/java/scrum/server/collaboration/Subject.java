package scrum.server.collaboration;

import java.util.Set;

import scrum.server.admin.User;

public class Subject extends GSubject {

	// --- dependencies ---

	private static CommentDao commentDao;

	public static void setCommentDao(CommentDao commentDao) {
		Subject.commentDao = commentDao;
	}

	// --- ---

	public Set<Comment> getComments() {
		return commentDao.getCommentsByParent(this);
	}

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getProject().generateSubjectNumber());
	}

	public String getReferenceAndLabel() {
		return getReference() + " " + getLabel();
	}

	public String getReference() {
		return scrum.client.collaboration.Subject.REFERENCE_PREFIX + getNumber();
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();
	}

	@Override
	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}

}