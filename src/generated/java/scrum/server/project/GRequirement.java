// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.EntityGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GRequirement
            extends AEntity
            implements ilarkesto.auth.ViewProtected<scrum.server.admin.User>, ilarkesto.search.Searchable, java.lang.Comparable<Requirement> {

    // --- AEntity ---

    public final RequirementDao getDao() {
        return requirementDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("sprintId", this.sprintId);
        properties.put("issueId", this.issueId);
        properties.put("number", this.number);
        properties.put("qualitysIds", this.qualitysIds);
        properties.put("label", this.label);
        properties.put("description", this.description);
        properties.put("testDescription", this.testDescription);
        properties.put("estimatedWork", this.estimatedWork);
        properties.put("rejectDate", this.rejectDate == null ? null : this.rejectDate.toString());
        properties.put("closed", this.closed);
        properties.put("dirty", this.dirty);
        properties.put("workEstimationVotingActive", this.workEstimationVotingActive);
        properties.put("workEstimationVotingShowoff", this.workEstimationVotingShowoff);
        properties.put("tasksOrderIds", this.tasksOrderIds);
    }

    public int compareTo(Requirement other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final ilarkesto.core.logging.Log LOG = ilarkesto.core.logging.Log.get(GRequirement.class);

    public static final String TYPE = "requirement";


    // -----------------------------------------------------------
    // - Searchable
    // -----------------------------------------------------------

    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getLabel(), key)) return true;
        if (matchesKey(getDescription(), key)) return true;
        if (matchesKey(getTestDescription(), key)) return true;
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

    public final String getProjectId() {
        return this.projectId;
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
        fireModified("project="+project);
    }

    protected scrum.server.project.Project prepareProject(scrum.server.project.Project project) {
        return project;
    }

    protected void repairDeadProjectReference(String entityId) {
        if (this.projectId == null || entityId.equals(this.projectId)) {
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
    // - sprint
    // -----------------------------------------------------------

    private String sprintId;
    private transient scrum.server.sprint.Sprint sprintCache;

    private void updateSprintCache() {
        sprintCache = this.sprintId == null ? null : (scrum.server.sprint.Sprint)sprintDao.getById(this.sprintId);
    }

    public final String getSprintId() {
        return this.sprintId;
    }

    public final scrum.server.sprint.Sprint getSprint() {
        if (sprintCache == null) updateSprintCache();
        return sprintCache;
    }

    public final void setSprint(scrum.server.sprint.Sprint sprint) {
        sprint = prepareSprint(sprint);
        if (isSprint(sprint)) return;
        this.sprintId = sprint == null ? null : sprint.getId();
        sprintCache = sprint;
        fireModified("sprint="+sprint);
    }

    protected scrum.server.sprint.Sprint prepareSprint(scrum.server.sprint.Sprint sprint) {
        return sprint;
    }

    protected void repairDeadSprintReference(String entityId) {
        if (this.sprintId == null || entityId.equals(this.sprintId)) {
            setSprint(null);
        }
    }

    public final boolean isSprintSet() {
        return this.sprintId != null;
    }

    public final boolean isSprint(scrum.server.sprint.Sprint sprint) {
        if (this.sprintId == null && sprint == null) return true;
        return sprint != null && sprint.getId().equals(this.sprintId);
    }

    protected final void updateSprint(Object value) {
        setSprint(value == null ? null : (scrum.server.sprint.Sprint)sprintDao.getById((String)value));
    }

    // -----------------------------------------------------------
    // - issue
    // -----------------------------------------------------------

    private String issueId;
    private transient scrum.server.issues.Issue issueCache;

    private void updateIssueCache() {
        issueCache = this.issueId == null ? null : (scrum.server.issues.Issue)issueDao.getById(this.issueId);
    }

    public final String getIssueId() {
        return this.issueId;
    }

    public final scrum.server.issues.Issue getIssue() {
        if (issueCache == null) updateIssueCache();
        return issueCache;
    }

    public final void setIssue(scrum.server.issues.Issue issue) {
        issue = prepareIssue(issue);
        if (isIssue(issue)) return;
        this.issueId = issue == null ? null : issue.getId();
        issueCache = issue;
        fireModified("issue="+issue);
    }

    protected scrum.server.issues.Issue prepareIssue(scrum.server.issues.Issue issue) {
        return issue;
    }

    protected void repairDeadIssueReference(String entityId) {
        if (this.issueId == null || entityId.equals(this.issueId)) {
            setIssue(null);
        }
    }

    public final boolean isIssueSet() {
        return this.issueId != null;
    }

    public final boolean isIssue(scrum.server.issues.Issue issue) {
        if (this.issueId == null && issue == null) return true;
        return issue != null && issue.getId().equals(this.issueId);
    }

    protected final void updateIssue(Object value) {
        setIssue(value == null ? null : (scrum.server.issues.Issue)issueDao.getById((String)value));
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
        fireModified("number="+number);
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
    // - qualitys
    // -----------------------------------------------------------

    private java.util.Set<String> qualitysIds = new java.util.HashSet<String>();

    public final java.util.Set<scrum.server.project.Quality> getQualitys() {
        return (java.util.Set) qualityDao.getByIdsAsSet(this.qualitysIds);
    }

    public final void setQualitys(Collection<scrum.server.project.Quality> qualitys) {
        qualitys = prepareQualitys(qualitys);
        if (qualitys == null) qualitys = Collections.emptyList();
        java.util.Set<String> ids = getIdsAsSet(qualitys);
        if (this.qualitysIds.equals(ids)) return;
        this.qualitysIds = ids;
        fireModified("qualitys="+Str.format(qualitys));
    }

    protected Collection<scrum.server.project.Quality> prepareQualitys(Collection<scrum.server.project.Quality> qualitys) {
        return qualitys;
    }

    protected void repairDeadQualityReference(String entityId) {
        if (this.qualitysIds.remove(entityId)) fireModified("qualitys-=" + entityId);
    }

    public final boolean containsQuality(scrum.server.project.Quality quality) {
        if (quality == null) return false;
        return this.qualitysIds.contains(quality.getId());
    }

    public final int getQualitysCount() {
        return this.qualitysIds.size();
    }

    public final boolean isQualitysEmpty() {
        return this.qualitysIds.isEmpty();
    }

    public final boolean addQuality(scrum.server.project.Quality quality) {
        if (quality == null) throw new IllegalArgumentException("quality == null");
        boolean added = this.qualitysIds.add(quality.getId());
        if (added) fireModified("qualitys+=" + quality);
        return added;
    }

    public final boolean addQualitys(Collection<scrum.server.project.Quality> qualitys) {
        if (qualitys == null) throw new IllegalArgumentException("qualitys == null");
        boolean added = false;
        for (scrum.server.project.Quality quality : qualitys) {
            added = added | this.qualitysIds.add(quality.getId());
        }
        if (added) fireModified("qualitys+="+Str.format(qualitys));
        return added;
    }

    public final boolean removeQuality(scrum.server.project.Quality quality) {
        if (quality == null) throw new IllegalArgumentException("quality == null");
        if (this.qualitysIds == null) return false;
        boolean removed = this.qualitysIds.remove(quality.getId());
        if (removed) fireModified("qualitys-=" + quality);
        return removed;
    }

    public final boolean removeQualitys(Collection<scrum.server.project.Quality> qualitys) {
        if (qualitys == null) return false;
        if (qualitys.isEmpty()) return false;
        boolean removed = false;
        for (scrum.server.project.Quality _element: qualitys) {
            removed = removed | removeQuality(_element);
        }
        if (removed) fireModified("qualitys-="+Str.format(qualitys));
        return removed;
    }

    public final boolean clearQualitys() {
        if (this.qualitysIds.isEmpty()) return false;
        this.qualitysIds.clear();
        fireModified("qualitys cleared");
        return true;
    }

    protected final void updateQualitys(Object value) {
        Collection<String> ids = (Collection<String>) value;
        setQualitys((java.util.Set) qualityDao.getByIdsAsSet(ids));
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
        fireModified("label="+label);
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
        fireModified("description="+description);
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
    // - testDescription
    // -----------------------------------------------------------

    private java.lang.String testDescription;

    public final java.lang.String getTestDescription() {
        return testDescription;
    }

    public final void setTestDescription(java.lang.String testDescription) {
        testDescription = prepareTestDescription(testDescription);
        if (isTestDescription(testDescription)) return;
        this.testDescription = testDescription;
        fireModified("testDescription="+testDescription);
    }

    protected java.lang.String prepareTestDescription(java.lang.String testDescription) {
        testDescription = Str.removeUnreadableChars(testDescription);
        return testDescription;
    }

    public final boolean isTestDescriptionSet() {
        return this.testDescription != null;
    }

    public final boolean isTestDescription(java.lang.String testDescription) {
        if (this.testDescription == null && testDescription == null) return true;
        return this.testDescription != null && this.testDescription.equals(testDescription);
    }

    protected final void updateTestDescription(Object value) {
        setTestDescription((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - estimatedWork
    // -----------------------------------------------------------

    private java.lang.Float estimatedWork;

    public final java.lang.Float getEstimatedWork() {
        return estimatedWork;
    }

    public final void setEstimatedWork(java.lang.Float estimatedWork) {
        estimatedWork = prepareEstimatedWork(estimatedWork);
        if (isEstimatedWork(estimatedWork)) return;
        this.estimatedWork = estimatedWork;
        fireModified("estimatedWork="+estimatedWork);
    }

    protected java.lang.Float prepareEstimatedWork(java.lang.Float estimatedWork) {
        return estimatedWork;
    }

    public final boolean isEstimatedWorkSet() {
        return this.estimatedWork != null;
    }

    public final boolean isEstimatedWork(java.lang.Float estimatedWork) {
        if (this.estimatedWork == null && estimatedWork == null) return true;
        return this.estimatedWork != null && this.estimatedWork.equals(estimatedWork);
    }

    protected final void updateEstimatedWork(Object value) {
        setEstimatedWork((java.lang.Float)value);
    }

    // -----------------------------------------------------------
    // - rejectDate
    // -----------------------------------------------------------

    private ilarkesto.base.time.Date rejectDate;

    public final ilarkesto.base.time.Date getRejectDate() {
        return rejectDate;
    }

    public final void setRejectDate(ilarkesto.base.time.Date rejectDate) {
        rejectDate = prepareRejectDate(rejectDate);
        if (isRejectDate(rejectDate)) return;
        this.rejectDate = rejectDate;
        fireModified("rejectDate="+rejectDate);
    }

    protected ilarkesto.base.time.Date prepareRejectDate(ilarkesto.base.time.Date rejectDate) {
        return rejectDate;
    }

    public final boolean isRejectDateSet() {
        return this.rejectDate != null;
    }

    public final boolean isRejectDate(ilarkesto.base.time.Date rejectDate) {
        if (this.rejectDate == null && rejectDate == null) return true;
        return this.rejectDate != null && this.rejectDate.equals(rejectDate);
    }

    protected final void updateRejectDate(Object value) {
        value = value == null ? null : new ilarkesto.base.time.Date((String)value);
        setRejectDate((ilarkesto.base.time.Date)value);
    }

    // -----------------------------------------------------------
    // - closed
    // -----------------------------------------------------------

    private boolean closed;

    public final boolean isClosed() {
        return closed;
    }

    public final void setClosed(boolean closed) {
        closed = prepareClosed(closed);
        if (isClosed(closed)) return;
        this.closed = closed;
        fireModified("closed="+closed);
    }

    protected boolean prepareClosed(boolean closed) {
        return closed;
    }

    public final boolean isClosed(boolean closed) {
        return this.closed == closed;
    }

    protected final void updateClosed(Object value) {
        setClosed((Boolean)value);
    }

    // -----------------------------------------------------------
    // - dirty
    // -----------------------------------------------------------

    private boolean dirty;

    public final boolean isDirty() {
        return dirty;
    }

    public final void setDirty(boolean dirty) {
        dirty = prepareDirty(dirty);
        if (isDirty(dirty)) return;
        this.dirty = dirty;
        fireModified("dirty="+dirty);
    }

    protected boolean prepareDirty(boolean dirty) {
        return dirty;
    }

    public final boolean isDirty(boolean dirty) {
        return this.dirty == dirty;
    }

    protected final void updateDirty(Object value) {
        setDirty((Boolean)value);
    }

    // -----------------------------------------------------------
    // - workEstimationVotingActive
    // -----------------------------------------------------------

    private boolean workEstimationVotingActive;

    public final boolean isWorkEstimationVotingActive() {
        return workEstimationVotingActive;
    }

    public final void setWorkEstimationVotingActive(boolean workEstimationVotingActive) {
        workEstimationVotingActive = prepareWorkEstimationVotingActive(workEstimationVotingActive);
        if (isWorkEstimationVotingActive(workEstimationVotingActive)) return;
        this.workEstimationVotingActive = workEstimationVotingActive;
        fireModified("workEstimationVotingActive="+workEstimationVotingActive);
    }

    protected boolean prepareWorkEstimationVotingActive(boolean workEstimationVotingActive) {
        return workEstimationVotingActive;
    }

    public final boolean isWorkEstimationVotingActive(boolean workEstimationVotingActive) {
        return this.workEstimationVotingActive == workEstimationVotingActive;
    }

    protected final void updateWorkEstimationVotingActive(Object value) {
        setWorkEstimationVotingActive((Boolean)value);
    }

    // -----------------------------------------------------------
    // - workEstimationVotingShowoff
    // -----------------------------------------------------------

    private boolean workEstimationVotingShowoff;

    public final boolean isWorkEstimationVotingShowoff() {
        return workEstimationVotingShowoff;
    }

    public final void setWorkEstimationVotingShowoff(boolean workEstimationVotingShowoff) {
        workEstimationVotingShowoff = prepareWorkEstimationVotingShowoff(workEstimationVotingShowoff);
        if (isWorkEstimationVotingShowoff(workEstimationVotingShowoff)) return;
        this.workEstimationVotingShowoff = workEstimationVotingShowoff;
        fireModified("workEstimationVotingShowoff="+workEstimationVotingShowoff);
    }

    protected boolean prepareWorkEstimationVotingShowoff(boolean workEstimationVotingShowoff) {
        return workEstimationVotingShowoff;
    }

    public final boolean isWorkEstimationVotingShowoff(boolean workEstimationVotingShowoff) {
        return this.workEstimationVotingShowoff == workEstimationVotingShowoff;
    }

    protected final void updateWorkEstimationVotingShowoff(Object value) {
        setWorkEstimationVotingShowoff((Boolean)value);
    }

    // -----------------------------------------------------------
    // - tasksOrderIds
    // -----------------------------------------------------------

    private java.util.List<java.lang.String> tasksOrderIds = new java.util.ArrayList<java.lang.String>();

    public final java.util.List<java.lang.String> getTasksOrderIds() {
        return new java.util.ArrayList<java.lang.String>(tasksOrderIds);
    }

    public final void setTasksOrderIds(Collection<java.lang.String> tasksOrderIds) {
        tasksOrderIds = prepareTasksOrderIds(tasksOrderIds);
        if (tasksOrderIds == null) tasksOrderIds = Collections.emptyList();
        if (this.tasksOrderIds.equals(tasksOrderIds)) return;
        this.tasksOrderIds = new java.util.ArrayList<java.lang.String>(tasksOrderIds);
        fireModified("tasksOrderIds="+Str.format(tasksOrderIds));
    }

    protected Collection<java.lang.String> prepareTasksOrderIds(Collection<java.lang.String> tasksOrderIds) {
        return tasksOrderIds;
    }

    public final boolean containsTasksOrderId(java.lang.String tasksOrderId) {
        if (tasksOrderId == null) return false;
        return this.tasksOrderIds.contains(tasksOrderId);
    }

    public final int getTasksOrderIdsCount() {
        return this.tasksOrderIds.size();
    }

    public final boolean isTasksOrderIdsEmpty() {
        return this.tasksOrderIds.isEmpty();
    }

    public final boolean addTasksOrderId(java.lang.String tasksOrderId) {
        if (tasksOrderId == null) throw new IllegalArgumentException("tasksOrderId == null");
        boolean added = this.tasksOrderIds.add(tasksOrderId);
        if (added) fireModified("tasksOrderIds+=" + tasksOrderId);
        return added;
    }

    public final boolean addTasksOrderIds(Collection<java.lang.String> tasksOrderIds) {
        if (tasksOrderIds == null) throw new IllegalArgumentException("tasksOrderIds == null");
        boolean added = false;
        for (java.lang.String tasksOrderId : tasksOrderIds) {
            added = added | this.tasksOrderIds.add(tasksOrderId);
        }
        if (added) fireModified("tasksOrderIds+="+Str.format(tasksOrderIds));
        return added;
    }

    public final boolean removeTasksOrderId(java.lang.String tasksOrderId) {
        if (tasksOrderId == null) throw new IllegalArgumentException("tasksOrderId == null");
        if (this.tasksOrderIds == null) return false;
        boolean removed = this.tasksOrderIds.remove(tasksOrderId);
        if (removed) fireModified("tasksOrderIds-=" + tasksOrderId);
        return removed;
    }

    public final boolean removeTasksOrderIds(Collection<java.lang.String> tasksOrderIds) {
        if (tasksOrderIds == null) return false;
        if (tasksOrderIds.isEmpty()) return false;
        boolean removed = false;
        for (java.lang.String _element: tasksOrderIds) {
            removed = removed | removeTasksOrderId(_element);
        }
        if (removed) fireModified("tasksOrderIds-="+Str.format(tasksOrderIds));
        return removed;
    }

    public final boolean clearTasksOrderIds() {
        if (this.tasksOrderIds.isEmpty()) return false;
        this.tasksOrderIds.clear();
        fireModified("tasksOrderIds cleared");
        return true;
    }

    public final String getTasksOrderIdsAsCommaSeparatedString() {
        if (this.tasksOrderIds.isEmpty()) return null;
        return Str.concat(this.tasksOrderIds,", ");
    }

    public final void setTasksOrderIdsAsCommaSeparatedString(String tasksOrderIds) {
        this.tasksOrderIds = new java.util.ArrayList(Str.parseCommaSeparatedString(tasksOrderIds));
    }

    protected final void updateTasksOrderIds(Object value) {
        setTasksOrderIds((java.util.List<java.lang.String>) value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("projectId")) updateProject(value);
            if (property.equals("sprintId")) updateSprint(value);
            if (property.equals("issueId")) updateIssue(value);
            if (property.equals("number")) updateNumber(value);
            if (property.equals("qualitysIds")) updateQualitys(value);
            if (property.equals("label")) updateLabel(value);
            if (property.equals("description")) updateDescription(value);
            if (property.equals("testDescription")) updateTestDescription(value);
            if (property.equals("estimatedWork")) updateEstimatedWork(value);
            if (property.equals("rejectDate")) updateRejectDate(value);
            if (property.equals("closed")) updateClosed(value);
            if (property.equals("dirty")) updateDirty(value);
            if (property.equals("workEstimationVotingActive")) updateWorkEstimationVotingActive(value);
            if (property.equals("workEstimationVotingShowoff")) updateWorkEstimationVotingShowoff(value);
            if (property.equals("tasksOrderIds")) updateTasksOrderIds(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadProjectReference(entityId);
        repairDeadSprintReference(entityId);
        repairDeadIssueReference(entityId);
        if (this.qualitysIds == null) this.qualitysIds = new java.util.HashSet<String>();
        repairDeadQualityReference(entityId);
        if (this.tasksOrderIds == null) this.tasksOrderIds = new java.util.ArrayList<java.lang.String>();
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
            getSprint();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead sprint reference");
            repairDeadSprintReference(this.sprintId);
        }
        try {
            getIssue();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead issue reference");
            repairDeadIssueReference(this.issueId);
        }
        if (this.qualitysIds == null) this.qualitysIds = new java.util.HashSet<String>();
        Set<String> qualitys = new HashSet<String>(this.qualitysIds);
        for (String entityId : qualitys) {
            try {
                qualityDao.getById(entityId);
            } catch (EntityDoesNotExistException ex) {
                LOG.info("Repairing dead quality reference");
                repairDeadQualityReference(entityId);
            }
        }
        if (this.tasksOrderIds == null) this.tasksOrderIds = new java.util.ArrayList<java.lang.String>();
    }


    // -----------------------------------------------------------
    // - dependencies
    // -----------------------------------------------------------

    static scrum.server.project.ProjectDao projectDao;

    public static final void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        GRequirement.projectDao = projectDao;
    }

    static scrum.server.sprint.SprintDao sprintDao;

    public static final void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        GRequirement.sprintDao = sprintDao;
    }

    static scrum.server.issues.IssueDao issueDao;

    public static final void setIssueDao(scrum.server.issues.IssueDao issueDao) {
        GRequirement.issueDao = issueDao;
    }

    static scrum.server.project.QualityDao qualityDao;

    public static final void setQualityDao(scrum.server.project.QualityDao qualityDao) {
        GRequirement.qualityDao = qualityDao;
    }

    static RequirementDao requirementDao;

    public static final void setRequirementDao(RequirementDao requirementDao) {
        GRequirement.requirementDao = requirementDao;
    }

}