









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
            implements ilarkesto.search.Searchable, java.lang.Comparable<Project> {

    // --- AEntity ---

    public final ProjectDao getDao() {
        return projectDao;
    }

    protected void repairDeadValueObject(AValueObject valueObject) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("teamMembersIds", this.teamMembersIds);
        properties.put("adminsIds", this.adminsIds);
        properties.put("currentSprintId", this.currentSprintId);
        properties.put("description", this.description);
        properties.put("begin", this.begin == null ? null : this.begin.toString());
        properties.put("productOwnerId", this.productOwnerId);
        properties.put("scrumMasterId", this.scrumMasterId);
        properties.put("label", this.label);
        properties.put("end", this.end == null ? null : this.end.toString());
        properties.put("nextSprintId", this.nextSprintId);
    }

    public int compareTo(Project other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GProject.class);

    public static final String TYPE = "project";

    // --- copy constructor ---
    public GProject(GProject template) {
        super(template);
        if (template==null) return;

        setTeamMembers(template.getTeamMembers());
        setAdmins(template.getAdmins());
        setCurrentSprint(template.getCurrentSprint());
        setDescription(template.getDescription());
        setBegin(template.getBegin());
        setProductOwner(template.getProductOwner());
        setScrumMaster(template.getScrumMaster());
        setLabel(template.getLabel());
        setEnd(template.getEnd());
        setNextSprint(template.getNextSprint());
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
    // - teamMembers
    // -----------------------------------------------------------

    private java.util.Set<String> teamMembersIds = new java.util.HashSet<String>();

    public final java.util.Set<scrum.server.admin.User> getTeamMembers() {
        return (java.util.Set) userDao.getByIdsAsSet(this.teamMembersIds);
    }

    public final void setTeamMembers(java.util.Set<scrum.server.admin.User> teamMembers) {
        teamMembers = prepareTeamMembers(teamMembers);
        if (teamMembers == null) throw new IllegalArgumentException("null is not allowed");
        java.util.Set<String> ids = getIdsAsSet(teamMembers);
        if (this.teamMembersIds.equals(ids)) return;
        this.teamMembersIds = ids;
        entityModified();
    }

    protected java.util.Set<scrum.server.admin.User> prepareTeamMembers(java.util.Set<scrum.server.admin.User> teamMembers) {
        return teamMembers;
    }

    protected void repairDeadTeamMemberReference(String entityId) {
        if (this.teamMembersIds.remove(entityId)) entityModified();
    }

    public final boolean containsTeamMember(scrum.server.admin.User teamMember) {
        if (teamMember == null) return false;
        return this.teamMembersIds.contains(teamMember.getId());
    }

    public final int getTeamMembersCount() {
        return this.teamMembersIds.size();
    }

    public final boolean isTeamMembersEmpty() {
        return this.teamMembersIds.isEmpty();
    }

    public final boolean addTeamMember(scrum.server.admin.User teamMember) {
        if (teamMember == null) throw new IllegalArgumentException("teamMember == null");
        boolean added = this.teamMembersIds.add(teamMember.getId());
        if (added) entityModified();
        return added;
    }

    public final boolean addTeamMembers(Collection<scrum.server.admin.User> teamMembers) {
        if (teamMembers == null) throw new IllegalArgumentException("teamMembers == null");
        boolean added = false;
        for (scrum.server.admin.User teamMember : teamMembers) {
            added = added | this.teamMembersIds.add(teamMember.getId());
        }
        if (added) entityModified();
        return added;
    }

    public final boolean removeTeamMember(scrum.server.admin.User teamMember) {
        if (teamMember == null) throw new IllegalArgumentException("teamMember == null");
        if (this.teamMembersIds == null) return false;
        boolean removed = this.teamMembersIds.remove(teamMember.getId());
        if (removed) entityModified();
        return removed;
    }

    public final boolean removeTeamMembers(Collection<scrum.server.admin.User> teamMembers) {
        if (teamMembers == null) return false;
        if (teamMembers.isEmpty()) return false;
        boolean removed = false;
        for (scrum.server.admin.User _element: teamMembers) {
            removed = removed | removeTeamMember(_element);
        }
        return removed;
    }

    public final boolean clearTeamMembers() {
        if (this.teamMembersIds.isEmpty()) return false;
        this.teamMembersIds.clear();
        entityModified();
        return true;
    }

    // -----------------------------------------------------------
    // - admins
    // -----------------------------------------------------------

    private java.util.Set<String> adminsIds = new java.util.HashSet<String>();

    public final java.util.Set<scrum.server.admin.User> getAdmins() {
        return (java.util.Set) userDao.getByIdsAsSet(this.adminsIds);
    }

    public final void setAdmins(java.util.Set<scrum.server.admin.User> admins) {
        admins = prepareAdmins(admins);
        if (admins == null) throw new IllegalArgumentException("null is not allowed");
        java.util.Set<String> ids = getIdsAsSet(admins);
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
    // - productOwner
    // -----------------------------------------------------------

    private String productOwnerId;

    public final scrum.server.admin.User getProductOwner() {
        if (this.productOwnerId == null) return null;
        return (scrum.server.admin.User)userDao.getById(this.productOwnerId);
    }

    public final void setProductOwner(scrum.server.admin.User productOwner) {
        productOwner = prepareProductOwner(productOwner);
        if (isProductOwner(productOwner)) return;
        this.productOwnerId = productOwner == null ? null : productOwner.getId();
        entityModified();
    }

    protected scrum.server.admin.User prepareProductOwner(scrum.server.admin.User productOwner) {
        return productOwner;
    }

    protected void repairDeadProductOwnerReference(String entityId) {
        if (entityId.equals(this.productOwnerId)) {
            this.productOwnerId = null;
            entityModified();
        }
    }

    public final boolean isProductOwnerSet() {
        return this.productOwnerId != null;
    }

    public final boolean isProductOwner(scrum.server.admin.User productOwner) {
        if (this.productOwnerId == null && productOwner == null) return true;
        return productOwner != null && productOwner.getId().equals(this.productOwnerId);
    }

    // -----------------------------------------------------------
    // - scrumMaster
    // -----------------------------------------------------------

    private String scrumMasterId;

    public final scrum.server.admin.User getScrumMaster() {
        if (this.scrumMasterId == null) return null;
        return (scrum.server.admin.User)userDao.getById(this.scrumMasterId);
    }

    public final void setScrumMaster(scrum.server.admin.User scrumMaster) {
        scrumMaster = prepareScrumMaster(scrumMaster);
        if (isScrumMaster(scrumMaster)) return;
        this.scrumMasterId = scrumMaster == null ? null : scrumMaster.getId();
        entityModified();
    }

    protected scrum.server.admin.User prepareScrumMaster(scrum.server.admin.User scrumMaster) {
        return scrumMaster;
    }

    protected void repairDeadScrumMasterReference(String entityId) {
        if (entityId.equals(this.scrumMasterId)) {
            this.scrumMasterId = null;
            entityModified();
        }
    }

    public final boolean isScrumMasterSet() {
        return this.scrumMasterId != null;
    }

    public final boolean isScrumMaster(scrum.server.admin.User scrumMaster) {
        if (this.scrumMasterId == null && scrumMaster == null) return true;
        return scrumMaster != null && scrumMaster.getId().equals(this.scrumMasterId);
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

    // -----------------------------------------------------------
    // - nextSprint
    // -----------------------------------------------------------

    private String nextSprintId;

    public final scrum.server.sprint.Sprint getNextSprint() {
        if (this.nextSprintId == null) return null;
        return (scrum.server.sprint.Sprint)sprintDao.getById(this.nextSprintId);
    }

    public final void setNextSprint(scrum.server.sprint.Sprint nextSprint) {
        nextSprint = prepareNextSprint(nextSprint);
        if (isNextSprint(nextSprint)) return;
        this.nextSprintId = nextSprint == null ? null : nextSprint.getId();
        entityModified();
    }

    protected scrum.server.sprint.Sprint prepareNextSprint(scrum.server.sprint.Sprint nextSprint) {
        return nextSprint;
    }

    protected void repairDeadNextSprintReference(String entityId) {
        if (entityId.equals(this.nextSprintId)) {
            this.nextSprintId = null;
            entityModified();
        }
    }

    public final boolean isNextSprintSet() {
        return this.nextSprintId != null;
    }

    public final boolean isNextSprint(scrum.server.sprint.Sprint nextSprint) {
        if (this.nextSprintId == null && nextSprint == null) return true;
        return nextSprint != null && nextSprint.getId().equals(this.nextSprintId);
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        if (this.teamMembersIds == null) this.teamMembersIds = new java.util.HashSet<String>();
        repairDeadTeamMemberReference(entityId);
        if (this.adminsIds == null) this.adminsIds = new java.util.HashSet<String>();
        repairDeadAdminReference(entityId);
        repairDeadCurrentSprintReference(entityId);
        repairDeadProductOwnerReference(entityId);
        repairDeadScrumMasterReference(entityId);
        repairDeadNextSprintReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        if (this.teamMembersIds == null) this.teamMembersIds = new java.util.HashSet<String>();
        Set<String> teamMembers = new HashSet<String>(this.teamMembersIds);
        for (String entityId : teamMembers) {
            try {
                userDao.getById(entityId);
            } catch (EntityDoesNotExistException ex) {
                LOG.info("Repairing dead teamMember reference");
                repairDeadTeamMemberReference(entityId);
            }
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
        try {
            getCurrentSprint();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead currentSprint reference");
            repairDeadCurrentSprintReference(this.currentSprintId);
        }
        try {
            getProductOwner();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead productOwner reference");
            repairDeadProductOwnerReference(this.productOwnerId);
        }
        try {
            getScrumMaster();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead scrumMaster reference");
            repairDeadScrumMasterReference(this.scrumMasterId);
        }
        try {
            getNextSprint();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead nextSprint reference");
            repairDeadNextSprintReference(this.nextSprintId);
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
