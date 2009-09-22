// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.project;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GProjectDao
            extends ilarkesto.persistence.ADao<Project> {

    public final String getEntityName() {
        return Project.TYPE;
    }

    public final Class getEntityClass() {
        return Project.class;
    }

    public Set<Project> getEntitiesVisibleForUser(final scrum.server.admin.User user) {
        return getEntities(new Predicate<Project>() {
            public boolean test(Project e) {
                return Auth.isVisible(e, user);
            }
        });
    }

    // --- clear caches ---
    public void clearCaches() {
        projectsByLabelCache.clear();
        labelsCache = null;
        projectsByDescriptionCache.clear();
        descriptionsCache = null;
        projectsByBeginCache.clear();
        beginsCache = null;
        projectsByEndCache.clear();
        endsCache = null;
        projectsByParticipantCache.clear();
        participantsCache = null;
        projectsByAdminCache.clear();
        adminsCache = null;
        projectsByProductOwnerCache.clear();
        productOwnersCache = null;
        projectsByScrumMasterCache.clear();
        scrumMastersCache = null;
        projectsByTeamMemberCache.clear();
        teamMembersCache = null;
        projectsByCurrentSprintCache.clear();
        currentSprintsCache = null;
        projectsByNextSprintCache.clear();
        nextSprintsCache = null;
        projectsByRequirementsOrderIdCache.clear();
        requirementsOrderIdsCache = null;
        projectsByLastTaskNumberCache.clear();
        lastTaskNumbersCache = null;
        projectsByLastRequirementNumberCache.clear();
        lastRequirementNumbersCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Project) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Project) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Project>> projectsByLabelCache = new Cache<java.lang.String,Set<Project>>(
            new Cache.Factory<java.lang.String,Set<Project>>() {
                public Set<Project> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<Project> getProjectsByLabel(java.lang.String label) {
        return projectsByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (Project e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<Project> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.isLabel(value);
        }

    }

    // -----------------------------------------------------------
    // - description
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Project>> projectsByDescriptionCache = new Cache<java.lang.String,Set<Project>>(
            new Cache.Factory<java.lang.String,Set<Project>>() {
                public Set<Project> create(java.lang.String description) {
                    return getEntities(new IsDescription(description));
                }
            });

    public final Set<Project> getProjectsByDescription(java.lang.String description) {
        return projectsByDescriptionCache.get(description);
    }
    private Set<java.lang.String> descriptionsCache;

    public final Set<java.lang.String> getDescriptions() {
        if (descriptionsCache == null) {
            descriptionsCache = new HashSet<java.lang.String>();
            for (Project e : getEntities()) {
                if (e.isDescriptionSet()) descriptionsCache.add(e.getDescription());
            }
        }
        return descriptionsCache;
    }

    private static class IsDescription implements Predicate<Project> {

        private java.lang.String value;

        public IsDescription(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.isDescription(value);
        }

    }

    // -----------------------------------------------------------
    // - begin
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.Date,Set<Project>> projectsByBeginCache = new Cache<ilarkesto.base.time.Date,Set<Project>>(
            new Cache.Factory<ilarkesto.base.time.Date,Set<Project>>() {
                public Set<Project> create(ilarkesto.base.time.Date begin) {
                    return getEntities(new IsBegin(begin));
                }
            });

    public final Set<Project> getProjectsByBegin(ilarkesto.base.time.Date begin) {
        return projectsByBeginCache.get(begin);
    }
    private Set<ilarkesto.base.time.Date> beginsCache;

    public final Set<ilarkesto.base.time.Date> getBegins() {
        if (beginsCache == null) {
            beginsCache = new HashSet<ilarkesto.base.time.Date>();
            for (Project e : getEntities()) {
                if (e.isBeginSet()) beginsCache.add(e.getBegin());
            }
        }
        return beginsCache;
    }

    private static class IsBegin implements Predicate<Project> {

        private ilarkesto.base.time.Date value;

        public IsBegin(ilarkesto.base.time.Date value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.isBegin(value);
        }

    }

    // -----------------------------------------------------------
    // - end
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.Date,Set<Project>> projectsByEndCache = new Cache<ilarkesto.base.time.Date,Set<Project>>(
            new Cache.Factory<ilarkesto.base.time.Date,Set<Project>>() {
                public Set<Project> create(ilarkesto.base.time.Date end) {
                    return getEntities(new IsEnd(end));
                }
            });

    public final Set<Project> getProjectsByEnd(ilarkesto.base.time.Date end) {
        return projectsByEndCache.get(end);
    }
    private Set<ilarkesto.base.time.Date> endsCache;

    public final Set<ilarkesto.base.time.Date> getEnds() {
        if (endsCache == null) {
            endsCache = new HashSet<ilarkesto.base.time.Date>();
            for (Project e : getEntities()) {
                if (e.isEndSet()) endsCache.add(e.getEnd());
            }
        }
        return endsCache;
    }

    private static class IsEnd implements Predicate<Project> {

        private ilarkesto.base.time.Date value;

        public IsEnd(ilarkesto.base.time.Date value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.isEnd(value);
        }

    }

    // -----------------------------------------------------------
    // - participants
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<Project>> projectsByParticipantCache = new Cache<scrum.server.admin.User,Set<Project>>(
            new Cache.Factory<scrum.server.admin.User,Set<Project>>() {
                public Set<Project> create(scrum.server.admin.User participant) {
                    return getEntities(new ContainsParticipant(participant));
                }
            });

    public final Set<Project> getProjectsByParticipant(scrum.server.admin.User participant) {
        return projectsByParticipantCache.get(participant);
    }
    private Set<scrum.server.admin.User> participantsCache;

    public final Set<scrum.server.admin.User> getParticipants() {
        if (participantsCache == null) {
            participantsCache = new HashSet<scrum.server.admin.User>();
            for (Project e : getEntities()) {
                participantsCache.addAll(e.getParticipants());
            }
        }
        return participantsCache;
    }

    private static class ContainsParticipant implements Predicate<Project> {

        private scrum.server.admin.User value;

        public ContainsParticipant(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.containsParticipant(value);
        }

    }

    // -----------------------------------------------------------
    // - admins
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<Project>> projectsByAdminCache = new Cache<scrum.server.admin.User,Set<Project>>(
            new Cache.Factory<scrum.server.admin.User,Set<Project>>() {
                public Set<Project> create(scrum.server.admin.User admin) {
                    return getEntities(new ContainsAdmin(admin));
                }
            });

    public final Set<Project> getProjectsByAdmin(scrum.server.admin.User admin) {
        return projectsByAdminCache.get(admin);
    }
    private Set<scrum.server.admin.User> adminsCache;

    public final Set<scrum.server.admin.User> getAdmins() {
        if (adminsCache == null) {
            adminsCache = new HashSet<scrum.server.admin.User>();
            for (Project e : getEntities()) {
                adminsCache.addAll(e.getAdmins());
            }
        }
        return adminsCache;
    }

    private static class ContainsAdmin implements Predicate<Project> {

        private scrum.server.admin.User value;

        public ContainsAdmin(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.containsAdmin(value);
        }

    }

    // -----------------------------------------------------------
    // - productOwners
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<Project>> projectsByProductOwnerCache = new Cache<scrum.server.admin.User,Set<Project>>(
            new Cache.Factory<scrum.server.admin.User,Set<Project>>() {
                public Set<Project> create(scrum.server.admin.User productOwner) {
                    return getEntities(new ContainsProductOwner(productOwner));
                }
            });

    public final Set<Project> getProjectsByProductOwner(scrum.server.admin.User productOwner) {
        return projectsByProductOwnerCache.get(productOwner);
    }
    private Set<scrum.server.admin.User> productOwnersCache;

    public final Set<scrum.server.admin.User> getProductOwners() {
        if (productOwnersCache == null) {
            productOwnersCache = new HashSet<scrum.server.admin.User>();
            for (Project e : getEntities()) {
                productOwnersCache.addAll(e.getProductOwners());
            }
        }
        return productOwnersCache;
    }

    private static class ContainsProductOwner implements Predicate<Project> {

        private scrum.server.admin.User value;

        public ContainsProductOwner(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.containsProductOwner(value);
        }

    }

    // -----------------------------------------------------------
    // - scrumMasters
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<Project>> projectsByScrumMasterCache = new Cache<scrum.server.admin.User,Set<Project>>(
            new Cache.Factory<scrum.server.admin.User,Set<Project>>() {
                public Set<Project> create(scrum.server.admin.User scrumMaster) {
                    return getEntities(new ContainsScrumMaster(scrumMaster));
                }
            });

    public final Set<Project> getProjectsByScrumMaster(scrum.server.admin.User scrumMaster) {
        return projectsByScrumMasterCache.get(scrumMaster);
    }
    private Set<scrum.server.admin.User> scrumMastersCache;

    public final Set<scrum.server.admin.User> getScrumMasters() {
        if (scrumMastersCache == null) {
            scrumMastersCache = new HashSet<scrum.server.admin.User>();
            for (Project e : getEntities()) {
                scrumMastersCache.addAll(e.getScrumMasters());
            }
        }
        return scrumMastersCache;
    }

    private static class ContainsScrumMaster implements Predicate<Project> {

        private scrum.server.admin.User value;

        public ContainsScrumMaster(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.containsScrumMaster(value);
        }

    }

    // -----------------------------------------------------------
    // - teamMembers
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<Project>> projectsByTeamMemberCache = new Cache<scrum.server.admin.User,Set<Project>>(
            new Cache.Factory<scrum.server.admin.User,Set<Project>>() {
                public Set<Project> create(scrum.server.admin.User teamMember) {
                    return getEntities(new ContainsTeamMember(teamMember));
                }
            });

    public final Set<Project> getProjectsByTeamMember(scrum.server.admin.User teamMember) {
        return projectsByTeamMemberCache.get(teamMember);
    }
    private Set<scrum.server.admin.User> teamMembersCache;

    public final Set<scrum.server.admin.User> getTeamMembers() {
        if (teamMembersCache == null) {
            teamMembersCache = new HashSet<scrum.server.admin.User>();
            for (Project e : getEntities()) {
                teamMembersCache.addAll(e.getTeamMembers());
            }
        }
        return teamMembersCache;
    }

    private static class ContainsTeamMember implements Predicate<Project> {

        private scrum.server.admin.User value;

        public ContainsTeamMember(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.containsTeamMember(value);
        }

    }

    // -----------------------------------------------------------
    // - currentSprint
    // -----------------------------------------------------------

    private final Cache<scrum.server.sprint.Sprint,Set<Project>> projectsByCurrentSprintCache = new Cache<scrum.server.sprint.Sprint,Set<Project>>(
            new Cache.Factory<scrum.server.sprint.Sprint,Set<Project>>() {
                public Set<Project> create(scrum.server.sprint.Sprint currentSprint) {
                    return getEntities(new IsCurrentSprint(currentSprint));
                }
            });

    public final Set<Project> getProjectsByCurrentSprint(scrum.server.sprint.Sprint currentSprint) {
        return projectsByCurrentSprintCache.get(currentSprint);
    }
    private Set<scrum.server.sprint.Sprint> currentSprintsCache;

    public final Set<scrum.server.sprint.Sprint> getCurrentSprints() {
        if (currentSprintsCache == null) {
            currentSprintsCache = new HashSet<scrum.server.sprint.Sprint>();
            for (Project e : getEntities()) {
                if (e.isCurrentSprintSet()) currentSprintsCache.add(e.getCurrentSprint());
            }
        }
        return currentSprintsCache;
    }

    private static class IsCurrentSprint implements Predicate<Project> {

        private scrum.server.sprint.Sprint value;

        public IsCurrentSprint(scrum.server.sprint.Sprint value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.isCurrentSprint(value);
        }

    }

    // -----------------------------------------------------------
    // - nextSprint
    // -----------------------------------------------------------

    private final Cache<scrum.server.sprint.Sprint,Set<Project>> projectsByNextSprintCache = new Cache<scrum.server.sprint.Sprint,Set<Project>>(
            new Cache.Factory<scrum.server.sprint.Sprint,Set<Project>>() {
                public Set<Project> create(scrum.server.sprint.Sprint nextSprint) {
                    return getEntities(new IsNextSprint(nextSprint));
                }
            });

    public final Set<Project> getProjectsByNextSprint(scrum.server.sprint.Sprint nextSprint) {
        return projectsByNextSprintCache.get(nextSprint);
    }
    private Set<scrum.server.sprint.Sprint> nextSprintsCache;

    public final Set<scrum.server.sprint.Sprint> getNextSprints() {
        if (nextSprintsCache == null) {
            nextSprintsCache = new HashSet<scrum.server.sprint.Sprint>();
            for (Project e : getEntities()) {
                if (e.isNextSprintSet()) nextSprintsCache.add(e.getNextSprint());
            }
        }
        return nextSprintsCache;
    }

    private static class IsNextSprint implements Predicate<Project> {

        private scrum.server.sprint.Sprint value;

        public IsNextSprint(scrum.server.sprint.Sprint value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.isNextSprint(value);
        }

    }

    // -----------------------------------------------------------
    // - requirementsOrderIds
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Project>> projectsByRequirementsOrderIdCache = new Cache<java.lang.String,Set<Project>>(
            new Cache.Factory<java.lang.String,Set<Project>>() {
                public Set<Project> create(java.lang.String requirementsOrderId) {
                    return getEntities(new ContainsRequirementsOrderId(requirementsOrderId));
                }
            });

    public final Set<Project> getProjectsByRequirementsOrderId(java.lang.String requirementsOrderId) {
        return projectsByRequirementsOrderIdCache.get(requirementsOrderId);
    }
    private Set<java.lang.String> requirementsOrderIdsCache;

    public final Set<java.lang.String> getRequirementsOrderIds() {
        if (requirementsOrderIdsCache == null) {
            requirementsOrderIdsCache = new HashSet<java.lang.String>();
            for (Project e : getEntities()) {
                requirementsOrderIdsCache.addAll(e.getRequirementsOrderIds());
            }
        }
        return requirementsOrderIdsCache;
    }

    private static class ContainsRequirementsOrderId implements Predicate<Project> {

        private java.lang.String value;

        public ContainsRequirementsOrderId(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.containsRequirementsOrderId(value);
        }

    }

    // -----------------------------------------------------------
    // - lastTaskNumber
    // -----------------------------------------------------------

    private final Cache<Integer,Set<Project>> projectsByLastTaskNumberCache = new Cache<Integer,Set<Project>>(
            new Cache.Factory<Integer,Set<Project>>() {
                public Set<Project> create(Integer lastTaskNumber) {
                    return getEntities(new IsLastTaskNumber(lastTaskNumber));
                }
            });

    public final Set<Project> getProjectsByLastTaskNumber(int lastTaskNumber) {
        return projectsByLastTaskNumberCache.get(lastTaskNumber);
    }
    private Set<Integer> lastTaskNumbersCache;

    public final Set<Integer> getLastTaskNumbers() {
        if (lastTaskNumbersCache == null) {
            lastTaskNumbersCache = new HashSet<Integer>();
            for (Project e : getEntities()) {
                lastTaskNumbersCache.add(e.getLastTaskNumber());
            }
        }
        return lastTaskNumbersCache;
    }

    private static class IsLastTaskNumber implements Predicate<Project> {

        private int value;

        public IsLastTaskNumber(int value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.isLastTaskNumber(value);
        }

    }

    // -----------------------------------------------------------
    // - lastRequirementNumber
    // -----------------------------------------------------------

    private final Cache<Integer,Set<Project>> projectsByLastRequirementNumberCache = new Cache<Integer,Set<Project>>(
            new Cache.Factory<Integer,Set<Project>>() {
                public Set<Project> create(Integer lastRequirementNumber) {
                    return getEntities(new IsLastRequirementNumber(lastRequirementNumber));
                }
            });

    public final Set<Project> getProjectsByLastRequirementNumber(int lastRequirementNumber) {
        return projectsByLastRequirementNumberCache.get(lastRequirementNumber);
    }
    private Set<Integer> lastRequirementNumbersCache;

    public final Set<Integer> getLastRequirementNumbers() {
        if (lastRequirementNumbersCache == null) {
            lastRequirementNumbersCache = new HashSet<Integer>();
            for (Project e : getEntities()) {
                lastRequirementNumbersCache.add(e.getLastRequirementNumber());
            }
        }
        return lastRequirementNumbersCache;
    }

    private static class IsLastRequirementNumber implements Predicate<Project> {

        private int value;

        public IsLastRequirementNumber(int value) {
            this.value = value;
        }

        public boolean test(Project e) {
            return e.isLastRequirementNumber(value);
        }

    }

    // --- valueObject classes ---
    @Override
    protected Set<Class> getValueObjectClasses() {
        Set<Class> ret = new HashSet<Class>(super.getValueObjectClasses());
        return ret;
    }

    @Override
    public Map<String, Class> getAliases() {
        Map<String, Class> aliases = new HashMap<String, Class>(super.getAliases());
        return aliases;
    }

    // --- dependencies ---

    protected scrum.server.sprint.SprintDao sprintDao;

    public void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        this.sprintDao = sprintDao;
    }

}