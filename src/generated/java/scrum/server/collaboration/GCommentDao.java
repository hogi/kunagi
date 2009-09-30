// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.collaboration;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GCommentDao
            extends ilarkesto.persistence.ADao<Comment> {

    public final String getEntityName() {
        return Comment.TYPE;
    }

    public final Class getEntityClass() {
        return Comment.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        commentsByParentCache.clear();
        parentsCache = null;
        commentsByAuthorCache.clear();
        authorsCache = null;
        commentsByTextCache.clear();
        textsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Comment) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Comment) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - parent
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Requirement,Set<Comment>> commentsByParentCache = new Cache<scrum.server.project.Requirement,Set<Comment>>(
            new Cache.Factory<scrum.server.project.Requirement,Set<Comment>>() {
                public Set<Comment> create(scrum.server.project.Requirement parent) {
                    return getEntities(new IsParent(parent));
                }
            });

    public final Set<Comment> getCommentsByParent(scrum.server.project.Requirement parent) {
        return commentsByParentCache.get(parent);
    }
    private Set<scrum.server.project.Requirement> parentsCache;

    public final Set<scrum.server.project.Requirement> getParents() {
        if (parentsCache == null) {
            parentsCache = new HashSet<scrum.server.project.Requirement>();
            for (Comment e : getEntities()) {
                if (e.isParentSet()) parentsCache.add(e.getParent());
            }
        }
        return parentsCache;
    }

    private static class IsParent implements Predicate<Comment> {

        private scrum.server.project.Requirement value;

        public IsParent(scrum.server.project.Requirement value) {
            this.value = value;
        }

        public boolean test(Comment e) {
            return e.isParent(value);
        }

    }

    // -----------------------------------------------------------
    // - author
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<Comment>> commentsByAuthorCache = new Cache<scrum.server.admin.User,Set<Comment>>(
            new Cache.Factory<scrum.server.admin.User,Set<Comment>>() {
                public Set<Comment> create(scrum.server.admin.User author) {
                    return getEntities(new IsAuthor(author));
                }
            });

    public final Set<Comment> getCommentsByAuthor(scrum.server.admin.User author) {
        return commentsByAuthorCache.get(author);
    }
    private Set<scrum.server.admin.User> authorsCache;

    public final Set<scrum.server.admin.User> getAuthors() {
        if (authorsCache == null) {
            authorsCache = new HashSet<scrum.server.admin.User>();
            for (Comment e : getEntities()) {
                if (e.isAuthorSet()) authorsCache.add(e.getAuthor());
            }
        }
        return authorsCache;
    }

    private static class IsAuthor implements Predicate<Comment> {

        private scrum.server.admin.User value;

        public IsAuthor(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(Comment e) {
            return e.isAuthor(value);
        }

    }

    // -----------------------------------------------------------
    // - text
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Comment>> commentsByTextCache = new Cache<java.lang.String,Set<Comment>>(
            new Cache.Factory<java.lang.String,Set<Comment>>() {
                public Set<Comment> create(java.lang.String text) {
                    return getEntities(new IsText(text));
                }
            });

    public final Set<Comment> getCommentsByText(java.lang.String text) {
        return commentsByTextCache.get(text);
    }
    private Set<java.lang.String> textsCache;

    public final Set<java.lang.String> getTexts() {
        if (textsCache == null) {
            textsCache = new HashSet<java.lang.String>();
            for (Comment e : getEntities()) {
                if (e.isTextSet()) textsCache.add(e.getText());
            }
        }
        return textsCache;
    }

    private static class IsText implements Predicate<Comment> {

        private java.lang.String value;

        public IsText(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Comment e) {
            return e.isText(value);
        }

    }

    // --- valueObject classes ---
    @Override
    protected Set<Class> getValueObjectClasses() {
        Set<Class> ret = new HashSet<Class>(super.getValueObjectClasses());
        return ret;
    }

    @Override
    public Map<String, Class> getAliases() {
        Map<String, Class> aliases = new HashMap<String, Class>(super.getAliases());
        return aliases;
    }

    // --- dependencies ---

    protected scrum.server.project.RequirementDao requirementDao;

    public void setRequirementDao(scrum.server.project.RequirementDao requirementDao) {
        this.requirementDao = requirementDao;
    }

}