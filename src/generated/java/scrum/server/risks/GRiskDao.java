// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.risks;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

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
        risksByProjectCache.clear();
        projectsCache = null;
        risksByNumberCache.clear();
        numbersCache = null;
        risksByLabelCache.clear();
        labelsCache = null;
        risksByDescriptionCache.clear();
        descriptionsCache = null;
        risksByProbabilityMitigationCache.clear();
        probabilityMitigationsCache = null;
        risksByImpactMitigationCache.clear();
        impactMitigationsCache = null;
        risksByProbabilityCache.clear();
        probabilitysCache = null;
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
    // - number
    // -----------------------------------------------------------

    private final Cache<Integer,Set<Risk>> risksByNumberCache = new Cache<Integer,Set<Risk>>(
            new Cache.Factory<Integer,Set<Risk>>() {
                public Set<Risk> create(Integer number) {
                    return getEntities(new IsNumber(number));
                }
            });

    public final Set<Risk> getRisksByNumber(int number) {
        return risksByNumberCache.get(number);
    }
    private Set<Integer> numbersCache;

    public final Set<Integer> getNumbers() {
        if (numbersCache == null) {
            numbersCache = new HashSet<Integer>();
            for (Risk e : getEntities()) {
                numbersCache.add(e.getNumber());
            }
        }
        return numbersCache;
    }

    private static class IsNumber implements Predicate<Risk> {

        private int value;

        public IsNumber(int value) {
            this.value = value;
        }

        public boolean test(Risk e) {
            return e.isNumber(value);
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
    // - probabilityMitigation
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Risk>> risksByProbabilityMitigationCache = new Cache<java.lang.String,Set<Risk>>(
            new Cache.Factory<java.lang.String,Set<Risk>>() {
                public Set<Risk> create(java.lang.String probabilityMitigation) {
                    return getEntities(new IsProbabilityMitigation(probabilityMitigation));
                }
            });

    public final Set<Risk> getRisksByProbabilityMitigation(java.lang.String probabilityMitigation) {
        return risksByProbabilityMitigationCache.get(probabilityMitigation);
    }
    private Set<java.lang.String> probabilityMitigationsCache;

    public final Set<java.lang.String> getProbabilityMitigations() {
        if (probabilityMitigationsCache == null) {
            probabilityMitigationsCache = new HashSet<java.lang.String>();
            for (Risk e : getEntities()) {
                if (e.isProbabilityMitigationSet()) probabilityMitigationsCache.add(e.getProbabilityMitigation());
            }
        }
        return probabilityMitigationsCache;
    }

    private static class IsProbabilityMitigation implements Predicate<Risk> {

        private java.lang.String value;

        public IsProbabilityMitigation(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Risk e) {
            return e.isProbabilityMitigation(value);
        }

    }

    // -----------------------------------------------------------
    // - impactMitigation
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Risk>> risksByImpactMitigationCache = new Cache<java.lang.String,Set<Risk>>(
            new Cache.Factory<java.lang.String,Set<Risk>>() {
                public Set<Risk> create(java.lang.String impactMitigation) {
                    return getEntities(new IsImpactMitigation(impactMitigation));
                }
            });

    public final Set<Risk> getRisksByImpactMitigation(java.lang.String impactMitigation) {
        return risksByImpactMitigationCache.get(impactMitigation);
    }
    private Set<java.lang.String> impactMitigationsCache;

    public final Set<java.lang.String> getImpactMitigations() {
        if (impactMitigationsCache == null) {
            impactMitigationsCache = new HashSet<java.lang.String>();
            for (Risk e : getEntities()) {
                if (e.isImpactMitigationSet()) impactMitigationsCache.add(e.getImpactMitigation());
            }
        }
        return impactMitigationsCache;
    }

    private static class IsImpactMitigation implements Predicate<Risk> {

        private java.lang.String value;

        public IsImpactMitigation(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Risk e) {
            return e.isImpactMitigation(value);
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

    scrum.server.project.ProjectDao projectDao;

    public void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

}