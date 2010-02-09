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
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public abstract boolean isEditable();

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

    private transient LabelModel labelModel;

    public LabelModel getLabelModel() {
        if (labelModel == null) labelModel = createLabelModel();
        return labelModel;
    }

    protected LabelModel createLabelModel() { return new LabelModel(); }

    protected class LabelModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getLabel();
        }

        @Override
        public void setValue(java.lang.String value) {
            setLabel(value);
        }

        @Override
        public boolean isMandatory() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

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

    private transient DescriptionModel descriptionModel;

    public DescriptionModel getDescriptionModel() {
        if (descriptionModel == null) descriptionModel = createDescriptionModel();
        return descriptionModel;
    }

    protected DescriptionModel createDescriptionModel() { return new DescriptionModel(); }

    protected class DescriptionModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getDescription();
        }

        @Override
        public void setValue(java.lang.String value) {
            setDescription(value);
        }

        @Override
        public boolean isRichtext() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

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

    private transient BeginModel beginModel;

    public BeginModel getBeginModel() {
        if (beginModel == null) beginModel = createBeginModel();
        return beginModel;
    }

    protected BeginModel createBeginModel() { return new BeginModel(); }

    protected class BeginModel extends ilarkesto.gwt.client.editor.ADateEditorModel {

        @Override
        public ilarkesto.gwt.client.Date getValue() {
            return getBegin();
        }

        @Override
        public void setValue(ilarkesto.gwt.client.Date value) {
            setBegin(value);
        }

        @Override
        protected void onChangeValue(ilarkesto.gwt.client.Date oldValue, ilarkesto.gwt.client.Date newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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

    private transient EndModel endModel;

    public EndModel getEndModel() {
        if (endModel == null) endModel = createEndModel();
        return endModel;
    }

    protected EndModel createEndModel() { return new EndModel(); }

    protected class EndModel extends ilarkesto.gwt.client.editor.ADateEditorModel {

        @Override
        public ilarkesto.gwt.client.Date getValue() {
            return getEnd();
        }

        @Override
        public void setValue(ilarkesto.gwt.client.Date value) {
            setEnd(value);
        }

        @Override
        protected void onChangeValue(ilarkesto.gwt.client.Date oldValue, ilarkesto.gwt.client.Date newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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

    // --- velocity ---

    private java.lang.Integer velocity ;

    public final java.lang.Integer getVelocity() {
        return this.velocity ;
    }

    public final Project setVelocity(java.lang.Integer velocity) {
        if (isVelocity(velocity)) return (Project)this;
        this.velocity = velocity ;
        propertyChanged("velocity", this.velocity);
        return (Project)this;
    }

    public final boolean isVelocity(java.lang.Integer velocity) {
        return equals(this.velocity, velocity);
    }

    private transient VelocityModel velocityModel;

    public VelocityModel getVelocityModel() {
        if (velocityModel == null) velocityModel = createVelocityModel();
        return velocityModel;
    }

    protected VelocityModel createVelocityModel() { return new VelocityModel(); }

    protected class VelocityModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getVelocity();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setVelocity(value);
        }

            @Override
            public void increment() {
                setVelocity(getVelocity() + 1);
            }

            @Override
            public void decrement() {
                setVelocity(getVelocity() - 1);
            }

        @Override
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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

    private transient LastTaskNumberModel lastTaskNumberModel;

    public LastTaskNumberModel getLastTaskNumberModel() {
        if (lastTaskNumberModel == null) lastTaskNumberModel = createLastTaskNumberModel();
        return lastTaskNumberModel;
    }

    protected LastTaskNumberModel createLastTaskNumberModel() { return new LastTaskNumberModel(); }

    protected class LastTaskNumberModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getLastTaskNumber();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setLastTaskNumber(value);
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
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

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

    private transient LastRequirementNumberModel lastRequirementNumberModel;

    public LastRequirementNumberModel getLastRequirementNumberModel() {
        if (lastRequirementNumberModel == null) lastRequirementNumberModel = createLastRequirementNumberModel();
        return lastRequirementNumberModel;
    }

    protected LastRequirementNumberModel createLastRequirementNumberModel() { return new LastRequirementNumberModel(); }

    protected class LastRequirementNumberModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getLastRequirementNumber();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setLastRequirementNumber(value);
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
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

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

    private transient LastQualityNumberModel lastQualityNumberModel;

    public LastQualityNumberModel getLastQualityNumberModel() {
        if (lastQualityNumberModel == null) lastQualityNumberModel = createLastQualityNumberModel();
        return lastQualityNumberModel;
    }

    protected LastQualityNumberModel createLastQualityNumberModel() { return new LastQualityNumberModel(); }

    protected class LastQualityNumberModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getLastQualityNumber();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setLastQualityNumber(value);
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
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

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

    private transient LastRiskNumberModel lastRiskNumberModel;

    public LastRiskNumberModel getLastRiskNumberModel() {
        if (lastRiskNumberModel == null) lastRiskNumberModel = createLastRiskNumberModel();
        return lastRiskNumberModel;
    }

    protected LastRiskNumberModel createLastRiskNumberModel() { return new LastRiskNumberModel(); }

    protected class LastRiskNumberModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getLastRiskNumber();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setLastRiskNumber(value);
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
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

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

    private transient LastIssueNumberModel lastIssueNumberModel;

    public LastIssueNumberModel getLastIssueNumberModel() {
        if (lastIssueNumberModel == null) lastIssueNumberModel = createLastIssueNumberModel();
        return lastIssueNumberModel;
    }

    protected LastIssueNumberModel createLastIssueNumberModel() { return new LastIssueNumberModel(); }

    protected class LastIssueNumberModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getLastIssueNumber();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setLastIssueNumber(value);
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
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

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

    private transient LastImpedimentNumberModel lastImpedimentNumberModel;

    public LastImpedimentNumberModel getLastImpedimentNumberModel() {
        if (lastImpedimentNumberModel == null) lastImpedimentNumberModel = createLastImpedimentNumberModel();
        return lastImpedimentNumberModel;
    }

    protected LastImpedimentNumberModel createLastImpedimentNumberModel() { return new LastImpedimentNumberModel(); }

    protected class LastImpedimentNumberModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getLastImpedimentNumber();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setLastImpedimentNumber(value);
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
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- lastFileNumber ---

    private int lastFileNumber ;

    public final int getLastFileNumber() {
        return this.lastFileNumber ;
    }

    public final Project setLastFileNumber(int lastFileNumber) {
        if (isLastFileNumber(lastFileNumber)) return (Project)this;
        this.lastFileNumber = lastFileNumber ;
        propertyChanged("lastFileNumber", this.lastFileNumber);
        return (Project)this;
    }

    public final boolean isLastFileNumber(int lastFileNumber) {
        return equals(this.lastFileNumber, lastFileNumber);
    }

    private transient LastFileNumberModel lastFileNumberModel;

    public LastFileNumberModel getLastFileNumberModel() {
        if (lastFileNumberModel == null) lastFileNumberModel = createLastFileNumberModel();
        return lastFileNumberModel;
    }

    protected LastFileNumberModel createLastFileNumberModel() { return new LastFileNumberModel(); }

    protected class LastFileNumberModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getLastFileNumber();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setLastFileNumber(value);
        }

            @Override
            public void increment() {
                setLastFileNumber(getLastFileNumber() + 1);
            }

            @Override
            public void decrement() {
                setLastFileNumber(getLastFileNumber() - 1);
            }

        @Override
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- lastSubjectNumber ---

    private int lastSubjectNumber ;

    public final int getLastSubjectNumber() {
        return this.lastSubjectNumber ;
    }

    public final Project setLastSubjectNumber(int lastSubjectNumber) {
        if (isLastSubjectNumber(lastSubjectNumber)) return (Project)this;
        this.lastSubjectNumber = lastSubjectNumber ;
        propertyChanged("lastSubjectNumber", this.lastSubjectNumber);
        return (Project)this;
    }

    public final boolean isLastSubjectNumber(int lastSubjectNumber) {
        return equals(this.lastSubjectNumber, lastSubjectNumber);
    }

    private transient LastSubjectNumberModel lastSubjectNumberModel;

    public LastSubjectNumberModel getLastSubjectNumberModel() {
        if (lastSubjectNumberModel == null) lastSubjectNumberModel = createLastSubjectNumberModel();
        return lastSubjectNumberModel;
    }

    protected LastSubjectNumberModel createLastSubjectNumberModel() { return new LastSubjectNumberModel(); }

    protected class LastSubjectNumberModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getLastSubjectNumber();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setLastSubjectNumber(value);
        }

            @Override
            public void increment() {
                setLastSubjectNumber(getLastSubjectNumber() + 1);
            }

            @Override
            public void decrement() {
                setLastSubjectNumber(getLastSubjectNumber() - 1);
            }

        @Override
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

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

    private transient PunishmentFactorModel punishmentFactorModel;

    public PunishmentFactorModel getPunishmentFactorModel() {
        if (punishmentFactorModel == null) punishmentFactorModel = createPunishmentFactorModel();
        return punishmentFactorModel;
    }

    protected PunishmentFactorModel createPunishmentFactorModel() { return new PunishmentFactorModel(); }

    protected class PunishmentFactorModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getPunishmentFactor();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setPunishmentFactor(value);
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
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

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

    private transient PunishmentUnitModel punishmentUnitModel;

    public PunishmentUnitModel getPunishmentUnitModel() {
        if (punishmentUnitModel == null) punishmentUnitModel = createPunishmentUnitModel();
        return punishmentUnitModel;
    }

    protected PunishmentUnitModel createPunishmentUnitModel() { return new PunishmentUnitModel(); }

    protected class PunishmentUnitModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getPunishmentUnit();
        }

        @Override
        public void setValue(java.lang.String value) {
            setPunishmentUnit(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

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
        velocity  = (java.lang.Integer) props.get("velocity");
        requirementsOrderIds  = (java.util.List<java.lang.String>) props.get("requirementsOrderIds");
        lastTaskNumber  = (Integer) props.get("lastTaskNumber");
        lastRequirementNumber  = (Integer) props.get("lastRequirementNumber");
        lastQualityNumber  = (Integer) props.get("lastQualityNumber");
        lastRiskNumber  = (Integer) props.get("lastRiskNumber");
        lastIssueNumber  = (Integer) props.get("lastIssueNumber");
        lastImpedimentNumber  = (Integer) props.get("lastImpedimentNumber");
        lastFileNumber  = (Integer) props.get("lastFileNumber");
        lastSubjectNumber  = (Integer) props.get("lastSubjectNumber");
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
        properties.put("velocity", this.velocity);
        properties.put("requirementsOrderIds", this.requirementsOrderIds);
        properties.put("lastTaskNumber", this.lastTaskNumber);
        properties.put("lastRequirementNumber", this.lastRequirementNumber);
        properties.put("lastQualityNumber", this.lastQualityNumber);
        properties.put("lastRiskNumber", this.lastRiskNumber);
        properties.put("lastIssueNumber", this.lastIssueNumber);
        properties.put("lastImpedimentNumber", this.lastImpedimentNumber);
        properties.put("lastFileNumber", this.lastFileNumber);
        properties.put("lastSubjectNumber", this.lastSubjectNumber);
        properties.put("punishmentFactor", this.punishmentFactor);
        properties.put("punishmentUnit", this.punishmentUnit);
    }

    @Override
    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getLabel(), key)) return true;
        if (matchesKey(getDescription(), key)) return true;
        return false;
    }

}