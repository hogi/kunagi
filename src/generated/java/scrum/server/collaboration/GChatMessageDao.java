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

public abstract class GChatMessageDao
            extends ilarkesto.persistence.ADao<ChatMessage> {

    public final String getEntityName() {
        return ChatMessage.TYPE;
    }

    public final Class getEntityClass() {
        return ChatMessage.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        chatMessagesByProjectCache.clear();
        projectsCache = null;
        chatMessagesByAuthorCache.clear();
        authorsCache = null;
        chatMessagesByTextCache.clear();
        textsCache = null;
        chatMessagesByDateAndTimeCache.clear();
        dateAndTimesCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof ChatMessage) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof ChatMessage) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<ChatMessage>> chatMessagesByProjectCache = new Cache<scrum.server.project.Project,Set<ChatMessage>>(
            new Cache.Factory<scrum.server.project.Project,Set<ChatMessage>>() {
                public Set<ChatMessage> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<ChatMessage> getChatMessagesByProject(scrum.server.project.Project project) {
        return chatMessagesByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (ChatMessage e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<ChatMessage> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(ChatMessage e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - author
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<ChatMessage>> chatMessagesByAuthorCache = new Cache<scrum.server.admin.User,Set<ChatMessage>>(
            new Cache.Factory<scrum.server.admin.User,Set<ChatMessage>>() {
                public Set<ChatMessage> create(scrum.server.admin.User author) {
                    return getEntities(new IsAuthor(author));
                }
            });

    public final Set<ChatMessage> getChatMessagesByAuthor(scrum.server.admin.User author) {
        return chatMessagesByAuthorCache.get(author);
    }
    private Set<scrum.server.admin.User> authorsCache;

    public final Set<scrum.server.admin.User> getAuthors() {
        if (authorsCache == null) {
            authorsCache = new HashSet<scrum.server.admin.User>();
            for (ChatMessage e : getEntities()) {
                if (e.isAuthorSet()) authorsCache.add(e.getAuthor());
            }
        }
        return authorsCache;
    }

    private static class IsAuthor implements Predicate<ChatMessage> {

        private scrum.server.admin.User value;

        public IsAuthor(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(ChatMessage e) {
            return e.isAuthor(value);
        }

    }

    // -----------------------------------------------------------
    // - text
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<ChatMessage>> chatMessagesByTextCache = new Cache<java.lang.String,Set<ChatMessage>>(
            new Cache.Factory<java.lang.String,Set<ChatMessage>>() {
                public Set<ChatMessage> create(java.lang.String text) {
                    return getEntities(new IsText(text));
                }
            });

    public final Set<ChatMessage> getChatMessagesByText(java.lang.String text) {
        return chatMessagesByTextCache.get(text);
    }
    private Set<java.lang.String> textsCache;

    public final Set<java.lang.String> getTexts() {
        if (textsCache == null) {
            textsCache = new HashSet<java.lang.String>();
            for (ChatMessage e : getEntities()) {
                if (e.isTextSet()) textsCache.add(e.getText());
            }
        }
        return textsCache;
    }

    private static class IsText implements Predicate<ChatMessage> {

        private java.lang.String value;

        public IsText(java.lang.String value) {
            this.value = value;
        }

        public boolean test(ChatMessage e) {
            return e.isText(value);
        }

    }

    // -----------------------------------------------------------
    // - dateAndTime
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.DateAndTime,Set<ChatMessage>> chatMessagesByDateAndTimeCache = new Cache<ilarkesto.base.time.DateAndTime,Set<ChatMessage>>(
            new Cache.Factory<ilarkesto.base.time.DateAndTime,Set<ChatMessage>>() {
                public Set<ChatMessage> create(ilarkesto.base.time.DateAndTime dateAndTime) {
                    return getEntities(new IsDateAndTime(dateAndTime));
                }
            });

    public final Set<ChatMessage> getChatMessagesByDateAndTime(ilarkesto.base.time.DateAndTime dateAndTime) {
        return chatMessagesByDateAndTimeCache.get(dateAndTime);
    }
    private Set<ilarkesto.base.time.DateAndTime> dateAndTimesCache;

    public final Set<ilarkesto.base.time.DateAndTime> getDateAndTimes() {
        if (dateAndTimesCache == null) {
            dateAndTimesCache = new HashSet<ilarkesto.base.time.DateAndTime>();
            for (ChatMessage e : getEntities()) {
                if (e.isDateAndTimeSet()) dateAndTimesCache.add(e.getDateAndTime());
            }
        }
        return dateAndTimesCache;
    }

    private static class IsDateAndTime implements Predicate<ChatMessage> {

        private ilarkesto.base.time.DateAndTime value;

        public IsDateAndTime(ilarkesto.base.time.DateAndTime value) {
            this.value = value;
        }

        public boolean test(ChatMessage e) {
            return e.isDateAndTime(value);
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

    protected scrum.server.project.ProjectDao projectDao;

    public void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

}