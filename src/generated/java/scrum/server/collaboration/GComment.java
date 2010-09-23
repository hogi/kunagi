// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.EntityGenerator










package scrum.server.collaboration;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GComment
            extends AEntity
            implements ilarkesto.auth.ViewProtected<scrum.server.admin.User>, ilarkesto.search.Searchable, java.lang.Comparable<Comment> {

    // --- AEntity ---

    public final CommentDao getDao() {
        return commentDao;
    }

    protected void repairDeadDatob(ADatob datob) {
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

    public int compareTo(Comment other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final ilarkesto.core.logging.Log LOG = ilarkesto.core.logging.Log.get(GComment.class);

    public static final String TYPE = "comment";


    // -----------------------------------------------------------
    // - Searchable
    // -----------------------------------------------------------

    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getText(), key)) return true;
        return false;
    }

    // -----------------------------------------------------------
    // - parent
    // -----------------------------------------------------------

    private String parentId;
    private transient ilarkesto.persistence.AEntity parentCache;

    private void updateParentCache() {
        parentCache = this.parentId == null ? null : (ilarkesto.persistence.AEntity)getDaoService().getById(this.parentId);
    }

    public final String getParentId() {
        return this.parentId;
    }

    public final ilarkesto.persistence.AEntity getParent() {
        if (parentCache == null) updateParentCache();
        return parentCache;
    }

    public final void setParent(ilarkesto.persistence.AEntity parent) {
        parent = prepareParent(parent);
        if (isParent(parent)) return;
        this.parentId = parent == null ? null : parent.getId();
        parentCache = parent;
        fireModified("parent="+parent);
    }

    protected ilarkesto.persistence.AEntity prepareParent(ilarkesto.persistence.AEntity parent) {
        return parent;
    }

    protected void repairDeadParentReference(String entityId) {
        if (this.parentId == null || entityId.equals(this.parentId)) {
            repairMissingMaster();
        }
    }

    public final boolean isParentSet() {
        return this.parentId != null;
    }

    public final boolean isParent(ilarkesto.persistence.AEntity parent) {
        if (this.parentId == null && parent == null) return true;
        return parent != null && parent.getId().equals(this.parentId);
    }

    protected final void updateParent(Object value) {
        setParent(value == null ? null : (ilarkesto.persistence.AEntity)getDaoService().getById((String)value));
    }

    // -----------------------------------------------------------
    // - author
    // -----------------------------------------------------------

    private String authorId;
    private transient scrum.server.admin.User authorCache;

    private void updateAuthorCache() {
        authorCache = this.authorId == null ? null : (scrum.server.admin.User)userDao.getById(this.authorId);
    }

    public final String getAuthorId() {
        return this.authorId;
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
        fireModified("author="+author);
    }

    protected scrum.server.admin.User prepareAuthor(scrum.server.admin.User author) {
        return author;
    }

    protected void repairDeadAuthorReference(String entityId) {
        if (this.authorId == null || entityId.equals(this.authorId)) {
            setAuthor(null);
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
    // - published
    // -----------------------------------------------------------

    private boolean published;

    public final boolean isPublished() {
        return published;
    }

    public final void setPublished(boolean published) {
        published = preparePublished(published);
        if (isPublished(published)) return;
        this.published = published;
        fireModified("published="+published);
    }

    protected boolean preparePublished(boolean published) {
        return published;
    }

    public final boolean isPublished(boolean published) {
        return this.published == published;
    }

    protected final void updatePublished(Object value) {
        setPublished((Boolean)value);
    }

    // -----------------------------------------------------------
    // - authorName
    // -----------------------------------------------------------

    private java.lang.String authorName;

    public final java.lang.String getAuthorName() {
        return authorName;
    }

    public final void setAuthorName(java.lang.String authorName) {
        authorName = prepareAuthorName(authorName);
        if (isAuthorName(authorName)) return;
        this.authorName = authorName;
        fireModified("authorName="+authorName);
    }

    protected java.lang.String prepareAuthorName(java.lang.String authorName) {
        authorName = Str.removeUnreadableChars(authorName);
        return authorName;
    }

    public final boolean isAuthorNameSet() {
        return this.authorName != null;
    }

    public final boolean isAuthorName(java.lang.String authorName) {
        if (this.authorName == null && authorName == null) return true;
        return this.authorName != null && this.authorName.equals(authorName);
    }

    protected final void updateAuthorName(Object value) {
        setAuthorName((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - authorEmail
    // -----------------------------------------------------------

    private java.lang.String authorEmail;

    public final java.lang.String getAuthorEmail() {
        return authorEmail;
    }

    public final void setAuthorEmail(java.lang.String authorEmail) {
        authorEmail = prepareAuthorEmail(authorEmail);
        if (isAuthorEmail(authorEmail)) return;
        this.authorEmail = authorEmail;
        fireModified("authorEmail="+authorEmail);
    }

    protected java.lang.String prepareAuthorEmail(java.lang.String authorEmail) {
        authorEmail = Str.removeUnreadableChars(authorEmail);
        return authorEmail;
    }

    public final boolean isAuthorEmailSet() {
        return this.authorEmail != null;
    }

    public final boolean isAuthorEmail(java.lang.String authorEmail) {
        if (this.authorEmail == null && authorEmail == null) return true;
        return this.authorEmail != null && this.authorEmail.equals(authorEmail);
    }

    protected final void updateAuthorEmail(Object value) {
        setAuthorEmail((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - authorNameVisible
    // -----------------------------------------------------------

    private boolean authorNameVisible;

    public final boolean isAuthorNameVisible() {
        return authorNameVisible;
    }

    public final void setAuthorNameVisible(boolean authorNameVisible) {
        authorNameVisible = prepareAuthorNameVisible(authorNameVisible);
        if (isAuthorNameVisible(authorNameVisible)) return;
        this.authorNameVisible = authorNameVisible;
        fireModified("authorNameVisible="+authorNameVisible);
    }

    protected boolean prepareAuthorNameVisible(boolean authorNameVisible) {
        return authorNameVisible;
    }

    public final boolean isAuthorNameVisible(boolean authorNameVisible) {
        return this.authorNameVisible == authorNameVisible;
    }

    protected final void updateAuthorNameVisible(Object value) {
        setAuthorNameVisible((Boolean)value);
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
        fireModified("text="+text);
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
        fireModified("dateAndTime="+dateAndTime);
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
            if (property.equals("parentId")) updateParent(value);
            if (property.equals("authorId")) updateAuthor(value);
            if (property.equals("published")) updatePublished(value);
            if (property.equals("authorName")) updateAuthorName(value);
            if (property.equals("authorEmail")) updateAuthorEmail(value);
            if (property.equals("authorNameVisible")) updateAuthorNameVisible(value);
            if (property.equals("text")) updateText(value);
            if (property.equals("dateAndTime")) updateDateAndTime(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadParentReference(entityId);
        repairDeadAuthorReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        if (!isParentSet()) {
            repairMissingMaster();
            return;
        }
        try {
            getParent();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead parent reference");
            repairDeadParentReference(this.parentId);
        }
        try {
            getAuthor();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead author reference");
            repairDeadAuthorReference(this.authorId);
        }
    }

    static CommentDao commentDao;

    public static final void setCommentDao(CommentDao commentDao) {
        GComment.commentDao = commentDao;
    }

}