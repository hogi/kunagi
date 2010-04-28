// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtEntityGenerator










package scrum.client.release;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GRelease
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GRelease() {
    }

    public GRelease(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "release";

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

    public final Release setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (Release) this;
        this.projectId = id;
        propertyChanged("projectId", this.projectId);
        return (Release)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- number ---

    private int number ;

    public final int getNumber() {
        return this.number ;
    }

    public final Release setNumber(int number) {
        if (isNumber(number)) return (Release)this;
        this.number = number ;
        propertyChanged("number", this.number);
        return (Release)this;
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

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Release setLabel(java.lang.String label) {
        if (isLabel(label)) return (Release)this;
        this.label = label ;
        propertyChanged("label", this.label);
        return (Release)this;
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

    // --- note ---

    private java.lang.String note ;

    public final java.lang.String getNote() {
        return this.note ;
    }

    public final Release setNote(java.lang.String note) {
        if (isNote(note)) return (Release)this;
        this.note = note ;
        propertyChanged("note", this.note);
        return (Release)this;
    }

    public final boolean isNote(java.lang.String note) {
        return equals(this.note, note);
    }

    private transient NoteModel noteModel;

    public NoteModel getNoteModel() {
        if (noteModel == null) noteModel = createNoteModel();
        return noteModel;
    }

    protected NoteModel createNoteModel() { return new NoteModel(); }

    protected class NoteModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getNote();
        }

        @Override
        public void setValue(java.lang.String value) {
            setNote(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- publicationDate ---

    private ilarkesto.gwt.client.Date publicationDate ;

    public final ilarkesto.gwt.client.Date getPublicationDate() {
        return this.publicationDate ;
    }

    public final Release setPublicationDate(ilarkesto.gwt.client.Date publicationDate) {
        if (isPublicationDate(publicationDate)) return (Release)this;
        this.publicationDate = publicationDate ;
        propertyChanged("publicationDate", this.publicationDate);
        return (Release)this;
    }

    public final boolean isPublicationDate(ilarkesto.gwt.client.Date publicationDate) {
        return equals(this.publicationDate, publicationDate);
    }

    private transient PublicationDateModel publicationDateModel;

    public PublicationDateModel getPublicationDateModel() {
        if (publicationDateModel == null) publicationDateModel = createPublicationDateModel();
        return publicationDateModel;
    }

    protected PublicationDateModel createPublicationDateModel() { return new PublicationDateModel(); }

    protected class PublicationDateModel extends ilarkesto.gwt.client.editor.ADateEditorModel {

        @Override
        public ilarkesto.gwt.client.Date getValue() {
            return getPublicationDate();
        }

        @Override
        public void setValue(ilarkesto.gwt.client.Date value) {
            setPublicationDate(value);
        }

        @Override
        protected void onChangeValue(ilarkesto.gwt.client.Date oldValue, ilarkesto.gwt.client.Date newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- published ---

    private boolean published ;

    public final boolean isPublished() {
        return this.published ;
    }

    public final Release setPublished(boolean published) {
        if (isPublished(published)) return (Release)this;
        this.published = published ;
        propertyChanged("published", this.published);
        return (Release)this;
    }

    public final boolean isPublished(boolean published) {
        return equals(this.published, published);
    }

    // --- releaseNotes ---

    private java.lang.String releaseNotes ;

    public final java.lang.String getReleaseNotes() {
        return this.releaseNotes ;
    }

    public final Release setReleaseNotes(java.lang.String releaseNotes) {
        if (isReleaseNotes(releaseNotes)) return (Release)this;
        this.releaseNotes = releaseNotes ;
        propertyChanged("releaseNotes", this.releaseNotes);
        return (Release)this;
    }

    public final boolean isReleaseNotes(java.lang.String releaseNotes) {
        return equals(this.releaseNotes, releaseNotes);
    }

    private transient ReleaseNotesModel releaseNotesModel;

    public ReleaseNotesModel getReleaseNotesModel() {
        if (releaseNotesModel == null) releaseNotesModel = createReleaseNotesModel();
        return releaseNotesModel;
    }

    protected ReleaseNotesModel createReleaseNotesModel() { return new ReleaseNotesModel(); }

    protected class ReleaseNotesModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getReleaseNotes();
        }

        @Override
        public void setValue(java.lang.String value) {
            setReleaseNotes(value);
        }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        number  = (Integer) props.get("number");
        label  = (java.lang.String) props.get("label");
        note  = (java.lang.String) props.get("note");
        String publicationDateAsString = (String) props.get("publicationDate");
        publicationDate  =  publicationDateAsString == null ? null : new ilarkesto.gwt.client.Date(publicationDateAsString);
        published  = (Boolean) props.get("published");
        releaseNotes  = (java.lang.String) props.get("releaseNotes");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("number", this.number);
        properties.put("label", this.label);
        properties.put("note", this.note);
        properties.put("publicationDate", this.publicationDate == null ? null : this.publicationDate.toString());
        properties.put("published", this.published);
        properties.put("releaseNotes", this.releaseNotes);
    }

    @Override
    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getLabel(), key)) return true;
        if (matchesKey(getNote(), key)) return true;
        if (matchesKey(getReleaseNotes(), key)) return true;
        return false;
    }

}