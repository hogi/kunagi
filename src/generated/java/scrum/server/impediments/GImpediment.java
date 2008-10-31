









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
            extends AEntity {

    // --- AEntity ---

    public final ImpedimentDao getDao() {
        return impedimentDao;
    }

    protected void repairDeadValueObject(AValueObject valueObject) {
    }

    private static final Logger LOG = Logger.get(GImpediment.class);

    public static final String TYPE = "impediment";

    // --- copy constructor ---
    public GImpediment(GImpediment template) {
        super(template);
        if (template==null) return;

        setSolution(template.getSolution());
        setSolved(template.isSolved());
        setLabel(template.getLabel());
        setDescription(template.getDescription());
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

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
    }


    // -----------------------------------------------------------
    // - composites
    // -----------------------------------------------------------

    // --- dependencies ---

    protected static ImpedimentDao impedimentDao;

    public static final void setImpedimentDao(ImpedimentDao impedimentDao) {
        GImpediment.impedimentDao = impedimentDao;
    }

}
