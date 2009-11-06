// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.journal;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GProjectEventDao
            extends ilarkesto.persistence.ADao<ProjectEvent> {

    public final String getEntityName() {
        return ProjectEvent.TYPE;
    }

    public final Class getEntityClass() {
        return ProjectEvent.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        projectEventsByProjectCache.clear();
        projectsCache = null;
        projectEventsByTextCache.clear();
        textsCache = null;
        projectEventsByTimestampCache.clear();
        timestampsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof ProjectEvent) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof ProjectEvent) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<ProjectEvent>> projectEventsByProjectCache = new Cache<scrum.server.project.Project,Set<ProjectEvent>>(
            new Cache.Factory<scrum.server.project.Project,Set<ProjectEvent>>() {
                public Set<ProjectEvent> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<ProjectEvent> getProjectEventsByProject(scrum.server.project.Project project) {
        return projectEventsByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (ProjectEvent e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<ProjectEvent> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(ProjectEvent e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - text
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<ProjectEvent>> projectEventsByTextCache = new Cache<java.lang.String,Set<ProjectEvent>>(
            new Cache.Factory<java.lang.String,Set<ProjectEvent>>() {
                public Set<ProjectEvent> create(java.lang.String text) {
                    return getEntities(new IsText(text));
                }
            });

    public final Set<ProjectEvent> getProjectEventsByText(java.lang.String text) {
        return projectEventsByTextCache.get(text);
    }
    private Set<java.lang.String> textsCache;

    public final Set<java.lang.String> getTexts() {
        if (textsCache == null) {
            textsCache = new HashSet<java.lang.String>();
            for (ProjectEvent e : getEntities()) {
                if (e.isTextSet()) textsCache.add(e.getText());
            }
        }
        return textsCache;
    }

    private static class IsText implements Predicate<ProjectEvent> {

        private java.lang.String value;

        public IsText(java.lang.String value) {
            this.value = value;
        }

        public boolean test(ProjectEvent e) {
            return e.isText(value);
        }

    }

    // -----------------------------------------------------------
    // - timestamp
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.DateAndTime,Set<ProjectEvent>> projectEventsByTimestampCache = new Cache<ilarkesto.base.time.DateAndTime,Set<ProjectEvent>>(
            new Cache.Factory<ilarkesto.base.time.DateAndTime,Set<ProjectEvent>>() {
                public Set<ProjectEvent> create(ilarkesto.base.time.DateAndTime timestamp) {
                    return getEntities(new IsTimestamp(timestamp));
                }
            });

    public final Set<ProjectEvent> getProjectEventsByTimestamp(ilarkesto.base.time.DateAndTime timestamp) {
        return projectEventsByTimestampCache.get(timestamp);
    }
    private Set<ilarkesto.base.time.DateAndTime> timestampsCache;

    public final Set<ilarkesto.base.time.DateAndTime> getTimestamps() {
        if (timestampsCache == null) {
            timestampsCache = new HashSet<ilarkesto.base.time.DateAndTime>();
            for (ProjectEvent e : getEntities()) {
                if (e.isTimestampSet()) timestampsCache.add(e.getTimestamp());
            }
        }
        return timestampsCache;
    }

    private static class IsTimestamp implements Predicate<ProjectEvent> {

        private ilarkesto.base.time.DateAndTime value;

        public IsTimestamp(ilarkesto.base.time.DateAndTime value) {
            this.value = value;
        }

        public boolean test(ProjectEvent e) {
            return e.isTimestamp(value);
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

    scrum.server.project.ProjectDao projectDao;

    public void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

}