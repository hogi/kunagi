// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.EntityGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
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
        properties.put("shortDescription", this.shortDescription);
        properties.put("description", this.description);
        properties.put("longDescription", this.longDescription);
        properties.put("begin", this.begin == null ? null : this.begin.toString());
        properties.put("end", this.end == null ? null : this.end.toString());
        properties.put("participantsIds", this.participantsIds);
        properties.put("adminsIds", this.adminsIds);
        properties.put("productOwnersIds", this.productOwnersIds);
        properties.put("scrumMastersIds", this.scrumMastersIds);
        properties.put("teamMembersIds", this.teamMembersIds);
        properties.put("currentSprintId", this.currentSprintId);
        properties.put("nextSprintId", this.nextSprintId);
        properties.put("velocity", this.velocity);
        properties.put("requirementsOrderIds", this.requirementsOrderIds);
        properties.put("urgentIssuesOrderIds", this.urgentIssuesOrderIds);
        properties.put("lastSprintNumber", this.lastSprintNumber);
        properties.put("lastTaskNumber", this.lastTaskNumber);
        properties.put("lastRequirementNumber", this.lastRequirementNumber);
        properties.put("lastQualityNumber", this.lastQualityNumber);
        properties.put("lastRiskNumber", this.lastRiskNumber);
        properties.put("lastIssueNumber", this.lastIssueNumber);
        properties.put("lastImpedimentNumber", this.lastImpedimentNumber);
        properties.put("lastFileNumber", this.lastFileNumber);
        properties.put("lastSubjectNumber", this.lastSubjectNumber);
        properties.put("lastEventNumber", this.lastEventNumber);
        properties.put("lastReleaseNumber", this.lastReleaseNumber);
        properties.put("lastBlogEntryNumber", this.lastBlogEntryNumber);
        properties.put("punishmentFactor", this.punishmentFactor);
        properties.put("punishmentUnit", this.punishmentUnit);
        properties.put("homepageDir", this.homepageDir);
        properties.put("autoUpdateHomepage", this.autoUpdateHomepage);
        properties.put("lastOpenedDateAndTime", this.lastOpenedDateAndTime == null ? null : this.lastOpenedDateAndTime.toString());
    }

    public int compareTo(Project other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final ilarkesto.core.logging.Log LOG = ilarkesto.core.logging.Log.get(GProject.class);

    public static final String TYPE = "project";


    // -----------------------------------------------------------
    // - Searchable
    // -----------------------------------------------------------

    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getLabel(), key)) return true;
        if (matchesKey(getShortDescription(), key)) return true;
        if (matchesKey(getDescription(), key)) return true;
        if (matchesKey(getLongDescription(), key)) return true;
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
    // - shortDescription
    // -----------------------------------------------------------

    private java.lang.String shortDescription;

    public final java.lang.String getShortDescription() {
        return shortDescription;
    }

    public final void setShortDescription(java.lang.String shortDescription) {
        shortDescription = prepareShortDescription(shortDescription);
        if (isShortDescription(shortDescription)) return;
        this.shortDescription = shortDescription;
        fireModified();
    }

    protected java.lang.String prepareShortDescription(java.lang.String shortDescription) {
        shortDescription = Str.removeUnreadableChars(shortDescription);
        return shortDescription;
    }

    public final boolean isShortDescriptionSet() {
        return this.shortDescription != null;
    }

    public final boolean isShortDescription(java.lang.String shortDescription) {
        if (this.shortDescription == null && shortDescription == null) return true;
        return this.shortDescription != null && this.shortDescription.equals(shortDescription);
    }

    protected final void updateShortDescription(Object value) {
        setShortDescription((java.lang.String)value);
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
    // - longDescription
    // -----------------------------------------------------------

    private java.lang.String longDescription;

    public final java.lang.String getLongDescription() {
        return longDescription;
    }

    public final void setLongDescription(java.lang.String longDescription) {
        longDescription = prepareLongDescription(longDescription);
        if (isLongDescription(longDescription)) return;
        this.longDescription = longDescription;
        fireModified();
    }

    protected java.lang.String prepareLongDescription(java.lang.String longDescription) {
        longDescription = Str.removeUnreadableChars(longDescription);
        return longDescription;
    }

    public final boolean isLongDescriptionSet() {
        return this.longDescription != null;
    }

    public final boolean isLongDescription(java.lang.String longDescription) {
        if (this.longDescription == null && longDescription == null) return true;
        return this.longDescription != null && this.longDescription.equals(longDescription);
    }

    protected final void updateLongDescription(Object value) {
        setLongDescription((java.lang.String)value);
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

    public final String getCurrentSprintId() {
        return this.currentSprintId;
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
        if (this.currentSprintId == null || entityId.equals(this.currentSprintId)) {
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

    public final String getNextSprintId() {
        return this.nextSprintId;
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
        if (this.nextSprintId == null || entityId.equals(this.nextSprintId)) {
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

    // -----------------------------------------------------------
    // - urgentIssuesOrderIds
    // -----------------------------------------------------------

    private java.util.List<java.lang.String> urgentIssuesOrderIds = new java.util.ArrayList<java.lang.String>();

    public final java.util.List<java.lang.String> getUrgentIssuesOrderIds() {
        return new java.util.ArrayList<java.lang.String>(urgentIssuesOrderIds);
    }

    public final void setUrgentIssuesOrderIds(Collection<java.lang.String> urgentIssuesOrderIds) {
        urgentIssuesOrderIds = prepareUrgentIssuesOrderIds(urgentIssuesOrderIds);
        if (urgentIssuesOrderIds == null) urgentIssuesOrderIds = Collections.emptyList();
        if (this.urgentIssuesOrderIds.equals(urgentIssuesOrderIds)) return;
        this.urgentIssuesOrderIds = new java.util.ArrayList<java.lang.String>(urgentIssuesOrderIds);
        fireModified();
    }

    protected Collection<java.lang.String> prepareUrgentIssuesOrderIds(Collection<java.lang.String> urgentIssuesOrderIds) {
        return urgentIssuesOrderIds;
    }

    public final boolean containsUrgentIssuesOrderId(java.lang.String urgentIssuesOrderId) {
        if (urgentIssuesOrderId == null) return false;
        return this.urgentIssuesOrderIds.contains(urgentIssuesOrderId);
    }

    public final int getUrgentIssuesOrderIdsCount() {
        return this.urgentIssuesOrderIds.size();
    }

    public final boolean isUrgentIssuesOrderIdsEmpty() {
        return this.urgentIssuesOrderIds.isEmpty();
    }

    public final boolean addUrgentIssuesOrderId(java.lang.String urgentIssuesOrderId) {
        if (urgentIssuesOrderId == null) throw new IllegalArgumentException("urgentIssuesOrderId == null");
        boolean added = this.urgentIssuesOrderIds.add(urgentIssuesOrderId);
        if (added) fireModified();
        return added;
    }

    public final boolean addUrgentIssuesOrderIds(Collection<java.lang.String> urgentIssuesOrderIds) {
        if (urgentIssuesOrderIds == null) throw new IllegalArgumentException("urgentIssuesOrderIds == null");
        boolean added = false;
        for (java.lang.String urgentIssuesOrderId : urgentIssuesOrderIds) {
            added = added | this.urgentIssuesOrderIds.add(urgentIssuesOrderId);
        }
        if (added) fireModified();
        return added;
    }

    public final boolean removeUrgentIssuesOrderId(java.lang.String urgentIssuesOrderId) {
        if (urgentIssuesOrderId == null) throw new IllegalArgumentException("urgentIssuesOrderId == null");
        if (this.urgentIssuesOrderIds == null) return false;
        boolean removed = this.urgentIssuesOrderIds.remove(urgentIssuesOrderId);
        if (removed) fireModified();
        return removed;
    }

    public final boolean removeUrgentIssuesOrderIds(Collection<java.lang.String> urgentIssuesOrderIds) {
        if (urgentIssuesOrderIds == null) return false;
        if (urgentIssuesOrderIds.isEmpty()) return false;
        boolean removed = false;
        for (java.lang.String _element: urgentIssuesOrderIds) {
            removed = removed | removeUrgentIssuesOrderId(_element);
        }
        return removed;
    }

    public final boolean clearUrgentIssuesOrderIds() {
        if (this.urgentIssuesOrderIds.isEmpty()) return false;
        this.urgentIssuesOrderIds.clear();
        fireModified();
        return true;
    }

    public final String getUrgentIssuesOrderIdsAsCommaSeparatedString() {
        if (this.urgentIssuesOrderIds.isEmpty()) return null;
        return Str.concat(this.urgentIssuesOrderIds,", ");
    }

    public final void setUrgentIssuesOrderIdsAsCommaSeparatedString(String urgentIssuesOrderIds) {
        this.urgentIssuesOrderIds = new java.util.ArrayList(Str.parseCommaSeparatedString(urgentIssuesOrderIds));
    }

    protected final void updateUrgentIssuesOrderIds(Object value) {
        setUrgentIssuesOrderIds((java.util.List<java.lang.String>) value);
    }

    // -----------------------------------------------------------
    // - lastSprintNumber
    // -----------------------------------------------------------

    private int lastSprintNumber;

    public final int getLastSprintNumber() {
        return lastSprintNumber;
    }

    public final void setLastSprintNumber(int lastSprintNumber) {
        lastSprintNumber = prepareLastSprintNumber(lastSprintNumber);
        if (isLastSprintNumber(lastSprintNumber)) return;
        this.lastSprintNumber = lastSprintNumber;
        fireModified();
    }

    protected int prepareLastSprintNumber(int lastSprintNumber) {
        return lastSprintNumber;
    }

    public final boolean isLastSprintNumber(int lastSprintNumber) {
        return this.lastSprintNumber == lastSprintNumber;
    }

    protected final void updateLastSprintNumber(Object value) {
        setLastSprintNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - lastTaskNumber
    // -----------------------------------------------------------

    private int lastTaskNumber;

    public final int getLastTaskNumber() {
        return lastTaskNumber;
    }

    public final void setLastTaskNumber(int lastTaskNumber) {
        lastTaskNumber = prepareLastTaskNumber(lastTaskNumber);
        if (isLastTaskNumber(lastTaskNumber)) return;
        this.lastTaskNumber = lastTaskNumber;
        fireModified();
    }

    protected int prepareLastTaskNumber(int lastTaskNumber) {
        return lastTaskNumber;
    }

    public final boolean isLastTaskNumber(int lastTaskNumber) {
        return this.lastTaskNumber == lastTaskNumber;
    }

    protected final void updateLastTaskNumber(Object value) {
        setLastTaskNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - lastRequirementNumber
    // -----------------------------------------------------------

    private int lastRequirementNumber;

    public final int getLastRequirementNumber() {
        return lastRequirementNumber;
    }

    public final void setLastRequirementNumber(int lastRequirementNumber) {
        lastRequirementNumber = prepareLastRequirementNumber(lastRequirementNumber);
        if (isLastRequirementNumber(lastRequirementNumber)) return;
        this.lastRequirementNumber = lastRequirementNumber;
        fireModified();
    }

    protected int prepareLastRequirementNumber(int lastRequirementNumber) {
        return lastRequirementNumber;
    }

    public final boolean isLastRequirementNumber(int lastRequirementNumber) {
        return this.lastRequirementNumber == lastRequirementNumber;
    }

    protected final void updateLastRequirementNumber(Object value) {
        setLastRequirementNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - lastQualityNumber
    // -----------------------------------------------------------

    private int lastQualityNumber;

    public final int getLastQualityNumber() {
        return lastQualityNumber;
    }

    public final void setLastQualityNumber(int lastQualityNumber) {
        lastQualityNumber = prepareLastQualityNumber(lastQualityNumber);
        if (isLastQualityNumber(lastQualityNumber)) return;
        this.lastQualityNumber = lastQualityNumber;
        fireModified();
    }

    protected int prepareLastQualityNumber(int lastQualityNumber) {
        return lastQualityNumber;
    }

    public final boolean isLastQualityNumber(int lastQualityNumber) {
        return this.lastQualityNumber == lastQualityNumber;
    }

    protected final void updateLastQualityNumber(Object value) {
        setLastQualityNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - lastRiskNumber
    // -----------------------------------------------------------

    private int lastRiskNumber;

    public final int getLastRiskNumber() {
        return lastRiskNumber;
    }

    public final void setLastRiskNumber(int lastRiskNumber) {
        lastRiskNumber = prepareLastRiskNumber(lastRiskNumber);
        if (isLastRiskNumber(lastRiskNumber)) return;
        this.lastRiskNumber = lastRiskNumber;
        fireModified();
    }

    protected int prepareLastRiskNumber(int lastRiskNumber) {
        return lastRiskNumber;
    }

    public final boolean isLastRiskNumber(int lastRiskNumber) {
        return this.lastRiskNumber == lastRiskNumber;
    }

    protected final void updateLastRiskNumber(Object value) {
        setLastRiskNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - lastIssueNumber
    // -----------------------------------------------------------

    private int lastIssueNumber;

    public final int getLastIssueNumber() {
        return lastIssueNumber;
    }

    public final void setLastIssueNumber(int lastIssueNumber) {
        lastIssueNumber = prepareLastIssueNumber(lastIssueNumber);
        if (isLastIssueNumber(lastIssueNumber)) return;
        this.lastIssueNumber = lastIssueNumber;
        fireModified();
    }

    protected int prepareLastIssueNumber(int lastIssueNumber) {
        return lastIssueNumber;
    }

    public final boolean isLastIssueNumber(int lastIssueNumber) {
        return this.lastIssueNumber == lastIssueNumber;
    }

    protected final void updateLastIssueNumber(Object value) {
        setLastIssueNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - lastImpedimentNumber
    // -----------------------------------------------------------

    private int lastImpedimentNumber;

    public final int getLastImpedimentNumber() {
        return lastImpedimentNumber;
    }

    public final void setLastImpedimentNumber(int lastImpedimentNumber) {
        lastImpedimentNumber = prepareLastImpedimentNumber(lastImpedimentNumber);
        if (isLastImpedimentNumber(lastImpedimentNumber)) return;
        this.lastImpedimentNumber = lastImpedimentNumber;
        fireModified();
    }

    protected int prepareLastImpedimentNumber(int lastImpedimentNumber) {
        return lastImpedimentNumber;
    }

    public final boolean isLastImpedimentNumber(int lastImpedimentNumber) {
        return this.lastImpedimentNumber == lastImpedimentNumber;
    }

    protected final void updateLastImpedimentNumber(Object value) {
        setLastImpedimentNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - lastFileNumber
    // -----------------------------------------------------------

    private int lastFileNumber;

    public final int getLastFileNumber() {
        return lastFileNumber;
    }

    public final void setLastFileNumber(int lastFileNumber) {
        lastFileNumber = prepareLastFileNumber(lastFileNumber);
        if (isLastFileNumber(lastFileNumber)) return;
        this.lastFileNumber = lastFileNumber;
        fireModified();
    }

    protected int prepareLastFileNumber(int lastFileNumber) {
        return lastFileNumber;
    }

    public final boolean isLastFileNumber(int lastFileNumber) {
        return this.lastFileNumber == lastFileNumber;
    }

    protected final void updateLastFileNumber(Object value) {
        setLastFileNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - lastSubjectNumber
    // -----------------------------------------------------------

    private int lastSubjectNumber;

    public final int getLastSubjectNumber() {
        return lastSubjectNumber;
    }

    public final void setLastSubjectNumber(int lastSubjectNumber) {
        lastSubjectNumber = prepareLastSubjectNumber(lastSubjectNumber);
        if (isLastSubjectNumber(lastSubjectNumber)) return;
        this.lastSubjectNumber = lastSubjectNumber;
        fireModified();
    }

    protected int prepareLastSubjectNumber(int lastSubjectNumber) {
        return lastSubjectNumber;
    }

    public final boolean isLastSubjectNumber(int lastSubjectNumber) {
        return this.lastSubjectNumber == lastSubjectNumber;
    }

    protected final void updateLastSubjectNumber(Object value) {
        setLastSubjectNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - lastEventNumber
    // -----------------------------------------------------------

    private int lastEventNumber;

    public final int getLastEventNumber() {
        return lastEventNumber;
    }

    public final void setLastEventNumber(int lastEventNumber) {
        lastEventNumber = prepareLastEventNumber(lastEventNumber);
        if (isLastEventNumber(lastEventNumber)) return;
        this.lastEventNumber = lastEventNumber;
        fireModified();
    }

    protected int prepareLastEventNumber(int lastEventNumber) {
        return lastEventNumber;
    }

    public final boolean isLastEventNumber(int lastEventNumber) {
        return this.lastEventNumber == lastEventNumber;
    }

    protected final void updateLastEventNumber(Object value) {
        setLastEventNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - lastReleaseNumber
    // -----------------------------------------------------------

    private int lastReleaseNumber;

    public final int getLastReleaseNumber() {
        return lastReleaseNumber;
    }

    public final void setLastReleaseNumber(int lastReleaseNumber) {
        lastReleaseNumber = prepareLastReleaseNumber(lastReleaseNumber);
        if (isLastReleaseNumber(lastReleaseNumber)) return;
        this.lastReleaseNumber = lastReleaseNumber;
        fireModified();
    }

    protected int prepareLastReleaseNumber(int lastReleaseNumber) {
        return lastReleaseNumber;
    }

    public final boolean isLastReleaseNumber(int lastReleaseNumber) {
        return this.lastReleaseNumber == lastReleaseNumber;
    }

    protected final void updateLastReleaseNumber(Object value) {
        setLastReleaseNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - lastBlogEntryNumber
    // -----------------------------------------------------------

    private int lastBlogEntryNumber;

    public final int getLastBlogEntryNumber() {
        return lastBlogEntryNumber;
    }

    public final void setLastBlogEntryNumber(int lastBlogEntryNumber) {
        lastBlogEntryNumber = prepareLastBlogEntryNumber(lastBlogEntryNumber);
        if (isLastBlogEntryNumber(lastBlogEntryNumber)) return;
        this.lastBlogEntryNumber = lastBlogEntryNumber;
        fireModified();
    }

    protected int prepareLastBlogEntryNumber(int lastBlogEntryNumber) {
        return lastBlogEntryNumber;
    }

    public final boolean isLastBlogEntryNumber(int lastBlogEntryNumber) {
        return this.lastBlogEntryNumber == lastBlogEntryNumber;
    }

    protected final void updateLastBlogEntryNumber(Object value) {
        setLastBlogEntryNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - punishmentFactor
    // -----------------------------------------------------------

    private int punishmentFactor;

    public final int getPunishmentFactor() {
        return punishmentFactor;
    }

    public final void setPunishmentFactor(int punishmentFactor) {
        punishmentFactor = preparePunishmentFactor(punishmentFactor);
        if (isPunishmentFactor(punishmentFactor)) return;
        this.punishmentFactor = punishmentFactor;
        fireModified();
    }

    protected int preparePunishmentFactor(int punishmentFactor) {
        return punishmentFactor;
    }

    public final boolean isPunishmentFactor(int punishmentFactor) {
        return this.punishmentFactor == punishmentFactor;
    }

    protected final void updatePunishmentFactor(Object value) {
        setPunishmentFactor((Integer)value);
    }

    // -----------------------------------------------------------
    // - punishmentUnit
    // -----------------------------------------------------------

    private java.lang.String punishmentUnit;

    public final java.lang.String getPunishmentUnit() {
        return punishmentUnit;
    }

    public final void setPunishmentUnit(java.lang.String punishmentUnit) {
        punishmentUnit = preparePunishmentUnit(punishmentUnit);
        if (isPunishmentUnit(punishmentUnit)) return;
        this.punishmentUnit = punishmentUnit;
        fireModified();
    }

    protected java.lang.String preparePunishmentUnit(java.lang.String punishmentUnit) {
        punishmentUnit = Str.removeUnreadableChars(punishmentUnit);
        return punishmentUnit;
    }

    public final boolean isPunishmentUnitSet() {
        return this.punishmentUnit != null;
    }

    public final boolean isPunishmentUnit(java.lang.String punishmentUnit) {
        if (this.punishmentUnit == null && punishmentUnit == null) return true;
        return this.punishmentUnit != null && this.punishmentUnit.equals(punishmentUnit);
    }

    protected final void updatePunishmentUnit(Object value) {
        setPunishmentUnit((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - homepageDir
    // -----------------------------------------------------------

    private java.lang.String homepageDir;

    public final java.lang.String getHomepageDir() {
        return homepageDir;
    }

    public final void setHomepageDir(java.lang.String homepageDir) {
        homepageDir = prepareHomepageDir(homepageDir);
        if (isHomepageDir(homepageDir)) return;
        this.homepageDir = homepageDir;
        fireModified();
    }

    protected java.lang.String prepareHomepageDir(java.lang.String homepageDir) {
        homepageDir = Str.removeUnreadableChars(homepageDir);
        return homepageDir;
    }

    public final boolean isHomepageDirSet() {
        return this.homepageDir != null;
    }

    public final boolean isHomepageDir(java.lang.String homepageDir) {
        if (this.homepageDir == null && homepageDir == null) return true;
        return this.homepageDir != null && this.homepageDir.equals(homepageDir);
    }

    protected final void updateHomepageDir(Object value) {
        setHomepageDir((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - autoUpdateHomepage
    // -----------------------------------------------------------

    private boolean autoUpdateHomepage;

    public final boolean isAutoUpdateHomepage() {
        return autoUpdateHomepage;
    }

    public final void setAutoUpdateHomepage(boolean autoUpdateHomepage) {
        autoUpdateHomepage = prepareAutoUpdateHomepage(autoUpdateHomepage);
        if (isAutoUpdateHomepage(autoUpdateHomepage)) return;
        this.autoUpdateHomepage = autoUpdateHomepage;
        fireModified();
    }

    protected boolean prepareAutoUpdateHomepage(boolean autoUpdateHomepage) {
        return autoUpdateHomepage;
    }

    public final boolean isAutoUpdateHomepage(boolean autoUpdateHomepage) {
        return this.autoUpdateHomepage == autoUpdateHomepage;
    }

    protected final void updateAutoUpdateHomepage(Object value) {
        setAutoUpdateHomepage((Boolean)value);
    }

    // -----------------------------------------------------------
    // - lastOpenedDateAndTime
    // -----------------------------------------------------------

    private ilarkesto.base.time.DateAndTime lastOpenedDateAndTime;

    public final ilarkesto.base.time.DateAndTime getLastOpenedDateAndTime() {
        return lastOpenedDateAndTime;
    }

    public final void setLastOpenedDateAndTime(ilarkesto.base.time.DateAndTime lastOpenedDateAndTime) {
        lastOpenedDateAndTime = prepareLastOpenedDateAndTime(lastOpenedDateAndTime);
        if (isLastOpenedDateAndTime(lastOpenedDateAndTime)) return;
        this.lastOpenedDateAndTime = lastOpenedDateAndTime;
        fireModified();
    }

    protected ilarkesto.base.time.DateAndTime prepareLastOpenedDateAndTime(ilarkesto.base.time.DateAndTime lastOpenedDateAndTime) {
        return lastOpenedDateAndTime;
    }

    public final boolean isLastOpenedDateAndTimeSet() {
        return this.lastOpenedDateAndTime != null;
    }

    public final boolean isLastOpenedDateAndTime(ilarkesto.base.time.DateAndTime lastOpenedDateAndTime) {
        if (this.lastOpenedDateAndTime == null && lastOpenedDateAndTime == null) return true;
        return this.lastOpenedDateAndTime != null && this.lastOpenedDateAndTime.equals(lastOpenedDateAndTime);
    }

    protected final void updateLastOpenedDateAndTime(Object value) {
        value = value == null ? null : new ilarkesto.base.time.DateAndTime((String)value);
        setLastOpenedDateAndTime((ilarkesto.base.time.DateAndTime)value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("label")) updateLabel(value);
            if (property.equals("shortDescription")) updateShortDescription(value);
            if (property.equals("description")) updateDescription(value);
            if (property.equals("longDescription")) updateLongDescription(value);
            if (property.equals("begin")) updateBegin(value);
            if (property.equals("end")) updateEnd(value);
            if (property.equals("participantsIds")) updateParticipants(value);
            if (property.equals("adminsIds")) updateAdmins(value);
            if (property.equals("productOwnersIds")) updateProductOwners(value);
            if (property.equals("scrumMastersIds")) updateScrumMasters(value);
            if (property.equals("teamMembersIds")) updateTeamMembers(value);
            if (property.equals("currentSprintId")) updateCurrentSprint(value);
            if (property.equals("nextSprintId")) updateNextSprint(value);
            if (property.equals("velocity")) updateVelocity(value);
            if (property.equals("requirementsOrderIds")) updateRequirementsOrderIds(value);
            if (property.equals("urgentIssuesOrderIds")) updateUrgentIssuesOrderIds(value);
            if (property.equals("lastSprintNumber")) updateLastSprintNumber(value);
            if (property.equals("lastTaskNumber")) updateLastTaskNumber(value);
            if (property.equals("lastRequirementNumber")) updateLastRequirementNumber(value);
            if (property.equals("lastQualityNumber")) updateLastQualityNumber(value);
            if (property.equals("lastRiskNumber")) updateLastRiskNumber(value);
            if (property.equals("lastIssueNumber")) updateLastIssueNumber(value);
            if (property.equals("lastImpedimentNumber")) updateLastImpedimentNumber(value);
            if (property.equals("lastFileNumber")) updateLastFileNumber(value);
            if (property.equals("lastSubjectNumber")) updateLastSubjectNumber(value);
            if (property.equals("lastEventNumber")) updateLastEventNumber(value);
            if (property.equals("lastReleaseNumber")) updateLastReleaseNumber(value);
            if (property.equals("lastBlogEntryNumber")) updateLastBlogEntryNumber(value);
            if (property.equals("punishmentFactor")) updatePunishmentFactor(value);
            if (property.equals("punishmentUnit")) updatePunishmentUnit(value);
            if (property.equals("homepageDir")) updateHomepageDir(value);
            if (property.equals("autoUpdateHomepage")) updateAutoUpdateHomepage(value);
            if (property.equals("lastOpenedDateAndTime")) updateLastOpenedDateAndTime(value);
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
        if (this.urgentIssuesOrderIds == null) this.urgentIssuesOrderIds = new java.util.ArrayList<java.lang.String>();
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
        if (this.urgentIssuesOrderIds == null) this.urgentIssuesOrderIds = new java.util.ArrayList<java.lang.String>();
    }


    // -----------------------------------------------------------
    // - dependencies
    // -----------------------------------------------------------

    static scrum.server.sprint.SprintDao sprintDao;

    public static final void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        GProject.sprintDao = sprintDao;
    }

    static ProjectDao projectDao;

    public static final void setProjectDao(ProjectDao projectDao) {
        GProject.projectDao = projectDao;
    }

}