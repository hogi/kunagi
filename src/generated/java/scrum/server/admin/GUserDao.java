// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.DaoGenerator










package scrum.server.admin;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GUserDao
            extends ilarkesto.auth.AUserDao<User> {

    public final String getEntityName() {
        return User.TYPE;
    }

    public final Class getEntityClass() {
        return User.class;
    }

    public Set<User> getEntitiesVisibleForUser(final scrum.server.admin.User user) {
        return getEntities(new Predicate<User>() {
            public boolean test(User e) {
                return Auth.isVisible(e, user);
            }
        });
    }

    // --- clear caches ---
    public void clearCaches() {
        namesCache = null;
        usersByAdminCache.clear();
        usersByEmailVerifiedCache.clear();
        emailsCache = null;
        usersByCurrentProjectCache.clear();
        currentProjectsCache = null;
        usersByColorCache.clear();
        colorsCache = null;
        usersByLastLoginDateAndTimeCache.clear();
        lastLoginDateAndTimesCache = null;
        usersByRegistrationDateAndTimeCache.clear();
        registrationDateAndTimesCache = null;
        usersByDisabledCache.clear();
        usersByHideUserGuideBlogCache.clear();
        usersByHideUserGuideCalendarCache.clear();
        usersByHideUserGuideFilesCache.clear();
        usersByHideUserGuideForumCache.clear();
        usersByHideUserGuideImpedimentsCache.clear();
        usersByHideUserGuideIssuesCache.clear();
        usersByHideUserGuideJournalCache.clear();
        usersByHideUserGuideNextSprintCache.clear();
        usersByHideUserGuideProductBacklogCache.clear();
        usersByHideUserGuideCourtroomCache.clear();
        usersByHideUserGuideQualityBacklogCache.clear();
        usersByHideUserGuideReleasesCache.clear();
        usersByHideUserGuideRisksCache.clear();
        usersByHideUserGuideSprintBacklogCache.clear();
        usersByHideUserGuideWhiteboardCache.clear();
        loginTokensCache = null;
        openIdsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof User) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof User) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - name
    // -----------------------------------------------------------

    public final User getUserByName(java.lang.String name) {
        return getEntity(new IsName(name));
    }
    private Set<java.lang.String> namesCache;

    public final Set<java.lang.String> getNames() {
        if (namesCache == null) {
            namesCache = new HashSet<java.lang.String>();
            for (User e : getEntities()) {
                if (e.isNameSet()) namesCache.add(e.getName());
            }
        }
        return namesCache;
    }

    private static class IsName implements Predicate<User> {

        private java.lang.String value;

        public IsName(java.lang.String value) {
            this.value = value;
        }

        public boolean test(User e) {
            return e.isName(value);
        }

    }

    // -----------------------------------------------------------
    // - admin
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByAdminCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean admin) {
                    return getEntities(new IsAdmin(admin));
                }
            });

    public final Set<User> getUsersByAdmin(boolean admin) {
        return usersByAdminCache.get(admin);
    }

    private static class IsAdmin implements Predicate<User> {

        private boolean value;

        public IsAdmin(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isAdmin();
        }

    }

    // -----------------------------------------------------------
    // - emailVerified
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByEmailVerifiedCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean emailVerified) {
                    return getEntities(new IsEmailVerified(emailVerified));
                }
            });

    public final Set<User> getUsersByEmailVerified(boolean emailVerified) {
        return usersByEmailVerifiedCache.get(emailVerified);
    }

    private static class IsEmailVerified implements Predicate<User> {

        private boolean value;

        public IsEmailVerified(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isEmailVerified();
        }

    }

    // -----------------------------------------------------------
    // - email
    // -----------------------------------------------------------

    public final User getUserByEmail(java.lang.String email) {
        return getEntity(new IsEmail(email));
    }
    private Set<java.lang.String> emailsCache;

    public final Set<java.lang.String> getEmails() {
        if (emailsCache == null) {
            emailsCache = new HashSet<java.lang.String>();
            for (User e : getEntities()) {
                if (e.isEmailSet()) emailsCache.add(e.getEmail());
            }
        }
        return emailsCache;
    }

    private static class IsEmail implements Predicate<User> {

        private java.lang.String value;

        public IsEmail(java.lang.String value) {
            this.value = value;
        }

        public boolean test(User e) {
            return e.isEmail(value);
        }

    }

    // -----------------------------------------------------------
    // - currentProject
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<User>> usersByCurrentProjectCache = new Cache<scrum.server.project.Project,Set<User>>(
            new Cache.Factory<scrum.server.project.Project,Set<User>>() {
                public Set<User> create(scrum.server.project.Project currentProject) {
                    return getEntities(new IsCurrentProject(currentProject));
                }
            });

    public final Set<User> getUsersByCurrentProject(scrum.server.project.Project currentProject) {
        return usersByCurrentProjectCache.get(currentProject);
    }
    private Set<scrum.server.project.Project> currentProjectsCache;

    public final Set<scrum.server.project.Project> getCurrentProjects() {
        if (currentProjectsCache == null) {
            currentProjectsCache = new HashSet<scrum.server.project.Project>();
            for (User e : getEntities()) {
                if (e.isCurrentProjectSet()) currentProjectsCache.add(e.getCurrentProject());
            }
        }
        return currentProjectsCache;
    }

    private static class IsCurrentProject implements Predicate<User> {

        private scrum.server.project.Project value;

        public IsCurrentProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(User e) {
            return e.isCurrentProject(value);
        }

    }

    // -----------------------------------------------------------
    // - color
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<User>> usersByColorCache = new Cache<java.lang.String,Set<User>>(
            new Cache.Factory<java.lang.String,Set<User>>() {
                public Set<User> create(java.lang.String color) {
                    return getEntities(new IsColor(color));
                }
            });

    public final Set<User> getUsersByColor(java.lang.String color) {
        return usersByColorCache.get(color);
    }
    private Set<java.lang.String> colorsCache;

    public final Set<java.lang.String> getColors() {
        if (colorsCache == null) {
            colorsCache = new HashSet<java.lang.String>();
            for (User e : getEntities()) {
                if (e.isColorSet()) colorsCache.add(e.getColor());
            }
        }
        return colorsCache;
    }

    private static class IsColor implements Predicate<User> {

        private java.lang.String value;

        public IsColor(java.lang.String value) {
            this.value = value;
        }

        public boolean test(User e) {
            return e.isColor(value);
        }

    }

    // -----------------------------------------------------------
    // - lastLoginDateAndTime
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.DateAndTime,Set<User>> usersByLastLoginDateAndTimeCache = new Cache<ilarkesto.base.time.DateAndTime,Set<User>>(
            new Cache.Factory<ilarkesto.base.time.DateAndTime,Set<User>>() {
                public Set<User> create(ilarkesto.base.time.DateAndTime lastLoginDateAndTime) {
                    return getEntities(new IsLastLoginDateAndTime(lastLoginDateAndTime));
                }
            });

    public final Set<User> getUsersByLastLoginDateAndTime(ilarkesto.base.time.DateAndTime lastLoginDateAndTime) {
        return usersByLastLoginDateAndTimeCache.get(lastLoginDateAndTime);
    }
    private Set<ilarkesto.base.time.DateAndTime> lastLoginDateAndTimesCache;

    public final Set<ilarkesto.base.time.DateAndTime> getLastLoginDateAndTimes() {
        if (lastLoginDateAndTimesCache == null) {
            lastLoginDateAndTimesCache = new HashSet<ilarkesto.base.time.DateAndTime>();
            for (User e : getEntities()) {
                if (e.isLastLoginDateAndTimeSet()) lastLoginDateAndTimesCache.add(e.getLastLoginDateAndTime());
            }
        }
        return lastLoginDateAndTimesCache;
    }

    private static class IsLastLoginDateAndTime implements Predicate<User> {

        private ilarkesto.base.time.DateAndTime value;

        public IsLastLoginDateAndTime(ilarkesto.base.time.DateAndTime value) {
            this.value = value;
        }

        public boolean test(User e) {
            return e.isLastLoginDateAndTime(value);
        }

    }

    // -----------------------------------------------------------
    // - registrationDateAndTime
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.DateAndTime,Set<User>> usersByRegistrationDateAndTimeCache = new Cache<ilarkesto.base.time.DateAndTime,Set<User>>(
            new Cache.Factory<ilarkesto.base.time.DateAndTime,Set<User>>() {
                public Set<User> create(ilarkesto.base.time.DateAndTime registrationDateAndTime) {
                    return getEntities(new IsRegistrationDateAndTime(registrationDateAndTime));
                }
            });

    public final Set<User> getUsersByRegistrationDateAndTime(ilarkesto.base.time.DateAndTime registrationDateAndTime) {
        return usersByRegistrationDateAndTimeCache.get(registrationDateAndTime);
    }
    private Set<ilarkesto.base.time.DateAndTime> registrationDateAndTimesCache;

    public final Set<ilarkesto.base.time.DateAndTime> getRegistrationDateAndTimes() {
        if (registrationDateAndTimesCache == null) {
            registrationDateAndTimesCache = new HashSet<ilarkesto.base.time.DateAndTime>();
            for (User e : getEntities()) {
                if (e.isRegistrationDateAndTimeSet()) registrationDateAndTimesCache.add(e.getRegistrationDateAndTime());
            }
        }
        return registrationDateAndTimesCache;
    }

    private static class IsRegistrationDateAndTime implements Predicate<User> {

        private ilarkesto.base.time.DateAndTime value;

        public IsRegistrationDateAndTime(ilarkesto.base.time.DateAndTime value) {
            this.value = value;
        }

        public boolean test(User e) {
            return e.isRegistrationDateAndTime(value);
        }

    }

    // -----------------------------------------------------------
    // - disabled
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByDisabledCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean disabled) {
                    return getEntities(new IsDisabled(disabled));
                }
            });

    public final Set<User> getUsersByDisabled(boolean disabled) {
        return usersByDisabledCache.get(disabled);
    }

    private static class IsDisabled implements Predicate<User> {

        private boolean value;

        public IsDisabled(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isDisabled();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideBlog
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideBlogCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideBlog) {
                    return getEntities(new IsHideUserGuideBlog(hideUserGuideBlog));
                }
            });

    public final Set<User> getUsersByHideUserGuideBlog(boolean hideUserGuideBlog) {
        return usersByHideUserGuideBlogCache.get(hideUserGuideBlog);
    }

    private static class IsHideUserGuideBlog implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideBlog(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideBlog();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideCalendar
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideCalendarCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideCalendar) {
                    return getEntities(new IsHideUserGuideCalendar(hideUserGuideCalendar));
                }
            });

    public final Set<User> getUsersByHideUserGuideCalendar(boolean hideUserGuideCalendar) {
        return usersByHideUserGuideCalendarCache.get(hideUserGuideCalendar);
    }

    private static class IsHideUserGuideCalendar implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideCalendar(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideCalendar();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideFiles
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideFilesCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideFiles) {
                    return getEntities(new IsHideUserGuideFiles(hideUserGuideFiles));
                }
            });

    public final Set<User> getUsersByHideUserGuideFiles(boolean hideUserGuideFiles) {
        return usersByHideUserGuideFilesCache.get(hideUserGuideFiles);
    }

    private static class IsHideUserGuideFiles implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideFiles(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideFiles();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideForum
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideForumCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideForum) {
                    return getEntities(new IsHideUserGuideForum(hideUserGuideForum));
                }
            });

    public final Set<User> getUsersByHideUserGuideForum(boolean hideUserGuideForum) {
        return usersByHideUserGuideForumCache.get(hideUserGuideForum);
    }

    private static class IsHideUserGuideForum implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideForum(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideForum();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideImpediments
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideImpedimentsCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideImpediments) {
                    return getEntities(new IsHideUserGuideImpediments(hideUserGuideImpediments));
                }
            });

    public final Set<User> getUsersByHideUserGuideImpediments(boolean hideUserGuideImpediments) {
        return usersByHideUserGuideImpedimentsCache.get(hideUserGuideImpediments);
    }

    private static class IsHideUserGuideImpediments implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideImpediments(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideImpediments();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideIssues
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideIssuesCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideIssues) {
                    return getEntities(new IsHideUserGuideIssues(hideUserGuideIssues));
                }
            });

    public final Set<User> getUsersByHideUserGuideIssues(boolean hideUserGuideIssues) {
        return usersByHideUserGuideIssuesCache.get(hideUserGuideIssues);
    }

    private static class IsHideUserGuideIssues implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideIssues(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideIssues();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideJournal
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideJournalCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideJournal) {
                    return getEntities(new IsHideUserGuideJournal(hideUserGuideJournal));
                }
            });

    public final Set<User> getUsersByHideUserGuideJournal(boolean hideUserGuideJournal) {
        return usersByHideUserGuideJournalCache.get(hideUserGuideJournal);
    }

    private static class IsHideUserGuideJournal implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideJournal(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideJournal();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideNextSprint
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideNextSprintCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideNextSprint) {
                    return getEntities(new IsHideUserGuideNextSprint(hideUserGuideNextSprint));
                }
            });

    public final Set<User> getUsersByHideUserGuideNextSprint(boolean hideUserGuideNextSprint) {
        return usersByHideUserGuideNextSprintCache.get(hideUserGuideNextSprint);
    }

    private static class IsHideUserGuideNextSprint implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideNextSprint(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideNextSprint();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideProductBacklog
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideProductBacklogCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideProductBacklog) {
                    return getEntities(new IsHideUserGuideProductBacklog(hideUserGuideProductBacklog));
                }
            });

    public final Set<User> getUsersByHideUserGuideProductBacklog(boolean hideUserGuideProductBacklog) {
        return usersByHideUserGuideProductBacklogCache.get(hideUserGuideProductBacklog);
    }

    private static class IsHideUserGuideProductBacklog implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideProductBacklog(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideProductBacklog();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideCourtroom
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideCourtroomCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideCourtroom) {
                    return getEntities(new IsHideUserGuideCourtroom(hideUserGuideCourtroom));
                }
            });

    public final Set<User> getUsersByHideUserGuideCourtroom(boolean hideUserGuideCourtroom) {
        return usersByHideUserGuideCourtroomCache.get(hideUserGuideCourtroom);
    }

    private static class IsHideUserGuideCourtroom implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideCourtroom(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideCourtroom();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideQualityBacklog
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideQualityBacklogCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideQualityBacklog) {
                    return getEntities(new IsHideUserGuideQualityBacklog(hideUserGuideQualityBacklog));
                }
            });

    public final Set<User> getUsersByHideUserGuideQualityBacklog(boolean hideUserGuideQualityBacklog) {
        return usersByHideUserGuideQualityBacklogCache.get(hideUserGuideQualityBacklog);
    }

    private static class IsHideUserGuideQualityBacklog implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideQualityBacklog(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideQualityBacklog();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideReleases
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideReleasesCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideReleases) {
                    return getEntities(new IsHideUserGuideReleases(hideUserGuideReleases));
                }
            });

    public final Set<User> getUsersByHideUserGuideReleases(boolean hideUserGuideReleases) {
        return usersByHideUserGuideReleasesCache.get(hideUserGuideReleases);
    }

    private static class IsHideUserGuideReleases implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideReleases(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideReleases();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideRisks
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideRisksCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideRisks) {
                    return getEntities(new IsHideUserGuideRisks(hideUserGuideRisks));
                }
            });

    public final Set<User> getUsersByHideUserGuideRisks(boolean hideUserGuideRisks) {
        return usersByHideUserGuideRisksCache.get(hideUserGuideRisks);
    }

    private static class IsHideUserGuideRisks implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideRisks(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideRisks();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideSprintBacklog
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideSprintBacklogCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideSprintBacklog) {
                    return getEntities(new IsHideUserGuideSprintBacklog(hideUserGuideSprintBacklog));
                }
            });

    public final Set<User> getUsersByHideUserGuideSprintBacklog(boolean hideUserGuideSprintBacklog) {
        return usersByHideUserGuideSprintBacklogCache.get(hideUserGuideSprintBacklog);
    }

    private static class IsHideUserGuideSprintBacklog implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideSprintBacklog(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideSprintBacklog();
        }

    }

    // -----------------------------------------------------------
    // - hideUserGuideWhiteboard
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<User>> usersByHideUserGuideWhiteboardCache = new Cache<Boolean,Set<User>>(
            new Cache.Factory<Boolean,Set<User>>() {
                public Set<User> create(Boolean hideUserGuideWhiteboard) {
                    return getEntities(new IsHideUserGuideWhiteboard(hideUserGuideWhiteboard));
                }
            });

    public final Set<User> getUsersByHideUserGuideWhiteboard(boolean hideUserGuideWhiteboard) {
        return usersByHideUserGuideWhiteboardCache.get(hideUserGuideWhiteboard);
    }

    private static class IsHideUserGuideWhiteboard implements Predicate<User> {

        private boolean value;

        public IsHideUserGuideWhiteboard(boolean value) {
            this.value = value;
        }

        public boolean test(User e) {
            return value == e.isHideUserGuideWhiteboard();
        }

    }

    // -----------------------------------------------------------
    // - loginToken
    // -----------------------------------------------------------

    public final User getUserByLoginToken(java.lang.String loginToken) {
        return getEntity(new IsLoginToken(loginToken));
    }
    private Set<java.lang.String> loginTokensCache;

    public final Set<java.lang.String> getLoginTokens() {
        if (loginTokensCache == null) {
            loginTokensCache = new HashSet<java.lang.String>();
            for (User e : getEntities()) {
                if (e.isLoginTokenSet()) loginTokensCache.add(e.getLoginToken());
            }
        }
        return loginTokensCache;
    }

    private static class IsLoginToken implements Predicate<User> {

        private java.lang.String value;

        public IsLoginToken(java.lang.String value) {
            this.value = value;
        }

        public boolean test(User e) {
            return e.isLoginToken(value);
        }

    }

    // -----------------------------------------------------------
    // - openId
    // -----------------------------------------------------------

    public final User getUserByOpenId(java.lang.String openId) {
        return getEntity(new IsOpenId(openId));
    }
    private Set<java.lang.String> openIdsCache;

    public final Set<java.lang.String> getOpenIds() {
        if (openIdsCache == null) {
            openIdsCache = new HashSet<java.lang.String>();
            for (User e : getEntities()) {
                if (e.isOpenIdSet()) openIdsCache.add(e.getOpenId());
            }
        }
        return openIdsCache;
    }

    private static class IsOpenId implements Predicate<User> {

        private java.lang.String value;

        public IsOpenId(java.lang.String value) {
            this.value = value;
        }

        public boolean test(User e) {
            return e.isOpenId(value);
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