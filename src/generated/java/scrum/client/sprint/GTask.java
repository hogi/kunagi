// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtEntityGenerator










package scrum.client.sprint;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GTask
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GTask() {
    }

    public GTask(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "task";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- requirement ---

    private String requirementId;

    public final scrum.client.project.Requirement getRequirement() {
        if (requirementId == null) return null;
        return getDao().getRequirement(this.requirementId);
    }

    public final boolean isRequirementSet() {
        return requirementId != null;
    }

    public final Task setRequirement(scrum.client.project.Requirement requirement) {
        String id = requirement == null ? null : requirement.getId();
        if (equals(this.requirementId, id)) return (Task) this;
        this.requirementId = id;
        propertyChanged("requirementId", this.requirementId);
        return (Task)this;
    }

    public final boolean isRequirement(scrum.client.project.Requirement requirement) {
        return equals(this.requirementId, requirement);
    }

    // --- number ---

    private int number ;

    public final int getNumber() {
        return this.number ;
    }

    public final Task setNumber(int number) {
        if (isNumber(number)) return (Task)this;
        this.number = number ;
        propertyChanged("number", this.number);
        return (Task)this;
    }

    public final boolean isNumber(int number) {
        return equals(this.number, number);
    }

    public transient ilarkesto.gwt.client.editor.AIntegerPropertyEditor numberEditor = new ilarkesto.gwt.client.editor.AIntegerPropertyEditor() {

        @Override
        public Integer getValue() {
            return getNumber();
        }

        @Override
        public void increment() {
            setNumber(getNumber() + 1);
        }

        @Override
        public void decrement() {
            setNumber(getNumber() - 1);
        }

        @Override
        public void setValue(Integer value) {
            setNumber(value);
        }

    };

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Task setLabel(java.lang.String label) {
        if (isLabel(label)) return (Task)this;
        this.label = label ;
        propertyChanged("label", this.label);
        return (Task)this;
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

    public final Task setDescription(java.lang.String description) {
        if (isDescription(description)) return (Task)this;
        this.description = description ;
        propertyChanged("description", this.description);
        return (Task)this;
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

    // --- remainingWork ---

    private int remainingWork ;

    public final int getRemainingWork() {
        return this.remainingWork ;
    }

    public final Task setRemainingWork(int remainingWork) {
        if (isRemainingWork(remainingWork)) return (Task)this;
        this.remainingWork = remainingWork ;
        propertyChanged("remainingWork", this.remainingWork);
        return (Task)this;
    }

    public final boolean isRemainingWork(int remainingWork) {
        return equals(this.remainingWork, remainingWork);
    }

    public transient ilarkesto.gwt.client.editor.AIntegerPropertyEditor remainingWorkEditor = new ilarkesto.gwt.client.editor.AIntegerPropertyEditor() {

        @Override
        public Integer getValue() {
            return getRemainingWork();
        }

        @Override
        public void increment() {
            setRemainingWork(getRemainingWork() + 1);
        }

        @Override
        public void decrement() {
            setRemainingWork(getRemainingWork() - 1);
        }

        @Override
        public void setValue(Integer value) {
            setRemainingWork(value);
        }

    };

    // --- burnedWork ---

    private int burnedWork ;

    public final int getBurnedWork() {
        return this.burnedWork ;
    }

    public final Task setBurnedWork(int burnedWork) {
        if (isBurnedWork(burnedWork)) return (Task)this;
        this.burnedWork = burnedWork ;
        propertyChanged("burnedWork", this.burnedWork);
        return (Task)this;
    }

    public final boolean isBurnedWork(int burnedWork) {
        return equals(this.burnedWork, burnedWork);
    }

    public transient ilarkesto.gwt.client.editor.AIntegerPropertyEditor burnedWorkEditor = new ilarkesto.gwt.client.editor.AIntegerPropertyEditor() {

        @Override
        public Integer getValue() {
            return getBurnedWork();
        }

        @Override
        public void increment() {
            setBurnedWork(getBurnedWork() + 1);
        }

        @Override
        public void decrement() {
            setBurnedWork(getBurnedWork() - 1);
        }

        @Override
        public void setValue(Integer value) {
            setBurnedWork(value);
        }

    };

    // --- owner ---

    private String ownerId;

    public final scrum.client.admin.User getOwner() {
        if (ownerId == null) return null;
        return getDao().getUser(this.ownerId);
    }

    public final boolean isOwnerSet() {
        return ownerId != null;
    }

    public final Task setOwner(scrum.client.admin.User owner) {
        String id = owner == null ? null : owner.getId();
        if (equals(this.ownerId, id)) return (Task) this;
        this.ownerId = id;
        propertyChanged("ownerId", this.ownerId);
        return (Task)this;
    }

    public final boolean isOwner(scrum.client.admin.User owner) {
        return equals(this.ownerId, owner);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        requirementId = (String) props.get("requirementId");
        number  = (Integer) props.get("number");
        label  = (java.lang.String) props.get("label");
        description  = (java.lang.String) props.get("description");
        remainingWork  = (Integer) props.get("remainingWork");
        burnedWork  = (Integer) props.get("burnedWork");
        ownerId = (String) props.get("ownerId");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("requirementId", this.requirementId);
        properties.put("number", this.number);
        properties.put("label", this.label);
        properties.put("description", this.description);
        properties.put("remainingWork", this.remainingWork);
        properties.put("burnedWork", this.burnedWork);
        properties.put("ownerId", this.ownerId);
    }

}