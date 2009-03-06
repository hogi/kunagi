// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.risks;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.fp.*;
import ilarkesto.persistence.*;

public abstract class GRiskDao
            extends ilarkesto.persistence.ADao<Risk> {

    public final String getEntityName() {
        return Risk.TYPE;
    }

    public final Class getEntityClass() {
        return Risk.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        risksByProbabilityCache.clear();
        probabilitysCache = null;
        risksByLabelCache.clear();
        labelsCache = null;
        risksByProjectCache.clear();
        projectsCache = null;
        risksByDescriptionCache.clear();
        descriptionsCache = null;
        risksByImpactCache.clear();
        impactsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Risk) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Risk) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - probability
    // -----------------------------------------------------------

    private final Cache<Integer,Set<Risk>> risksByProbabilityCache = new Cache<Integer,Set<Risk>>(
            new Cache.Factory<Integer,Set<Risk>>() {
                public Set<Risk> create(Integer probability) {
                    return getEntities(new IsProbability(probability));
                }
            });

    public final Set<Risk> getRisksByProbability(int probability) {
        return risksByProbabilityCache.get(probability);
    }
    private Set<Integer> probabilitysCache;

    public final Set<Integer> getProbabilitys() {
        if (probabilitysCache == null) {
            probabilitysCache = new HashSet<Integer>();
            for (Risk e : getEntities()) {
                probabilitysCache.add(e.getProbability());
            }
        }
        return probabilitysCache;
    }

    private static class IsProbability implements Predicate<Risk> {

        private int value;

        public IsProbability(int value) {
            this.value = value;
        }

        public boolean test(Risk e) {
            return e.isProbability(value);
        }

    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Risk>> risksByLabelCache = new Cache<java.lang.String,Set<Risk>>(
            new Cache.Factory<java.lang.String,Set<Risk>>() {
                public Set<Risk> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<Risk> getRisksByLabel(java.lang.String label) {
        return risksByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (Risk e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<Risk> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Risk e) {
            return e.isLabel(value);
        }

    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<Risk>> risksByProjectCache = new Cache<scrum.server.project.Project,Set<Risk>>(
            new Cache.Factory<scrum.server.project.Project,Set<Risk>>() {
                public Set<Risk> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<Risk> getRisksByProject(scrum.server.project.Project project) {
        return risksByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (Risk e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<Risk> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(Risk e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - description
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Risk>> risksByDescriptionCache = new Cache<java.lang.String,Set<Risk>>(
            new Cache.Factory<java.lang.String,Set<Risk>>() {
                public Set<Risk> create(java.lang.String description) {
                    return getEntities(new IsDescription(description));
                }
            });

    public final Set<Risk> getRisksByDescription(java.lang.String description) {
        return risksByDescriptionCache.get(description);
    }
    private Set<java.lang.String> descriptionsCache;

    public final Set<java.lang.String> getDescriptions() {
        if (descriptionsCache == null) {
            descriptionsCache = new HashSet<java.lang.String>();
            for (Risk e : getEntities()) {
                if (e.isDescriptionSet()) descriptionsCache.add(e.getDescription());
            }
        }
        return descriptionsCache;
    }

    private static class IsDescription implements Predicate<Risk> {

        private java.lang.String value;

        public IsDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Risk e) {
            return e.isDescription(value);
        }

    }

    // -----------------------------------------------------------
    // - impact
    // -----------------------------------------------------------

    private final Cache<Integer,Set<Risk>> risksByImpactCache = new Cache<Integer,Set<Risk>>(
            new Cache.Factory<Integer,Set<Risk>>() {
                public Set<Risk> create(Integer impact) {
                    return getEntities(new IsImpact(impact));
                }
            });

    public final Set<Risk> getRisksByImpact(int impact) {
        return risksByImpactCache.get(impact);
    }
    private Set<Integer> impactsCache;

    public final Set<Integer> getImpacts() {
        if (impactsCache == null) {
            impactsCache = new HashSet<Integer>();
            for (Risk e : getEntities()) {
                impactsCache.add(e.getImpact());
            }
        }
        return impactsCache;
    }

    private static class IsImpact implements Predicate<Risk> {

        private int value;

        public IsImpact(int value) {
            this.value = value;
        }

        public boolean test(Risk e) {
            return e.isImpact(value);
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