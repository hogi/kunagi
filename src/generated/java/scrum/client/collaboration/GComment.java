// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtEntityGenerator










package scrum.client.collaboration;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GComment
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GComment() {
    }

    public GComment(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "comment";

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

    public final Comment setParent(ilarkesto.gwt.client.AGwtEntity parent) {
        String id = parent == null ? null : parent.getId();
        if (equals(this.parentId, id)) return (Comment) this;
        this.parentId = id;
        propertyChanged("parentId", this.parentId);
        return (Comment)this;
    }

    public final boolean isParent(ilarkesto.gwt.client.AGwtEntity parent) {
        return equals(this.parentId, parent);
    }

    // --- author ---

    private String authorId;

    public final scrum.client.admin.User getAuthor() {
        if (authorId == null) return null;
        return getDao().getUser(this.authorId);
    }

    public final boolean isAuthorSet() {
        return authorId != null;
    }

    public final Comment setAuthor(scrum.client.admin.User author) {
        String id = author == null ? null : author.getId();
        if (equals(this.authorId, id)) return (Comment) this;
        this.authorId = id;
        propertyChanged("authorId", this.authorId);
        return (Comment)this;
    }

    public final boolean isAuthor(scrum.client.admin.User author) {
        return equals(this.authorId, author);
    }

    // --- text ---

    private java.lang.String text ;

    public final java.lang.String getText() {
        return this.text ;
    }

    public final Comment setText(java.lang.String text) {
        if (isText(text)) return (Comment)this;
        this.text = text ;
        propertyChanged("text", this.text);
        return (Comment)this;
    }

    public final boolean isText(java.lang.String text) {
        return equals(this.text, text);
    }

    private transient TextModel textModel;

    public TextModel getTextModel() {
        if (textModel == null) textModel = createTextModel();
        return textModel;
    }

    protected TextModel createTextModel() { return new TextModel(); }

    protected class TextModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getText();
        }

        @Override
        public void setValue(java.lang.String value) {
            setText(value);
        }

        @Override
        public boolean isMandatory() { return true; }

    }

    // --- dateAndTime ---

    private ilarkesto.gwt.client.DateAndTime dateAndTime ;

    public final ilarkesto.gwt.client.DateAndTime getDateAndTime() {
        return this.dateAndTime ;
    }

    public final Comment setDateAndTime(ilarkesto.gwt.client.DateAndTime dateAndTime) {
        if (isDateAndTime(dateAndTime)) return (Comment)this;
        this.dateAndTime = dateAndTime ;
        propertyChanged("dateAndTime", this.dateAndTime);
        return (Comment)this;
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

    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        parentId = (String) props.get("parentId");
        authorId = (String) props.get("authorId");
        text  = (java.lang.String) props.get("text");
        String dateAndTimeAsString = (String) props.get("dateAndTime");
        dateAndTime  =  dateAndTimeAsString == null ? null : new ilarkesto.gwt.client.DateAndTime(dateAndTimeAsString);
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("parentId", this.parentId);
        properties.put("authorId", this.authorId);
        properties.put("text", this.text);
        properties.put("dateAndTime", this.dateAndTime == null ? null : this.dateAndTime.toString());
    }

}