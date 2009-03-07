// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GAttributeDao
            extends ilarkesto.persistence.ADao<Attribute> {

    public final String getEntityName() {
        return Attribute.TYPE;
    }

    public final Class getEntityClass() {
        return Attribute.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        attributesByProjectCache.clear();
        projectsCache = null;
        attributesByLabelCache.clear();
        labelsCache = null;
        attributesByDescriptionCache.clear();
        descriptionsCache = null;
        attributesByTestDescriptionCache.clear();
        testDescriptionsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Attribute) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Attribute) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<Attribute>> attributesByProjectCache = new Cache<scrum.server.project.Project,Set<Attribute>>(
            new Cache.Factory<scrum.server.project.Project,Set<Attribute>>() {
                public Set<Attribute> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<Attribute> getAttributesByProject(scrum.server.project.Project project) {
        return attributesByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (Attribute e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<Attribute> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(Attribute e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Attribute>> attributesByLabelCache = new Cache<java.lang.String,Set<Attribute>>(
            new Cache.Factory<java.lang.String,Set<Attribute>>() {
                public Set<Attribute> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<Attribute> getAttributesByLabel(java.lang.String label) {
        return attributesByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (Attribute e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<Attribute> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Attribute e) {
            return e.isLabel(value);
        }

    }

    // -----------------------------------------------------------
    // - description
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Attribute>> attributesByDescriptionCache = new Cache<java.lang.String,Set<Attribute>>(
            new Cache.Factory<java.lang.String,Set<Attribute>>() {
                public Set<Attribute> create(java.lang.String description) {
                    return getEntities(new IsDescription(description));
                }
            });

    public final Set<Attribute> getAttributesByDescription(java.lang.String description) {
        return attributesByDescriptionCache.get(description);
    }
    private Set<java.lang.String> descriptionsCache;

    public final Set<java.lang.String> getDescriptions() {
        if (descriptionsCache == null) {
            descriptionsCache = new HashSet<java.lang.String>();
            for (Attribute e : getEntities()) {
                if (e.isDescriptionSet()) descriptionsCache.add(e.getDescription());
            }
        }
        return descriptionsCache;
    }

    private static class IsDescription implements Predicate<Attribute> {

        private java.lang.String value;

        public IsDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Attribute e) {
            return e.isDescription(value);
        }

    }

    // -----------------------------------------------------------
    // - testDescription
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Attribute>> attributesByTestDescriptionCache = new Cache<java.lang.String,Set<Attribute>>(
            new Cache.Factory<java.lang.String,Set<Attribute>>() {
                public Set<Attribute> create(java.lang.String testDescription) {
                    return getEntities(new IsTestDescription(testDescription));
                }
            });

    public final Set<Attribute> getAttributesByTestDescription(java.lang.String testDescription) {
        return attributesByTestDescriptionCache.get(testDescription);
    }
    private Set<java.lang.String> testDescriptionsCache;

    public final Set<java.lang.String> getTestDescriptions() {
        if (testDescriptionsCache == null) {
            testDescriptionsCache = new HashSet<java.lang.String>();
            for (Attribute e : getEntities()) {
                if (e.isTestDescriptionSet()) testDescriptionsCache.add(e.getTestDescription());
            }
        }
        return testDescriptionsCache;
    }

    private static class IsTestDescription implements Predicate<Attribute> {

        private java.lang.String value;

        public IsTestDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Attribute e) {
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