// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.estimation;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GRequirementEstimationVoteDao
            extends ilarkesto.persistence.ADao<RequirementEstimationVote> {

    public final String getEntityName() {
        return RequirementEstimationVote.TYPE;
    }

    public final Class getEntityClass() {
        return RequirementEstimationVote.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        requirementEstimationVotesByRequirementCache.clear();
        requirementsCache = null;
        requirementEstimationVotesByUserCache.clear();
        usersCache = null;
        requirementEstimationVotesByEstimatedWorkCache.clear();
        estimatedWorksCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof RequirementEstimationVote) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof RequirementEstimationVote) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - requirement
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Requirement,Set<RequirementEstimationVote>> requirementEstimationVotesByRequirementCache = new Cache<scrum.server.project.Requirement,Set<RequirementEstimationVote>>(
            new Cache.Factory<scrum.server.project.Requirement,Set<RequirementEstimationVote>>() {
                public Set<RequirementEstimationVote> create(scrum.server.project.Requirement requirement) {
                    return getEntities(new IsRequirement(requirement));
                }
            });

    public final Set<RequirementEstimationVote> getRequirementEstimationVotesByRequirement(scrum.server.project.Requirement requirement) {
        return requirementEstimationVotesByRequirementCache.get(requirement);
    }
    private Set<scrum.server.project.Requirement> requirementsCache;

    public final Set<scrum.server.project.Requirement> getRequirements() {
        if (requirementsCache == null) {
            requirementsCache = new HashSet<scrum.server.project.Requirement>();
            for (RequirementEstimationVote e : getEntities()) {
                if (e.isRequirementSet()) requirementsCache.add(e.getRequirement());
            }
        }
        return requirementsCache;
    }

    private static class IsRequirement implements Predicate<RequirementEstimationVote> {

        private scrum.server.project.Requirement value;

        public IsRequirement(scrum.server.project.Requirement value) {
            this.value = value;
        }

        public boolean test(RequirementEstimationVote e) {
            return e.isRequirement(value);
        }

    }

    // -----------------------------------------------------------
    // - user
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<RequirementEstimationVote>> requirementEstimationVotesByUserCache = new Cache<scrum.server.admin.User,Set<RequirementEstimationVote>>(
            new Cache.Factory<scrum.server.admin.User,Set<RequirementEstimationVote>>() {
                public Set<RequirementEstimationVote> create(scrum.server.admin.User user) {
                    return getEntities(new IsUser(user));
                }
            });

    public final Set<RequirementEstimationVote> getRequirementEstimationVotesByUser(scrum.server.admin.User user) {
        return requirementEstimationVotesByUserCache.get(user);
    }
    private Set<scrum.server.admin.User> usersCache;

    public final Set<scrum.server.admin.User> getUsers() {
        if (usersCache == null) {
            usersCache = new HashSet<scrum.server.admin.User>();
            for (RequirementEstimationVote e : getEntities()) {
                if (e.isUserSet()) usersCache.add(e.getUser());
            }
        }
        return usersCache;
    }

    private static class IsUser implements Predicate<RequirementEstimationVote> {

        private scrum.server.admin.User value;

        public IsUser(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(RequirementEstimationVote e) {
            return e.isUser(value);
        }

    }

    // -----------------------------------------------------------
    // - estimatedWork
    // -----------------------------------------------------------

    private final Cache<java.lang.Integer,Set<RequirementEstimationVote>> requirementEstimationVotesByEstimatedWorkCache = new Cache<java.lang.Integer,Set<RequirementEstimationVote>>(
            new Cache.Factory<java.lang.Integer,Set<RequirementEstimationVote>>() {
                public Set<RequirementEstimationVote> create(java.lang.Integer estimatedWork) {
                    return getEntities(new IsEstimatedWork(estimatedWork));
                }
            });

    public final Set<RequirementEstimationVote> getRequirementEstimationVotesByEstimatedWork(java.lang.Integer estimatedWork) {
        return requirementEstimationVotesByEstimatedWorkCache.get(estimatedWork);
    }
    private Set<java.lang.Integer> estimatedWorksCache;

    public final Set<java.lang.Integer> getEstimatedWorks() {
        if (estimatedWorksCache == null) {
            estimatedWorksCache = new HashSet<java.lang.Integer>();
            for (RequirementEstimationVote e : getEntities()) {
                if (e.isEstimatedWorkSet()) estimatedWorksCache.add(e.getEstimatedWork());
            }
        }
        return estimatedWorksCache;
    }

    private static class IsEstimatedWork implements Predicate<RequirementEstimationVote> {

        private java.lang.Integer value;

        public IsEstimatedWork(java.lang.Integer value) {
            this.value = value;
        }

        public boolean test(RequirementEstimationVote e) {
            return e.isEstimatedWork(value);
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

    scrum.server.project.RequirementDao requirementDao;

    public void setRequirementDao(scrum.server.project.RequirementDao requirementDao) {
        this.requirementDao = requirementDao;
    }

}