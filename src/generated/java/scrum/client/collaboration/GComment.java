// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtEntityGenerator










package scrum.client.collaboration;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
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

    public abstract boolean isEditable();

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

    // --- published ---

    private boolean published ;

    public final boolean isPublished() {
        return this.published ;
    }

    public final Comment setPublished(boolean published) {
        if (isPublished(published)) return (Comment)this;
        this.published = published ;
        propertyChanged("published", this.published);
        return (Comment)this;
    }

    public final boolean isPublished(boolean published) {
        return equals(this.published, published);
    }

    private transient PublishedModel publishedModel;

    public PublishedModel getPublishedModel() {
        if (publishedModel == null) publishedModel = createPublishedModel();
        return publishedModel;
    }

    protected PublishedModel createPublishedModel() { return new PublishedModel(); }

    protected class PublishedModel extends ilarkesto.gwt.client.editor.ABooleanEditorModel {

        @Override
        public String getId() {
            return "Comment_published";
        }

        @Override
        public java.lang.Boolean getValue() {
            return isPublished();
        }

        @Override
        public void setValue(java.lang.Boolean value) {
            setPublished(value);
        }

        @Override
        protected void onChangeValue(java.lang.Boolean oldValue, java.lang.Boolean newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- authorName ---

    private java.lang.String authorName ;

    public final java.lang.String getAuthorName() {
        return this.authorName ;
    }

    public final Comment setAuthorName(java.lang.String authorName) {
        if (isAuthorName(authorName)) return (Comment)this;
        this.authorName = authorName ;
        propertyChanged("authorName", this.authorName);
        return (Comment)this;
    }

    public final boolean isAuthorName(java.lang.String authorName) {
        return equals(this.authorName, authorName);
    }

    private transient AuthorNameModel authorNameModel;

    public AuthorNameModel getAuthorNameModel() {
        if (authorNameModel == null) authorNameModel = createAuthorNameModel();
        return authorNameModel;
    }

    protected AuthorNameModel createAuthorNameModel() { return new AuthorNameModel(); }

    protected class AuthorNameModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "Comment_authorName";
        }

        @Override
        public java.lang.String getValue() {
            return getAuthorName();
        }

        @Override
        public void setValue(java.lang.String value) {
            setAuthorName(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- authorEmail ---

    private java.lang.String authorEmail ;

    public final java.lang.String getAuthorEmail() {
        return this.authorEmail ;
    }

    public final Comment setAuthorEmail(java.lang.String authorEmail) {
        if (isAuthorEmail(authorEmail)) return (Comment)this;
        this.authorEmail = authorEmail ;
        propertyChanged("authorEmail", this.authorEmail);
        return (Comment)this;
    }

    public final boolean isAuthorEmail(java.lang.String authorEmail) {
        return equals(this.authorEmail, authorEmail);
    }

    private transient AuthorEmailModel authorEmailModel;

    public AuthorEmailModel getAuthorEmailModel() {
        if (authorEmailModel == null) authorEmailModel = createAuthorEmailModel();
        return authorEmailModel;
    }

    protected AuthorEmailModel createAuthorEmailModel() { return new AuthorEmailModel(); }

    protected class AuthorEmailModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public String getId() {
            return "Comment_authorEmail";
        }

        @Override
        public java.lang.String getValue() {
            return getAuthorEmail();
        }

        @Override
        public void setValue(java.lang.String value) {
            setAuthorEmail(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- authorNameVisible ---

    private boolean authorNameVisible ;

    public final boolean isAuthorNameVisible() {
        return this.authorNameVisible ;
    }

    public final Comment setAuthorNameVisible(boolean authorNameVisible) {
        if (isAuthorNameVisible(authorNameVisible)) return (Comment)this;
        this.authorNameVisible = authorNameVisible ;
        propertyChanged("authorNameVisible", this.authorNameVisible);
        return (Comment)this;
    }

    public final boolean isAuthorNameVisible(boolean authorNameVisible) {
        return equals(this.authorNameVisible, authorNameVisible);
    }

    private transient AuthorNameVisibleModel authorNameVisibleModel;

    public AuthorNameVisibleModel getAuthorNameVisibleModel() {
        if (authorNameVisibleModel == null) authorNameVisibleModel = createAuthorNameVisibleModel();
        return authorNameVisibleModel;
    }

    protected AuthorNameVisibleModel createAuthorNameVisibleModel() { return new AuthorNameVisibleModel(); }

    protected class AuthorNameVisibleModel extends ilarkesto.gwt.client.editor.ABooleanEditorModel {

        @Override
        public String getId() {
            return "Comment_authorNameVisible";
        }

        @Override
        public java.lang.Boolean getValue() {
            return isAuthorNameVisible();
        }

        @Override
        public void setValue(java.lang.Boolean value) {
            setAuthorNameVisible(value);
        }

        @Override
        protected void onChangeValue(java.lang.Boolean oldValue, java.lang.Boolean newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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
        public String getId() {
            return "Comment_text";
        }

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

        @Override
        public boolean isEditable() { return GComment.this.isEditable(); }

        @Override
        public boolean isRichtext() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

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
        public String getId() {
            return "Comment_dateAndTime";
        }

        @Override
        public ilarkesto.gwt.client.DateAndTime getValue() {
            return getDateAndTime();
        }

        @Override
        public void setValue(ilarkesto.gwt.client.DateAndTime value) {
            setDateAndTime(value);
        }

        @Override
        protected void onChangeValue(ilarkesto.gwt.client.DateAndTime oldValue, ilarkesto.gwt.client.DateAndTime newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        parentId = (String) props.get("parentId");
        authorId = (String) props.get("authorId");
        published  = (Boolean) props.get("published");
        authorName  = (java.lang.String) props.get("authorName");
        authorEmail  = (java.lang.String) props.get("authorEmail");
        authorNameVisible  = (Boolean) props.get("authorNameVisible");
        text  = (java.lang.String) props.get("text");
        String dateAndTimeAsString = (String) props.get("dateAndTime");
        dateAndTime  =  dateAndTimeAsString == null ? null : new ilarkesto.gwt.client.DateAndTime(dateAndTimeAsString);
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("parentId", this.parentId);
        properties.put("authorId", this.authorId);
        properties.put("published", this.published);
        properties.put("authorName", this.authorName);
        properties.put("authorEmail", this.authorEmail);
        properties.put("authorNameVisible", this.authorNameVisible);
        properties.put("text", this.text);
        properties.put("dateAndTime", this.dateAndTime == null ? null : this.dateAndTime.toString());
    }

    @Override
    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getText(), key)) return true;
        return false;
    }

}