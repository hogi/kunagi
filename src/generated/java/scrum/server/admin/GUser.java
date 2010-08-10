// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.EntityGenerator










package scrum.server.admin;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GUser
            extends AUser
            implements ilarkesto.auth.ViewProtected<scrum.server.admin.User>, ilarkesto.auth.EditProtected<scrum.server.admin.User>, ilarkesto.search.Searchable, java.lang.Comparable<User> {

    // --- AEntity ---

    public final UserDao getDao() {
        return userDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("name", this.name);
        properties.put("admin", this.admin);
        properties.put("emailVerified", this.emailVerified);
        properties.put("email", this.email);
        properties.put("currentProjectId", this.currentProjectId);
        properties.put("color", this.color);
        properties.put("lastLoginDateAndTime", this.lastLoginDateAndTime == null ? null : this.lastLoginDateAndTime.toString());
        properties.put("registrationDateAndTime", this.registrationDateAndTime == null ? null : this.registrationDateAndTime.toString());
        properties.put("disabled", this.disabled);
        properties.put("hideUserGuideBlog", this.hideUserGuideBlog);
        properties.put("hideUserGuideCalendar", this.hideUserGuideCalendar);
        properties.put("hideUserGuideFiles", this.hideUserGuideFiles);
        properties.put("hideUserGuideForum", this.hideUserGuideForum);
        properties.put("hideUserGuideImpediments", this.hideUserGuideImpediments);
        properties.put("hideUserGuideIssues", this.hideUserGuideIssues);
        properties.put("hideUserGuideJournal", this.hideUserGuideJournal);
        properties.put("hideUserGuideNextSprint", this.hideUserGuideNextSprint);
        properties.put("hideUserGuideProductBacklog", this.hideUserGuideProductBacklog);
        properties.put("hideUserGuideCourtroom", this.hideUserGuideCourtroom);
        properties.put("hideUserGuideQualityBacklog", this.hideUserGuideQualityBacklog);
        properties.put("hideUserGuideReleases", this.hideUserGuideReleases);
        properties.put("hideUserGuideRisks", this.hideUserGuideRisks);
        properties.put("hideUserGuideSprintBacklog", this.hideUserGuideSprintBacklog);
        properties.put("hideUserGuideWhiteboard", this.hideUserGuideWhiteboard);
    }

    public int compareTo(User other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final ilarkesto.core.logging.Log LOG = ilarkesto.core.logging.Log.get(GUser.class);

    public static final String TYPE = "user";


    // -----------------------------------------------------------
    // - Searchable
    // -----------------------------------------------------------

    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getName(), key)) return true;
        if (matchesKey(getEmail(), key)) return true;
        return false;
    }

    // -----------------------------------------------------------
    // - name
    // -----------------------------------------------------------

    private java.lang.String name;

    public final java.lang.String getName() {
        return name;
    }

    public final void setName(java.lang.String name) {
        name = prepareName(name);
        if (isName(name)) return;
        if (name != null && getDao().getUserByName(name) != null) throw new ilarkesto.persistence.UniqueFieldConstraintException(this, "name", name);
        this.name = name;
        fireModified("name="+name);
    }

    protected java.lang.String prepareName(java.lang.String name) {
        name = Str.removeUnreadableChars(name);
        return name;
    }

    public final boolean isNameSet() {
        return this.name != null;
    }

    public final boolean isName(java.lang.String name) {
        if (this.name == null && name == null) return true;
        return this.name != null && this.name.equals(name);
    }

    protected final void updateName(Object value) {
        setName((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - admin
    // -----------------------------------------------------------

    private boolean admin;

    public final boolean isAdmin() {
        return admin;
    }

    public final void setAdmin(boolean admin) {
        admin = prepareAdmin(admin);
        if (isAdmin(admin)) return;
        this.admin = admin;
        fireModified("admin="+admin);
    }

    protected boolean prepareAdmin(boolean admin) {
        return admin;
    }

    public final boolean isAdmin(boolean admin) {
        return this.admin == admin;
    }

    protected final void updateAdmin(Object value) {
        setAdmin((Boolean)value);
    }

    // -----------------------------------------------------------
    // - emailVerified
    // -----------------------------------------------------------

    private boolean emailVerified;

    public final boolean isEmailVerified() {
        return emailVerified;
    }

    public final void setEmailVerified(boolean emailVerified) {
        emailVerified = prepareEmailVerified(emailVerified);
        if (isEmailVerified(emailVerified)) return;
        this.emailVerified = emailVerified;
        fireModified("emailVerified="+emailVerified);
    }

    protected boolean prepareEmailVerified(boolean emailVerified) {
        return emailVerified;
    }

    public final boolean isEmailVerified(boolean emailVerified) {
        return this.emailVerified == emailVerified;
    }

    protected final void updateEmailVerified(Object value) {
        setEmailVerified((Boolean)value);
    }

    // -----------------------------------------------------------
    // - email
    // -----------------------------------------------------------

    private java.lang.String email;

    public final java.lang.String getEmail() {
        return email;
    }

    public final void setEmail(java.lang.String email) {
        email = prepareEmail(email);
        if (isEmail(email)) return;
        if (email != null && getDao().getUserByEmail(email) != null) throw new ilarkesto.persistence.UniqueFieldConstraintException(this, "email", email);
        this.email = email;
        fireModified("email="+email);
    }

    protected java.lang.String prepareEmail(java.lang.String email) {
        email = Str.removeUnreadableChars(email);
        return email;
    }

    public final boolean isEmailSet() {
        return this.email != null;
    }

    public final boolean isEmail(java.lang.String email) {
        if (this.email == null && email == null) return true;
        return this.email != null && this.email.equals(email);
    }

    protected final void updateEmail(Object value) {
        setEmail((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - currentProject
    // -----------------------------------------------------------

    private String currentProjectId;
    private transient scrum.server.project.Project currentProjectCache;

    private void updateCurrentProjectCache() {
        currentProjectCache = this.currentProjectId == null ? null : (scrum.server.project.Project)projectDao.getById(this.currentProjectId);
    }

    public final String getCurrentProjectId() {
        return this.currentProjectId;
    }

    public final scrum.server.project.Project getCurrentProject() {
        if (currentProjectCache == null) updateCurrentProjectCache();
        return currentProjectCache;
    }

    public final void setCurrentProject(scrum.server.project.Project currentProject) {
        currentProject = prepareCurrentProject(currentProject);
        if (isCurrentProject(currentProject)) return;
        this.currentProjectId = currentProject == null ? null : currentProject.getId();
        currentProjectCache = currentProject;
        fireModified("currentProject="+currentProject);
    }

    protected scrum.server.project.Project prepareCurrentProject(scrum.server.project.Project currentProject) {
        return currentProject;
    }

    protected void repairDeadCurrentProjectReference(String entityId) {
        if (this.currentProjectId == null || entityId.equals(this.currentProjectId)) {
            setCurrentProject(null);
        }
    }

    public final boolean isCurrentProjectSet() {
        return this.currentProjectId != null;
    }

    public final boolean isCurrentProject(scrum.server.project.Project currentProject) {
        if (this.currentProjectId == null && currentProject == null) return true;
        return currentProject != null && currentProject.getId().equals(this.currentProjectId);
    }

    protected final void updateCurrentProject(Object value) {
        setCurrentProject(value == null ? null : (scrum.server.project.Project)projectDao.getById((String)value));
    }

    // -----------------------------------------------------------
    // - color
    // -----------------------------------------------------------

    private java.lang.String color;

    public final java.lang.String getColor() {
        return color;
    }

    public final void setColor(java.lang.String color) {
        color = prepareColor(color);
        if (isColor(color)) return;
        this.color = color;
        fireModified("color="+color);
    }

    protected java.lang.String prepareColor(java.lang.String color) {
        color = Str.removeUnreadableChars(color);
        return color;
    }

    public final boolean isColorSet() {
        return this.color != null;
    }

    public final boolean isColor(java.lang.String color) {
        if (this.color == null && color == null) return true;
        return this.color != null && this.color.equals(color);
    }

    protected final void updateColor(Object value) {
        setColor((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - lastLoginDateAndTime
    // -----------------------------------------------------------

    private ilarkesto.base.time.DateAndTime lastLoginDateAndTime;

    public final ilarkesto.base.time.DateAndTime getLastLoginDateAndTime() {
        return lastLoginDateAndTime;
    }

    public final void setLastLoginDateAndTime(ilarkesto.base.time.DateAndTime lastLoginDateAndTime) {
        lastLoginDateAndTime = prepareLastLoginDateAndTime(lastLoginDateAndTime);
        if (isLastLoginDateAndTime(lastLoginDateAndTime)) return;
        this.lastLoginDateAndTime = lastLoginDateAndTime;
        fireModified("lastLoginDateAndTime="+lastLoginDateAndTime);
    }

    protected ilarkesto.base.time.DateAndTime prepareLastLoginDateAndTime(ilarkesto.base.time.DateAndTime lastLoginDateAndTime) {
        return lastLoginDateAndTime;
    }

    public final boolean isLastLoginDateAndTimeSet() {
        return this.lastLoginDateAndTime != null;
    }

    public final boolean isLastLoginDateAndTime(ilarkesto.base.time.DateAndTime lastLoginDateAndTime) {
        if (this.lastLoginDateAndTime == null && lastLoginDateAndTime == null) return true;
        return this.lastLoginDateAndTime != null && this.lastLoginDateAndTime.equals(lastLoginDateAndTime);
    }

    protected final void updateLastLoginDateAndTime(Object value) {
        value = value == null ? null : new ilarkesto.base.time.DateAndTime((String)value);
        setLastLoginDateAndTime((ilarkesto.base.time.DateAndTime)value);
    }

    // -----------------------------------------------------------
    // - registrationDateAndTime
    // -----------------------------------------------------------

    private ilarkesto.base.time.DateAndTime registrationDateAndTime;

    public final ilarkesto.base.time.DateAndTime getRegistrationDateAndTime() {
        return registrationDateAndTime;
    }

    public final void setRegistrationDateAndTime(ilarkesto.base.time.DateAndTime registrationDateAndTime) {
        registrationDateAndTime = prepareRegistrationDateAndTime(registrationDateAndTime);
        if (isRegistrationDateAndTime(registrationDateAndTime)) return;
        this.registrationDateAndTime = registrationDateAndTime;
        fireModified("registrationDateAndTime="+registrationDateAndTime);
    }

    protected ilarkesto.base.time.DateAndTime prepareRegistrationDateAndTime(ilarkesto.base.time.DateAndTime registrationDateAndTime) {
        return registrationDateAndTime;
    }

    public final boolean isRegistrationDateAndTimeSet() {
        return this.registrationDateAndTime != null;
    }

    public final boolean isRegistrationDateAndTime(ilarkesto.base.time.DateAndTime registrationDateAndTime) {
        if (this.registrationDateAndTime == null && registrationDateAndTime == null) return true;
        return this.registrationDateAndTime != null && this.registrationDateAndTime.equals(registrationDateAndTime);
    }

    protected final void updateRegistrationDateAndTime(Object value) {
        value = value == null ? null : new ilarkesto.base.time.DateAndTime((String)value);
        setRegistrationDateAndTime((ilarkesto.base.time.DateAndTime)value);
    }

    // -----------------------------------------------------------
    // - disabled
    // -----------------------------------------------------------

    private boolean disabled;

    public final boolean isDisabled() {
        return disabled;
    }

    public final void setDisabled(boolean disabled) {
        disabled = prepareDisabled(disabled);
        if (isDisabled(disabled)) return;
        this.disabled = disabled;
        fireModified("disabled="+disabled);
    }

    protected boolean prepareDisabled(boolean disabled) {
        return disabled;
    }

    public final boolean isDisabled(boolean disabled) {
        return this.disabled == disabled;
    }

    protected final void updateDisabled(Object value) {
        setDisabled((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideBlog
    // -----------------------------------------------------------

    private boolean hideUserGuideBlog;

    public final boolean isHideUserGuideBlog() {
        return hideUserGuideBlog;
    }

    public final void setHideUserGuideBlog(boolean hideUserGuideBlog) {
        hideUserGuideBlog = prepareHideUserGuideBlog(hideUserGuideBlog);
        if (isHideUserGuideBlog(hideUserGuideBlog)) return;
        this.hideUserGuideBlog = hideUserGuideBlog;
        fireModified("hideUserGuideBlog="+hideUserGuideBlog);
    }

    protected boolean prepareHideUserGuideBlog(boolean hideUserGuideBlog) {
        return hideUserGuideBlog;
    }

    public final boolean isHideUserGuideBlog(boolean hideUserGuideBlog) {
        return this.hideUserGuideBlog == hideUserGuideBlog;
    }

    protected final void updateHideUserGuideBlog(Object value) {
        setHideUserGuideBlog((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideCalendar
    // -----------------------------------------------------------

    private boolean hideUserGuideCalendar;

    public final boolean isHideUserGuideCalendar() {
        return hideUserGuideCalendar;
    }

    public final void setHideUserGuideCalendar(boolean hideUserGuideCalendar) {
        hideUserGuideCalendar = prepareHideUserGuideCalendar(hideUserGuideCalendar);
        if (isHideUserGuideCalendar(hideUserGuideCalendar)) return;
        this.hideUserGuideCalendar = hideUserGuideCalendar;
        fireModified("hideUserGuideCalendar="+hideUserGuideCalendar);
    }

    protected boolean prepareHideUserGuideCalendar(boolean hideUserGuideCalendar) {
        return hideUserGuideCalendar;
    }

    public final boolean isHideUserGuideCalendar(boolean hideUserGuideCalendar) {
        return this.hideUserGuideCalendar == hideUserGuideCalendar;
    }

    protected final void updateHideUserGuideCalendar(Object value) {
        setHideUserGuideCalendar((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideFiles
    // -----------------------------------------------------------

    private boolean hideUserGuideFiles;

    public final boolean isHideUserGuideFiles() {
        return hideUserGuideFiles;
    }

    public final void setHideUserGuideFiles(boolean hideUserGuideFiles) {
        hideUserGuideFiles = prepareHideUserGuideFiles(hideUserGuideFiles);
        if (isHideUserGuideFiles(hideUserGuideFiles)) return;
        this.hideUserGuideFiles = hideUserGuideFiles;
        fireModified("hideUserGuideFiles="+hideUserGuideFiles);
    }

    protected boolean prepareHideUserGuideFiles(boolean hideUserGuideFiles) {
        return hideUserGuideFiles;
    }

    public final boolean isHideUserGuideFiles(boolean hideUserGuideFiles) {
        return this.hideUserGuideFiles == hideUserGuideFiles;
    }

    protected final void updateHideUserGuideFiles(Object value) {
        setHideUserGuideFiles((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideForum
    // -----------------------------------------------------------

    private boolean hideUserGuideForum;

    public final boolean isHideUserGuideForum() {
        return hideUserGuideForum;
    }

    public final void setHideUserGuideForum(boolean hideUserGuideForum) {
        hideUserGuideForum = prepareHideUserGuideForum(hideUserGuideForum);
        if (isHideUserGuideForum(hideUserGuideForum)) return;
        this.hideUserGuideForum = hideUserGuideForum;
        fireModified("hideUserGuideForum="+hideUserGuideForum);
    }

    protected boolean prepareHideUserGuideForum(boolean hideUserGuideForum) {
        return hideUserGuideForum;
    }

    public final boolean isHideUserGuideForum(boolean hideUserGuideForum) {
        return this.hideUserGuideForum == hideUserGuideForum;
    }

    protected final void updateHideUserGuideForum(Object value) {
        setHideUserGuideForum((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideImpediments
    // -----------------------------------------------------------

    private boolean hideUserGuideImpediments;

    public final boolean isHideUserGuideImpediments() {
        return hideUserGuideImpediments;
    }

    public final void setHideUserGuideImpediments(boolean hideUserGuideImpediments) {
        hideUserGuideImpediments = prepareHideUserGuideImpediments(hideUserGuideImpediments);
        if (isHideUserGuideImpediments(hideUserGuideImpediments)) return;
        this.hideUserGuideImpediments = hideUserGuideImpediments;
        fireModified("hideUserGuideImpediments="+hideUserGuideImpediments);
    }

    protected boolean prepareHideUserGuideImpediments(boolean hideUserGuideImpediments) {
        return hideUserGuideImpediments;
    }

    public final boolean isHideUserGuideImpediments(boolean hideUserGuideImpediments) {
        return this.hideUserGuideImpediments == hideUserGuideImpediments;
    }

    protected final void updateHideUserGuideImpediments(Object value) {
        setHideUserGuideImpediments((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideIssues
    // -----------------------------------------------------------

    private boolean hideUserGuideIssues;

    public final boolean isHideUserGuideIssues() {
        return hideUserGuideIssues;
    }

    public final void setHideUserGuideIssues(boolean hideUserGuideIssues) {
        hideUserGuideIssues = prepareHideUserGuideIssues(hideUserGuideIssues);
        if (isHideUserGuideIssues(hideUserGuideIssues)) return;
        this.hideUserGuideIssues = hideUserGuideIssues;
        fireModified("hideUserGuideIssues="+hideUserGuideIssues);
    }

    protected boolean prepareHideUserGuideIssues(boolean hideUserGuideIssues) {
        return hideUserGuideIssues;
    }

    public final boolean isHideUserGuideIssues(boolean hideUserGuideIssues) {
        return this.hideUserGuideIssues == hideUserGuideIssues;
    }

    protected final void updateHideUserGuideIssues(Object value) {
        setHideUserGuideIssues((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideJournal
    // -----------------------------------------------------------

    private boolean hideUserGuideJournal;

    public final boolean isHideUserGuideJournal() {
        return hideUserGuideJournal;
    }

    public final void setHideUserGuideJournal(boolean hideUserGuideJournal) {
        hideUserGuideJournal = prepareHideUserGuideJournal(hideUserGuideJournal);
        if (isHideUserGuideJournal(hideUserGuideJournal)) return;
        this.hideUserGuideJournal = hideUserGuideJournal;
        fireModified("hideUserGuideJournal="+hideUserGuideJournal);
    }

    protected boolean prepareHideUserGuideJournal(boolean hideUserGuideJournal) {
        return hideUserGuideJournal;
    }

    public final boolean isHideUserGuideJournal(boolean hideUserGuideJournal) {
        return this.hideUserGuideJournal == hideUserGuideJournal;
    }

    protected final void updateHideUserGuideJournal(Object value) {
        setHideUserGuideJournal((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideNextSprint
    // -----------------------------------------------------------

    private boolean hideUserGuideNextSprint;

    public final boolean isHideUserGuideNextSprint() {
        return hideUserGuideNextSprint;
    }

    public final void setHideUserGuideNextSprint(boolean hideUserGuideNextSprint) {
        hideUserGuideNextSprint = prepareHideUserGuideNextSprint(hideUserGuideNextSprint);
        if (isHideUserGuideNextSprint(hideUserGuideNextSprint)) return;
        this.hideUserGuideNextSprint = hideUserGuideNextSprint;
        fireModified("hideUserGuideNextSprint="+hideUserGuideNextSprint);
    }

    protected boolean prepareHideUserGuideNextSprint(boolean hideUserGuideNextSprint) {
        return hideUserGuideNextSprint;
    }

    public final boolean isHideUserGuideNextSprint(boolean hideUserGuideNextSprint) {
        return this.hideUserGuideNextSprint == hideUserGuideNextSprint;
    }

    protected final void updateHideUserGuideNextSprint(Object value) {
        setHideUserGuideNextSprint((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideProductBacklog
    // -----------------------------------------------------------

    private boolean hideUserGuideProductBacklog;

    public final boolean isHideUserGuideProductBacklog() {
        return hideUserGuideProductBacklog;
    }

    public final void setHideUserGuideProductBacklog(boolean hideUserGuideProductBacklog) {
        hideUserGuideProductBacklog = prepareHideUserGuideProductBacklog(hideUserGuideProductBacklog);
        if (isHideUserGuideProductBacklog(hideUserGuideProductBacklog)) return;
        this.hideUserGuideProductBacklog = hideUserGuideProductBacklog;
        fireModified("hideUserGuideProductBacklog="+hideUserGuideProductBacklog);
    }

    protected boolean prepareHideUserGuideProductBacklog(boolean hideUserGuideProductBacklog) {
        return hideUserGuideProductBacklog;
    }

    public final boolean isHideUserGuideProductBacklog(boolean hideUserGuideProductBacklog) {
        return this.hideUserGuideProductBacklog == hideUserGuideProductBacklog;
    }

    protected final void updateHideUserGuideProductBacklog(Object value) {
        setHideUserGuideProductBacklog((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideCourtroom
    // -----------------------------------------------------------

    private boolean hideUserGuideCourtroom;

    public final boolean isHideUserGuideCourtroom() {
        return hideUserGuideCourtroom;
    }

    public final void setHideUserGuideCourtroom(boolean hideUserGuideCourtroom) {
        hideUserGuideCourtroom = prepareHideUserGuideCourtroom(hideUserGuideCourtroom);
        if (isHideUserGuideCourtroom(hideUserGuideCourtroom)) return;
        this.hideUserGuideCourtroom = hideUserGuideCourtroom;
        fireModified("hideUserGuideCourtroom="+hideUserGuideCourtroom);
    }

    protected boolean prepareHideUserGuideCourtroom(boolean hideUserGuideCourtroom) {
        return hideUserGuideCourtroom;
    }

    public final boolean isHideUserGuideCourtroom(boolean hideUserGuideCourtroom) {
        return this.hideUserGuideCourtroom == hideUserGuideCourtroom;
    }

    protected final void updateHideUserGuideCourtroom(Object value) {
        setHideUserGuideCourtroom((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideQualityBacklog
    // -----------------------------------------------------------

    private boolean hideUserGuideQualityBacklog;

    public final boolean isHideUserGuideQualityBacklog() {
        return hideUserGuideQualityBacklog;
    }

    public final void setHideUserGuideQualityBacklog(boolean hideUserGuideQualityBacklog) {
        hideUserGuideQualityBacklog = prepareHideUserGuideQualityBacklog(hideUserGuideQualityBacklog);
        if (isHideUserGuideQualityBacklog(hideUserGuideQualityBacklog)) return;
        this.hideUserGuideQualityBacklog = hideUserGuideQualityBacklog;
        fireModified("hideUserGuideQualityBacklog="+hideUserGuideQualityBacklog);
    }

    protected boolean prepareHideUserGuideQualityBacklog(boolean hideUserGuideQualityBacklog) {
        return hideUserGuideQualityBacklog;
    }

    public final boolean isHideUserGuideQualityBacklog(boolean hideUserGuideQualityBacklog) {
        return this.hideUserGuideQualityBacklog == hideUserGuideQualityBacklog;
    }

    protected final void updateHideUserGuideQualityBacklog(Object value) {
        setHideUserGuideQualityBacklog((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideReleases
    // -----------------------------------------------------------

    private boolean hideUserGuideReleases;

    public final boolean isHideUserGuideReleases() {
        return hideUserGuideReleases;
    }

    public final void setHideUserGuideReleases(boolean hideUserGuideReleases) {
        hideUserGuideReleases = prepareHideUserGuideReleases(hideUserGuideReleases);
        if (isHideUserGuideReleases(hideUserGuideReleases)) return;
        this.hideUserGuideReleases = hideUserGuideReleases;
        fireModified("hideUserGuideReleases="+hideUserGuideReleases);
    }

    protected boolean prepareHideUserGuideReleases(boolean hideUserGuideReleases) {
        return hideUserGuideReleases;
    }

    public final boolean isHideUserGuideReleases(boolean hideUserGuideReleases) {
        return this.hideUserGuideReleases == hideUserGuideReleases;
    }

    protected final void updateHideUserGuideReleases(Object value) {
        setHideUserGuideReleases((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideRisks
    // -----------------------------------------------------------

    private boolean hideUserGuideRisks;

    public final boolean isHideUserGuideRisks() {
        return hideUserGuideRisks;
    }

    public final void setHideUserGuideRisks(boolean hideUserGuideRisks) {
        hideUserGuideRisks = prepareHideUserGuideRisks(hideUserGuideRisks);
        if (isHideUserGuideRisks(hideUserGuideRisks)) return;
        this.hideUserGuideRisks = hideUserGuideRisks;
        fireModified("hideUserGuideRisks="+hideUserGuideRisks);
    }

    protected boolean prepareHideUserGuideRisks(boolean hideUserGuideRisks) {
        return hideUserGuideRisks;
    }

    public final boolean isHideUserGuideRisks(boolean hideUserGuideRisks) {
        return this.hideUserGuideRisks == hideUserGuideRisks;
    }

    protected final void updateHideUserGuideRisks(Object value) {
        setHideUserGuideRisks((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideSprintBacklog
    // -----------------------------------------------------------

    private boolean hideUserGuideSprintBacklog;

    public final boolean isHideUserGuideSprintBacklog() {
        return hideUserGuideSprintBacklog;
    }

    public final void setHideUserGuideSprintBacklog(boolean hideUserGuideSprintBacklog) {
        hideUserGuideSprintBacklog = prepareHideUserGuideSprintBacklog(hideUserGuideSprintBacklog);
        if (isHideUserGuideSprintBacklog(hideUserGuideSprintBacklog)) return;
        this.hideUserGuideSprintBacklog = hideUserGuideSprintBacklog;
        fireModified("hideUserGuideSprintBacklog="+hideUserGuideSprintBacklog);
    }

    protected boolean prepareHideUserGuideSprintBacklog(boolean hideUserGuideSprintBacklog) {
        return hideUserGuideSprintBacklog;
    }

    public final boolean isHideUserGuideSprintBacklog(boolean hideUserGuideSprintBacklog) {
        return this.hideUserGuideSprintBacklog == hideUserGuideSprintBacklog;
    }

    protected final void updateHideUserGuideSprintBacklog(Object value) {
        setHideUserGuideSprintBacklog((Boolean)value);
    }

    // -----------------------------------------------------------
    // - hideUserGuideWhiteboard
    // -----------------------------------------------------------

    private boolean hideUserGuideWhiteboard;

    public final boolean isHideUserGuideWhiteboard() {
        return hideUserGuideWhiteboard;
    }

    public final void setHideUserGuideWhiteboard(boolean hideUserGuideWhiteboard) {
        hideUserGuideWhiteboard = prepareHideUserGuideWhiteboard(hideUserGuideWhiteboard);
        if (isHideUserGuideWhiteboard(hideUserGuideWhiteboard)) return;
        this.hideUserGuideWhiteboard = hideUserGuideWhiteboard;
        fireModified("hideUserGuideWhiteboard="+hideUserGuideWhiteboard);
    }

    protected boolean prepareHideUserGuideWhiteboard(boolean hideUserGuideWhiteboard) {
        return hideUserGuideWhiteboard;
    }

    public final boolean isHideUserGuideWhiteboard(boolean hideUserGuideWhiteboard) {
        return this.hideUserGuideWhiteboard == hideUserGuideWhiteboard;
    }

    protected final void updateHideUserGuideWhiteboard(Object value) {
        setHideUserGuideWhiteboard((Boolean)value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("name")) updateName(value);
            if (property.equals("admin")) updateAdmin(value);
            if (property.equals("emailVerified")) updateEmailVerified(value);
            if (property.equals("email")) updateEmail(value);
            if (property.equals("currentProjectId")) updateCurrentProject(value);
            if (property.equals("color")) updateColor(value);
            if (property.equals("lastLoginDateAndTime")) updateLastLoginDateAndTime(value);
            if (property.equals("registrationDateAndTime")) updateRegistrationDateAndTime(value);
            if (property.equals("disabled")) updateDisabled(value);
            if (property.equals("hideUserGuideBlog")) updateHideUserGuideBlog(value);
            if (property.equals("hideUserGuideCalendar")) updateHideUserGuideCalendar(value);
            if (property.equals("hideUserGuideFiles")) updateHideUserGuideFiles(value);
            if (property.equals("hideUserGuideForum")) updateHideUserGuideForum(value);
            if (property.equals("hideUserGuideImpediments")) updateHideUserGuideImpediments(value);
            if (property.equals("hideUserGuideIssues")) updateHideUserGuideIssues(value);
            if (property.equals("hideUserGuideJournal")) updateHideUserGuideJournal(value);
            if (property.equals("hideUserGuideNextSprint")) updateHideUserGuideNextSprint(value);
            if (property.equals("hideUserGuideProductBacklog")) updateHideUserGuideProductBacklog(value);
            if (property.equals("hideUserGuideCourtroom")) updateHideUserGuideCourtroom(value);
            if (property.equals("hideUserGuideQualityBacklog")) updateHideUserGuideQualityBacklog(value);
            if (property.equals("hideUserGuideReleases")) updateHideUserGuideReleases(value);
            if (property.equals("hideUserGuideRisks")) updateHideUserGuideRisks(value);
            if (property.equals("hideUserGuideSprintBacklog")) updateHideUserGuideSprintBacklog(value);
            if (property.equals("hideUserGuideWhiteboard")) updateHideUserGuideWhiteboard(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadCurrentProjectReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        try {
            getCurrentProject();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead currentProject reference");
            repairDeadCurrentProjectReference(this.currentProjectId);
        }
    }


    // -----------------------------------------------------------
    // - dependencies
    // -----------------------------------------------------------

    static scrum.server.project.ProjectDao projectDao;

    public static final void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        GUser.projectDao = projectDao;
    }

    static UserDao userDao;

    public static final void setUserDao(UserDao userDao) {
        GUser.userDao = userDao;
    }

}