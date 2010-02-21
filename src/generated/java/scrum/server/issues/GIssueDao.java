// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.DaoGenerator










package scrum.server.issues;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GIssueDao
            extends ilarkesto.persistence.ADao<Issue> {

    public final String getEntityName() {
        return Issue.TYPE;
    }

    public final Class getEntityClass() {
        return Issue.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        issuesByProjectCache.clear();
        projectsCache = null;
        issuesByNumberCache.clear();
        numbersCache = null;
        issuesByTypeCache.clear();
        typesCache = null;
        issuesByDateCache.clear();
        datesCache = null;
        issuesByCreatorCache.clear();
        creatorsCache = null;
        issuesByLabelCache.clear();
        labelsCache = null;
        issuesByDescriptionCache.clear();
        descriptionsCache = null;
        issuesByAcceptDateCache.clear();
        acceptDatesCache = null;
        issuesBySuspendDateCache.clear();
        suspendDatesCache = null;
        issuesByCloseDateCache.clear();
        closeDatesCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Issue) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Issue) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<Issue>> issuesByProjectCache = new Cache<scrum.server.project.Project,Set<Issue>>(
            new Cache.Factory<scrum.server.project.Project,Set<Issue>>() {
                public Set<Issue> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<Issue> getIssuesByProject(scrum.server.project.Project project) {
        return issuesByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (Issue e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<Issue> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - number
    // -----------------------------------------------------------

    private final Cache<Integer,Set<Issue>> issuesByNumberCache = new Cache<Integer,Set<Issue>>(
            new Cache.Factory<Integer,Set<Issue>>() {
                public Set<Issue> create(Integer number) {
                    return getEntities(new IsNumber(number));
                }
            });

    public final Set<Issue> getIssuesByNumber(int number) {
        return issuesByNumberCache.get(number);
    }
    private Set<Integer> numbersCache;

    public final Set<Integer> getNumbers() {
        if (numbersCache == null) {
            numbersCache = new HashSet<Integer>();
            for (Issue e : getEntities()) {
                numbersCache.add(e.getNumber());
            }
        }
        return numbersCache;
    }

    private static class IsNumber implements Predicate<Issue> {

        private int value;

        public IsNumber(int value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isNumber(value);
        }

    }

    // -----------------------------------------------------------
    // - type
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Issue>> issuesByTypeCache = new Cache<java.lang.String,Set<Issue>>(
            new Cache.Factory<java.lang.String,Set<Issue>>() {
                public Set<Issue> create(java.lang.String type) {
                    return getEntities(new IsType(type));
                }
            });

    public final Set<Issue> getIssuesByType(java.lang.String type) {
        return issuesByTypeCache.get(type);
    }
    private Set<java.lang.String> typesCache;

    public final Set<java.lang.String> getTypes() {
        if (typesCache == null) {
            typesCache = new HashSet<java.lang.String>();
            for (Issue e : getEntities()) {
                if (e.isTypeSet()) typesCache.add(e.getType());
            }
        }
        return typesCache;
    }

    private static class IsType implements Predicate<Issue> {

        private java.lang.String value;

        public IsType(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isType(value);
        }

    }

    // -----------------------------------------------------------
    // - date
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.DateAndTime,Set<Issue>> issuesByDateCache = new Cache<ilarkesto.base.time.DateAndTime,Set<Issue>>(
            new Cache.Factory<ilarkesto.base.time.DateAndTime,Set<Issue>>() {
                public Set<Issue> create(ilarkesto.base.time.DateAndTime date) {
                    return getEntities(new IsDate(date));
                }
            });

    public final Set<Issue> getIssuesByDate(ilarkesto.base.time.DateAndTime date) {
        return issuesByDateCache.get(date);
    }
    private Set<ilarkesto.base.time.DateAndTime> datesCache;

    public final Set<ilarkesto.base.time.DateAndTime> getDates() {
        if (datesCache == null) {
            datesCache = new HashSet<ilarkesto.base.time.DateAndTime>();
            for (Issue e : getEntities()) {
                if (e.isDateSet()) datesCache.add(e.getDate());
            }
        }
        return datesCache;
    }

    private static class IsDate implements Predicate<Issue> {

        private ilarkesto.base.time.DateAndTime value;

        public IsDate(ilarkesto.base.time.DateAndTime value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isDate(value);
        }

    }

    // -----------------------------------------------------------
    // - creator
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<Issue>> issuesByCreatorCache = new Cache<scrum.server.admin.User,Set<Issue>>(
            new Cache.Factory<scrum.server.admin.User,Set<Issue>>() {
                public Set<Issue> create(scrum.server.admin.User creator) {
                    return getEntities(new IsCreator(creator));
                }
            });

    public final Set<Issue> getIssuesByCreator(scrum.server.admin.User creator) {
        return issuesByCreatorCache.get(creator);
    }
    private Set<scrum.server.admin.User> creatorsCache;

    public final Set<scrum.server.admin.User> getCreators() {
        if (creatorsCache == null) {
            creatorsCache = new HashSet<scrum.server.admin.User>();
            for (Issue e : getEntities()) {
                if (e.isCreatorSet()) creatorsCache.add(e.getCreator());
            }
        }
        return creatorsCache;
    }

    private static class IsCreator implements Predicate<Issue> {

        private scrum.server.admin.User value;

        public IsCreator(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isCreator(value);
        }

    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Issue>> issuesByLabelCache = new Cache<java.lang.String,Set<Issue>>(
            new Cache.Factory<java.lang.String,Set<Issue>>() {
                public Set<Issue> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<Issue> getIssuesByLabel(java.lang.String label) {
        return issuesByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (Issue e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<Issue> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isLabel(value);
        }

    }

    // -----------------------------------------------------------
    // - description
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Issue>> issuesByDescriptionCache = new Cache<java.lang.String,Set<Issue>>(
            new Cache.Factory<java.lang.String,Set<Issue>>() {
                public Set<Issue> create(java.lang.String description) {
                    return getEntities(new IsDescription(description));
                }
            });

    public final Set<Issue> getIssuesByDescription(java.lang.String description) {
        return issuesByDescriptionCache.get(description);
    }
    private Set<java.lang.String> descriptionsCache;

    public final Set<java.lang.String> getDescriptions() {
        if (descriptionsCache == null) {
            descriptionsCache = new HashSet<java.lang.String>();
            for (Issue e : getEntities()) {
                if (e.isDescriptionSet()) descriptionsCache.add(e.getDescription());
            }
        }
        return descriptionsCache;
    }

    private static class IsDescription implements Predicate<Issue> {

        private java.lang.String value;

        public IsDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isDescription(value);
        }

    }

    // -----------------------------------------------------------
    // - acceptDate
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.Date,Set<Issue>> issuesByAcceptDateCache = new Cache<ilarkesto.base.time.Date,Set<Issue>>(
            new Cache.Factory<ilarkesto.base.time.Date,Set<Issue>>() {
                public Set<Issue> create(ilarkesto.base.time.Date acceptDate) {
                    return getEntities(new IsAcceptDate(acceptDate));
                }
            });

    public final Set<Issue> getIssuesByAcceptDate(ilarkesto.base.time.Date acceptDate) {
        return issuesByAcceptDateCache.get(acceptDate);
    }
    private Set<ilarkesto.base.time.Date> acceptDatesCache;

    public final Set<ilarkesto.base.time.Date> getAcceptDates() {
        if (acceptDatesCache == null) {
            acceptDatesCache = new HashSet<ilarkesto.base.time.Date>();
            for (Issue e : getEntities()) {
                if (e.isAcceptDateSet()) acceptDatesCache.add(e.getAcceptDate());
            }
        }
        return acceptDatesCache;
    }

    private static class IsAcceptDate implements Predicate<Issue> {

        private ilarkesto.base.time.Date value;

        public IsAcceptDate(ilarkesto.base.time.Date value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isAcceptDate(value);
        }

    }

    // -----------------------------------------------------------
    // - suspendDate
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.Date,Set<Issue>> issuesBySuspendDateCache = new Cache<ilarkesto.base.time.Date,Set<Issue>>(
            new Cache.Factory<ilarkesto.base.time.Date,Set<Issue>>() {
                public Set<Issue> create(ilarkesto.base.time.Date suspendDate) {
                    return getEntities(new IsSuspendDate(suspendDate));
                }
            });

    public final Set<Issue> getIssuesBySuspendDate(ilarkesto.base.time.Date suspendDate) {
        return issuesBySuspendDateCache.get(suspendDate);
    }
    private Set<ilarkesto.base.time.Date> suspendDatesCache;

    public final Set<ilarkesto.base.time.Date> getSuspendDates() {
        if (suspendDatesCache == null) {
            suspendDatesCache = new HashSet<ilarkesto.base.time.Date>();
            for (Issue e : getEntities()) {
                if (e.isSuspendDateSet()) suspendDatesCache.add(e.getSuspendDate());
            }
        }
        return suspendDatesCache;
    }

    private static class IsSuspendDate implements Predicate<Issue> {

        private ilarkesto.base.time.Date value;

        public IsSuspendDate(ilarkesto.base.time.Date value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isSuspendDate(value);
        }

    }

    // -----------------------------------------------------------
    // - closeDate
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.Date,Set<Issue>> issuesByCloseDateCache = new Cache<ilarkesto.base.time.Date,Set<Issue>>(
            new Cache.Factory<ilarkesto.base.time.Date,Set<Issue>>() {
                public Set<Issue> create(ilarkesto.base.time.Date closeDate) {
                    return getEntities(new IsCloseDate(closeDate));
                }
            });

    public final Set<Issue> getIssuesByCloseDate(ilarkesto.base.time.Date closeDate) {
        return issuesByCloseDateCache.get(closeDate);
    }
    private Set<ilarkesto.base.time.Date> closeDatesCache;

    public final Set<ilarkesto.base.time.Date> getCloseDates() {
        if (closeDatesCache == null) {
            closeDatesCache = new HashSet<ilarkesto.base.time.Date>();
            for (Issue e : getEntities()) {
                if (e.isCloseDateSet()) closeDatesCache.add(e.getCloseDate());
            }
        }
        return closeDatesCache;
    }

    private static class IsCloseDate implements Predicate<Issue> {

        private ilarkesto.base.time.Date value;

        public IsCloseDate(ilarkesto.base.time.Date value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isCloseDate(value);
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