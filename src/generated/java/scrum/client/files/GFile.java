// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtEntityGenerator










package scrum.client.files;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GFile
            extends scrum.client.common.AScrumGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GFile() {
    }

    public GFile(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "file";

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

    public final File setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (File) this;
        this.projectId = id;
        propertyChanged("projectId", this.projectId);
        return (File)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- filename ---

    private java.lang.String filename ;

    public final java.lang.String getFilename() {
        return this.filename ;
    }

    public final File setFilename(java.lang.String filename) {
        if (isFilename(filename)) return (File)this;
        this.filename = filename ;
        propertyChanged("filename", this.filename);
        return (File)this;
    }

    public final boolean isFilename(java.lang.String filename) {
        return equals(this.filename, filename);
    }

    private transient FilenameModel filenameModel;

    public FilenameModel getFilenameModel() {
        if (filenameModel == null) filenameModel = createFilenameModel();
        return filenameModel;
    }

    protected FilenameModel createFilenameModel() { return new FilenameModel(); }

    protected class FilenameModel extends ilarkesto.gwt.client.editor.ATextEditorModel {

        @Override
        public java.lang.String getValue() {
            return getFilename();
        }

        @Override
        public void setValue(java.lang.String value) {
            setFilename(value);
        }

        @Override
        public boolean isMandatory() { return true; }

        @Override
        public boolean isEditable() { return false; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- uploadTime ---

    private ilarkesto.gwt.client.DateAndTime uploadTime ;

    public final ilarkesto.gwt.client.DateAndTime getUploadTime() {
        return this.uploadTime ;
    }

    public final File setUploadTime(ilarkesto.gwt.client.DateAndTime uploadTime) {
        if (isUploadTime(uploadTime)) return (File)this;
        this.uploadTime = uploadTime ;
        propertyChanged("uploadTime", this.uploadTime);
        return (File)this;
    }

    public final boolean isUploadTime(ilarkesto.gwt.client.DateAndTime uploadTime) {
        return equals(this.uploadTime, uploadTime);
    }

    private transient UploadTimeModel uploadTimeModel;

    public UploadTimeModel getUploadTimeModel() {
        if (uploadTimeModel == null) uploadTimeModel = createUploadTimeModel();
        return uploadTimeModel;
    }

    protected UploadTimeModel createUploadTimeModel() { return new UploadTimeModel(); }

    protected class UploadTimeModel extends ilarkesto.gwt.client.editor.ADateAndTimeEditorModel {

        @Override
        public ilarkesto.gwt.client.DateAndTime getValue() {
            return getUploadTime();
        }

        @Override
        public void setValue(ilarkesto.gwt.client.DateAndTime value) {
            setUploadTime(value);
        }

        @Override
        public boolean isMandatory() { return true; }

        @Override
        public boolean isEditable() { return false; }

        @Override
        protected void onChangeValue(ilarkesto.gwt.client.DateAndTime oldValue, ilarkesto.gwt.client.DateAndTime newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final File setLabel(java.lang.String label) {
        if (isLabel(label)) return (File)this;
        this.label = label ;
        propertyChanged("label", this.label);
        return (File)this;
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

    // --- number ---

    private int number ;

    public final int getNumber() {
        return this.number ;
    }

    public final File setNumber(int number) {
        if (isNumber(number)) return (File)this;
        this.number = number ;
        propertyChanged("number", this.number);
        return (File)this;
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

    // --- note ---

    private java.lang.String note ;

    public final java.lang.String getNote() {
        return this.note ;
    }

    public final File setNote(java.lang.String note) {
        if (isNote(note)) return (File)this;
        this.note = note ;
        propertyChanged("note", this.note);
        return (File)this;
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
        public boolean isRichtext() { return true; }

        @Override
        protected void onChangeValue(java.lang.String oldValue, java.lang.String newValue) {
            super.onChangeValue(oldValue, newValue);
            addUndo(this, oldValue);
        }

    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        filename  = (java.lang.String) props.get("filename");
        String uploadTimeAsString = (String) props.get("uploadTime");
        uploadTime  =  uploadTimeAsString == null ? null : new ilarkesto.gwt.client.DateAndTime(uploadTimeAsString);
        label  = (java.lang.String) props.get("label");
        number  = (Integer) props.get("number");
        note  = (java.lang.String) props.get("note");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("filename", this.filename);
        properties.put("uploadTime", this.uploadTime == null ? null : this.uploadTime.toString());
        properties.put("label", this.label);
        properties.put("number", this.number);
        properties.put("note", this.note);
    }

    @Override
    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getFilename(), key)) return true;
        if (matchesKey(getLabel(), key)) return true;
        if (matchesKey(getNote(), key)) return true;
        return false;
    }

}