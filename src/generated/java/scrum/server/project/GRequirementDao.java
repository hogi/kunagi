// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.DaoGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GRequirementDao
            extends ilarkesto.persistence.ADao<Requirement> {

    public final String getEntityName() {
        return Requirement.TYPE;
    }

    public final Class getEntityClass() {
        return Requirement.class;
    }

    public Set<Requirement> getEntitiesVisibleForUser(final scrum.server.admin.User user) {
        return getEntities(new Predicate<Requirement>() {
            public boolean test(Requirement e) {
                return Auth.isVisible(e, user);
            }
        });
    }

    // --- clear caches ---
    public void clearCaches() {
        requirementsByProjectCache.clear();
        projectsCache = null;
        requirementsBySprintCache.clear();
        sprintsCache = null;
        requirementsByNumberCache.clear();
        numbersCache = null;
        requirementsByQualityCache.clear();
        qualitysCache = null;
        requirementsByLabelCache.clear();
        labelsCache = null;
        requirementsByDescriptionCache.clear();
        descriptionsCache = null;
        requirementsByTestDescriptionCache.clear();
        testDescriptionsCache = null;
        requirementsByEstimatedWorkCache.clear();
        estimatedWorksCache = null;
        requirementsByClosedCache.clear();
        requirementsByDirtyCache.clear();
        requirementsByWorkEstimationVotingActiveCache.clear();
        requirementsByWorkEstimationVotingShowoffCache.clear();
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
    // - number
    // -----------------------------------------------------------

    private final Cache<Integer,Set<Requirement>> requirementsByNumberCache = new Cache<Integer,Set<Requirement>>(
            new Cache.Factory<Integer,Set<Requirement>>() {
                public Set<Requirement> create(Integer number) {
                    return getEntities(new IsNumber(number));
                }
            });

    public final Set<Requirement> getRequirementsByNumber(int number) {
        return requirementsByNumberCache.get(number);
    }
    private Set<Integer> numbersCache;

    public final Set<Integer> getNumbers() {
        if (numbersCache == null) {
            numbersCache = new HashSet<Integer>();
            for (Requirement e : getEntities()) {
                numbersCache.add(e.getNumber());
            }
        }
        return numbersCache;
    }

    private static class IsNumber implements Predicate<Requirement> {

        private int value;

        public IsNumber(int value) {
            this.value = value;
        }

        public boolean test(Requirement e) {
            return e.isNumber(value);
        }

    }

    // -----------------------------------------------------------
    // - qualitys
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Quality,Set<Requirement>> requirementsByQualityCache = new Cache<scrum.server.project.Quality,Set<Requirement>>(
            new Cache.Factory<scrum.server.project.Quality,Set<Requirement>>() {
                public Set<Requirement> create(scrum.server.project.Quality quality) {
                    return getEntities(new ContainsQuality(quality));
                }
            });

    public final Set<Requirement> getRequirementsByQuality(scrum.server.project.Quality quality) {
        return requirementsByQualityCache.get(quality);
    }
    private Set<scrum.server.project.Quality> qualitysCache;

    public final Set<scrum.server.project.Quality> getQualitys() {
        if (qualitysCache == null) {
            qualitysCache = new HashSet<scrum.server.project.Quality>();
            for (Requirement e : getEntities()) {
                qualitysCache.addAll(e.getQualitys());
            }
        }
        return qualitysCache;
    }

    private static class ContainsQuality implements Predicate<Requirement> {

        private scrum.server.project.Quality value;

        public ContainsQuality(scrum.server.project.Quality value) {
            this.value = value;
        }

        public boolean test(Requirement e) {
            return e.containsQuality(value);
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
    // - estimatedWork
    // -----------------------------------------------------------

    private final Cache<java.lang.Float,Set<Requirement>> requirementsByEstimatedWorkCache = new Cache<java.lang.Float,Set<Requirement>>(
            new Cache.Factory<java.lang.Float,Set<Requirement>>() {
                public Set<Requirement> create(java.lang.Float estimatedWork) {
                    return getEntities(new IsEstimatedWork(estimatedWork));
                }
            });

    public final Set<Requirement> getRequirementsByEstimatedWork(java.lang.Float estimatedWork) {
        return requirementsByEstimatedWorkCache.get(estimatedWork);
    }
    private Set<java.lang.Float> estimatedWorksCache;

    public final Set<java.lang.Float> getEstimatedWorks() {
        if (estimatedWorksCache == null) {
            estimatedWorksCache = new HashSet<java.lang.Float>();
            for (Requirement e : getEntities()) {
                if (e.isEstimatedWorkSet()) estimatedWorksCache.add(e.getEstimatedWork());
            }
        }
        return estimatedWorksCache;
    }

    private static class IsEstimatedWork implements Predicate<Requirement> {

        private java.lang.Float value;

        public IsEstimatedWork(java.lang.Float value) {
            this.value = value;
        }

        public boolean test(Requirement e) {
            return e.isEstimatedWork(value);
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
    // - dirty
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<Requirement>> requirementsByDirtyCache = new Cache<Boolean,Set<Requirement>>(
            new Cache.Factory<Boolean,Set<Requirement>>() {
                public Set<Requirement> create(Boolean dirty) {
                    return getEntities(new IsDirty(dirty));
                }
            });

    public final Set<Requirement> getRequirementsByDirty(boolean dirty) {
        return requirementsByDirtyCache.get(dirty);
    }

    private static class IsDirty implements Predicate<Requirement> {

        private boolean value;

        public IsDirty(boolean value) {
            this.value = value;
        }

        public boolean test(Requirement e) {
            return value == e.isDirty();
        }

    }

    // -----------------------------------------------------------
    // - workEstimationVotingActive
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<Requirement>> requirementsByWorkEstimationVotingActiveCache = new Cache<Boolean,Set<Requirement>>(
            new Cache.Factory<Boolean,Set<Requirement>>() {
                public Set<Requirement> create(Boolean workEstimationVotingActive) {
                    return getEntities(new IsWorkEstimationVotingActive(workEstimationVotingActive));
                }
            });

    public final Set<Requirement> getRequirementsByWorkEstimationVotingActive(boolean workEstimationVotingActive) {
        return requirementsByWorkEstimationVotingActiveCache.get(workEstimationVotingActive);
    }

    private static class IsWorkEstimationVotingActive implements Predicate<Requirement> {

        private boolean value;

        public IsWorkEstimationVotingActive(boolean value) {
            this.value = value;
        }

        public boolean test(Requirement e) {
            return value == e.isWorkEstimationVotingActive();
        }

    }

    // -----------------------------------------------------------
    // - workEstimationVotingShowoff
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<Requirement>> requirementsByWorkEstimationVotingShowoffCache = new Cache<Boolean,Set<Requirement>>(
            new Cache.Factory<Boolean,Set<Requirement>>() {
                public Set<Requirement> create(Boolean workEstimationVotingShowoff) {
                    return getEntities(new IsWorkEstimationVotingShowoff(workEstimationVotingShowoff));
                }
            });

    public final Set<Requirement> getRequirementsByWorkEstimationVotingShowoff(boolean workEstimationVotingShowoff) {
        return requirementsByWorkEstimationVotingShowoffCache.get(workEstimationVotingShowoff);
    }

    private static class IsWorkEstimationVotingShowoff implements Predicate<Requirement> {

        private boolean value;

        public IsWorkEstimationVotingShowoff(boolean value) {
            this.value = value;
        }

        public boolean test(Requirement e) {
            return value == e.isWorkEstimationVotingShowoff();
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

    scrum.server.sprint.SprintDao sprintDao;

    public void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        this.sprintDao = sprintDao;
    }

    scrum.server.project.QualityDao qualityDao;

    public void setQualityDao(scrum.server.project.QualityDao qualityDao) {
        this.qualityDao = qualityDao;
    }

}