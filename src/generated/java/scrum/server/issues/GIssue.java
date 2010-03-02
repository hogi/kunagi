// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.EntityGenerator










package scrum.server.issues;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GIssue
            extends AEntity
            implements ilarkesto.auth.ViewProtected<scrum.server.admin.User>, ilarkesto.search.Searchable, java.lang.Comparable<Issue> {

    // --- AEntity ---

    public final IssueDao getDao() {
        return issueDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("number", this.number);
        properties.put("type", this.type);
        properties.put("date", this.date == null ? null : this.date.toString());
        properties.put("creatorId", this.creatorId);
        properties.put("label", this.label);
        properties.put("description", this.description);
        properties.put("acceptDate", this.acceptDate == null ? null : this.acceptDate.toString());
        properties.put("suspendDate", this.suspendDate == null ? null : this.suspendDate.toString());
        properties.put("closeDate", this.closeDate == null ? null : this.closeDate.toString());
    }

    public int compareTo(Issue other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final ilarkesto.core.logging.Log LOG = ilarkesto.core.logging.Log.get(GIssue.class);

    public static final String TYPE = "issue";


    // -----------------------------------------------------------
    // - Searchable
    // -----------------------------------------------------------

    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getLabel(), key)) return true;
        if (matchesKey(getDescription(), key)) return true;
        return false;
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private String projectId;
    private transient scrum.server.project.Project projectCache;

    private void updateProjectCache() {
        projectCache = this.projectId == null ? null : (scrum.server.project.Project)projectDao.getById(this.projectId);
    }

    public final scrum.server.project.Project getProject() {
        if (projectCache == null) updateProjectCache();
        return projectCache;
    }

    public final void setProject(scrum.server.project.Project project) {
        project = prepareProject(project);
        if (isProject(project)) return;
        this.projectId = project == null ? null : project.getId();
        projectCache = project;
        fireModified();
    }

    protected scrum.server.project.Project prepareProject(scrum.server.project.Project project) {
        return project;
    }

    protected void repairDeadProjectReference(String entityId) {
        if (entityId.equals(this.projectId)) {
            repairMissingMaster();
        }
    }

    public final boolean isProjectSet() {
        return this.projectId != null;
    }

    public final boolean isProject(scrum.server.project.Project project) {
        if (this.projectId == null && project == null) return true;
        return project != null && project.getId().equals(this.projectId);
    }

    protected final void updateProject(Object value) {
        setProject(value == null ? null : (scrum.server.project.Project)projectDao.getById((String)value));
    }

    // -----------------------------------------------------------
    // - number
    // -----------------------------------------------------------

    private int number;

    public final int getNumber() {
        return number;
    }

    public final void setNumber(int number) {
        number = prepareNumber(number);
        if (isNumber(number)) return;
        this.number = number;
        fireModified();
    }

    protected int prepareNumber(int number) {
        return number;
    }

    public final boolean isNumber(int number) {
        return this.number == number;
    }

    protected final void updateNumber(Object value) {
        setNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - type
    // -----------------------------------------------------------

    private java.lang.String type;

    public final java.lang.String getType() {
        return type;
    }

    public final void setType(java.lang.String type) {
        type = prepareType(type);
        if (isType(type)) return;
        this.type = type;
        fireModified();
    }

    protected java.lang.String prepareType(java.lang.String type) {
        type = Str.removeUnreadableChars(type);
        return type;
    }

    public final boolean isTypeSet() {
        return this.type != null;
    }

    public final boolean isType(java.lang.String type) {
        if (this.type == null && type == null) return true;
        return this.type != null && this.type.equals(type);
    }

    protected final void updateType(Object value) {
        setType((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - date
    // -----------------------------------------------------------

    private ilarkesto.base.time.DateAndTime date;

    public final ilarkesto.base.time.DateAndTime getDate() {
        return date;
    }

    public final void setDate(ilarkesto.base.time.DateAndTime date) {
        date = prepareDate(date);
        if (isDate(date)) return;
        this.date = date;
        fireModified();
    }

    protected ilarkesto.base.time.DateAndTime prepareDate(ilarkesto.base.time.DateAndTime date) {
        return date;
    }

    public final boolean isDateSet() {
        return this.date != null;
    }

    public final boolean isDate(ilarkesto.base.time.DateAndTime date) {
        if (this.date == null && date == null) return true;
        return this.date != null && this.date.equals(date);
    }

    protected final void updateDate(Object value) {
        value = value == null ? null : new ilarkesto.base.time.DateAndTime((String)value);
        setDate((ilarkesto.base.time.DateAndTime)value);
    }

    // -----------------------------------------------------------
    // - creator
    // -----------------------------------------------------------

    private String creatorId;
    private transient scrum.server.admin.User creatorCache;

    private void updateCreatorCache() {
        creatorCache = this.creatorId == null ? null : (scrum.server.admin.User)userDao.getById(this.creatorId);
    }

    public final scrum.server.admin.User getCreator() {
        if (creatorCache == null) updateCreatorCache();
        return creatorCache;
    }

    public final void setCreator(scrum.server.admin.User creator) {
        creator = prepareCreator(creator);
        if (isCreator(creator)) return;
        this.creatorId = creator == null ? null : creator.getId();
        creatorCache = creator;
        fireModified();
    }

    protected scrum.server.admin.User prepareCreator(scrum.server.admin.User creator) {
        return creator;
    }

    protected void repairDeadCreatorReference(String entityId) {
        if (entityId.equals(this.creatorId)) {
            this.creatorId = null;
            fireModified();
        }
    }

    public final boolean isCreatorSet() {
        return this.creatorId != null;
    }

    public final boolean isCreator(scrum.server.admin.User creator) {
        if (this.creatorId == null && creator == null) return true;
        return creator != null && creator.getId().equals(this.creatorId);
    }

    protected final void updateCreator(Object value) {
        setCreator(value == null ? null : (scrum.server.admin.User)userDao.getById((String)value));
    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private java.lang.String label;

    public final java.lang.String getLabel() {
        return label;
    }

    public final void setLabel(java.lang.String label) {
        label = prepareLabel(label);
        if (isLabel(label)) return;
        this.label = label;
        fireModified();
    }

    protected java.lang.String prepareLabel(java.lang.String label) {
        label = Str.removeUnreadableChars(label);
        return label;
    }

    public final boolean isLabelSet() {
        return this.label != null;
    }

    public final boolean isLabel(java.lang.String label) {
        if (this.label == null && label == null) return true;
        return this.label != null && this.label.equals(label);
    }

    protected final void updateLabel(Object value) {
        setLabel((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - description
    // -----------------------------------------------------------

    private java.lang.String description;

    public final java.lang.String getDescription() {
        return description;
    }

    public final void setDescription(java.lang.String description) {
        description = prepareDescription(description);
        if (isDescription(description)) return;
        this.description = description;
        fireModified();
    }

    protected java.lang.String prepareDescription(java.lang.String description) {
        description = Str.removeUnreadableChars(description);
        return description;
    }

    public final boolean isDescriptionSet() {
        return this.description != null;
    }

    public final boolean isDescription(java.lang.String description) {
        if (this.description == null && description == null) return true;
        return this.description != null && this.description.equals(description);
    }

    protected final void updateDescription(Object value) {
        setDescription((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - acceptDate
    // -----------------------------------------------------------

    private ilarkesto.base.time.Date acceptDate;

    public final ilarkesto.base.time.Date getAcceptDate() {
        return acceptDate;
    }

    public final void setAcceptDate(ilarkesto.base.time.Date acceptDate) {
        acceptDate = prepareAcceptDate(acceptDate);
        if (isAcceptDate(acceptDate)) return;
        this.acceptDate = acceptDate;
        fireModified();
    }

    protected ilarkesto.base.time.Date prepareAcceptDate(ilarkesto.base.time.Date acceptDate) {
        return acceptDate;
    }

    public final boolean isAcceptDateSet() {
        return this.acceptDate != null;
    }

    public final boolean isAcceptDate(ilarkesto.base.time.Date acceptDate) {
        if (this.acceptDate == null && acceptDate == null) return true;
        return this.acceptDate != null && this.acceptDate.equals(acceptDate);
    }

    protected final void updateAcceptDate(Object value) {
        value = value == null ? null : new ilarkesto.base.time.Date((String)value);
        setAcceptDate((ilarkesto.base.time.Date)value);
    }

    // -----------------------------------------------------------
    // - suspendDate
    // -----------------------------------------------------------

    private ilarkesto.base.time.Date suspendDate;

    public final ilarkesto.base.time.Date getSuspendDate() {
        return suspendDate;
    }

    public final void setSuspendDate(ilarkesto.base.time.Date suspendDate) {
        suspendDate = prepareSuspendDate(suspendDate);
        if (isSuspendDate(suspendDate)) return;
        this.suspendDate = suspendDate;
        fireModified();
    }

    protected ilarkesto.base.time.Date prepareSuspendDate(ilarkesto.base.time.Date suspendDate) {
        return suspendDate;
    }

    public final boolean isSuspendDateSet() {
        return this.suspendDate != null;
    }

    public final boolean isSuspendDate(ilarkesto.base.time.Date suspendDate) {
        if (this.suspendDate == null && suspendDate == null) return true;
        return this.suspendDate != null && this.suspendDate.equals(suspendDate);
    }

    protected final void updateSuspendDate(Object value) {
        value = value == null ? null : new ilarkesto.base.time.Date((String)value);
        setSuspendDate((ilarkesto.base.time.Date)value);
    }

    // -----------------------------------------------------------
    // - closeDate
    // -----------------------------------------------------------

    private ilarkesto.base.time.Date closeDate;

    public final ilarkesto.base.time.Date getCloseDate() {
        return closeDate;
    }

    public final void setCloseDate(ilarkesto.base.time.Date closeDate) {
        closeDate = prepareCloseDate(closeDate);
        if (isCloseDate(closeDate)) return;
        this.closeDate = closeDate;
        fireModified();
    }

    protected ilarkesto.base.time.Date prepareCloseDate(ilarkesto.base.time.Date closeDate) {
        return closeDate;
    }

    public final boolean isCloseDateSet() {
        return this.closeDate != null;
    }

    public final boolean isCloseDate(ilarkesto.base.time.Date closeDate) {
        if (this.closeDate == null && closeDate == null) return true;
        return this.closeDate != null && this.closeDate.equals(closeDate);
    }

    protected final void updateCloseDate(Object value) {
        value = value == null ? null : new ilarkesto.base.time.Date((String)value);
        setCloseDate((ilarkesto.base.time.Date)value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("projectId")) updateProject(value);
            if (property.equals("number")) updateNumber(value);
            if (property.equals("type")) updateType(value);
            if (property.equals("date")) updateDate(value);
            if (property.equals("creatorId")) updateCreator(value);
            if (property.equals("label")) updateLabel(value);
            if (property.equals("description")) updateDescription(value);
            if (property.equals("acceptDate")) updateAcceptDate(value);
            if (property.equals("suspendDate")) updateSuspendDate(value);
            if (property.equals("closeDate")) updateCloseDate(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadProjectReference(entityId);
        repairDeadCreatorReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        if (!isProjectSet()) {
            repairMissingMaster();
            return;
        }
        try {
            getProject();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead project reference");
            repairDeadProjectReference(this.projectId);
        }
        try {
            getCreator();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead creator reference");
            repairDeadCreatorReference(this.creatorId);
        }
    }


    // -----------------------------------------------------------
    // - dependencies
    // -----------------------------------------------------------

    static scrum.server.project.ProjectDao projectDao;

    public static final void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        GIssue.projectDao = projectDao;
    }

    static IssueDao issueDao;

    public static final void setIssueDao(IssueDao issueDao) {
        GIssue.issueDao = issueDao;
    }

}