package scrum.server.collaboration;

import ilarkesto.fp.Predicate;

import java.util.Set;

public class CommentDao extends GCommentDao {

	public Set<Comment> getCommentsByParentId(final String parentId) {
		return getEntities(new Predicate<Comment>() {

			public boolean test(Comment e) {
				return e.getParent().getId().equals(parentId);
			}
		});
	}

}