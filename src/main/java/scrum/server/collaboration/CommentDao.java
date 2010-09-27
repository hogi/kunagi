package scrum.server.collaboration;

import ilarkesto.base.AFactoryCache;
import ilarkesto.base.Cache;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.core.base.Str;
import ilarkesto.fp.Predicate;
import ilarkesto.persistence.AEntity;

import java.util.Set;

public class CommentDao extends GCommentDao {

	@Override
	public void clearCaches() {
		super.clearCaches();
		commentsByParentIdCache.clear();
	}

	public Set<Comment> getPublishedCommentsByParent(final AEntity parent) {
		return getEntities(new Predicate<Comment>() {

			@Override
			public boolean test(Comment c) {
				return c.isPublished() && c.isParent(parent);
			}
		});
	}

	private Cache<String, Set<Comment>> commentsByParentIdCache = new AFactoryCache<String, Set<Comment>>() {

		@Override
		public Set<Comment> create(final String parentId) {
			return getEntities(new Predicate<Comment>() {

				@Override
				public boolean test(Comment e) {
					return e.getParent().getId().equals(parentId);
				}
			});
		}

	};

	public Set<Comment> getCommentsByParentId(final String parentId) {
		return commentsByParentIdCache.get(parentId);
	}

	public Comment postComment(AEntity entity, String text, String name, String email) {
		assert entity != null;
		if (Str.isBlank(name)) name = "anonymous";
		Comment comment = newEntityInstance();
		comment.setParent(entity);
		comment.setText(text);
		comment.setAuthorName(name);
		comment.setAuthorEmail(email);
		comment.setDateAndTime(DateAndTime.now());
		saveEntity(comment);
		return comment;
	}

}