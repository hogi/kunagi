// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.files;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GFile
            extends AEntity
            implements ilarkesto.auth.ViewProtected<scrum.server.admin.User>, ilarkesto.search.Searchable, java.lang.Comparable<File> {

    // --- AEntity ---

    public final FileDao getDao() {
        return fileDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("filename", this.filename);
        properties.put("uploadTime", this.uploadTime == null ? null : this.uploadTime.toString());
        properties.put("label", this.label);
        properties.put("number", this.number);
    }

    public int compareTo(File other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GFile.class);

    public static final String TYPE = "file";


    // -----------------------------------------------------------
    // - Searchable
    // -----------------------------------------------------------

    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getFilename(), key)) return true;
        if (matchesKey(getLabel(), key)) return true;
        return false;
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private String projectId;
    private transient scrum.server.project.Project projectCache;

    private void updateProjectCache() {
        projectCache = this.projectId == null ? null : (scrum.server.project.Project)projectDao.getById(this.projectId);
    }

    public final scrum.server.project.Project getProject() {
        if (projectCache == null) updateProjectCache();
        return projectCache;
    }

    public final void setProject(scrum.server.project.Project project) {
        project = prepareProject(project);
        if (isProject(project)) return;
        this.projectId = project == null ? null : project.getId();
        projectCache = project;
        fireModified();
    }

    protected scrum.server.project.Project prepareProject(scrum.server.project.Project project) {
        return project;
    }

    protected void repairDeadProjectReference(String entityId) {
        if (entityId.equals(this.projectId)) {
            repairMissingMaster();
        }
    }

    public final boolean isProjectSet() {
        return this.projectId != null;
    }

    public final boolean isProject(scrum.server.project.Project project) {
        if (this.projectId == null && project == null) return true;
        return project != null && project.getId().equals(this.projectId);
    }

    protected final void updateProject(Object value) {
        setProject(value == null ? null : (scrum.server.project.Project)projectDao.getById((String)value));
    }

    // -----------------------------------------------------------
    // - filename
    // -----------------------------------------------------------

    private java.lang.String filename;

    public final java.lang.String getFilename() {
        return filename;
    }

    public final void setFilename(java.lang.String filename) {
        filename = prepareFilename(filename);
        if (isFilename(filename)) return;
        this.filename = filename;
        fireModified();
    }

    protected java.lang.String prepareFilename(java.lang.String filename) {
        filename = Str.removeUnreadableChars(filename);
        return filename;
    }

    public final boolean isFilenameSet() {
        return this.filename != null;
    }

    public final boolean isFilename(java.lang.String filename) {
        if (this.filename == null && filename == null) return true;
        return this.filename != null && this.filename.equals(filename);
    }

    protected final void updateFilename(Object value) {
        setFilename((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - uploadTime
    // -----------------------------------------------------------

    private ilarkesto.base.time.DateAndTime uploadTime;

    public final ilarkesto.base.time.DateAndTime getUploadTime() {
        return uploadTime;
    }

    public final void setUploadTime(ilarkesto.base.time.DateAndTime uploadTime) {
        uploadTime = prepareUploadTime(uploadTime);
        if (isUploadTime(uploadTime)) return;
        this.uploadTime = uploadTime;
        fireModified();
    }

    protected ilarkesto.base.time.DateAndTime prepareUploadTime(ilarkesto.base.time.DateAndTime uploadTime) {
        return uploadTime;
    }

    public final boolean isUploadTimeSet() {
        return this.uploadTime != null;
    }

    public final boolean isUploadTime(ilarkesto.base.time.DateAndTime uploadTime) {
        if (this.uploadTime == null && uploadTime == null) return true;
        return this.uploadTime != null && this.uploadTime.equals(uploadTime);
    }

    protected final void updateUploadTime(Object value) {
        value = value == null ? null : new ilarkesto.base.time.DateAndTime((String)value);
        setUploadTime((ilarkesto.base.time.DateAndTime)value);
    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private java.lang.String label;

    public final java.lang.String getLabel() {
        return label;
    }

    public final void setLabel(java.lang.String label) {
        label = prepareLabel(label);
        if (isLabel(label)) return;
        this.label = label;
        fireModified();
    }

    protected java.lang.String prepareLabel(java.lang.String label) {
        label = Str.removeUnreadableChars(label);
        return label;
    }

    public final boolean isLabelSet() {
        return this.label != null;
    }

    public final boolean isLabel(java.lang.String label) {
        if (this.label == null && label == null) return true;
        return this.label != null && this.label.equals(label);
    }

    protected final void updateLabel(Object value) {
        setLabel((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - number
    // -----------------------------------------------------------

    private int number;

    public final int getNumber() {
        return number;
    }

    public final void setNumber(int number) {
        number = prepareNumber(number);
        if (isNumber(number)) return;
        this.number = number;
        fireModified();
    }

    protected int prepareNumber(int number) {
        return number;
    }

    public final boolean isNumber(int number) {
        return this.number == number;
    }

    protected final void updateNumber(Object value) {
        setNumber((Integer)value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("projectId")) updateProject(value);
            if (property.equals("filename")) updateFilename(value);
            if (property.equals("uploadTime")) updateUploadTime(value);
            if (property.equals("label")) updateLabel(value);
            if (property.equals("number")) updateNumber(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadProjectReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        if (!isProjectSet()) {
            repairMissingMaster();
            return;
        }
        try {
            getProject();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead project reference");
            repairDeadProjectReference(this.projectId);
        }
    }


    // -----------------------------------------------------------
    // - dependencies
    // -----------------------------------------------------------

    static scrum.server.project.ProjectDao projectDao;

    public static final void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        GFile.projectDao = projectDao;
    }

    static FileDao fileDao;

    public static final void setFileDao(FileDao fileDao) {
        GFile.fileDao = fileDao;
    }

}