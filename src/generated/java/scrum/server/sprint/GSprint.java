









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
            extends AEntity {

    // --- AEntity ---

    public final SprintDao getDao() {
        return sprintDao;
    }

    protected void repairDeadValueObject(AValueObject valueObject) {
    }

    private static final Logger LOG = Logger.get(GSprint.class);

    public static final String TYPE = "sprint";

    // --- copy constructor ---
    public GSprint(GSprint template) {
        super(template);
        if (template==null) return;

        setLabel(template.getLabel());
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

    protected static SprintDao sprintDao;

    public static final void setSprintDao(SprintDao sprintDao) {
        GSprint.sprintDao = sprintDao;
    }

}
