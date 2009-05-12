// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GProject
            extends AEntity
            implements ilarkesto.auth.ViewProtected<scrum.server.admin.User>, ilarkesto.auth.DeleteProtected<scrum.server.admin.User>, ilarkesto.search.Searchable, java.lang.Comparable<Project> {

    // --- AEntity ---

    public final ProjectDao getDao() {
        return projectDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("label", this.label);
        properties.put("description", this.description);
        properties.put("begin", this.begin == null ? null : this.begin.toString());
        properties.put("end", this.end == null ? null : this.end.toString());
        properties.put("participantsIds", this.participantsIds);
        properties.put("adminsIds", this.adminsIds);
        properties.put("productOwnersIds", this.productOwnersIds);
        properties.put("scrumMastersIds", this.scrumMastersIds);
        properties.put("teamMembersIds", this.teamMembersIds);
        properties.put("currentSprintId", this.currentSprintId);
        properties.put("nextSprintId", this.nextSprintId);
        properties.put("requirementsOrderIds", this.requirementsOrderIds);
    }

    public int compareTo(Project other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GProject.class);

    public static final String TYPE = "project";


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
    // - participants
    // -----------------------------------------------------------

    private java.util.Set<String> participantsIds = new java.util.HashSet<String>();

    public final java.util.Set<scrum.server.admin.User> getParticipants() {
        return (java.util.Set) userDao.getByIdsAsSet(this.participantsIds);
    }

    public final void setParticipants(Collection<scrum.server.admin.User> participants) {
        participants = prepareParticipants(participants);
        if (participants == null) participants = Collections.emptyList();
        java.util.Set<String> ids = getIdsAsSet(participants);
        if (this.participantsIds.equals(ids)) return;
        this.participantsIds = ids;
        fireModified();
    }

    protected Collection<scrum.server.admin.User> prepareParticipants(Collection<scrum.server.admin.User> participants) {
        return participants;
    }

    protected void repairDeadParticipantReference(String entityId) {
        if (this.participantsIds.remove(entityId)) fireModified();
    }

    public final boolean containsParticipant(scrum.server.admin.User participant) {
        if (participant == null) return false;
        return this.participantsIds.contains(participant.getId());
    }

    public final int getParticipantsCount() {
        return this.participantsIds.size();
    }

    public final boolean isParticipantsEmpty() {
        return this.participantsIds.isEmpty();
    }

    public final boolean addParticipant(scrum.server.admin.User participant) {
        if (participant == null) throw new IllegalArgumentException("participant == null");
        boolean added = this.participantsIds.add(participant.getId());
        if (added) fireModified();
        return added;
    }

    public final boolean addParticipants(Collection<scrum.server.admin.User> participants) {
        if (participants == null) throw new IllegalArgumentException("participants == null");
        boolean added = false;
        for (scrum.server.admin.User participant : participants) {
            added = added | this.participantsIds.add(participant.getId());
        }
        if (added) fireModified();
        return added;
    }

    public final boolean removeParticipant(scrum.server.admin.User participant) {
        if (participant == null) throw new IllegalArgumentException("participant == null");
        if (this.participantsIds == null) return false;
        boolean removed = this.participantsIds.remove(participant.getId());
        if (removed) fireModified();
        return removed;
    }

    public final boolean removeParticipants(Collection<scrum.server.admin.User> participants) {
        if (participants == null) return false;
        if (participants.isEmpty()) return false;
        boolean removed = false;
        for (scrum.server.admin.User _element: participants) {
            removed = removed | removeParticipant(_element);
        }
        return removed;
    }

    public final boolean clearParticipants() {
        if (this.participantsIds.isEmpty()) return false;
        this.participantsIds.clear();
        fireModified();
        return true;
    }

    protected final void updateParticipants(Object value) {
        Collection<String> ids = (Collection<String>) value;
        setParticipants((java.util.Set) userDao.getByIdsAsSet(ids));
    }

    // -----------------------------------------------------------
    // - admins
    // -----------------------------------------------------------

    private java.util.Set<String> adminsIds = new java.util.HashSet<String>();

    public final java.util.Set<scrum.server.admin.User> getAdmins() {
        return (java.util.Set) userDao.getByIdsAsSet(this.adminsIds);
    }

    public final void setAdmins(Collection<scrum.server.admin.User> admins) {
        admins = prepareAdmins(admins);
        if (admins == null) admins = Collections.emptyList();
        java.util.Set<String> ids = getIdsAsSet(admins);
        if (this.adminsIds.equals(ids)) return;
        this.adminsIds = ids;
        fireModified();
    }

    protected Collection<scrum.server.admin.User> prepareAdmins(Collection<scrum.server.admin.User> admins) {
        return admins;
    }

    protected void repairDeadAdminReference(String entityId) {
        if (this.adminsIds.remove(entityId)) fireModified();
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
        if (added) fireModified();
        return added;
    }

    public final boolean addAdmins(Collection<scrum.server.admin.User> admins) {
        if (admins == null) throw new IllegalArgumentException("admins == null");
        boolean added = false;
        for (scrum.server.admin.User admin : admins) {
            added = added | this.adminsIds.add(admin.getId());
        }
        if (added) fireModified();
        return added;
    }

    public final boolean removeAdmin(scrum.server.admin.User admin) {
        if (admin == null) throw new IllegalArgumentException("admin == null");
        if (this.adminsIds == null) return false;
        boolean removed = this.adminsIds.remove(admin.getId());
        if (removed) fireModified();
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
        fireModified();
        return true;
    }

    protected final void updateAdmins(Object value) {
        Collection<String> ids = (Collection<String>) value;
        setAdmins((java.util.Set) userDao.getByIdsAsSet(ids));
    }

    // -----------------------------------------------------------
    // - productOwners
    // -----------------------------------------------------------

    private java.util.Set<String> productOwnersIds = new java.util.HashSet<String>();

    public final java.util.Set<scrum.server.admin.User> getProductOwners() {
        return (java.util.Set) userDao.getByIdsAsSet(this.productOwnersIds);
    }

    public final void setProductOwners(Collection<scrum.server.admin.User> productOwners) {
        productOwners = prepareProductOwners(productOwners);
        if (productOwners == null) productOwners = Collections.emptyList();
        java.util.Set<String> ids = getIdsAsSet(productOwners);
        if (this.productOwnersIds.equals(ids)) return;
        this.productOwnersIds = ids;
        fireModified();
    }

    protected Collection<scrum.server.admin.User> prepareProductOwners(Collection<scrum.server.admin.User> productOwners) {
        return productOwners;
    }

    protected void repairDeadProductOwnerReference(String entityId) {
        if (this.productOwnersIds.remove(entityId)) fireModified();
    }

    public final boolean containsProductOwner(scrum.server.admin.User productOwner) {
        if (productOwner == null) return false;
        return this.productOwnersIds.contains(productOwner.getId());
    }

    public final int getProductOwnersCount() {
        return this.productOwnersIds.size();
    }

    public final boolean isProductOwnersEmpty() {
        return this.productOwnersIds.isEmpty();
    }

    public final boolean addProductOwner(scrum.server.admin.User productOwner) {
        if (productOwner == null) throw new IllegalArgumentException("productOwner == null");
        boolean added = this.productOwnersIds.add(productOwner.getId());
        if (added) fireModified();
        return added;
    }

    public final boolean addProductOwners(Collection<scrum.server.admin.User> productOwners) {
        if (productOwners == null) throw new IllegalArgumentException("productOwners == null");
        boolean added = false;
        for (scrum.server.admin.User productOwner : productOwners) {
            added = added | this.productOwnersIds.add(productOwner.getId());
        }
        if (added) fireModified();
        return added;
    }

    public final boolean removeProductOwner(scrum.server.admin.User productOwner) {
        if (productOwner == null) throw new IllegalArgumentException("productOwner == null");
        if (this.productOwnersIds == null) return false;
        boolean removed = this.productOwnersIds.remove(productOwner.getId());
        if (removed) fireModified();
        return removed;
    }

    public final boolean removeProductOwners(Collection<scrum.server.admin.User> productOwners) {
        if (productOwners == null) return false;
        if (productOwners.isEmpty()) return false;
        boolean removed = false;
        for (scrum.server.admin.User _element: productOwners) {
            removed = removed | removeProductOwner(_element);
        }
        return removed;
    }

    public final boolean clearProductOwners() {
        if (this.productOwnersIds.isEmpty()) return false;
        this.productOwnersIds.clear();
        fireModified();
        return true;
    }

    protected final void updateProductOwners(Object value) {
        Collection<String> ids = (Collection<String>) value;
        setProductOwners((java.util.Set) userDao.getByIdsAsSet(ids));
    }

    // -----------------------------------------------------------
    // - scrumMasters
    // -----------------------------------------------------------

    private java.util.Set<String> scrumMastersIds = new java.util.HashSet<String>();

    public final java.util.Set<scrum.server.admin.User> getScrumMasters() {
        return (java.util.Set) userDao.getByIdsAsSet(this.scrumMastersIds);
    }

    public final void setScrumMasters(Collection<scrum.server.admin.User> scrumMasters) {
        scrumMasters = prepareScrumMasters(scrumMasters);
        if (scrumMasters == null) scrumMasters = Collections.emptyList();
        java.util.Set<String> ids = getIdsAsSet(scrumMasters);
        if (this.scrumMastersIds.equals(ids)) return;
        this.scrumMastersIds = ids;
        fireModified();
    }

    protected Collection<scrum.server.admin.User> prepareScrumMasters(Collection<scrum.server.admin.User> scrumMasters) {
        return scrumMasters;
    }

    protected void repairDeadScrumMasterReference(String entityId) {
        if (this.scrumMastersIds.remove(entityId)) fireModified();
    }

    public final boolean containsScrumMaster(scrum.server.admin.User scrumMaster) {
        if (scrumMaster == null) return false;
        return this.scrumMastersIds.contains(scrumMaster.getId());
    }

    public final int getScrumMastersCount() {
        return this.scrumMastersIds.size();
    }

    public final boolean isScrumMastersEmpty() {
        return this.scrumMastersIds.isEmpty();
    }

    public final boolean addScrumMaster(scrum.server.admin.User scrumMaster) {
        if (scrumMaster == null) throw new IllegalArgumentException("scrumMaster == null");
        boolean added = this.scrumMastersIds.add(scrumMaster.getId());
        if (added) fireModified();
        return added;
    }

    public final boolean addScrumMasters(Collection<scrum.server.admin.User> scrumMasters) {
        if (scrumMasters == null) throw new IllegalArgumentException("scrumMasters == null");
        boolean added = false;
        for (scrum.server.admin.User scrumMaster : scrumMasters) {
            added = added | this.scrumMastersIds.add(scrumMaster.getId());
        }
        if (added) fireModified();
        return added;
    }

    public final boolean removeScrumMaster(scrum.server.admin.User scrumMaster) {
        if (scrumMaster == null) throw new IllegalArgumentException("scrumMaster == null");
        if (this.scrumMastersIds == null) return false;
        boolean removed = this.scrumMastersIds.remove(scrumMaster.getId());
        if (removed) fireModified();
        return removed;
    }

    public final boolean removeScrumMasters(Collection<scrum.server.admin.User> scrumMasters) {
        if (scrumMasters == null) return false;
        if (scrumMasters.isEmpty()) return false;
        boolean removed = false;
        for (scrum.server.admin.User _element: scrumMasters) {
            removed = removed | removeScrumMaster(_element);
        }
        return removed;
    }

    public final boolean clearScrumMasters() {
        if (this.scrumMastersIds.isEmpty()) return false;
        this.scrumMastersIds.clear();
        fireModified();
        return true;
    }

    protected final void updateScrumMasters(Object value) {
        Collection<String> ids = (Collection<String>) value;
        setScrumMasters((java.util.Set) userDao.getByIdsAsSet(ids));
    }

    // -----------------------------------------------------------
    // - teamMembers
    // -----------------------------------------------------------

    private java.util.Set<String> teamMembersIds = new java.util.HashSet<String>();

    public final java.util.Set<scrum.server.admin.User> getTeamMembers() {
        return (java.util.Set) userDao.getByIdsAsSet(this.teamMembersIds);
    }

    public final void setTeamMembers(Collection<scrum.server.admin.User> teamMembers) {
        teamMembers = prepareTeamMembers(teamMembers);
        if (teamMembers == null) teamMembers = Collections.emptyList();
        java.util.Set<String> ids = getIdsAsSet(teamMembers);
        if (this.teamMembersIds.equals(ids)) return;
        this.teamMembersIds = ids;
        fireModified();
    }

    protected Collection<scrum.server.admin.User> prepareTeamMembers(Collection<scrum.server.admin.User> teamMembers) {
        return teamMembers;
    }

    protected void repairDeadTeamMemberReference(String entityId) {
        if (this.teamMembersIds.remove(entityId)) fireModified();
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
        if (added) fireModified();
        return added;
    }

    public final boolean addTeamMembers(Collection<scrum.server.admin.User> teamMembers) {
        if (teamMembers == null) throw new IllegalArgumentException("teamMembers == null");
        boolean added = false;
        for (scrum.server.admin.User teamMember : teamMembers) {
            added = added | this.teamMembersIds.add(teamMember.getId());
        }
        if (added) fireModified();
        return added;
    }

    public final boolean removeTeamMember(scrum.server.admin.User teamMember) {
        if (teamMember == null) throw new IllegalArgumentException("teamMember == null");
        if (this.teamMembersIds == null) return false;
        boolean removed = this.teamMembersIds.remove(teamMember.getId());
        if (removed) fireModified();
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
        fireModified();
        return true;
    }

    protected final void updateTeamMembers(Object value) {
        Collection<String> ids = (Collection<String>) value;
        setTeamMembers((java.util.Set) userDao.getByIdsAsSet(ids));
    }

    // -----------------------------------------------------------
    // - currentSprint
    // -----------------------------------------------------------

    private String currentSprintId;
    private transient scrum.server.sprint.Sprint currentSprintCache;

    private void updateCurrentSprintCache() {
        currentSprintCache = this.currentSprintId == null ? null : (scrum.server.sprint.Sprint)sprintDao.getById(this.currentSprintId);
    }

    public final scrum.server.sprint.Sprint getCurrentSprint() {
        if (currentSprintCache == null) updateCurrentSprintCache();
        return currentSprintCache;
    }

    public final void setCurrentSprint(scrum.server.sprint.Sprint currentSprint) {
        currentSprint = prepareCurrentSprint(currentSprint);
        if (isCurrentSprint(currentSprint)) return;
        this.currentSprintId = currentSprint == null ? null : currentSprint.getId();
        currentSprintCache = currentSprint;
        fireModified();
    }

    protected scrum.server.sprint.Sprint prepareCurrentSprint(scrum.server.sprint.Sprint currentSprint) {
        return currentSprint;
    }

    protected void repairDeadCurrentSprintReference(String entityId) {
        if (entityId.equals(this.currentSprintId)) {
            this.currentSprintId = null;
            fireModified();
        }
    }

    public final boolean isCurrentSprintSet() {
        return this.currentSprintId != null;
    }

    public final boolean isCurrentSprint(scrum.server.sprint.Sprint currentSprint) {
        if (this.currentSprintId == null && currentSprint == null) return true;
        return currentSprint != null && currentSprint.getId().equals(this.currentSprintId);
    }

    protected final void updateCurrentSprint(Object value) {
        setCurrentSprint(value == null ? null : (scrum.server.sprint.Sprint)sprintDao.getById((String)value));
    }

    // -----------------------------------------------------------
    // - nextSprint
    // -----------------------------------------------------------

    private String nextSprintId;
    private transient scrum.server.sprint.Sprint nextSprintCache;

    private void updateNextSprintCache() {
        nextSprintCache = this.nextSprintId == null ? null : (scrum.server.sprint.Sprint)sprintDao.getById(this.nextSprintId);
    }

    public final scrum.server.sprint.Sprint getNextSprint() {
        if (nextSprintCache == null) updateNextSprintCache();
        return nextSprintCache;
    }

    public final void setNextSprint(scrum.server.sprint.Sprint nextSprint) {
        nextSprint = prepareNextSprint(nextSprint);
        if (isNextSprint(nextSprint)) return;
        this.nextSprintId = nextSprint == null ? null : nextSprint.getId();
        nextSprintCache = nextSprint;
        fireModified();
    }

    protected scrum.server.sprint.Sprint prepareNextSprint(scrum.server.sprint.Sprint nextSprint) {
        return nextSprint;
    }

    protected void repairDeadNextSprintReference(String entityId) {
        if (entityId.equals(this.nextSprintId)) {
            this.nextSprintId = null;
            fireModified();
        }
    }

    public final boolean isNextSprintSet() {
        return this.nextSprintId != null;
    }

    public final boolean isNextSprint(scrum.server.sprint.Sprint nextSprint) {
        if (this.nextSprintId == null && nextSprint == null) return true;
        return nextSprint != null && nextSprint.getId().equals(this.nextSprintId);
    }

    protected final void updateNextSprint(Object value) {
        setNextSprint(value == null ? null : (scrum.server.sprint.Sprint)sprintDao.getById((String)value));
    }

    // -----------------------------------------------------------
    // - requirementsOrderIds
    // -----------------------------------------------------------

    private java.util.List<java.lang.String> requirementsOrderIds = new java.util.ArrayList<java.lang.String>();

    public final java.util.List<java.lang.String> getRequirementsOrderIds() {
        return new java.util.ArrayList<java.lang.String>(requirementsOrderIds);
    }

    public final void setRequirementsOrderIds(Collection<java.lang.String> requirementsOrderIds) {
        requirementsOrderIds = prepareRequirementsOrderIds(requirementsOrderIds);
        if (requirementsOrderIds == null) requirementsOrderIds = Collections.emptyList();
        if (this.requirementsOrderIds.equals(requirementsOrderIds)) return;
        this.requirementsOrderIds = new java.util.ArrayList<java.lang.String>(requirementsOrderIds);
        fireModified();
    }

    protected Collection<java.lang.String> prepareRequirementsOrderIds(Collection<java.lang.String> requirementsOrderIds) {
        return requirementsOrderIds;
    }

    public final boolean containsRequirementsOrderId(java.lang.String requirementsOrderId) {
        if (requirementsOrderId == null) return false;
        return this.requirementsOrderIds.contains(requirementsOrderId);
    }

    public final int getRequirementsOrderIdsCount() {
        return this.requirementsOrderIds.size();
    }

    public final boolean isRequirementsOrderIdsEmpty() {
        return this.requirementsOrderIds.isEmpty();
    }

    public final boolean addRequirementsOrderId(java.lang.String requirementsOrderId) {
        if (requirementsOrderId == null) throw new IllegalArgumentException("requirementsOrderId == null");
        boolean added = this.requirementsOrderIds.add(requirementsOrderId);
        if (added) fireModified();
        return added;
    }

    public final boolean addRequirementsOrderIds(Collection<java.lang.String> requirementsOrderIds) {
        if (requirementsOrderIds == null) throw new IllegalArgumentException("requirementsOrderIds == null");
        boolean added = false;
        for (java.lang.String requirementsOrderId : requirementsOrderIds) {
            added = added | this.requirementsOrderIds.add(requirementsOrderId);
        }
        if (added) fireModified();
        return added;
    }

    public final boolean removeRequirementsOrderId(java.lang.String requirementsOrderId) {
        if (requirementsOrderId == null) throw new IllegalArgumentException("requirementsOrderId == null");
        if (this.requirementsOrderIds == null) return false;
        boolean removed = this.requirementsOrderIds.remove(requirementsOrderId);
        if (removed) fireModified();
        return removed;
    }

    public final boolean removeRequirementsOrderIds(Collection<java.lang.String> requirementsOrderIds) {
        if (requirementsOrderIds == null) return false;
        if (requirementsOrderIds.isEmpty()) return false;
        boolean removed = false;
        for (java.lang.String _element: requirementsOrderIds) {
            removed = removed | removeRequirementsOrderId(_element);
        }
        return removed;
    }

    public final boolean clearRequirementsOrderIds() {
        if (this.requirementsOrderIds.isEmpty()) return false;
        this.requirementsOrderIds.clear();
        fireModified();
        return true;
    }

    public final String getRequirementsOrderIdsAsCommaSeparatedString() {
        if (this.requirementsOrderIds.isEmpty()) return null;
        return Str.concat(this.requirementsOrderIds,", ");
    }

    public final void setRequirementsOrderIdsAsCommaSeparatedString(String requirementsOrderIds) {
        this.requirementsOrderIds = new java.util.ArrayList(Str.parseCommaSeparatedString(requirementsOrderIds));
    }

    protected final void updateRequirementsOrderIds(Object value) {
        setRequirementsOrderIds((java.util.List<java.lang.String>) value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("label")) updateLabel(value);
            if (property.equals("description")) updateDescription(value);
            if (property.equals("begin")) updateBegin(value);
            if (property.equals("end")) updateEnd(value);
            if (property.equals("participantsIds")) updateParticipants(value);
            if (property.equals("adminsIds")) updateAdmins(value);
            if (property.equals("productOwnersIds")) updateProductOwners(value);
            if (property.equals("scrumMastersIds")) updateScrumMasters(value);
            if (property.equals("teamMembersIds")) updateTeamMembers(value);
            if (property.equals("currentSprintId")) updateCurrentSprint(value);
            if (property.equals("nextSprintId")) updateNextSprint(value);
            if (property.equals("requirementsOrderIds")) updateRequirementsOrderIds(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        if (this.participantsIds == null) this.participantsIds = new java.util.HashSet<String>();
        repairDeadParticipantReference(entityId);
        if (this.adminsIds == null) this.adminsIds = new java.util.HashSet<String>();
        repairDeadAdminReference(entityId);
        if (this.productOwnersIds == null) this.productOwnersIds = new java.util.HashSet<String>();
        repairDeadProductOwnerReference(entityId);
        if (this.scrumMastersIds == null) this.scrumMastersIds = new java.util.HashSet<String>();
        repairDeadScrumMasterReference(entityId);
        if (this.teamMembersIds == null) this.teamMembersIds = new java.util.HashSet<String>();
        repairDeadTeamMemberReference(entityId);
        repairDeadCurrentSprintReference(entityId);
        repairDeadNextSprintReference(entityId);
        if (this.requirementsOrderIds == null) this.requirementsOrderIds = new java.util.ArrayList<java.lang.String>();
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        if (this.participantsIds == null) this.participantsIds = new java.util.HashSet<String>();
        Set<String> participants = new HashSet<String>(this.participantsIds);
        for (String entityId : participants) {
            try {
                userDao.getById(entityId);
            } catch (EntityDoesNotExistException ex) {
                LOG.info("Repairing dead participant reference");
                repairDeadParticipantReference(entityId);
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
        if (this.productOwnersIds == null) this.productOwnersIds = new java.util.HashSet<String>();
        Set<String> productOwners = new HashSet<String>(this.productOwnersIds);
        for (String entityId : productOwners) {
            try {
                userDao.getById(entityId);
            } catch (EntityDoesNotExistException ex) {
                LOG.info("Repairing dead productOwner reference");
                repairDeadProductOwnerReference(entityId);
            }
        }
        if (this.scrumMastersIds == null) this.scrumMastersIds = new java.util.HashSet<String>();
        Set<String> scrumMasters = new HashSet<String>(this.scrumMastersIds);
        for (String entityId : scrumMasters) {
            try {
                userDao.getById(entityId);
            } catch (EntityDoesNotExistException ex) {
                LOG.info("Repairing dead scrumMaster reference");
                repairDeadScrumMasterReference(entityId);
            }
        }
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
        try {
            getCurrentSprint();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead currentSprint reference");
            repairDeadCurrentSprintReference(this.currentSprintId);
        }
        try {
            getNextSprint();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead nextSprint reference");
            repairDeadNextSprintReference(this.nextSprintId);
        }
        if (this.requirementsOrderIds == null) this.requirementsOrderIds = new java.util.ArrayList<java.lang.String>();
    }


    // -----------------------------------------------------------
    // - dependencies
    // -----------------------------------------------------------

    protected static scrum.server.sprint.SprintDao sprintDao;

    public static final void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        GProject.sprintDao = sprintDao;
    }

    protected static ProjectDao projectDao;

    public static final void setProjectDao(ProjectDao projectDao) {
        GProject.projectDao = projectDao;
    }

}