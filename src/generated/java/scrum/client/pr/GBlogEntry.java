// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtEntityGenerator










package scrum.client.pr;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GBlogEntry
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GBlogEntry() {
    }

    public GBlogEntry(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "blogEntry";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- project ---

    private String projectId;

    public final scrum.client.project.Project getProject() {
        if (projectId == null) return null;
        return getDao().getProject(this.projectId);
    }

    public final boolean isProjectSet() {
        return projectId != null;
    }

    public final BlogEntry setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (BlogEntry) this;
        this.projectId = id;
        propertyChanged("projectId", this.projectId);
        return (BlogEntry)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- number ---

    private int number ;

    public final int getNumber() {
        return this.number ;
    }

    public final BlogEntry setNumber(int number) {
        if (isNumber(number)) return (BlogEntry)this;
        this.number = number ;
        propertyChanged("number", this.number);
        return (BlogEntry)this;
    }

    public final boolean isNumber(int number) {
        return equals(this.number, number);
    }

    private transient NumberModel numberModel;

    public NumberModel getNumberModel() {
        if (numberModel == null) numberModel = createNumberModel();
        return numberModel;
    }

    protected NumberModel createNumberModel() { return new NumberModel(); }

    protected class NumberModel extends ilarkesto.gwt.client.editor.AIntegerEditorModel {

        @Override
        public java.lang.Integer getValue() {
            return getNumber();
        }

        @Override
        public void setValue(java.lang.Integer value) {
            setNumber(value);
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
        public boolean isMandatory() { return true; }

        @Override
        protected void onChangeValue(java.lang.Integer oldValue, java.lang.Integer newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- authors ---

    private Set<String> authorsIds = new HashSet<String>();

    public final java.util.Set<scrum.client.admin.User> getAuthors() {
        if ( authorsIds.isEmpty()) return Collections.emptySet();
        return getDao().getUsers(this.authorsIds);
    }

    public final void setAuthors(Collection<scrum.client.admin.User> values) {
        authorsIds = ilarkesto.gwt.client.Gwt.getIdsAsSet(values);
        propertyChanged("authorsIds", this.authorsIds);
    }

    public final void addAuthor(scrum.client.admin.User author) {
        String id = author.getId();
        if (authorsIds.contains(id)) return;
        authorsIds.add(id);
        propertyChanged("authorsIds", this.authorsIds);
    }

    public final void removeAuthor(scrum.client.admin.User author) {
        String id = author.getId();
        if (!authorsIds.contains(id)) return;
        authorsIds.remove(id);
        propertyChanged("authorsIds", this.authorsIds);
    }

    // --- title ---

    private java.lang.String title ;

    public final java.lang.String getTitle() {
        return this.title ;
    }

    public final BlogEntry setTitle(java.lang.String title) {
        if (isTitle(title)) return (BlogEntry)this;
        this.title = title ;
        propertyChanged("title", this.title);
        return (BlogEntry)this;
    }

    public final boolean isTitle(java.lang.String title) {
        return equals(this.title, title);
    }

    private transient TitleModel titleModel;

    public TitleModel getTitleModel() {
        if (titleModel == null) titleModel = createTitleModel();
        return titleModel;
    }

    protected TitleModel createTitleModel() { return new TitleModel(); }

    protected class TitleModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getTitle();
        }

        @Override
        public void setValue(java.lang.String value) {
            setTitle(value);
        }

        @Override
        public boolean isMandatory() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- text ---

    private java.lang.String text ;

    public final java.lang.String getText() {
        return this.text ;
    }

    public final BlogEntry setText(java.lang.String text) {
        if (isText(text)) return (BlogEntry)this;
        this.text = text ;
        propertyChanged("text", this.text);
        return (BlogEntry)this;
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

    public final BlogEntry setDateAndTime(ilarkesto.gwt.client.DateAndTime dateAndTime) {
        if (isDateAndTime(dateAndTime)) return (BlogEntry)this;
        this.dateAndTime = dateAndTime ;
        propertyChanged("dateAndTime", this.dateAndTime);
        return (BlogEntry)this;
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
        protected void onChangeValue(ilarkesto.gwt.client.DateAndTime oldValue, ilarkesto.gwt.client.DateAndTime newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- releases ---

    private Set<String> releasesIds = new HashSet<String>();

    public final java.util.Set<scrum.client.release.Release> getReleases() {
        if ( releasesIds.isEmpty()) return Collections.emptySet();
        return getDao().getReleases(this.releasesIds);
    }

    public final void setReleases(Collection<scrum.client.release.Release> values) {
        releasesIds = ilarkesto.gwt.client.Gwt.getIdsAsSet(values);
        propertyChanged("releasesIds", this.releasesIds);
    }

    public final void addRelease(scrum.client.release.Release release) {
        String id = release.getId();
        if (releasesIds.contains(id)) return;
        releasesIds.add(id);
        propertyChanged("releasesIds", this.releasesIds);
    }

    public final void removeRelease(scrum.client.release.Release release) {
        String id = release.getId();
        if (!releasesIds.contains(id)) return;
        releasesIds.remove(id);
        propertyChanged("releasesIds", this.releasesIds);
    }

    // --- published ---

    private boolean published ;

    public final boolean isPublished() {
        return this.published ;
    }

    public final BlogEntry setPublished(boolean published) {
        if (isPublished(published)) return (BlogEntry)this;
        this.published = published ;
        propertyChanged("published", this.published);
        return (BlogEntry)this;
    }

    public final boolean isPublished(boolean published) {
        return equals(this.published, published);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        number  = (Integer) props.get("number");
        authorsIds = (Set<String>) props.get("authorsIds");
        title  = (java.lang.String) props.get("title");
        text  = (java.lang.String) props.get("text");
        String dateAndTimeAsString = (String) props.get("dateAndTime");
        dateAndTime  =  dateAndTimeAsString == null ? null : new ilarkesto.gwt.client.DateAndTime(dateAndTimeAsString);
        releasesIds = (Set<String>) props.get("releasesIds");
        published  = (Boolean) props.get("published");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("number", this.number);
        properties.put("authorsIds", this.authorsIds);
        properties.put("title", this.title);
        properties.put("text", this.text);
        properties.put("dateAndTime", this.dateAndTime == null ? null : this.dateAndTime.toString());
        properties.put("releasesIds", this.releasesIds);
        properties.put("published", this.published);
    }

    @Override
    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getTitle(), key)) return true;
        if (matchesKey(getText(), key)) return true;
        return false;
    }

}