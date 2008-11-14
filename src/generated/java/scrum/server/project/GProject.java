









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;

public abstract class GProject
            extends AEntity
            implements ilarkesto.search.Searchable {

    // --- AEntity ---

    public final ProjectDao getDao() {
        return projectDao;
    }

    protected void repairDeadValueObject(AValueObject valueObject) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("label", this.label);
        properties.put("currentSprintId", this.currentSprintId);
    }

    private static final Logger LOG = Logger.get(GProject.class);

    public static final String TYPE = "project";

    // --- copy constructor ---
    public GProject(GProject template) {
        super(template);
        if (template==null) return;

        setLabel(template.getLabel());
        setCurrentSprint(template.getCurrentSprint());
    }


    // -----------------------------------------------------------
    // - Searchable
    // -----------------------------------------------------------

    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getLabel(), key)) return true;
        return false;
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
    // - currentSprint
    // -----------------------------------------------------------

    private String currentSprintId;

    public final scrum.server.sprint.Sprint getCurrentSprint() {
        if (this.currentSprintId == null) return null;
        return (scrum.server.sprint.Sprint)sprintDao.getById(this.currentSprintId);
    }

    public final void setCurrentSprint(scrum.server.sprint.Sprint currentSprint) {
        currentSprint = prepareCurrentSprint(currentSprint);
        if (isCurrentSprint(currentSprint)) return;
        this.currentSprintId = currentSprint == null ? null : currentSprint.getId();
        entityModified();
    }

    protected scrum.server.sprint.Sprint prepareCurrentSprint(scrum.server.sprint.Sprint currentSprint) {
        return currentSprint;
    }

    protected void repairDeadCurrentSprintReference(String entityId) {
        if (entityId.equals(this.currentSprintId)) {
            this.currentSprintId = null;
            entityModified();
        }
    }

    public final boolean isCurrentSprintSet() {
        return this.currentSprintId != null;
    }

    public final boolean isCurrentSprint(scrum.server.sprint.Sprint currentSprint) {
        if (this.currentSprintId == null && currentSprint == null) return true;
        return currentSprint != null && currentSprint.getId().equals(this.currentSprintId);
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadCurrentSprintReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        try {
            getCurrentSprint();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead currentSprint reference");
            repairDeadCurrentSprintReference(this.currentSprintId);
        }
    }


    // -----------------------------------------------------------
    // - composites
    // -----------------------------------------------------------

    // --- dependencies ---

    protected static scrum.server.sprint.SprintDao sprintDao;

    public static final void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        GProject.sprintDao = sprintDao;
    }

    protected static ProjectDao projectDao;

    public static final void setProjectDao(ProjectDao projectDao) {
        GProject.projectDao = projectDao;
    }

}
