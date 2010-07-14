// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.EntityGenerator










package scrum.server.sprint;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GSprint
            extends AEntity
            implements ilarkesto.auth.ViewProtected<scrum.server.admin.User>, ilarkesto.search.Searchable, java.lang.Comparable<Sprint> {

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
        properties.put("planningNote", this.planningNote);
        properties.put("reviewNote", this.reviewNote);
        properties.put("retrospectiveNote", this.retrospectiveNote);
        properties.put("productOwnersIds", this.productOwnersIds);
        properties.put("scrumMastersIds", this.scrumMastersIds);
        properties.put("teamMembersIds", this.teamMembersIds);
    }

    public int compareTo(Sprint other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final ilarkesto.core.logging.Log LOG = ilarkesto.core.logging.Log.get(GSprint.class);

    public static final String TYPE = "sprint";


    // -----------------------------------------------------------
    // - Searchable
    // -----------------------------------------------------------

    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getLabel(), key)) return true;
        if (matchesKey(getGoal(), key)) return true;
        if (matchesKey(getCompletedRequirementLabels(), key)) return true;
        if (matchesKey(getPlanningNote(), key)) return true;
        if (matchesKey(getReviewNote(), key)) return true;
        if (matchesKey(getRetrospectiveNote(), key)) return true;
        return false;
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private String projectId;
    private transient scrum.server.project.Project projectCache;

    private void updateProjectCache() {
        projectCache = this.projectId == null ? null : (scrum.server.project.Project)projectDao.getById(this.projectId);
    }

    public final String getProjectId() {
        return this.projectId;
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
        if (this.projectId == null || entityId.equals(this.projectId)) {
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

    private java.lang.Float velocity;

    public final java.lang.Float getVelocity() {
        return velocity;
    }

    public final void setVelocity(java.lang.Float velocity) {
        velocity = prepareVelocity(velocity);
        if (isVelocity(velocity)) return;
        this.velocity = velocity;
        fireModified();
    }

    protected java.lang.Float prepareVelocity(java.lang.Float velocity) {
        return velocity;
    }

    public final boolean isVelocitySet() {
        return this.velocity != null;
    }

    public final boolean isVelocity(java.lang.Float velocity) {
        if (this.velocity == null && velocity == null) return true;
        return this.velocity != null && this.velocity.equals(velocity);
    }

    protected final void updateVelocity(Object value) {
        setVelocity((java.lang.Float)value);
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
    // - planningNote
    // -----------------------------------------------------------

    private java.lang.String planningNote;

    public final java.lang.String getPlanningNote() {
        return planningNote;
    }

    public final void setPlanningNote(java.lang.String planningNote) {
        planningNote = preparePlanningNote(planningNote);
        if (isPlanningNote(planningNote)) return;
        this.planningNote = planningNote;
        fireModified();
    }

    protected java.lang.String preparePlanningNote(java.lang.String planningNote) {
        planningNote = Str.removeUnreadableChars(planningNote);
        return planningNote;
    }

    public final boolean isPlanningNoteSet() {
        return this.planningNote != null;
    }

    public final boolean isPlanningNote(java.lang.String planningNote) {
        if (this.planningNote == null && planningNote == null) return true;
        return this.planningNote != null && this.planningNote.equals(planningNote);
    }

    protected final void updatePlanningNote(Object value) {
        setPlanningNote((java.lang.String)value);
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
            if (property.equals("planningNote")) updatePlanningNote(value);
            if (property.equals("reviewNote")) updateReviewNote(value);
            if (property.equals("retrospectiveNote")) updateRetrospectiveNote(value);
            if (property.equals("productOwnersIds")) updateProductOwners(value);
            if (property.equals("scrumMastersIds")) updateScrumMasters(value);
            if (property.equals("teamMembersIds")) updateTeamMembers(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadProjectReference(entityId);
        if (this.productOwnersIds == null) this.productOwnersIds = new java.util.HashSet<String>();
        repairDeadProductOwnerReference(entityId);
        if (this.scrumMastersIds == null) this.scrumMastersIds = new java.util.HashSet<String>();
        repairDeadScrumMasterReference(entityId);
        if (this.teamMembersIds == null) this.teamMembersIds = new java.util.HashSet<String>();
        repairDeadTeamMemberReference(entityId);
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