// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtEntityGenerator










package scrum.client.project;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GProject
            extends ilarkesto.gwt.client.AGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GProject() {
    }

    public GProject(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "project";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Project setLabel(java.lang.String label) {
        if (isLabel(label)) return (Project)this;
        this.label = label ;
        propertyChanged("label", this.label);
        return (Project)this;
    }

    public final boolean isLabel(java.lang.String label) {
        return equals(this.label, label);
    }

    public transient ilarkesto.gwt.client.editor.ATextPropertyEditor labelEditor = new ilarkesto.gwt.client.editor.ATextPropertyEditor() {

        @Override
        public String getValue() {
            return getLabel();
        }

        @Override
        public void setValue(String value) {
            setLabel(value);
        }


        @Override
        public boolean isMandatory() { return true; }
    };

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final Project setDescription(java.lang.String description) {
        if (isDescription(description)) return (Project)this;
        this.description = description ;
        propertyChanged("description", this.description);
        return (Project)this;
    }

    public final boolean isDescription(java.lang.String description) {
        return equals(this.description, description);
    }

    public transient ilarkesto.gwt.client.editor.ATextPropertyEditor descriptionEditor = new ilarkesto.gwt.client.editor.ATextPropertyEditor() {

        @Override
        public String getValue() {
            return getDescription();
        }

        @Override
        public void setValue(String value) {
            setDescription(value);
        }

    };

    // --- begin ---

    private ilarkesto.gwt.client.Date begin ;

    public final ilarkesto.gwt.client.Date getBegin() {
        return this.begin ;
    }

    public final Project setBegin(ilarkesto.gwt.client.Date begin) {
        if (isBegin(begin)) return (Project)this;
        this.begin = begin ;
        propertyChanged("begin", this.begin);
        return (Project)this;
    }

    public final boolean isBegin(ilarkesto.gwt.client.Date begin) {
        return equals(this.begin, begin);
    }

    // --- end ---

    private ilarkesto.gwt.client.Date end ;

    public final ilarkesto.gwt.client.Date getEnd() {
        return this.end ;
    }

    public final Project setEnd(ilarkesto.gwt.client.Date end) {
        if (isEnd(end)) return (Project)this;
        this.end = end ;
        propertyChanged("end", this.end);
        return (Project)this;
    }

    public final boolean isEnd(ilarkesto.gwt.client.Date end) {
        return equals(this.end, end);
    }

    // --- participants ---

    private Set<String> participantsIds = new HashSet<String>();

    public final java.util.Set<scrum.client.admin.User> getParticipants() {
        if ( participantsIds.isEmpty()) return Collections.emptySet();
        return getDao().getUsers(this.participantsIds);
    }

    public final void setParticipants(Collection<scrum.client.admin.User> values) {
        participantsIds = ilarkesto.gwt.client.Gwt.getIdsAsSet(values);
        propertyChanged("participantsIds", this.participantsIds);
    }

    public final void addParticipant(scrum.client.admin.User participant) {
        String id = participant.getId();
        if (participantsIds.contains(id)) return;
        participantsIds.add(id);
        propertyChanged("participantsIds", this.participantsIds);
    }

    public final void removeParticipant(scrum.client.admin.User participant) {
        String id = participant.getId();
        if (!participantsIds.contains(id)) return;
        participantsIds.remove(id);
        propertyChanged("participantsIds", this.participantsIds);
    }

    // --- admins ---

    private Set<String> adminsIds = new HashSet<String>();

    public final java.util.Set<scrum.client.admin.User> getAdmins() {
        if ( adminsIds.isEmpty()) return Collections.emptySet();
        return getDao().getUsers(this.adminsIds);
    }

    public final void setAdmins(Collection<scrum.client.admin.User> values) {
        adminsIds = ilarkesto.gwt.client.Gwt.getIdsAsSet(values);
        propertyChanged("adminsIds", this.adminsIds);
    }

    public final void addAdmin(scrum.client.admin.User admin) {
        String id = admin.getId();
        if (adminsIds.contains(id)) return;
        adminsIds.add(id);
        propertyChanged("adminsIds", this.adminsIds);
    }

    public final void removeAdmin(scrum.client.admin.User admin) {
        String id = admin.getId();
        if (!adminsIds.contains(id)) return;
        adminsIds.remove(id);
        propertyChanged("adminsIds", this.adminsIds);
    }

    // --- productOwners ---

    private Set<String> productOwnersIds = new HashSet<String>();

    public final java.util.Set<scrum.client.admin.User> getProductOwners() {
        if ( productOwnersIds.isEmpty()) return Collections.emptySet();
        return getDao().getUsers(this.productOwnersIds);
    }

    public final void setProductOwners(Collection<scrum.client.admin.User> values) {
        productOwnersIds = ilarkesto.gwt.client.Gwt.getIdsAsSet(values);
        propertyChanged("productOwnersIds", this.productOwnersIds);
    }

    public final void addProductOwner(scrum.client.admin.User productOwner) {
        String id = productOwner.getId();
        if (productOwnersIds.contains(id)) return;
        productOwnersIds.add(id);
        propertyChanged("productOwnersIds", this.productOwnersIds);
    }

    public final void removeProductOwner(scrum.client.admin.User productOwner) {
        String id = productOwner.getId();
        if (!productOwnersIds.contains(id)) return;
        productOwnersIds.remove(id);
        propertyChanged("productOwnersIds", this.productOwnersIds);
    }

    // --- scrumMasters ---

    private Set<String> scrumMastersIds = new HashSet<String>();

    public final java.util.Set<scrum.client.admin.User> getScrumMasters() {
        if ( scrumMastersIds.isEmpty()) return Collections.emptySet();
        return getDao().getUsers(this.scrumMastersIds);
    }

    public final void setScrumMasters(Collection<scrum.client.admin.User> values) {
        scrumMastersIds = ilarkesto.gwt.client.Gwt.getIdsAsSet(values);
        propertyChanged("scrumMastersIds", this.scrumMastersIds);
    }

    public final void addScrumMaster(scrum.client.admin.User scrumMaster) {
        String id = scrumMaster.getId();
        if (scrumMastersIds.contains(id)) return;
        scrumMastersIds.add(id);
        propertyChanged("scrumMastersIds", this.scrumMastersIds);
    }

    public final void removeScrumMaster(scrum.client.admin.User scrumMaster) {
        String id = scrumMaster.getId();
        if (!scrumMastersIds.contains(id)) return;
        scrumMastersIds.remove(id);
        propertyChanged("scrumMastersIds", this.scrumMastersIds);
    }

    // --- teamMembers ---

    private Set<String> teamMembersIds = new HashSet<String>();

    public final java.util.Set<scrum.client.admin.User> getTeamMembers() {
        if ( teamMembersIds.isEmpty()) return Collections.emptySet();
        return getDao().getUsers(this.teamMembersIds);
    }

    public final void setTeamMembers(Collection<scrum.client.admin.User> values) {
        teamMembersIds = ilarkesto.gwt.client.Gwt.getIdsAsSet(values);
        propertyChanged("teamMembersIds", this.teamMembersIds);
    }

    public final void addTeamMember(scrum.client.admin.User teamMember) {
        String id = teamMember.getId();
        if (teamMembersIds.contains(id)) return;
        teamMembersIds.add(id);
        propertyChanged("teamMembersIds", this.teamMembersIds);
    }

    public final void removeTeamMember(scrum.client.admin.User teamMember) {
        String id = teamMember.getId();
        if (!teamMembersIds.contains(id)) return;
        teamMembersIds.remove(id);
        propertyChanged("teamMembersIds", this.teamMembersIds);
    }

    // --- currentSprint ---

    private String currentSprintId;

    public final scrum.client.sprint.Sprint getCurrentSprint() {
        if (currentSprintId == null) return null;
        return getDao().getSprint(this.currentSprintId);
    }

    public final boolean isCurrentSprintSet() {
        return currentSprintId != null;
    }

    public final Project setCurrentSprint(scrum.client.sprint.Sprint currentSprint) {
        String id = currentSprint == null ? null : currentSprint.getId();
        if (equals(this.currentSprintId, id)) return (Project) this;
        this.currentSprintId = id;
        propertyChanged("currentSprintId", this.currentSprintId);
        return (Project)this;
    }

    public final boolean isCurrentSprint(scrum.client.sprint.Sprint currentSprint) {
        return equals(this.currentSprintId, currentSprint);
    }

    // --- nextSprint ---

    private String nextSprintId;

    public final scrum.client.sprint.Sprint getNextSprint() {
        if (nextSprintId == null) return null;
        return getDao().getSprint(this.nextSprintId);
    }

    public final boolean isNextSprintSet() {
        return nextSprintId != null;
    }

    public final Project setNextSprint(scrum.client.sprint.Sprint nextSprint) {
        String id = nextSprint == null ? null : nextSprint.getId();
        if (equals(this.nextSprintId, id)) return (Project) this;
        this.nextSprintId = id;
        propertyChanged("nextSprintId", this.nextSprintId);
        return (Project)this;
    }

    public final boolean isNextSprint(scrum.client.sprint.Sprint nextSprint) {
        return equals(this.nextSprintId, nextSprint);
    }

    // --- requirementsOrderIds ---

    private java.util.List<java.lang.String> requirementsOrderIds = new java.util.ArrayList<java.lang.String>();

    public final java.util.List<java.lang.String> getRequirementsOrderIds() {
        return new java.util.ArrayList<java.lang.String>(requirementsOrderIds);
    }

    public final void setRequirementsOrderIds(java.util.List<java.lang.String> requirementsOrderIds) {
        if (requirementsOrderIds == null) throw new IllegalArgumentException("null is not allowed");
        if (this.requirementsOrderIds.equals(requirementsOrderIds)) return;
        this.requirementsOrderIds = new java.util.ArrayList<java.lang.String>(requirementsOrderIds);
        propertyChanged("requirementsOrderIds", this.requirementsOrderIds);
    }

    // --- lastTaskNumber ---

    private int lastTaskNumber ;

    public final int getLastTaskNumber() {
        return this.lastTaskNumber ;
    }

    public final Project setLastTaskNumber(int lastTaskNumber) {
        if (isLastTaskNumber(lastTaskNumber)) return (Project)this;
        this.lastTaskNumber = lastTaskNumber ;
        propertyChanged("lastTaskNumber", this.lastTaskNumber);
        return (Project)this;
    }

    public final boolean isLastTaskNumber(int lastTaskNumber) {
        return equals(this.lastTaskNumber, lastTaskNumber);
    }

    public transient ilarkesto.gwt.client.editor.AIntegerPropertyEditor lastTaskNumberEditor = new ilarkesto.gwt.client.editor.AIntegerPropertyEditor() {

        @Override
        public Integer getValue() {
            return getLastTaskNumber();
        }

        @Override
        public void increment() {
            setLastTaskNumber(getLastTaskNumber() + 1);
        }

        @Override
        public void decrement() {
            setLastTaskNumber(getLastTaskNumber() - 1);
        }

        @Override
        public void setValue(Integer value) {
            setLastTaskNumber(value);
        }

    };

    // --- lastRequirementNumber ---

    private int lastRequirementNumber ;

    public final int getLastRequirementNumber() {
        return this.lastRequirementNumber ;
    }

    public final Project setLastRequirementNumber(int lastRequirementNumber) {
        if (isLastRequirementNumber(lastRequirementNumber)) return (Project)this;
        this.lastRequirementNumber = lastRequirementNumber ;
        propertyChanged("lastRequirementNumber", this.lastRequirementNumber);
        return (Project)this;
    }

    public final boolean isLastRequirementNumber(int lastRequirementNumber) {
        return equals(this.lastRequirementNumber, lastRequirementNumber);
    }

    public transient ilarkesto.gwt.client.editor.AIntegerPropertyEditor lastRequirementNumberEditor = new ilarkesto.gwt.client.editor.AIntegerPropertyEditor() {

        @Override
        public Integer getValue() {
            return getLastRequirementNumber();
        }

        @Override
        public void increment() {
            setLastRequirementNumber(getLastRequirementNumber() + 1);
        }

        @Override
        public void decrement() {
            setLastRequirementNumber(getLastRequirementNumber() - 1);
        }

        @Override
        public void setValue(Integer value) {
            setLastRequirementNumber(value);
        }

    };

    // --- lastQualityNumber ---

    private int lastQualityNumber ;

    public final int getLastQualityNumber() {
        return this.lastQualityNumber ;
    }

    public final Project setLastQualityNumber(int lastQualityNumber) {
        if (isLastQualityNumber(lastQualityNumber)) return (Project)this;
        this.lastQualityNumber = lastQualityNumber ;
        propertyChanged("lastQualityNumber", this.lastQualityNumber);
        return (Project)this;
    }

    public final boolean isLastQualityNumber(int lastQualityNumber) {
        return equals(this.lastQualityNumber, lastQualityNumber);
    }

    public transient ilarkesto.gwt.client.editor.AIntegerPropertyEditor lastQualityNumberEditor = new ilarkesto.gwt.client.editor.AIntegerPropertyEditor() {

        @Override
        public Integer getValue() {
            return getLastQualityNumber();
        }

        @Override
        public void increment() {
            setLastQualityNumber(getLastQualityNumber() + 1);
        }

        @Override
        public void decrement() {
            setLastQualityNumber(getLastQualityNumber() - 1);
        }

        @Override
        public void setValue(Integer value) {
            setLastQualityNumber(value);
        }

    };

    // --- lastRiskNumber ---

    private int lastRiskNumber ;

    public final int getLastRiskNumber() {
        return this.lastRiskNumber ;
    }

    public final Project setLastRiskNumber(int lastRiskNumber) {
        if (isLastRiskNumber(lastRiskNumber)) return (Project)this;
        this.lastRiskNumber = lastRiskNumber ;
        propertyChanged("lastRiskNumber", this.lastRiskNumber);
        return (Project)this;
    }

    public final boolean isLastRiskNumber(int lastRiskNumber) {
        return equals(this.lastRiskNumber, lastRiskNumber);
    }

    public transient ilarkesto.gwt.client.editor.AIntegerPropertyEditor lastRiskNumberEditor = new ilarkesto.gwt.client.editor.AIntegerPropertyEditor() {

        @Override
        public Integer getValue() {
            return getLastRiskNumber();
        }

        @Override
        public void increment() {
            setLastRiskNumber(getLastRiskNumber() + 1);
        }

        @Override
        public void decrement() {
            setLastRiskNumber(getLastRiskNumber() - 1);
        }

        @Override
        public void setValue(Integer value) {
            setLastRiskNumber(value);
        }

    };

    // --- lastIssueNumber ---

    private int lastIssueNumber ;

    public final int getLastIssueNumber() {
        return this.lastIssueNumber ;
    }

    public final Project setLastIssueNumber(int lastIssueNumber) {
        if (isLastIssueNumber(lastIssueNumber)) return (Project)this;
        this.lastIssueNumber = lastIssueNumber ;
        propertyChanged("lastIssueNumber", this.lastIssueNumber);
        return (Project)this;
    }

    public final boolean isLastIssueNumber(int lastIssueNumber) {
        return equals(this.lastIssueNumber, lastIssueNumber);
    }

    public transient ilarkesto.gwt.client.editor.AIntegerPropertyEditor lastIssueNumberEditor = new ilarkesto.gwt.client.editor.AIntegerPropertyEditor() {

        @Override
        public Integer getValue() {
            return getLastIssueNumber();
        }

        @Override
        public void increment() {
            setLastIssueNumber(getLastIssueNumber() + 1);
        }

        @Override
        public void decrement() {
            setLastIssueNumber(getLastIssueNumber() - 1);
        }

        @Override
        public void setValue(Integer value) {
            setLastIssueNumber(value);
        }

    };

    // --- lastImpedimentNumber ---

    private int lastImpedimentNumber ;

    public final int getLastImpedimentNumber() {
        return this.lastImpedimentNumber ;
    }

    public final Project setLastImpedimentNumber(int lastImpedimentNumber) {
        if (isLastImpedimentNumber(lastImpedimentNumber)) return (Project)this;
        this.lastImpedimentNumber = lastImpedimentNumber ;
        propertyChanged("lastImpedimentNumber", this.lastImpedimentNumber);
        return (Project)this;
    }

    public final boolean isLastImpedimentNumber(int lastImpedimentNumber) {
        return equals(this.lastImpedimentNumber, lastImpedimentNumber);
    }

    public transient ilarkesto.gwt.client.editor.AIntegerPropertyEditor lastImpedimentNumberEditor = new ilarkesto.gwt.client.editor.AIntegerPropertyEditor() {

        @Override
        public Integer getValue() {
            return getLastImpedimentNumber();
        }

        @Override
        public void increment() {
            setLastImpedimentNumber(getLastImpedimentNumber() + 1);
        }

        @Override
        public void decrement() {
            setLastImpedimentNumber(getLastImpedimentNumber() - 1);
        }

        @Override
        public void setValue(Integer value) {
            setLastImpedimentNumber(value);
        }

    };

    // --- punishmentFactor ---

    private int punishmentFactor ;

    public final int getPunishmentFactor() {
        return this.punishmentFactor ;
    }

    public final Project setPunishmentFactor(int punishmentFactor) {
        if (isPunishmentFactor(punishmentFactor)) return (Project)this;
        this.punishmentFactor = punishmentFactor ;
        propertyChanged("punishmentFactor", this.punishmentFactor);
        return (Project)this;
    }

    public final boolean isPunishmentFactor(int punishmentFactor) {
        return equals(this.punishmentFactor, punishmentFactor);
    }

    public transient ilarkesto.gwt.client.editor.AIntegerPropertyEditor punishmentFactorEditor = new ilarkesto.gwt.client.editor.AIntegerPropertyEditor() {

        @Override
        public Integer getValue() {
            return getPunishmentFactor();
        }

        @Override
        public void increment() {
            setPunishmentFactor(getPunishmentFactor() + 1);
        }

        @Override
        public void decrement() {
            setPunishmentFactor(getPunishmentFactor() - 1);
        }

        @Override
        public void setValue(Integer value) {
            setPunishmentFactor(value);
        }

    };

    // --- punishmentUnit ---

    private java.lang.String punishmentUnit ;

    public final java.lang.String getPunishmentUnit() {
        return this.punishmentUnit ;
    }

    public final Project setPunishmentUnit(java.lang.String punishmentUnit) {
        if (isPunishmentUnit(punishmentUnit)) return (Project)this;
        this.punishmentUnit = punishmentUnit ;
        propertyChanged("punishmentUnit", this.punishmentUnit);
        return (Project)this;
    }

    public final boolean isPunishmentUnit(java.lang.String punishmentUnit) {
        return equals(this.punishmentUnit, punishmentUnit);
    }

    public transient ilarkesto.gwt.client.editor.ATextPropertyEditor punishmentUnitEditor = new ilarkesto.gwt.client.editor.ATextPropertyEditor() {

        @Override
        public String getValue() {
            return getPunishmentUnit();
        }

        @Override
        public void setValue(String value) {
            setPunishmentUnit(value);
        }

    };

    // --- update properties by map ---

    public void updateProperties(Map props) {
        label  = (java.lang.String) props.get("label");
        description  = (java.lang.String) props.get("description");
        String beginAsString = (String) props.get("begin");
        begin  =  beginAsString == null ? null : new ilarkesto.gwt.client.Date(beginAsString);
        String endAsString = (String) props.get("end");
        end  =  endAsString == null ? null : new ilarkesto.gwt.client.Date(endAsString);
        participantsIds = (Set<String>) props.get("participantsIds");
        adminsIds = (Set<String>) props.get("adminsIds");
        productOwnersIds = (Set<String>) props.get("productOwnersIds");
        scrumMastersIds = (Set<String>) props.get("scrumMastersIds");
        teamMembersIds = (Set<String>) props.get("teamMembersIds");
        currentSprintId = (String) props.get("currentSprintId");
        nextSprintId = (String) props.get("nextSprintId");
        requirementsOrderIds  = (java.util.List<java.lang.String>) props.get("requirementsOrderIds");
        lastTaskNumber  = (Integer) props.get("lastTaskNumber");
        lastRequirementNumber  = (Integer) props.get("lastRequirementNumber");
        lastQualityNumber  = (Integer) props.get("lastQualityNumber");
        lastRiskNumber  = (Integer) props.get("lastRiskNumber");
        lastIssueNumber  = (Integer) props.get("lastIssueNumber");
        lastImpedimentNumber  = (Integer) props.get("lastImpedimentNumber");
        punishmentFactor  = (Integer) props.get("punishmentFactor");
        punishmentUnit  = (java.lang.String) props.get("punishmentUnit");
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
        properties.put("lastTaskNumber", this.lastTaskNumber);
        properties.put("lastRequirementNumber", this.lastRequirementNumber);
        properties.put("lastQualityNumber", this.lastQualityNumber);
        properties.put("lastRiskNumber", this.lastRiskNumber);
        properties.put("lastIssueNumber", this.lastIssueNumber);
        properties.put("lastImpedimentNumber", this.lastImpedimentNumber);
        properties.put("punishmentFactor", this.punishmentFactor);
        properties.put("punishmentUnit", this.punishmentUnit);
    }

}