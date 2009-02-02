









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.fp.*;
import ilarkesto.persistence.*;

public abstract class GRequirementDao
            extends ilarkesto.persistence.ADao<Requirement> {

    public final String getEntityName() {
        return Requirement.TYPE;
    }

    public final Class getEntityClass() {
        return Requirement.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        requirementsByDescriptionCache.clear();
        descriptionsCache = null;
        requirementsByLabelCache.clear();
        labelsCache = null;
        requirementsBySprintCache.clear();
        sprintsCache = null;
        requirementsByTestDescriptionCache.clear();
        testDescriptionsCache = null;
        requirementsByClosedCache.clear();
        requirementsByEstimatedWorkCache.clear();
        estimatedWorksCache = null;
        requirementsByProjectCache.clear();
        projectsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Requirement) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Requirement) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - description
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Requirement>> requirementsByDescriptionCache = new Cache<java.lang.String,Set<Requirement>>(
            new Cache.Factory<java.lang.String,Set<Requirement>>() {
                public Set<Requirement> create(java.lang.String description) {
                    return getEntities(new IsDescription(description));
                }
            });

    public final Set<Requirement> getRequirementsByDescription(java.lang.String description) {
        return requirementsByDescriptionCache.get(description);
    }
    private Set<java.lang.String> descriptionsCache;

    public final Set<java.lang.String> getDescriptions() {
        if (descriptionsCache == null) {
            descriptionsCache = new HashSet<java.lang.String>();
            for (Requirement e : getEntities()) {
                if (e.isDescriptionSet()) descriptionsCache.add(e.getDescription());
            }
        }
        return descriptionsCache;
    }

    private static class IsDescription implements Predicate<Requirement> {

        private java.lang.String value;

        public IsDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Requirement e) {
            return e.isDescription(value);
        }

    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Requirement>> requirementsByLabelCache = new Cache<java.lang.String,Set<Requirement>>(
            new Cache.Factory<java.lang.String,Set<Requirement>>() {
                public Set<Requirement> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<Requirement> getRequirementsByLabel(java.lang.String label) {
        return requirementsByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (Requirement e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<Requirement> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Requirement e) {
            return e.isLabel(value);
        }

    }

    // -----------------------------------------------------------
    // - sprint
    // -----------------------------------------------------------

    private final Cache<scrum.server.sprint.Sprint,Set<Requirement>> requirementsBySprintCache = new Cache<scrum.server.sprint.Sprint,Set<Requirement>>(
            new Cache.Factory<scrum.server.sprint.Sprint,Set<Requirement>>() {
                public Set<Requirement> create(scrum.server.sprint.Sprint sprint) {
                    return getEntities(new IsSprint(sprint));
                }
            });

    public final Set<Requirement> getRequirementsBySprint(scrum.server.sprint.Sprint sprint) {
        return requirementsBySprintCache.get(sprint);
    }
    private Set<scrum.server.sprint.Sprint> sprintsCache;

    public final Set<scrum.server.sprint.Sprint> getSprints() {
        if (sprintsCache == null) {
            sprintsCache = new HashSet<scrum.server.sprint.Sprint>();
            for (Requirement e : getEntities()) {
                if (e.isSprintSet()) sprintsCache.add(e.getSprint());
            }
        }
        return sprintsCache;
    }

    private static class IsSprint implements Predicate<Requirement> {

        private scrum.server.sprint.Sprint value;

        public IsSprint(scrum.server.sprint.Sprint value) {
            this.value = value;
        }

        public boolean test(Requirement e) {
            return e.isSprint(value);
        }

    }

    // -----------------------------------------------------------
    // - testDescription
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Requirement>> requirementsByTestDescriptionCache = new Cache<java.lang.String,Set<Requirement>>(
            new Cache.Factory<java.lang.String,Set<Requirement>>() {
                public Set<Requirement> create(java.lang.String testDescription) {
                    return getEntities(new IsTestDescription(testDescription));
                }
            });

    public final Set<Requirement> getRequirementsByTestDescription(java.lang.String testDescription) {
        return requirementsByTestDescriptionCache.get(testDescription);
    }
    private Set<java.lang.String> testDescriptionsCache;

    public final Set<java.lang.String> getTestDescriptions() {
        if (testDescriptionsCache == null) {
            testDescriptionsCache = new HashSet<java.lang.String>();
            for (Requirement e : getEntities()) {
                if (e.isTestDescriptionSet()) testDescriptionsCache.add(e.getTestDescription());
            }
        }
        return testDescriptionsCache;
    }

    private static class IsTestDescription implements Predicate<Requirement> {

        private java.lang.String value;

        public IsTestDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Requirement e) {
            return e.isTestDescription(value);
        }

    }

    // -----------------------------------------------------------
    // - closed
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<Requirement>> requirementsByClosedCache = new Cache<Boolean,Set<Requirement>>(
            new Cache.Factory<Boolean,Set<Requirement>>() {
                public Set<Requirement> create(Boolean closed) {
                    return getEntities(new IsClosed(closed));
                }
            });

    public final Set<Requirement> getRequirementsByClosed(boolean closed) {
        return requirementsByClosedCache.get(closed);
    }

    private static class IsClosed implements Predicate<Requirement> {

        private boolean value;

        public IsClosed(boolean value) {
            this.value = value;
        }

        public boolean test(Requirement e) {
            return value == e.isClosed();
        }

    }

    // -----------------------------------------------------------
    // - estimatedWork
    // -----------------------------------------------------------

    private final Cache<java.lang.Integer,Set<Requirement>> requirementsByEstimatedWorkCache = new Cache<java.lang.Integer,Set<Requirement>>(
            new Cache.Factory<java.lang.Integer,Set<Requirement>>() {
                public Set<Requirement> create(java.lang.Integer estimatedWork) {
                    return getEntities(new IsEstimatedWork(estimatedWork));
                }
            });

    public final Set<Requirement> getRequirementsByEstimatedWork(java.lang.Integer estimatedWork) {
        return requirementsByEstimatedWorkCache.get(estimatedWork);
    }
    private Set<java.lang.Integer> estimatedWorksCache;

    public final Set<java.lang.Integer> getEstimatedWorks() {
        if (estimatedWorksCache == null) {
            estimatedWorksCache = new HashSet<java.lang.Integer>();
            for (Requirement e : getEntities()) {
                if (e.isEstimatedWorkSet()) estimatedWorksCache.add(e.getEstimatedWork());
            }
        }
        return estimatedWorksCache;
    }

    private static class IsEstimatedWork implements Predicate<Requirement> {

        private java.lang.Integer value;

        public IsEstimatedWork(java.lang.Integer value) {
            this.value = value;
        }

        public boolean test(Requirement e) {
            return e.isEstimatedWork(value);
        }

    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<Requirement>> requirementsByProjectCache = new Cache<scrum.server.project.Project,Set<Requirement>>(
            new Cache.Factory<scrum.server.project.Project,Set<Requirement>>() {
                public Set<Requirement> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<Requirement> getRequirementsByProject(scrum.server.project.Project project) {
        return requirementsByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (Requirement e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<Requirement> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(Requirement e) {
            return e.isProject(value);
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
