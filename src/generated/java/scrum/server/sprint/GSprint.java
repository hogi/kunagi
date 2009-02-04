









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.sprint;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;

public abstract class GSprint
            extends AEntity
            implements java.lang.Comparable<Sprint> {

    // --- AEntity ---

    public final SprintDao getDao() {
        return sprintDao;
    }

    protected void repairDeadValueObject(AValueObject valueObject) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("begin", this.begin == null ? null : this.begin.toString());
        properties.put("goal", this.goal);
        properties.put("label", this.label);
        properties.put("end", this.end == null ? null : this.end.toString());
    }

    public int compareTo(Sprint other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GSprint.class);

    public static final String TYPE = "sprint";

    // --- copy constructor ---
    public GSprint(GSprint template) {
        super(template);
        if (template==null) return;

        setProject(template.getProject());
        setBegin(template.getBegin());
        setGoal(template.getGoal());
        setLabel(template.getLabel());
        setEnd(template.getEnd());
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private String projectId;

    public final scrum.server.project.Project getProject() {
        if (this.projectId == null) return null;
        return (scrum.server.project.Project)projectDao.getById(this.projectId);
    }

    public final void setProject(scrum.server.project.Project project) {
        project = prepareProject(project);
        if (isProject(project)) return;
        this.projectId = project == null ? null : project.getId();
        entityModified();
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
        entityModified();
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
        entityModified();
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
        entityModified();
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
        entityModified();
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
    // - composites
    // -----------------------------------------------------------

    // --- dependencies ---

    protected static scrum.server.project.ProjectDao projectDao;

    public static final void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        GSprint.projectDao = projectDao;
    }

    protected static SprintDao sprintDao;

    public static final void setSprintDao(SprintDao sprintDao) {
        GSprint.sprintDao = sprintDao;
    }

}
