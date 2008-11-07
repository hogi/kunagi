









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.fp.*;
import ilarkesto.persistence.*;

public abstract class GBacklogItemDao
            extends ilarkesto.persistence.ADao<BacklogItem> {

    public final String getEntityName() {
        return BacklogItem.TYPE;
    }

    public final Class getEntityClass() {
        return BacklogItem.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        backlogItemsByLabelCache.clear();
        labelsCache = null;
        backlogItemsByEffortCache.clear();
        effortsCache = null;
        backlogItemsByDescriptionCache.clear();
        descriptionsCache = null;
        backlogItemsByProjectCache.clear();
        projectsCache = null;
        backlogItemsByDoneCache.clear();
        backlogItemsByTestDescriptionCache.clear();
        testDescriptionsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof BacklogItem) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof BacklogItem) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<BacklogItem>> backlogItemsByLabelCache = new Cache<java.lang.String,Set<BacklogItem>>(
            new Cache.Factory<java.lang.String,Set<BacklogItem>>() {
                public Set<BacklogItem> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<BacklogItem> getBacklogItemsByLabel(java.lang.String label) {
        return backlogItemsByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (BacklogItem e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<BacklogItem> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(BacklogItem e) {
            return e.isLabel(value);
        }

    }

    // -----------------------------------------------------------
    // - effort
    // -----------------------------------------------------------

    private final Cache<java.lang.Integer,Set<BacklogItem>> backlogItemsByEffortCache = new Cache<java.lang.Integer,Set<BacklogItem>>(
            new Cache.Factory<java.lang.Integer,Set<BacklogItem>>() {
                public Set<BacklogItem> create(java.lang.Integer effort) {
                    return getEntities(new IsEffort(effort));
                }
            });

    public final Set<BacklogItem> getBacklogItemsByEffort(java.lang.Integer effort) {
        return backlogItemsByEffortCache.get(effort);
    }
    private Set<java.lang.Integer> effortsCache;

    public final Set<java.lang.Integer> getEfforts() {
        if (effortsCache == null) {
            effortsCache = new HashSet<java.lang.Integer>();
            for (BacklogItem e : getEntities()) {
                if (e.isEffortSet()) effortsCache.add(e.getEffort());
            }
        }
        return effortsCache;
    }

    private static class IsEffort implements Predicate<BacklogItem> {

        private java.lang.Integer value;

        public IsEffort(java.lang.Integer value) {
            this.value = value;
        }

        public boolean test(BacklogItem e) {
            return e.isEffort(value);
        }

    }

    // -----------------------------------------------------------
    // - description
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<BacklogItem>> backlogItemsByDescriptionCache = new Cache<java.lang.String,Set<BacklogItem>>(
            new Cache.Factory<java.lang.String,Set<BacklogItem>>() {
                public Set<BacklogItem> create(java.lang.String description) {
                    return getEntities(new IsDescription(description));
                }
            });

    public final Set<BacklogItem> getBacklogItemsByDescription(java.lang.String description) {
        return backlogItemsByDescriptionCache.get(description);
    }
    private Set<java.lang.String> descriptionsCache;

    public final Set<java.lang.String> getDescriptions() {
        if (descriptionsCache == null) {
            descriptionsCache = new HashSet<java.lang.String>();
            for (BacklogItem e : getEntities()) {
                if (e.isDescriptionSet()) descriptionsCache.add(e.getDescription());
            }
        }
        return descriptionsCache;
    }

    private static class IsDescription implements Predicate<BacklogItem> {

        private java.lang.String value;

        public IsDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(BacklogItem e) {
            return e.isDescription(value);
        }

    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<BacklogItem>> backlogItemsByProjectCache = new Cache<scrum.server.project.Project,Set<BacklogItem>>(
            new Cache.Factory<scrum.server.project.Project,Set<BacklogItem>>() {
                public Set<BacklogItem> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<BacklogItem> getBacklogItemsByProject(scrum.server.project.Project project) {
        return backlogItemsByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (BacklogItem e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<BacklogItem> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(BacklogItem e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - done
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<BacklogItem>> backlogItemsByDoneCache = new Cache<Boolean,Set<BacklogItem>>(
            new Cache.Factory<Boolean,Set<BacklogItem>>() {
                public Set<BacklogItem> create(Boolean done) {
                    return getEntities(new IsDone(done));
                }
            });

    public final Set<BacklogItem> getBacklogItemsByDone(boolean done) {
        return backlogItemsByDoneCache.get(done);
    }

    private static class IsDone implements Predicate<BacklogItem> {

        private boolean value;

        public IsDone(boolean value) {
            this.value = value;
        }

        public boolean test(BacklogItem e) {
            return value == e.isDone();
        }

    }

    // -----------------------------------------------------------
    // - testDescription
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<BacklogItem>> backlogItemsByTestDescriptionCache = new Cache<java.lang.String,Set<BacklogItem>>(
            new Cache.Factory<java.lang.String,Set<BacklogItem>>() {
                public Set<BacklogItem> create(java.lang.String testDescription) {
                    return getEntities(new IsTestDescription(testDescription));
                }
            });

    public final Set<BacklogItem> getBacklogItemsByTestDescription(java.lang.String testDescription) {
        return backlogItemsByTestDescriptionCache.get(testDescription);
    }
    private Set<java.lang.String> testDescriptionsCache;

    public final Set<java.lang.String> getTestDescriptions() {
        if (testDescriptionsCache == null) {
            testDescriptionsCache = new HashSet<java.lang.String>();
            for (BacklogItem e : getEntities()) {
                if (e.isTestDescriptionSet()) testDescriptionsCache.add(e.getTestDescription());
            }
        }
        return testDescriptionsCache;
    }

    private static class IsTestDescription implements Predicate<BacklogItem> {

        private java.lang.String value;

        public IsTestDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(BacklogItem e) {
            return e.isTestDescription(value);
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
