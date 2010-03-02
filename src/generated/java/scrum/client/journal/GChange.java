// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtEntityGenerator










package scrum.client.journal;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GChange
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GChange() {
    }

    public GChange(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "change";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- parent ---

    private String parentId;

    public final ilarkesto.gwt.client.AGwtEntity getParent() {
        if (parentId == null) return null;
        return getDao().getEntity(this.parentId);
    }

    public final boolean isParentSet() {
        return parentId != null;
    }

    public final Change setParent(ilarkesto.gwt.client.AGwtEntity parent) {
        String id = parent == null ? null : parent.getId();
        if (equals(this.parentId, id)) return (Change) this;
        this.parentId = id;
        propertyChanged("parentId", this.parentId);
        return (Change)this;
    }

    public final boolean isParent(ilarkesto.gwt.client.AGwtEntity parent) {
        return equals(this.parentId, parent);
    }

    // --- user ---

    private String userId;

    public final scrum.client.admin.User getUser() {
        if (userId == null) return null;
        return getDao().getUser(this.userId);
    }

    public final boolean isUserSet() {
        return userId != null;
    }

    public final Change setUser(scrum.client.admin.User user) {
        String id = user == null ? null : user.getId();
        if (equals(this.userId, id)) return (Change) this;
        this.userId = id;
        propertyChanged("userId", this.userId);
        return (Change)this;
    }

    public final boolean isUser(scrum.client.admin.User user) {
        return equals(this.userId, user);
    }

    // --- dateAndTime ---

    private ilarkesto.gwt.client.DateAndTime dateAndTime ;

    public final ilarkesto.gwt.client.DateAndTime getDateAndTime() {
        return this.dateAndTime ;
    }

    public final Change setDateAndTime(ilarkesto.gwt.client.DateAndTime dateAndTime) {
        if (isDateAndTime(dateAndTime)) return (Change)this;
        this.dateAndTime = dateAndTime ;
        propertyChanged("dateAndTime", this.dateAndTime);
        return (Change)this;
    }

    public final boolean isDateAndTime(ilarkesto.gwt.client.DateAndTime dateAndTime) {
        return equals(this.dateAndTime, dateAndTime);
    }

    private transient DateAndTimeModel dateAndTimeModel;

    public DateAndTimeModel getDateAndTimeModel() {
        if (dateAndTimeModel == null) dateAndTimeModel = createDateAndTimeModel();
        return dateAndTimeModel;
    }

    protected DateAndTimeModel createDateAndTimeModel() { return new DateAndTimeModel(); }

    protected class DateAndTimeModel extends ilarkesto.gwt.client.editor.ADateAndTimeEditorModel {

        @Override
        public ilarkesto.gwt.client.DateAndTime getValue() {
            return getDateAndTime();
        }

        @Override
        public void setValue(ilarkesto.gwt.client.DateAndTime value) {
            setDateAndTime(value);
        }

        @Override
        public boolean isMandatory() { return true; }

        @Override
        protected void onChangeValue(ilarkesto.gwt.client.DateAndTime oldValue, ilarkesto.gwt.client.DateAndTime newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- property ---

    private java.lang.String property ;

    public final java.lang.String getProperty() {
        return this.property ;
    }

    public final Change setProperty(java.lang.String property) {
        if (isProperty(property)) return (Change)this;
        this.property = property ;
        propertyChanged("property", this.property);
        return (Change)this;
    }

    public final boolean isProperty(java.lang.String property) {
        return equals(this.property, property);
    }

    private transient PropertyModel propertyModel;

    public PropertyModel getPropertyModel() {
        if (propertyModel == null) propertyModel = createPropertyModel();
        return propertyModel;
    }

    protected PropertyModel createPropertyModel() { return new PropertyModel(); }

    protected class PropertyModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getProperty();
        }

        @Override
        public void setValue(java.lang.String value) {
            setProperty(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        parentId = (String) props.get("parentId");
        userId = (String) props.get("userId");
        String dateAndTimeAsString = (String) props.get("dateAndTime");
        dateAndTime  =  dateAndTimeAsString == null ? null : new ilarkesto.gwt.client.DateAndTime(dateAndTimeAsString);
        property  = (java.lang.String) props.get("property");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("parentId", this.parentId);
        properties.put("userId", this.userId);
        properties.put("dateAndTime", this.dateAndTime == null ? null : this.dateAndTime.toString());
        properties.put("property", this.property);
    }

}