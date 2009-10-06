// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.collaboration;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GChatMessage
            extends AEntity
            implements java.lang.Comparable<ChatMessage> {

    // --- AEntity ---

    public final ChatMessageDao getDao() {
        return chatMessageDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("authorId", this.authorId);
        properties.put("text", this.text);
        properties.put("dateAndTime", this.dateAndTime == null ? null : this.dateAndTime.toString());
    }

    public int compareTo(ChatMessage other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GChatMessage.class);

    public static final String TYPE = "chatMessage";

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
    // - author
    // -----------------------------------------------------------

    private String authorId;
    private transient scrum.server.admin.User authorCache;

    private void updateAuthorCache() {
        authorCache = this.authorId == null ? null : (scrum.server.admin.User)userDao.getById(this.authorId);
    }

    public final scrum.server.admin.User getAuthor() {
        if (authorCache == null) updateAuthorCache();
        return authorCache;
    }

    public final void setAuthor(scrum.server.admin.User author) {
        author = prepareAuthor(author);
        if (isAuthor(author)) return;
        this.authorId = author == null ? null : author.getId();
        authorCache = author;
        fireModified();
    }

    protected scrum.server.admin.User prepareAuthor(scrum.server.admin.User author) {
        return author;
    }

    protected void repairDeadAuthorReference(String entityId) {
        if (entityId.equals(this.authorId)) {
            this.authorId = null;
            fireModified();
        }
    }

    public final boolean isAuthorSet() {
        return this.authorId != null;
    }

    public final boolean isAuthor(scrum.server.admin.User author) {
        if (this.authorId == null && author == null) return true;
        return author != null && author.getId().equals(this.authorId);
    }

    protected final void updateAuthor(Object value) {
        setAuthor(value == null ? null : (scrum.server.admin.User)userDao.getById((String)value));
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
    // - dateAndTime
    // -----------------------------------------------------------

    private ilarkesto.base.time.DateAndTime dateAndTime;

    public final ilarkesto.base.time.DateAndTime getDateAndTime() {
        return dateAndTime;
    }

    public final void setDateAndTime(ilarkesto.base.time.DateAndTime dateAndTime) {
        dateAndTime = prepareDateAndTime(dateAndTime);
        if (isDateAndTime(dateAndTime)) return;
        this.dateAndTime = dateAndTime;
        fireModified();
    }

    protected ilarkesto.base.time.DateAndTime prepareDateAndTime(ilarkesto.base.time.DateAndTime dateAndTime) {
        return dateAndTime;
    }

    public final boolean isDateAndTimeSet() {
        return this.dateAndTime != null;
    }

    public final boolean isDateAndTime(ilarkesto.base.time.DateAndTime dateAndTime) {
        if (this.dateAndTime == null && dateAndTime == null) return true;
        return this.dateAndTime != null && this.dateAndTime.equals(dateAndTime);
    }

    protected final void updateDateAndTime(Object value) {
        value = value == null ? null : new ilarkesto.base.time.DateAndTime((String)value);
        setDateAndTime((ilarkesto.base.time.DateAndTime)value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("projectId")) updateProject(value);
            if (property.equals("authorId")) updateAuthor(value);
            if (property.equals("text")) updateText(value);
            if (property.equals("dateAndTime")) updateDateAndTime(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadProjectReference(entityId);
        repairDeadAuthorReference(entityId);
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
        try {
            getAuthor();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead author reference");
            repairDeadAuthorReference(this.authorId);
        }
    }


    // -----------------------------------------------------------
    // - dependencies
    // -----------------------------------------------------------

    protected static scrum.server.project.ProjectDao projectDao;

    public static final void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        GChatMessage.projectDao = projectDao;
    }

    protected static ChatMessageDao chatMessageDao;

    public static final void setChatMessageDao(ChatMessageDao chatMessageDao) {
        GChatMessage.chatMessageDao = chatMessageDao;
    }

}