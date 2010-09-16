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

    public Set<Issue> getEntitiesVisibleForUser(final scrum.server.admin.User user) {
        return getEntities(new Predicate<Issue>() {
            public boolean test(Issue e) {
                return Auth.isVisible(e, user);
            }
        });
    }

    // --- clear caches ---
    public void clearCaches() {
        issuesByProjectCache.clear();
        projectsCache = null;
        issuesByStoryCache.clear();
        storysCache = null;
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
        issuesByStatementCache.clear();
        statementsCache = null;
        issuesByIssuerNameCache.clear();
        issuerNamesCache = null;
        issuesByIssuerEmailCache.clear();
        issuerEmailsCache = null;
        issuesByAcceptDateCache.clear();
        acceptDatesCache = null;
        issuesByUrgentCache.clear();
        issuesBySeverityCache.clear();
        severitysCache = null;
        issuesByOwnerCache.clear();
        ownersCache = null;
        issuesByFixDateCache.clear();
        fixDatesCache = null;
        issuesByCloseDateCache.clear();
        closeDatesCache = null;
        issuesBySuspendedUntilDateCache.clear();
        suspendedUntilDatesCache = null;
        issuesByAffectedReleaseCache.clear();
        affectedReleasesCache = null;
        issuesByFixReleaseCache.clear();
        fixReleasesCache = null;
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
    // - story
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Requirement,Set<Issue>> issuesByStoryCache = new Cache<scrum.server.project.Requirement,Set<Issue>>(
            new Cache.Factory<scrum.server.project.Requirement,Set<Issue>>() {
                public Set<Issue> create(scrum.server.project.Requirement story) {
                    return getEntities(new IsStory(story));
                }
            });

    public final Set<Issue> getIssuesByStory(scrum.server.project.Requirement story) {
        return issuesByStoryCache.get(story);
    }
    private Set<scrum.server.project.Requirement> storysCache;

    public final Set<scrum.server.project.Requirement> getStorys() {
        if (storysCache == null) {
            storysCache = new HashSet<scrum.server.project.Requirement>();
            for (Issue e : getEntities()) {
                if (e.isStorySet()) storysCache.add(e.getStory());
            }
        }
        return storysCache;
    }

    private static class IsStory implements Predicate<Issue> {

        private scrum.server.project.Requirement value;

        public IsStory(scrum.server.project.Requirement value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isStory(value);
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
    // - statement
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Issue>> issuesByStatementCache = new Cache<java.lang.String,Set<Issue>>(
            new Cache.Factory<java.lang.String,Set<Issue>>() {
                public Set<Issue> create(java.lang.String statement) {
                    return getEntities(new IsStatement(statement));
                }
            });

    public final Set<Issue> getIssuesByStatement(java.lang.String statement) {
        return issuesByStatementCache.get(statement);
    }
    private Set<java.lang.String> statementsCache;

    public final Set<java.lang.String> getStatements() {
        if (statementsCache == null) {
            statementsCache = new HashSet<java.lang.String>();
            for (Issue e : getEntities()) {
                if (e.isStatementSet()) statementsCache.add(e.getStatement());
            }
        }
        return statementsCache;
    }

    private static class IsStatement implements Predicate<Issue> {

        private java.lang.String value;

        public IsStatement(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isStatement(value);
        }

    }

    // -----------------------------------------------------------
    // - issuerName
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Issue>> issuesByIssuerNameCache = new Cache<java.lang.String,Set<Issue>>(
            new Cache.Factory<java.lang.String,Set<Issue>>() {
                public Set<Issue> create(java.lang.String issuerName) {
                    return getEntities(new IsIssuerName(issuerName));
                }
            });

    public final Set<Issue> getIssuesByIssuerName(java.lang.String issuerName) {
        return issuesByIssuerNameCache.get(issuerName);
    }
    private Set<java.lang.String> issuerNamesCache;

    public final Set<java.lang.String> getIssuerNames() {
        if (issuerNamesCache == null) {
            issuerNamesCache = new HashSet<java.lang.String>();
            for (Issue e : getEntities()) {
                if (e.isIssuerNameSet()) issuerNamesCache.add(e.getIssuerName());
            }
        }
        return issuerNamesCache;
    }

    private static class IsIssuerName implements Predicate<Issue> {

        private java.lang.String value;

        public IsIssuerName(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isIssuerName(value);
        }

    }

    // -----------------------------------------------------------
    // - issuerEmail
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Issue>> issuesByIssuerEmailCache = new Cache<java.lang.String,Set<Issue>>(
            new Cache.Factory<java.lang.String,Set<Issue>>() {
                public Set<Issue> create(java.lang.String issuerEmail) {
                    return getEntities(new IsIssuerEmail(issuerEmail));
                }
            });

    public final Set<Issue> getIssuesByIssuerEmail(java.lang.String issuerEmail) {
        return issuesByIssuerEmailCache.get(issuerEmail);
    }
    private Set<java.lang.String> issuerEmailsCache;

    public final Set<java.lang.String> getIssuerEmails() {
        if (issuerEmailsCache == null) {
            issuerEmailsCache = new HashSet<java.lang.String>();
            for (Issue e : getEntities()) {
                if (e.isIssuerEmailSet()) issuerEmailsCache.add(e.getIssuerEmail());
            }
        }
        return issuerEmailsCache;
    }

    private static class IsIssuerEmail implements Predicate<Issue> {

        private java.lang.String value;

        public IsIssuerEmail(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isIssuerEmail(value);
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
    // - urgent
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<Issue>> issuesByUrgentCache = new Cache<Boolean,Set<Issue>>(
            new Cache.Factory<Boolean,Set<Issue>>() {
                public Set<Issue> create(Boolean urgent) {
                    return getEntities(new IsUrgent(urgent));
                }
            });

    public final Set<Issue> getIssuesByUrgent(boolean urgent) {
        return issuesByUrgentCache.get(urgent);
    }

    private static class IsUrgent implements Predicate<Issue> {

        private boolean value;

        public IsUrgent(boolean value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return value == e.isUrgent();
        }

    }

    // -----------------------------------------------------------
    // - severity
    // -----------------------------------------------------------

    private final Cache<Integer,Set<Issue>> issuesBySeverityCache = new Cache<Integer,Set<Issue>>(
            new Cache.Factory<Integer,Set<Issue>>() {
                public Set<Issue> create(Integer severity) {
                    return getEntities(new IsSeverity(severity));
                }
            });

    public final Set<Issue> getIssuesBySeverity(int severity) {
        return issuesBySeverityCache.get(severity);
    }
    private Set<Integer> severitysCache;

    public final Set<Integer> getSeveritys() {
        if (severitysCache == null) {
            severitysCache = new HashSet<Integer>();
            for (Issue e : getEntities()) {
                severitysCache.add(e.getSeverity());
            }
        }
        return severitysCache;
    }

    private static class IsSeverity implements Predicate<Issue> {

        private int value;

        public IsSeverity(int value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isSeverity(value);
        }

    }

    // -----------------------------------------------------------
    // - owner
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<Issue>> issuesByOwnerCache = new Cache<scrum.server.admin.User,Set<Issue>>(
            new Cache.Factory<scrum.server.admin.User,Set<Issue>>() {
                public Set<Issue> create(scrum.server.admin.User owner) {
                    return getEntities(new IsOwner(owner));
                }
            });

    public final Set<Issue> getIssuesByOwner(scrum.server.admin.User owner) {
        return issuesByOwnerCache.get(owner);
    }
    private Set<scrum.server.admin.User> ownersCache;

    public final Set<scrum.server.admin.User> getOwners() {
        if (ownersCache == null) {
            ownersCache = new HashSet<scrum.server.admin.User>();
            for (Issue e : getEntities()) {
                if (e.isOwnerSet()) ownersCache.add(e.getOwner());
            }
        }
        return ownersCache;
    }

    private static class IsOwner implements Predicate<Issue> {

        private scrum.server.admin.User value;

        public IsOwner(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isOwner(value);
        }

    }

    // -----------------------------------------------------------
    // - fixDate
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.Date,Set<Issue>> issuesByFixDateCache = new Cache<ilarkesto.base.time.Date,Set<Issue>>(
            new Cache.Factory<ilarkesto.base.time.Date,Set<Issue>>() {
                public Set<Issue> create(ilarkesto.base.time.Date fixDate) {
                    return getEntities(new IsFixDate(fixDate));
                }
            });

    public final Set<Issue> getIssuesByFixDate(ilarkesto.base.time.Date fixDate) {
        return issuesByFixDateCache.get(fixDate);
    }
    private Set<ilarkesto.base.time.Date> fixDatesCache;

    public final Set<ilarkesto.base.time.Date> getFixDates() {
        if (fixDatesCache == null) {
            fixDatesCache = new HashSet<ilarkesto.base.time.Date>();
            for (Issue e : getEntities()) {
                if (e.isFixDateSet()) fixDatesCache.add(e.getFixDate());
            }
        }
        return fixDatesCache;
    }

    private static class IsFixDate implements Predicate<Issue> {

        private ilarkesto.base.time.Date value;

        public IsFixDate(ilarkesto.base.time.Date value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isFixDate(value);
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

    // -----------------------------------------------------------
    // - suspendedUntilDate
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.Date,Set<Issue>> issuesBySuspendedUntilDateCache = new Cache<ilarkesto.base.time.Date,Set<Issue>>(
            new Cache.Factory<ilarkesto.base.time.Date,Set<Issue>>() {
                public Set<Issue> create(ilarkesto.base.time.Date suspendedUntilDate) {
                    return getEntities(new IsSuspendedUntilDate(suspendedUntilDate));
                }
            });

    public final Set<Issue> getIssuesBySuspendedUntilDate(ilarkesto.base.time.Date suspendedUntilDate) {
        return issuesBySuspendedUntilDateCache.get(suspendedUntilDate);
    }
    private Set<ilarkesto.base.time.Date> suspendedUntilDatesCache;

    public final Set<ilarkesto.base.time.Date> getSuspendedUntilDates() {
        if (suspendedUntilDatesCache == null) {
            suspendedUntilDatesCache = new HashSet<ilarkesto.base.time.Date>();
            for (Issue e : getEntities()) {
                if (e.isSuspendedUntilDateSet()) suspendedUntilDatesCache.add(e.getSuspendedUntilDate());
            }
        }
        return suspendedUntilDatesCache;
    }

    private static class IsSuspendedUntilDate implements Predicate<Issue> {

        private ilarkesto.base.time.Date value;

        public IsSuspendedUntilDate(ilarkesto.base.time.Date value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.isSuspendedUntilDate(value);
        }

    }

    // -----------------------------------------------------------
    // - affectedReleases
    // -----------------------------------------------------------

    private final Cache<scrum.server.release.Release,Set<Issue>> issuesByAffectedReleaseCache = new Cache<scrum.server.release.Release,Set<Issue>>(
            new Cache.Factory<scrum.server.release.Release,Set<Issue>>() {
                public Set<Issue> create(scrum.server.release.Release affectedRelease) {
                    return getEntities(new ContainsAffectedRelease(affectedRelease));
                }
            });

    public final Set<Issue> getIssuesByAffectedRelease(scrum.server.release.Release affectedRelease) {
        return issuesByAffectedReleaseCache.get(affectedRelease);
    }
    private Set<scrum.server.release.Release> affectedReleasesCache;

    public final Set<scrum.server.release.Release> getAffectedReleases() {
        if (affectedReleasesCache == null) {
            affectedReleasesCache = new HashSet<scrum.server.release.Release>();
            for (Issue e : getEntities()) {
                affectedReleasesCache.addAll(e.getAffectedReleases());
            }
        }
        return affectedReleasesCache;
    }

    private static class ContainsAffectedRelease implements Predicate<Issue> {

        private scrum.server.release.Release value;

        public ContainsAffectedRelease(scrum.server.release.Release value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.containsAffectedRelease(value);
        }

    }

    // -----------------------------------------------------------
    // - fixReleases
    // -----------------------------------------------------------

    private final Cache<scrum.server.release.Release,Set<Issue>> issuesByFixReleaseCache = new Cache<scrum.server.release.Release,Set<Issue>>(
            new Cache.Factory<scrum.server.release.Release,Set<Issue>>() {
                public Set<Issue> create(scrum.server.release.Release fixRelease) {
                    return getEntities(new ContainsFixRelease(fixRelease));
                }
            });

    public final Set<Issue> getIssuesByFixRelease(scrum.server.release.Release fixRelease) {
        return issuesByFixReleaseCache.get(fixRelease);
    }
    private Set<scrum.server.release.Release> fixReleasesCache;

    public final Set<scrum.server.release.Release> getFixReleases() {
        if (fixReleasesCache == null) {
            fixReleasesCache = new HashSet<scrum.server.release.Release>();
            for (Issue e : getEntities()) {
                fixReleasesCache.addAll(e.getFixReleases());
            }
        }
        return fixReleasesCache;
    }

    private static class ContainsFixRelease implements Predicate<Issue> {

        private scrum.server.release.Release value;

        public ContainsFixRelease(scrum.server.release.Release value) {
            this.value = value;
        }

        public boolean test(Issue e) {
            return e.containsFixRelease(value);
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

    scrum.server.project.RequirementDao requirementDao;

    public void setRequirementDao(scrum.server.project.RequirementDao requirementDao) {
        this.requirementDao = requirementDao;
    }

    scrum.server.release.ReleaseDao releaseDao;

    public void setReleaseDao(scrum.server.release.ReleaseDao releaseDao) {
        this.releaseDao = releaseDao;
    }

}