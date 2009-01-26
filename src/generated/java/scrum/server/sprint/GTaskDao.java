









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.sprint;

import java.util.*;
import ilarkesto.auth.*;
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
        tasksByRemainingWorkCache.clear();
        remainingWorksCache = null;
        tasksByNoticeCache.clear();
        noticesCache = null;
        tasksByBurnedWorkCache.clear();
        burnedWorksCache = null;
        tasksByLabelCache.clear();
        labelsCache = null;
        tasksByStoryCache.clear();
        storysCache = null;
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
    // - remainingWork
    // -----------------------------------------------------------

    private final Cache<java.lang.Integer,Set<Task>> tasksByRemainingWorkCache = new Cache<java.lang.Integer,Set<Task>>(
            new Cache.Factory<java.lang.Integer,Set<Task>>() {
                public Set<Task> create(java.lang.Integer remainingWork) {
                    return getEntities(new IsRemainingWork(remainingWork));
                }
            });

    public final Set<Task> getTasksByRemainingWork(java.lang.Integer remainingWork) {
        return tasksByRemainingWorkCache.get(remainingWork);
    }
    private Set<java.lang.Integer> remainingWorksCache;

    public final Set<java.lang.Integer> getRemainingWorks() {
        if (remainingWorksCache == null) {
            remainingWorksCache = new HashSet<java.lang.Integer>();
            for (Task e : getEntities()) {
                if (e.isRemainingWorkSet()) remainingWorksCache.add(e.getRemainingWork());
            }
        }
        return remainingWorksCache;
    }

    private static class IsRemainingWork implements Predicate<Task> {

        private java.lang.Integer value;

        public IsRemainingWork(java.lang.Integer value) {
            this.value = value;
        }

        public boolean test(Task e) {
            return e.isRemainingWork(value);
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
    // - story
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Story,Set<Task>> tasksByStoryCache = new Cache<scrum.server.project.Story,Set<Task>>(
            new Cache.Factory<scrum.server.project.Story,Set<Task>>() {
                public Set<Task> create(scrum.server.project.Story story) {
                    return getEntities(new IsStory(story));
                }
            });

    public final Set<Task> getTasksByStory(scrum.server.project.Story story) {
        return tasksByStoryCache.get(story);
    }
    private Set<scrum.server.project.Story> storysCache;

    public final Set<scrum.server.project.Story> getStorys() {
        if (storysCache == null) {
            storysCache = new HashSet<scrum.server.project.Story>();
            for (Task e : getEntities()) {
                if (e.isStorySet()) storysCache.add(e.getStory());
            }
        }
        return storysCache;
    }

    private static class IsStory implements Predicate<Task> {

        private scrum.server.project.Story value;

        public IsStory(scrum.server.project.Story value) {
            this.value = value;
        }

        public boolean test(Task e) {
            return e.isStory(value);
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

    protected scrum.server.project.StoryDao storyDao;

    public void setStoryDao(scrum.server.project.StoryDao storyDao) {
        this.storyDao = storyDao;
    }

}
