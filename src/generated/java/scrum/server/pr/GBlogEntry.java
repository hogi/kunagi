// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.EntityGenerator










package scrum.server.pr;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GBlogEntry
            extends AEntity
            implements ilarkesto.auth.ViewProtected<scrum.server.admin.User>, ilarkesto.search.Searchable, java.lang.Comparable<BlogEntry> {

    // --- AEntity ---

    public final BlogEntryDao getDao() {
        return blogEntryDao;
    }

    protected void repairDeadDatob(ADatob datob) {
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

    public int compareTo(BlogEntry other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final ilarkesto.core.logging.Log LOG = ilarkesto.core.logging.Log.get(GBlogEntry.class);

    public static final String TYPE = "blogEntry";


    // -----------------------------------------------------------
    // - Searchable
    // -----------------------------------------------------------

    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getTitle(), key)) return true;
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

    public final String getProjectId() {
        return this.projectId;
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
        if (this.projectId == null || entityId.equals(this.projectId)) {
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

    // -----------------------------------------------------------
    // - authors
    // -----------------------------------------------------------

    private java.util.Set<String> authorsIds = new java.util.HashSet<String>();

    public final java.util.Set<scrum.server.admin.User> getAuthors() {
        return (java.util.Set) userDao.getByIdsAsSet(this.authorsIds);
    }

    public final void setAuthors(Collection<scrum.server.admin.User> authors) {
        authors = prepareAuthors(authors);
        if (authors == null) authors = Collections.emptyList();
        java.util.Set<String> ids = getIdsAsSet(authors);
        if (this.authorsIds.equals(ids)) return;
        this.authorsIds = ids;
        fireModified();
    }

    protected Collection<scrum.server.admin.User> prepareAuthors(Collection<scrum.server.admin.User> authors) {
        return authors;
    }

    protected void repairDeadAuthorReference(String entityId) {
        if (this.authorsIds.remove(entityId)) fireModified();
    }

    public final boolean containsAuthor(scrum.server.admin.User author) {
        if (author == null) return false;
        return this.authorsIds.contains(author.getId());
    }

    public final int getAuthorsCount() {
        return this.authorsIds.size();
    }

    public final boolean isAuthorsEmpty() {
        return this.authorsIds.isEmpty();
    }

    public final boolean addAuthor(scrum.server.admin.User author) {
        if (author == null) throw new IllegalArgumentException("author == null");
        boolean added = this.authorsIds.add(author.getId());
        if (added) fireModified();
        return added;
    }

    public final boolean addAuthors(Collection<scrum.server.admin.User> authors) {
        if (authors == null) throw new IllegalArgumentException("authors == null");
        boolean added = false;
        for (scrum.server.admin.User author : authors) {
            added = added | this.authorsIds.add(author.getId());
        }
        if (added) fireModified();
        return added;
    }

    public final boolean removeAuthor(scrum.server.admin.User author) {
        if (author == null) throw new IllegalArgumentException("author == null");
        if (this.authorsIds == null) return false;
        boolean removed = this.authorsIds.remove(author.getId());
        if (removed) fireModified();
        return removed;
    }

    public final boolean removeAuthors(Collection<scrum.server.admin.User> authors) {
        if (authors == null) return false;
        if (authors.isEmpty()) return false;
        boolean removed = false;
        for (scrum.server.admin.User _element: authors) {
            removed = removed | removeAuthor(_element);
        }
        return removed;
    }

    public final boolean clearAuthors() {
        if (this.authorsIds.isEmpty()) return false;
        this.authorsIds.clear();
        fireModified();
        return true;
    }

    protected final void updateAuthors(Object value) {
        Collection<String> ids = (Collection<String>) value;
        setAuthors((java.util.Set) userDao.getByIdsAsSet(ids));
    }

    // -----------------------------------------------------------
    // - title
    // -----------------------------------------------------------

    private java.lang.String title;

    public final java.lang.String getTitle() {
        return title;
    }

    public final void setTitle(java.lang.String title) {
        title = prepareTitle(title);
        if (isTitle(title)) return;
        this.title = title;
        fireModified();
    }

    protected java.lang.String prepareTitle(java.lang.String title) {
        title = Str.removeUnreadableChars(title);
        return title;
    }

    public final boolean isTitleSet() {
        return this.title != null;
    }

    public final boolean isTitle(java.lang.String title) {
        if (this.title == null && title == null) return true;
        return this.title != null && this.title.equals(title);
    }

    protected final void updateTitle(Object value) {
        setTitle((java.lang.String)value);
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

    // -----------------------------------------------------------
    // - releases
    // -----------------------------------------------------------

    private java.util.Set<String> releasesIds = new java.util.HashSet<String>();

    public final java.util.Set<scrum.server.release.Release> getReleases() {
        return (java.util.Set) releaseDao.getByIdsAsSet(this.releasesIds);
    }

    public final void setReleases(Collection<scrum.server.release.Release> releases) {
        releases = prepareReleases(releases);
        if (releases == null) releases = Collections.emptyList();
        java.util.Set<String> ids = getIdsAsSet(releases);
        if (this.releasesIds.equals(ids)) return;
        this.releasesIds = ids;
        fireModified();
    }

    protected Collection<scrum.server.release.Release> prepareReleases(Collection<scrum.server.release.Release> releases) {
        return releases;
    }

    protected void repairDeadReleaseReference(String entityId) {
        if (this.releasesIds.remove(entityId)) fireModified();
    }

    public final boolean containsRelease(scrum.server.release.Release release) {
        if (release == null) return false;
        return this.releasesIds.contains(release.getId());
    }

    public final int getReleasesCount() {
        return this.releasesIds.size();
    }

    public final boolean isReleasesEmpty() {
        return this.releasesIds.isEmpty();
    }

    public final boolean addRelease(scrum.server.release.Release release) {
        if (release == null) throw new IllegalArgumentException("release == null");
        boolean added = this.releasesIds.add(release.getId());
        if (added) fireModified();
        return added;
    }

    public final boolean addReleases(Collection<scrum.server.release.Release> releases) {
        if (releases == null) throw new IllegalArgumentException("releases == null");
        boolean added = false;
        for (scrum.server.release.Release release : releases) {
            added = added | this.releasesIds.add(release.getId());
        }
        if (added) fireModified();
        return added;
    }

    public final boolean removeRelease(scrum.server.release.Release release) {
        if (release == null) throw new IllegalArgumentException("release == null");
        if (this.releasesIds == null) return false;
        boolean removed = this.releasesIds.remove(release.getId());
        if (removed) fireModified();
        return removed;
    }

    public final boolean removeReleases(Collection<scrum.server.release.Release> releases) {
        if (releases == null) return false;
        if (releases.isEmpty()) return false;
        boolean removed = false;
        for (scrum.server.release.Release _element: releases) {
            removed = removed | removeRelease(_element);
        }
        return removed;
    }

    public final boolean clearReleases() {
        if (this.releasesIds.isEmpty()) return false;
        this.releasesIds.clear();
        fireModified();
        return true;
    }

    protected final void updateReleases(Object value) {
        Collection<String> ids = (Collection<String>) value;
        setReleases((java.util.Set) releaseDao.getByIdsAsSet(ids));
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
        fireModified();
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

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("projectId")) updateProject(value);
            if (property.equals("number")) updateNumber(value);
            if (property.equals("authorsIds")) updateAuthors(value);
            if (property.equals("title")) updateTitle(value);
            if (property.equals("text")) updateText(value);
            if (property.equals("dateAndTime")) updateDateAndTime(value);
            if (property.equals("releasesIds")) updateReleases(value);
            if (property.equals("published")) updatePublished(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadProjectReference(entityId);
        if (this.authorsIds == null) this.authorsIds = new java.util.HashSet<String>();
        repairDeadAuthorReference(entityId);
        if (this.releasesIds == null) this.releasesIds = new java.util.HashSet<String>();
        repairDeadReleaseReference(entityId);
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
        if (this.authorsIds == null) this.authorsIds = new java.util.HashSet<String>();
        Set<String> authors = new HashSet<String>(this.authorsIds);
        for (String entityId : authors) {
            try {
                userDao.getById(entityId);
            } catch (EntityDoesNotExistException ex) {
                LOG.info("Repairing dead author reference");
                repairDeadAuthorReference(entityId);
            }
        }
        if (this.releasesIds == null) this.releasesIds = new java.util.HashSet<String>();
        Set<String> releases = new HashSet<String>(this.releasesIds);
        for (String entityId : releases) {
            try {
                releaseDao.getById(entityId);
            } catch (EntityDoesNotExistException ex) {
                LOG.info("Repairing dead release reference");
                repairDeadReleaseReference(entityId);
            }
        }
    }


    // -----------------------------------------------------------
    // - dependencies
    // -----------------------------------------------------------

    static scrum.server.project.ProjectDao projectDao;

    public static final void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        GBlogEntry.projectDao = projectDao;
    }

    static scrum.server.release.ReleaseDao releaseDao;

    public static final void setReleaseDao(scrum.server.release.ReleaseDao releaseDao) {
        GBlogEntry.releaseDao = releaseDao;
    }

    static BlogEntryDao blogEntryDao;

    public static final void setBlogEntryDao(BlogEntryDao blogEntryDao) {
        GBlogEntry.blogEntryDao = blogEntryDao;
    }

}