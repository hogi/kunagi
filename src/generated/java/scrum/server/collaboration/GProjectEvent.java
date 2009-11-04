// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.collaboration;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GProjectEvent
            extends AEntity
            implements ilarkesto.search.Searchable, java.lang.Comparable<ProjectEvent> {

    // --- AEntity ---

    public final ProjectEventDao getDao() {
        return projectEventDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("text", this.text);
        properties.put("timestamp", this.timestamp == null ? null : this.timestamp.toString());
    }

    public int compareTo(ProjectEvent other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GProjectEvent.class);

    public static final String TYPE = "projectEvent";


    // -----------------------------------------------------------
    // - Searchable
    // -----------------------------------------------------------

    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getText(), key)) return true;
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
    // - text
    // -----------------------------------------------------------

    private java.lang.String text;

    public final java.lang.String getText() {
        return text;
    }

    public final void setText(java.lang.String text) {
        text = prepareText(text);
        if (isText(text)) return;
        this.text = text;
        fireModified();
    }

    protected java.lang.String prepareText(java.lang.String text) {
        text = Str.removeUnreadableChars(text);
        return text;
    }

    public final boolean isTextSet() {
        return this.text != null;
    }

    public final boolean isText(java.lang.String text) {
        if (this.text == null && text == null) return true;
        return this.text != null && this.text.equals(text);
    }

    protected final void updateText(Object value) {
        setText((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - timestamp
    // -----------------------------------------------------------

    private ilarkesto.base.time.DateAndTime timestamp;

    public final ilarkesto.base.time.DateAndTime getTimestamp() {
        return timestamp;
    }

    public final void setTimestamp(ilarkesto.base.time.DateAndTime timestamp) {
        timestamp = prepareTimestamp(timestamp);
        if (isTimestamp(timestamp)) return;
        this.timestamp = timestamp;
        fireModified();
    }

    protected ilarkesto.base.time.DateAndTime prepareTimestamp(ilarkesto.base.time.DateAndTime timestamp) {
        return timestamp;
    }

    public final boolean isTimestampSet() {
        return this.timestamp != null;
    }

    public final boolean isTimestamp(ilarkesto.base.time.DateAndTime timestamp) {
        if (this.timestamp == null && timestamp == null) return true;
        return this.timestamp != null && this.timestamp.equals(timestamp);
    }

    protected final void updateTimestamp(Object value) {
        value = value == null ? null : new ilarkesto.base.time.DateAndTime((String)value);
        setTimestamp((ilarkesto.base.time.DateAndTime)value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("projectId")) updateProject(value);
            if (property.equals("text")) updateText(value);
            if (property.equals("timestamp")) updateTimestamp(value);
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
        GProjectEvent.projectDao = projectDao;
    }

    static ProjectEventDao projectEventDao;

    public static final void setProjectEventDao(ProjectEventDao projectEventDao) {
        GProjectEvent.projectEventDao = projectEventDao;
    }

}