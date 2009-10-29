// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.sprint;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GSprint
            extends AEntity
            implements java.lang.Comparable<Sprint> {

    // --- AEntity ---

    public final SprintDao getDao() {
        return sprintDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("label", this.label);
        properties.put("goal", this.goal);
        properties.put("begin", this.begin == null ? null : this.begin.toString());
        properties.put("end", this.end == null ? null : this.end.toString());
        properties.put("velocity", this.velocity);
        properties.put("completedRequirementLabels", this.completedRequirementLabels);
        properties.put("reviewNote", this.reviewNote);
        properties.put("retrospectiveNote", this.retrospectiveNote);
    }

    public int compareTo(Sprint other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GSprint.class);

    public static final String TYPE = "sprint";

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
    // - goal
    // -----------------------------------------------------------

    private java.lang.String goal;

    public final java.lang.String getGoal() {
        return goal;
    }

    public final void setGoal(java.lang.String goal) {
        goal = prepareGoal(goal);
        if (isGoal(goal)) return;
        this.goal = goal;
        fireModified();
    }

    protected java.lang.String prepareGoal(java.lang.String goal) {
        goal = Str.removeUnreadableChars(goal);
        return goal;
    }

    public final boolean isGoalSet() {
        return this.goal != null;
    }

    public final boolean isGoal(java.lang.String goal) {
        if (this.goal == null && goal == null) return true;
        return this.goal != null && this.goal.equals(goal);
    }

    protected final void updateGoal(Object value) {
        setGoal((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - begin
    // -----------------------------------------------------------

    private ilarkesto.base.time.Date begin;

    public final ilarkesto.base.time.Date getBegin() {
        return begin;
    }

    public final void setBegin(ilarkesto.base.time.Date begin) {
        begin = prepareBegin(begin);
        if (isBegin(begin)) return;
        this.begin = begin;
        fireModified();
    }

    protected ilarkesto.base.time.Date prepareBegin(ilarkesto.base.time.Date begin) {
        return begin;
    }

    public final boolean isBeginSet() {
        return this.begin != null;
    }

    public final boolean isBegin(ilarkesto.base.time.Date begin) {
        if (this.begin == null && begin == null) return true;
        return this.begin != null && this.begin.equals(begin);
    }

    protected final void updateBegin(Object value) {
        value = value == null ? null : new ilarkesto.base.time.Date((String)value);
        setBegin((ilarkesto.base.time.Date)value);
    }

    // -----------------------------------------------------------
    // - end
    // -----------------------------------------------------------

    private ilarkesto.base.time.Date end;

    public final ilarkesto.base.time.Date getEnd() {
        return end;
    }

    public final void setEnd(ilarkesto.base.time.Date end) {
        end = prepareEnd(end);
        if (isEnd(end)) return;
        this.end = end;
        fireModified();
    }

    protected ilarkesto.base.time.Date prepareEnd(ilarkesto.base.time.Date end) {
        return end;
    }

    public final boolean isEndSet() {
        return this.end != null;
    }

    public final boolean isEnd(ilarkesto.base.time.Date end) {
        if (this.end == null && end == null) return true;
        return this.end != null && this.end.equals(end);
    }

    protected final void updateEnd(Object value) {
        value = value == null ? null : new ilarkesto.base.time.Date((String)value);
        setEnd((ilarkesto.base.time.Date)value);
    }

    // -----------------------------------------------------------
    // - velocity
    // -----------------------------------------------------------

    private java.lang.Integer velocity;

    public final java.lang.Integer getVelocity() {
        return velocity;
    }

    public final void setVelocity(java.lang.Integer velocity) {
        velocity = prepareVelocity(velocity);
        if (isVelocity(velocity)) return;
        this.velocity = velocity;
        fireModified();
    }

    protected java.lang.Integer prepareVelocity(java.lang.Integer velocity) {
        return velocity;
    }

    public final boolean isVelocitySet() {
        return this.velocity != null;
    }

    public final boolean isVelocity(java.lang.Integer velocity) {
        if (this.velocity == null && velocity == null) return true;
        return this.velocity != null && this.velocity.equals(velocity);
    }

    protected final void updateVelocity(Object value) {
        setVelocity((java.lang.Integer)value);
    }

    // -----------------------------------------------------------
    // - completedRequirementLabels
    // -----------------------------------------------------------

    private java.lang.String completedRequirementLabels;

    public final java.lang.String getCompletedRequirementLabels() {
        return completedRequirementLabels;
    }

    public final void setCompletedRequirementLabels(java.lang.String completedRequirementLabels) {
        completedRequirementLabels = prepareCompletedRequirementLabels(completedRequirementLabels);
        if (isCompletedRequirementLabels(completedRequirementLabels)) return;
        this.completedRequirementLabels = completedRequirementLabels;
        fireModified();
    }

    protected java.lang.String prepareCompletedRequirementLabels(java.lang.String completedRequirementLabels) {
        completedRequirementLabels = Str.removeUnreadableChars(completedRequirementLabels);
        return completedRequirementLabels;
    }

    public final boolean isCompletedRequirementLabelsSet() {
        return this.completedRequirementLabels != null;
    }

    public final boolean isCompletedRequirementLabels(java.lang.String completedRequirementLabels) {
        if (this.completedRequirementLabels == null && completedRequirementLabels == null) return true;
        return this.completedRequirementLabels != null && this.completedRequirementLabels.equals(completedRequirementLabels);
    }

    protected final void updateCompletedRequirementLabels(Object value) {
        setCompletedRequirementLabels((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - reviewNote
    // -----------------------------------------------------------

    private java.lang.String reviewNote;

    public final java.lang.String getReviewNote() {
        return reviewNote;
    }

    public final void setReviewNote(java.lang.String reviewNote) {
        reviewNote = prepareReviewNote(reviewNote);
        if (isReviewNote(reviewNote)) return;
        this.reviewNote = reviewNote;
        fireModified();
    }

    protected java.lang.String prepareReviewNote(java.lang.String reviewNote) {
        reviewNote = Str.removeUnreadableChars(reviewNote);
        return reviewNote;
    }

    public final boolean isReviewNoteSet() {
        return this.reviewNote != null;
    }

    public final boolean isReviewNote(java.lang.String reviewNote) {
        if (this.reviewNote == null && reviewNote == null) return true;
        return this.reviewNote != null && this.reviewNote.equals(reviewNote);
    }

    protected final void updateReviewNote(Object value) {
        setReviewNote((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - retrospectiveNote
    // -----------------------------------------------------------

    private java.lang.String retrospectiveNote;

    public final java.lang.String getRetrospectiveNote() {
        return retrospectiveNote;
    }

    public final void setRetrospectiveNote(java.lang.String retrospectiveNote) {
        retrospectiveNote = prepareRetrospectiveNote(retrospectiveNote);
        if (isRetrospectiveNote(retrospectiveNote)) return;
        this.retrospectiveNote = retrospectiveNote;
        fireModified();
    }

    protected java.lang.String prepareRetrospectiveNote(java.lang.String retrospectiveNote) {
        retrospectiveNote = Str.removeUnreadableChars(retrospectiveNote);
        return retrospectiveNote;
    }

    public final boolean isRetrospectiveNoteSet() {
        return this.retrospectiveNote != null;
    }

    public final boolean isRetrospectiveNote(java.lang.String retrospectiveNote) {
        if (this.retrospectiveNote == null && retrospectiveNote == null) return true;
        return this.retrospectiveNote != null && this.retrospectiveNote.equals(retrospectiveNote);
    }

    protected final void updateRetrospectiveNote(Object value) {
        setRetrospectiveNote((java.lang.String)value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("projectId")) updateProject(value);
            if (property.equals("label")) updateLabel(value);
            if (property.equals("goal")) updateGoal(value);
            if (property.equals("begin")) updateBegin(value);
            if (property.equals("end")) updateEnd(value);
            if (property.equals("velocity")) updateVelocity(value);
            if (property.equals("completedRequirementLabels")) updateCompletedRequirementLabels(value);
            if (property.equals("reviewNote")) updateReviewNote(value);
            if (property.equals("retrospectiveNote")) updateRetrospectiveNote(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadProjectReference(entityId);
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
    }


    // -----------------------------------------------------------
    // - dependencies
    // -----------------------------------------------------------

    static scrum.server.project.ProjectDao projectDao;

    public static final void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        GSprint.projectDao = projectDao;
    }

    static SprintDao sprintDao;

    public static final void setSprintDao(SprintDao sprintDao) {
        GSprint.sprintDao = sprintDao;
    }

}