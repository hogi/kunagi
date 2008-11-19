









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
        properties.put("adminsIds", this.adminsIds);
    }

    private static final Logger LOG = Logger.get(GProject.class);

    public static final String TYPE = "project";

    // --- copy constructor ---
    public GProject(GProject template) {
        super(template);
        if (template==null) return;

        setLabel(template.getLabel());
        setCurrentSprint(template.getCurrentSprint());
        setAdmins(template.getAdmins());
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

    // -----------------------------------------------------------
    // - admins
    // -----------------------------------------------------------

    private java.util.Set<String> adminsIds = new java.util.HashSet<String>();

    public final java.util.Set<scrum.server.admin.User> getAdmins() {
        return (Set)userDao.getByIds(this.adminsIds);
    }

    public final void setAdmins(java.util.Set<scrum.server.admin.User> admins) {
        admins = prepareAdmins(admins);
        if (admins == null) throw new IllegalArgumentException("null is not allowed");
        java.util.Set<String> ids = getIds(admins);
        if (this.adminsIds.equals(ids)) return;
        this.adminsIds = ids;
        entityModified();
    }

    protected java.util.Set<scrum.server.admin.User> prepareAdmins(java.util.Set<scrum.server.admin.User> admins) {
        return admins;
    }

    protected void repairDeadAdminReference(String entityId) {
        if (this.adminsIds.remove(entityId)) entityModified();
    }

    public final boolean containsAdmin(scrum.server.admin.User admin) {
        if (admin == null) return false;
        return this.adminsIds.contains(admin.getId());
    }

    public final int getAdminsCount() {
        return this.adminsIds.size();
    }

    public final boolean isAdminsEmpty() {
        return this.adminsIds.isEmpty();
    }

    public final boolean addAdmin(scrum.server.admin.User admin) {
        if (admin == null) throw new IllegalArgumentException("admin == null");
        boolean added = this.adminsIds.add(admin.getId());
        if (added) entityModified();
        return added;
    }

    public final boolean addAdmins(Collection<scrum.server.admin.User> admins) {
        if (admins == null) throw new IllegalArgumentException("admins == null");
        boolean added = false;
        for (scrum.server.admin.User admin : admins) {
            added = added | this.adminsIds.add(admin.getId());
        }
        if (added) entityModified();
        return added;
    }

    public final boolean removeAdmin(scrum.server.admin.User admin) {
        if (admin == null) throw new IllegalArgumentException("admin == null");
        if (this.adminsIds == null) return false;
        boolean removed = this.adminsIds.remove(admin.getId());
        if (removed) entityModified();
        return removed;
    }

    public final boolean removeAdmins(Collection<scrum.server.admin.User> admins) {
        if (admins == null) return false;
        if (admins.isEmpty()) return false;
        boolean removed = false;
        for (scrum.server.admin.User _element: admins) {
            removed = removed | removeAdmin(_element);
        }
        return removed;
    }

    public final boolean clearAdmins() {
        if (this.adminsIds.isEmpty()) return false;
        this.adminsIds.clear();
        entityModified();
        return true;
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadCurrentSprintReference(entityId);
        if (this.adminsIds == null) this.adminsIds = new java.util.HashSet<String>();
        repairDeadAdminReference(entityId);
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
        if (this.adminsIds == null) this.adminsIds = new java.util.HashSet<String>();
        Set<String> admins = new HashSet<String>(this.adminsIds);
        for (String entityId : admins) {
            try {
                userDao.getById(entityId);
            } catch (EntityDoesNotExistException ex) {
                LOG.info("Repairing dead admin reference");
                repairDeadAdminReference(entityId);
            }
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
