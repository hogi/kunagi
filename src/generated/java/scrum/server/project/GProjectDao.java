









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.fp.*;
import ilarkesto.persistence.*;

public abstract class GProjectDao
            extends ilarkesto.persistence.ADao<Project> {

    public final String getEntityName() {
        return Project.TYPE;
    }

    public final Class getEntityClass() {
        return Project.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        projectsByLabelCache.clear();
        labelsCache = null;
        projectsByCurrentSprintCache.clear();
        currentSprintsCache = null;
        projectsByAdminCache.clear();
        adminsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Project) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Project) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Project>> projectsByLabelCache = new Cache<java.lang.String,Set<Project>>(
            new Cache.Factory<java.lang.String,Set<Project>>() {
                public Set<Project> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<Project> getProjectsByLabel(java.lang.String label) {
        return projectsByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (Project e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<Project> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.isLabel(value);
        }

    }

    // -----------------------------------------------------------
    // - currentSprint
    // -----------------------------------------------------------

    private final Cache<scrum.server.sprint.Sprint,Set<Project>> projectsByCurrentSprintCache = new Cache<scrum.server.sprint.Sprint,Set<Project>>(
            new Cache.Factory<scrum.server.sprint.Sprint,Set<Project>>() {
                public Set<Project> create(scrum.server.sprint.Sprint currentSprint) {
                    return getEntities(new IsCurrentSprint(currentSprint));
                }
            });

    public final Set<Project> getProjectsByCurrentSprint(scrum.server.sprint.Sprint currentSprint) {
        return projectsByCurrentSprintCache.get(currentSprint);
    }
    private Set<scrum.server.sprint.Sprint> currentSprintsCache;

    public final Set<scrum.server.sprint.Sprint> getCurrentSprints() {
        if (currentSprintsCache == null) {
            currentSprintsCache = new HashSet<scrum.server.sprint.Sprint>();
            for (Project e : getEntities()) {
                if (e.isCurrentSprintSet()) currentSprintsCache.add(e.getCurrentSprint());
            }
        }
        return currentSprintsCache;
    }

    private static class IsCurrentSprint implements Predicate<Project> {

        private scrum.server.sprint.Sprint value;

        public IsCurrentSprint(scrum.server.sprint.Sprint value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.isCurrentSprint(value);
        }

    }

    // -----------------------------------------------------------
    // - admins
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<Project>> projectsByAdminCache = new Cache<scrum.server.admin.User,Set<Project>>(
            new Cache.Factory<scrum.server.admin.User,Set<Project>>() {
                public Set<Project> create(scrum.server.admin.User admin) {
                    return getEntities(new ContainsAdmin(admin));
                }
            });

    public final Set<Project> getProjectsByAdmin(scrum.server.admin.User admin) {
        return projectsByAdminCache.get(admin);
    }
    private Set<scrum.server.admin.User> adminsCache;

    public final Set<scrum.server.admin.User> getAdmins() {
        if (adminsCache == null) {
            adminsCache = new HashSet<scrum.server.admin.User>();
            for (Project e : getEntities()) {
                adminsCache.addAll(e.getAdmins());
            }
        }
        return adminsCache;
    }

    private static class ContainsAdmin implements Predicate<Project> {

        private scrum.server.admin.User value;

        public ContainsAdmin(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.containsAdmin(value);
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

    protected scrum.server.sprint.SprintDao sprintDao;

    public void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        this.sprintDao = sprintDao;
    }

}
