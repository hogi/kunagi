









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.fp.*;
import ilarkesto.persistence.*;

public abstract class GStoryDao
            extends ilarkesto.persistence.ADao<Story> {

    public final String getEntityName() {
        return Story.TYPE;
    }

    public final Class getEntityClass() {
        return Story.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        storysBySprintCache.clear();
        sprintsCache = null;
        storysByClosedCache.clear();
        storysByDescriptionCache.clear();
        descriptionsCache = null;
        storysByEstimatedWorkCache.clear();
        estimatedWorksCache = null;
        storysByProjectCache.clear();
        projectsCache = null;
        storysByTestDescriptionCache.clear();
        testDescriptionsCache = null;
        storysByLabelCache.clear();
        labelsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Story) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Story) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - sprint
    // -----------------------------------------------------------

    private final Cache<scrum.server.sprint.Sprint,Set<Story>> storysBySprintCache = new Cache<scrum.server.sprint.Sprint,Set<Story>>(
            new Cache.Factory<scrum.server.sprint.Sprint,Set<Story>>() {
                public Set<Story> create(scrum.server.sprint.Sprint sprint) {
                    return getEntities(new IsSprint(sprint));
                }
            });

    public final Set<Story> getStorysBySprint(scrum.server.sprint.Sprint sprint) {
        return storysBySprintCache.get(sprint);
    }
    private Set<scrum.server.sprint.Sprint> sprintsCache;

    public final Set<scrum.server.sprint.Sprint> getSprints() {
        if (sprintsCache == null) {
            sprintsCache = new HashSet<scrum.server.sprint.Sprint>();
            for (Story e : getEntities()) {
                if (e.isSprintSet()) sprintsCache.add(e.getSprint());
            }
        }
        return sprintsCache;
    }

    private static class IsSprint implements Predicate<Story> {

        private scrum.server.sprint.Sprint value;

        public IsSprint(scrum.server.sprint.Sprint value) {
            this.value = value;
        }

        public boolean test(Story e) {
            return e.isSprint(value);
        }

    }

    // -----------------------------------------------------------
    // - closed
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<Story>> storysByClosedCache = new Cache<Boolean,Set<Story>>(
            new Cache.Factory<Boolean,Set<Story>>() {
                public Set<Story> create(Boolean closed) {
                    return getEntities(new IsClosed(closed));
                }
            });

    public final Set<Story> getStorysByClosed(boolean closed) {
        return storysByClosedCache.get(closed);
    }

    private static class IsClosed implements Predicate<Story> {

        private boolean value;

        public IsClosed(boolean value) {
            this.value = value;
        }

        public boolean test(Story e) {
            return value == e.isClosed();
        }

    }

    // -----------------------------------------------------------
    // - description
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Story>> storysByDescriptionCache = new Cache<java.lang.String,Set<Story>>(
            new Cache.Factory<java.lang.String,Set<Story>>() {
                public Set<Story> create(java.lang.String description) {
                    return getEntities(new IsDescription(description));
                }
            });

    public final Set<Story> getStorysByDescription(java.lang.String description) {
        return storysByDescriptionCache.get(description);
    }
    private Set<java.lang.String> descriptionsCache;

    public final Set<java.lang.String> getDescriptions() {
        if (descriptionsCache == null) {
            descriptionsCache = new HashSet<java.lang.String>();
            for (Story e : getEntities()) {
                if (e.isDescriptionSet()) descriptionsCache.add(e.getDescription());
            }
        }
        return descriptionsCache;
    }

    private static class IsDescription implements Predicate<Story> {

        private java.lang.String value;

        public IsDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Story e) {
            return e.isDescription(value);
        }

    }

    // -----------------------------------------------------------
    // - estimatedWork
    // -----------------------------------------------------------

    private final Cache<java.lang.Integer,Set<Story>> storysByEstimatedWorkCache = new Cache<java.lang.Integer,Set<Story>>(
            new Cache.Factory<java.lang.Integer,Set<Story>>() {
                public Set<Story> create(java.lang.Integer estimatedWork) {
                    return getEntities(new IsEstimatedWork(estimatedWork));
                }
            });

    public final Set<Story> getStorysByEstimatedWork(java.lang.Integer estimatedWork) {
        return storysByEstimatedWorkCache.get(estimatedWork);
    }
    private Set<java.lang.Integer> estimatedWorksCache;

    public final Set<java.lang.Integer> getEstimatedWorks() {
        if (estimatedWorksCache == null) {
            estimatedWorksCache = new HashSet<java.lang.Integer>();
            for (Story e : getEntities()) {
                if (e.isEstimatedWorkSet()) estimatedWorksCache.add(e.getEstimatedWork());
            }
        }
        return estimatedWorksCache;
    }

    private static class IsEstimatedWork implements Predicate<Story> {

        private java.lang.Integer value;

        public IsEstimatedWork(java.lang.Integer value) {
            this.value = value;
        }

        public boolean test(Story e) {
            return e.isEstimatedWork(value);
        }

    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<Story>> storysByProjectCache = new Cache<scrum.server.project.Project,Set<Story>>(
            new Cache.Factory<scrum.server.project.Project,Set<Story>>() {
                public Set<Story> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<Story> getStorysByProject(scrum.server.project.Project project) {
        return storysByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (Story e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<Story> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(Story e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - testDescription
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Story>> storysByTestDescriptionCache = new Cache<java.lang.String,Set<Story>>(
            new Cache.Factory<java.lang.String,Set<Story>>() {
                public Set<Story> create(java.lang.String testDescription) {
                    return getEntities(new IsTestDescription(testDescription));
                }
            });

    public final Set<Story> getStorysByTestDescription(java.lang.String testDescription) {
        return storysByTestDescriptionCache.get(testDescription);
    }
    private Set<java.lang.String> testDescriptionsCache;

    public final Set<java.lang.String> getTestDescriptions() {
        if (testDescriptionsCache == null) {
            testDescriptionsCache = new HashSet<java.lang.String>();
            for (Story e : getEntities()) {
                if (e.isTestDescriptionSet()) testDescriptionsCache.add(e.getTestDescription());
            }
        }
        return testDescriptionsCache;
    }

    private static class IsTestDescription implements Predicate<Story> {

        private java.lang.String value;

        public IsTestDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Story e) {
            return e.isTestDescription(value);
        }

    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Story>> storysByLabelCache = new Cache<java.lang.String,Set<Story>>(
            new Cache.Factory<java.lang.String,Set<Story>>() {
                public Set<Story> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<Story> getStorysByLabel(java.lang.String label) {
        return storysByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (Story e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<Story> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Story e) {
            return e.isLabel(value);
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

    protected scrum.server.project.ProjectDao projectDao;

    public void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

}
