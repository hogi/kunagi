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

public abstract class GQualityDao
            extends ilarkesto.persistence.ADao<Quality> {

    public final String getEntityName() {
        return Quality.TYPE;
    }

    public final Class getEntityClass() {
        return Quality.class;
    }

    public Set<Quality> getEntitiesVisibleForUser(final scrum.server.admin.User user) {
        return getEntities(new Predicate<Quality>() {
            public boolean test(Quality e) {
                return Auth.isVisible(e, user);
            }
        });
    }

    // --- clear caches ---
    public void clearCaches() {
        qualitysByProjectCache.clear();
        projectsCache = null;
        qualitysByNumberCache.clear();
        numbersCache = null;
        qualitysByLabelCache.clear();
        labelsCache = null;
        qualitysByDescriptionCache.clear();
        descriptionsCache = null;
        qualitysByTestDescriptionCache.clear();
        testDescriptionsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Quality) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Quality) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<Quality>> qualitysByProjectCache = new Cache<scrum.server.project.Project,Set<Quality>>(
            new Cache.Factory<scrum.server.project.Project,Set<Quality>>() {
                public Set<Quality> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<Quality> getQualitysByProject(scrum.server.project.Project project) {
        return qualitysByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (Quality e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<Quality> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(Quality e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - number
    // -----------------------------------------------------------

    private final Cache<Integer,Set<Quality>> qualitysByNumberCache = new Cache<Integer,Set<Quality>>(
            new Cache.Factory<Integer,Set<Quality>>() {
                public Set<Quality> create(Integer number) {
                    return getEntities(new IsNumber(number));
                }
            });

    public final Set<Quality> getQualitysByNumber(int number) {
        return qualitysByNumberCache.get(number);
    }
    private Set<Integer> numbersCache;

    public final Set<Integer> getNumbers() {
        if (numbersCache == null) {
            numbersCache = new HashSet<Integer>();
            for (Quality e : getEntities()) {
                numbersCache.add(e.getNumber());
            }
        }
        return numbersCache;
    }

    private static class IsNumber implements Predicate<Quality> {

        private int value;

        public IsNumber(int value) {
            this.value = value;
        }

        public boolean test(Quality e) {
            return e.isNumber(value);
        }

    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Quality>> qualitysByLabelCache = new Cache<java.lang.String,Set<Quality>>(
            new Cache.Factory<java.lang.String,Set<Quality>>() {
                public Set<Quality> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<Quality> getQualitysByLabel(java.lang.String label) {
        return qualitysByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (Quality e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<Quality> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Quality e) {
            return e.isLabel(value);
        }

    }

    // -----------------------------------------------------------
    // - description
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Quality>> qualitysByDescriptionCache = new Cache<java.lang.String,Set<Quality>>(
            new Cache.Factory<java.lang.String,Set<Quality>>() {
                public Set<Quality> create(java.lang.String description) {
                    return getEntities(new IsDescription(description));
                }
            });

    public final Set<Quality> getQualitysByDescription(java.lang.String description) {
        return qualitysByDescriptionCache.get(description);
    }
    private Set<java.lang.String> descriptionsCache;

    public final Set<java.lang.String> getDescriptions() {
        if (descriptionsCache == null) {
            descriptionsCache = new HashSet<java.lang.String>();
            for (Quality e : getEntities()) {
                if (e.isDescriptionSet()) descriptionsCache.add(e.getDescription());
            }
        }
        return descriptionsCache;
    }

    private static class IsDescription implements Predicate<Quality> {

        private java.lang.String value;

        public IsDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Quality e) {
            return e.isDescription(value);
        }

    }

    // -----------------------------------------------------------
    // - testDescription
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Quality>> qualitysByTestDescriptionCache = new Cache<java.lang.String,Set<Quality>>(
            new Cache.Factory<java.lang.String,Set<Quality>>() {
                public Set<Quality> create(java.lang.String testDescription) {
                    return getEntities(new IsTestDescription(testDescription));
                }
            });

    public final Set<Quality> getQualitysByTestDescription(java.lang.String testDescription) {
        return qualitysByTestDescriptionCache.get(testDescription);
    }
    private Set<java.lang.String> testDescriptionsCache;

    public final Set<java.lang.String> getTestDescriptions() {
        if (testDescriptionsCache == null) {
            testDescriptionsCache = new HashSet<java.lang.String>();
            for (Quality e : getEntities()) {
                if (e.isTestDescriptionSet()) testDescriptionsCache.add(e.getTestDescription());
            }
        }
        return testDescriptionsCache;
    }

    private static class IsTestDescription implements Predicate<Quality> {

        private java.lang.String value;

        public IsTestDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Quality e) {
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

    scrum.server.project.ProjectDao projectDao;

    public void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

}