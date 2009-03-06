// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.sprint;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.fp.*;
import ilarkesto.persistence.*;

public abstract class GTaskDao
            extends ilarkesto.persistence.ADao<Task> {

    public final String getEntityName() {
        return Task.TYPE;
    }

    public final Class getEntityClass() {
        return Task.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        tasksByOwnerCache.clear();
        ownersCache = null;
        tasksByRemainingWorkCache.clear();
        remainingWorksCache = null;
        tasksByRequirementCache.clear();
        requirementsCache = null;
        tasksByLabelCache.clear();
        labelsCache = null;
        tasksByNoticeCache.clear();
        noticesCache = null;
        tasksByBurnedWorkCache.clear();
        burnedWorksCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Task) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Task) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - owner
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<Task>> tasksByOwnerCache = new Cache<scrum.server.admin.User,Set<Task>>(
            new Cache.Factory<scrum.server.admin.User,Set<Task>>() {
                public Set<Task> create(scrum.server.admin.User owner) {
                    return getEntities(new IsOwner(owner));
                }
            });

    public final Set<Task> getTasksByOwner(scrum.server.admin.User owner) {
        return tasksByOwnerCache.get(owner);
    }
    private Set<scrum.server.admin.User> ownersCache;

    public final Set<scrum.server.admin.User> getOwners() {
        if (ownersCache == null) {
            ownersCache = new HashSet<scrum.server.admin.User>();
            for (Task e : getEntities()) {
                if (e.isOwnerSet()) ownersCache.add(e.getOwner());
            }
        }
        return ownersCache;
    }

    private static class IsOwner implements Predicate<Task> {

        private scrum.server.admin.User value;

        public IsOwner(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(Task e) {
            return e.isOwner(value);
        }

    }

    // -----------------------------------------------------------
    // - remainingWork
    // -----------------------------------------------------------

    private final Cache<Integer,Set<Task>> tasksByRemainingWorkCache = new Cache<Integer,Set<Task>>(
            new Cache.Factory<Integer,Set<Task>>() {
                public Set<Task> create(Integer remainingWork) {
                    return getEntities(new IsRemainingWork(remainingWork));
                }
            });

    public final Set<Task> getTasksByRemainingWork(int remainingWork) {
        return tasksByRemainingWorkCache.get(remainingWork);
    }
    private Set<Integer> remainingWorksCache;

    public final Set<Integer> getRemainingWorks() {
        if (remainingWorksCache == null) {
            remainingWorksCache = new HashSet<Integer>();
            for (Task e : getEntities()) {
                remainingWorksCache.add(e.getRemainingWork());
            }
        }
        return remainingWorksCache;
    }

    private static class IsRemainingWork implements Predicate<Task> {

        private int value;

        public IsRemainingWork(int value) {
            this.value = value;
        }

        public boolean test(Task e) {
            return e.isRemainingWork(value);
        }

    }

    // -----------------------------------------------------------
    // - requirement
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Requirement,Set<Task>> tasksByRequirementCache = new Cache<scrum.server.project.Requirement,Set<Task>>(
            new Cache.Factory<scrum.server.project.Requirement,Set<Task>>() {
                public Set<Task> create(scrum.server.project.Requirement requirement) {
                    return getEntities(new IsRequirement(requirement));
                }
            });

    public final Set<Task> getTasksByRequirement(scrum.server.project.Requirement requirement) {
        return tasksByRequirementCache.get(requirement);
    }
    private Set<scrum.server.project.Requirement> requirementsCache;

    public final Set<scrum.server.project.Requirement> getRequirements() {
        if (requirementsCache == null) {
            requirementsCache = new HashSet<scrum.server.project.Requirement>();
            for (Task e : getEntities()) {
                if (e.isRequirementSet()) requirementsCache.add(e.getRequirement());
            }
        }
        return requirementsCache;
    }

    private static class IsRequirement implements Predicate<Task> {

        private scrum.server.project.Requirement value;

        public IsRequirement(scrum.server.project.Requirement value) {
            this.value = value;
        }

        public boolean test(Task e) {
            return e.isRequirement(value);
        }

    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Task>> tasksByLabelCache = new Cache<java.lang.String,Set<Task>>(
            new Cache.Factory<java.lang.String,Set<Task>>() {
                public Set<Task> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<Task> getTasksByLabel(java.lang.String label) {
        return tasksByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (Task e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<Task> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Task e) {
            return e.isLabel(value);
        }

    }

    // -----------------------------------------------------------
    // - notice
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Task>> tasksByNoticeCache = new Cache<java.lang.String,Set<Task>>(
            new Cache.Factory<java.lang.String,Set<Task>>() {
                public Set<Task> create(java.lang.String notice) {
                    return getEntities(new IsNotice(notice));
                }
            });

    public final Set<Task> getTasksByNotice(java.lang.String notice) {
        return tasksByNoticeCache.get(notice);
    }
    private Set<java.lang.String> noticesCache;

    public final Set<java.lang.String> getNotices() {
        if (noticesCache == null) {
            noticesCache = new HashSet<java.lang.String>();
            for (Task e : getEntities()) {
                if (e.isNoticeSet()) noticesCache.add(e.getNotice());
            }
        }
        return noticesCache;
    }

    private static class IsNotice implements Predicate<Task> {

        private java.lang.String value;

        public IsNotice(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Task e) {
            return e.isNotice(value);
        }

    }

    // -----------------------------------------------------------
    // - burnedWork
    // -----------------------------------------------------------

    private final Cache<Integer,Set<Task>> tasksByBurnedWorkCache = new Cache<Integer,Set<Task>>(
            new Cache.Factory<Integer,Set<Task>>() {
                public Set<Task> create(Integer burnedWork) {
                    return getEntities(new IsBurnedWork(burnedWork));
                }
            });

    public final Set<Task> getTasksByBurnedWork(int burnedWork) {
        return tasksByBurnedWorkCache.get(burnedWork);
    }
    private Set<Integer> burnedWorksCache;

    public final Set<Integer> getBurnedWorks() {
        if (burnedWorksCache == null) {
            burnedWorksCache = new HashSet<Integer>();
            for (Task e : getEntities()) {
                burnedWorksCache.add(e.getBurnedWork());
            }
        }
        return burnedWorksCache;
    }

    private static class IsBurnedWork implements Predicate<Task> {

        private int value;

        public IsBurnedWork(int value) {
            this.value = value;
        }

        public boolean test(Task e) {
            return e.isBurnedWork(value);
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

    protected scrum.server.project.RequirementDao requirementDao;

    public void setRequirementDao(scrum.server.project.RequirementDao requirementDao) {
        this.requirementDao = requirementDao;
    }

}