// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.DaoGenerator










package scrum.server.admin;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GProjectUserConfigDao
            extends ilarkesto.persistence.ADao<ProjectUserConfig> {

    public final String getEntityName() {
        return ProjectUserConfig.TYPE;
    }

    public final Class getEntityClass() {
        return ProjectUserConfig.class;
    }

    public Set<ProjectUserConfig> getEntitiesVisibleForUser(final scrum.server.admin.User user) {
        return getEntities(new Predicate<ProjectUserConfig>() {
            public boolean test(ProjectUserConfig e) {
                return Auth.isVisible(e, user);
            }
        });
    }

    // --- clear caches ---
    public void clearCaches() {
        projectUserConfigsByProjectCache.clear();
        projectsCache = null;
        projectUserConfigsByUserCache.clear();
        usersCache = null;
        projectUserConfigsByColorCache.clear();
        colorsCache = null;
        projectUserConfigsByMisconductsCache.clear();
        misconductssCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof ProjectUserConfig) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof ProjectUserConfig) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<ProjectUserConfig>> projectUserConfigsByProjectCache = new Cache<scrum.server.project.Project,Set<ProjectUserConfig>>(
            new Cache.Factory<scrum.server.project.Project,Set<ProjectUserConfig>>() {
                public Set<ProjectUserConfig> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<ProjectUserConfig> getProjectUserConfigsByProject(scrum.server.project.Project project) {
        return projectUserConfigsByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (ProjectUserConfig e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<ProjectUserConfig> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(ProjectUserConfig e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - user
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<ProjectUserConfig>> projectUserConfigsByUserCache = new Cache<scrum.server.admin.User,Set<ProjectUserConfig>>(
            new Cache.Factory<scrum.server.admin.User,Set<ProjectUserConfig>>() {
                public Set<ProjectUserConfig> create(scrum.server.admin.User user) {
                    return getEntities(new IsUser(user));
                }
            });

    public final Set<ProjectUserConfig> getProjectUserConfigsByUser(scrum.server.admin.User user) {
        return projectUserConfigsByUserCache.get(user);
    }
    private Set<scrum.server.admin.User> usersCache;

    public final Set<scrum.server.admin.User> getUsers() {
        if (usersCache == null) {
            usersCache = new HashSet<scrum.server.admin.User>();
            for (ProjectUserConfig e : getEntities()) {
                if (e.isUserSet()) usersCache.add(e.getUser());
            }
        }
        return usersCache;
    }

    private static class IsUser implements Predicate<ProjectUserConfig> {

        private scrum.server.admin.User value;

        public IsUser(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(ProjectUserConfig e) {
            return e.isUser(value);
        }

    }

    // -----------------------------------------------------------
    // - color
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<ProjectUserConfig>> projectUserConfigsByColorCache = new Cache<java.lang.String,Set<ProjectUserConfig>>(
            new Cache.Factory<java.lang.String,Set<ProjectUserConfig>>() {
                public Set<ProjectUserConfig> create(java.lang.String color) {
                    return getEntities(new IsColor(color));
                }
            });

    public final Set<ProjectUserConfig> getProjectUserConfigsByColor(java.lang.String color) {
        return projectUserConfigsByColorCache.get(color);
    }
    private Set<java.lang.String> colorsCache;

    public final Set<java.lang.String> getColors() {
        if (colorsCache == null) {
            colorsCache = new HashSet<java.lang.String>();
            for (ProjectUserConfig e : getEntities()) {
                if (e.isColorSet()) colorsCache.add(e.getColor());
            }
        }
        return colorsCache;
    }

    private static class IsColor implements Predicate<ProjectUserConfig> {

        private java.lang.String value;

        public IsColor(java.lang.String value) {
            this.value = value;
        }

        public boolean test(ProjectUserConfig e) {
            return e.isColor(value);
        }

    }

    // -----------------------------------------------------------
    // - misconducts
    // -----------------------------------------------------------

    private final Cache<Integer,Set<ProjectUserConfig>> projectUserConfigsByMisconductsCache = new Cache<Integer,Set<ProjectUserConfig>>(
            new Cache.Factory<Integer,Set<ProjectUserConfig>>() {
                public Set<ProjectUserConfig> create(Integer misconducts) {
                    return getEntities(new IsMisconducts(misconducts));
                }
            });

    public final Set<ProjectUserConfig> getProjectUserConfigsByMisconducts(int misconducts) {
        return projectUserConfigsByMisconductsCache.get(misconducts);
    }
    private Set<Integer> misconductssCache;

    public final Set<Integer> getMisconductss() {
        if (misconductssCache == null) {
            misconductssCache = new HashSet<Integer>();
            for (ProjectUserConfig e : getEntities()) {
                misconductssCache.add(e.getMisconducts());
            }
        }
        return misconductssCache;
    }

    private static class IsMisconducts implements Predicate<ProjectUserConfig> {

        private int value;

        public IsMisconducts(int value) {
            this.value = value;
        }

        public boolean test(ProjectUserConfig e) {
            return e.isMisconducts(value);
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