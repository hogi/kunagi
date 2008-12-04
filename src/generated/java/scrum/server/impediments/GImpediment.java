









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.impediments;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;

public abstract class GImpediment
            extends AEntity
            implements java.lang.Comparable<Impediment> {

    // --- AEntity ---

    public final ImpedimentDao getDao() {
        return impedimentDao;
    }

    protected void repairDeadValueObject(AValueObject valueObject) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("solution", this.solution);
        properties.put("solved", this.solved);
        properties.put("description", this.description);
        properties.put("label", this.label);
        properties.put("projectId", this.projectId);
    }

    public int compareTo(Impediment other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GImpediment.class);

    public static final String TYPE = "impediment";

    // --- copy constructor ---
    public GImpediment(GImpediment template) {
        super(template);
        if (template==null) return;

        setSolution(template.getSolution());
        setSolved(template.isSolved());
        setDescription(template.getDescription());
        setLabel(template.getLabel());
        setProject(template.getProject());
    }

    // -----------------------------------------------------------
    // - solution
    // -----------------------------------------------------------

    private java.lang.String solution;

    public final java.lang.String getSolution() {
        return solution;
    }

    public final void setSolution(java.lang.String solution) {
        solution = prepareSolution(solution);
        if (isSolution(solution)) return;
        this.solution = solution;
        entityModified();
    }

    protected java.lang.String prepareSolution(java.lang.String solution) {
        solution = Str.removeUnreadableChars(solution);
        return solution;
    }

    public final boolean isSolutionSet() {
        return this.solution != null;
    }

    public final boolean isSolution(java.lang.String solution) {
        if (this.solution == null && solution == null) return true;
        return this.solution != null && this.solution.equals(solution);
    }

    // -----------------------------------------------------------
    // - solved
    // -----------------------------------------------------------

    private boolean solved;

    public final boolean isSolved() {
        return solved;
    }

    public final void setSolved(boolean solved) {
        solved = prepareSolved(solved);
        if (isSolved(solved)) return;
        this.solved = solved;
        entityModified();
    }

    protected boolean prepareSolved(boolean solved) {
        return solved;
    }

    public final boolean isSolved(boolean solved) {
        return this.solved == solved;
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
        entityModified();
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
        GImpediment.projectDao = projectDao;
    }

    protected static ImpedimentDao impedimentDao;

    public static final void setImpedimentDao(ImpedimentDao impedimentDao) {
        GImpediment.impedimentDao = impedimentDao;
    }

}
