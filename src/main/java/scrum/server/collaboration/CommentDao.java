package scrum.server.collaboration;

import ilarkesto.base.AFactoryCache;
import ilarkesto.base.Cache;
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

}