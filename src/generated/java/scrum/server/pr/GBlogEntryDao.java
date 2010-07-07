// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.DaoGenerator










package scrum.server.pr;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GBlogEntryDao
            extends ilarkesto.persistence.ADao<BlogEntry> {

    public final String getEntityName() {
        return BlogEntry.TYPE;
    }

    public final Class getEntityClass() {
        return BlogEntry.class;
    }

    public Set<BlogEntry> getEntitiesVisibleForUser(final scrum.server.admin.User user) {
        return getEntities(new Predicate<BlogEntry>() {
            public boolean test(BlogEntry e) {
                return Auth.isVisible(e, user);
            }
        });
    }

    // --- clear caches ---
    public void clearCaches() {
        blogEntrysByProjectCache.clear();
        projectsCache = null;
        blogEntrysByNumberCache.clear();
        numbersCache = null;
        blogEntrysByAuthorCache.clear();
        authorsCache = null;
        blogEntrysByTitleCache.clear();
        titlesCache = null;
        blogEntrysByTextCache.clear();
        textsCache = null;
        blogEntrysByDateAndTimeCache.clear();
        dateAndTimesCache = null;
        blogEntrysByReleaseCache.clear();
        releasesCache = null;
        blogEntrysByPublishedCache.clear();
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof BlogEntry) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof BlogEntry) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<BlogEntry>> blogEntrysByProjectCache = new Cache<scrum.server.project.Project,Set<BlogEntry>>(
            new Cache.Factory<scrum.server.project.Project,Set<BlogEntry>>() {
                public Set<BlogEntry> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<BlogEntry> getBlogEntrysByProject(scrum.server.project.Project project) {
        return blogEntrysByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (BlogEntry e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<BlogEntry> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(BlogEntry e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - number
    // -----------------------------------------------------------

    private final Cache<Integer,Set<BlogEntry>> blogEntrysByNumberCache = new Cache<Integer,Set<BlogEntry>>(
            new Cache.Factory<Integer,Set<BlogEntry>>() {
                public Set<BlogEntry> create(Integer number) {
                    return getEntities(new IsNumber(number));
                }
            });

    public final Set<BlogEntry> getBlogEntrysByNumber(int number) {
        return blogEntrysByNumberCache.get(number);
    }
    private Set<Integer> numbersCache;

    public final Set<Integer> getNumbers() {
        if (numbersCache == null) {
            numbersCache = new HashSet<Integer>();
            for (BlogEntry e : getEntities()) {
                numbersCache.add(e.getNumber());
            }
        }
        return numbersCache;
    }

    private static class IsNumber implements Predicate<BlogEntry> {

        private int value;

        public IsNumber(int value) {
            this.value = value;
        }

        public boolean test(BlogEntry e) {
            return e.isNumber(value);
        }

    }

    // -----------------------------------------------------------
    // - authors
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<BlogEntry>> blogEntrysByAuthorCache = new Cache<scrum.server.admin.User,Set<BlogEntry>>(
            new Cache.Factory<scrum.server.admin.User,Set<BlogEntry>>() {
                public Set<BlogEntry> create(scrum.server.admin.User author) {
                    return getEntities(new ContainsAuthor(author));
                }
            });

    public final Set<BlogEntry> getBlogEntrysByAuthor(scrum.server.admin.User author) {
        return blogEntrysByAuthorCache.get(author);
    }
    private Set<scrum.server.admin.User> authorsCache;

    public final Set<scrum.server.admin.User> getAuthors() {
        if (authorsCache == null) {
            authorsCache = new HashSet<scrum.server.admin.User>();
            for (BlogEntry e : getEntities()) {
                authorsCache.addAll(e.getAuthors());
            }
        }
        return authorsCache;
    }

    private static class ContainsAuthor implements Predicate<BlogEntry> {

        private scrum.server.admin.User value;

        public ContainsAuthor(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(BlogEntry e) {
            return e.containsAuthor(value);
        }

    }

    // -----------------------------------------------------------
    // - title
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<BlogEntry>> blogEntrysByTitleCache = new Cache<java.lang.String,Set<BlogEntry>>(
            new Cache.Factory<java.lang.String,Set<BlogEntry>>() {
                public Set<BlogEntry> create(java.lang.String title) {
                    return getEntities(new IsTitle(title));
                }
            });

    public final Set<BlogEntry> getBlogEntrysByTitle(java.lang.String title) {
        return blogEntrysByTitleCache.get(title);
    }
    private Set<java.lang.String> titlesCache;

    public final Set<java.lang.String> getTitles() {
        if (titlesCache == null) {
            titlesCache = new HashSet<java.lang.String>();
            for (BlogEntry e : getEntities()) {
                if (e.isTitleSet()) titlesCache.add(e.getTitle());
            }
        }
        return titlesCache;
    }

    private static class IsTitle implements Predicate<BlogEntry> {

        private java.lang.String value;

        public IsTitle(java.lang.String value) {
            this.value = value;
        }

        public boolean test(BlogEntry e) {
            return e.isTitle(value);
        }

    }

    // -----------------------------------------------------------
    // - text
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<BlogEntry>> blogEntrysByTextCache = new Cache<java.lang.String,Set<BlogEntry>>(
            new Cache.Factory<java.lang.String,Set<BlogEntry>>() {
                public Set<BlogEntry> create(java.lang.String text) {
                    return getEntities(new IsText(text));
                }
            });

    public final Set<BlogEntry> getBlogEntrysByText(java.lang.String text) {
        return blogEntrysByTextCache.get(text);
    }
    private Set<java.lang.String> textsCache;

    public final Set<java.lang.String> getTexts() {
        if (textsCache == null) {
            textsCache = new HashSet<java.lang.String>();
            for (BlogEntry e : getEntities()) {
                if (e.isTextSet()) textsCache.add(e.getText());
            }
        }
        return textsCache;
    }

    private static class IsText implements Predicate<BlogEntry> {

        private java.lang.String value;

        public IsText(java.lang.String value) {
            this.value = value;
        }

        public boolean test(BlogEntry e) {
            return e.isText(value);
        }

    }

    // -----------------------------------------------------------
    // - dateAndTime
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.DateAndTime,Set<BlogEntry>> blogEntrysByDateAndTimeCache = new Cache<ilarkesto.base.time.DateAndTime,Set<BlogEntry>>(
            new Cache.Factory<ilarkesto.base.time.DateAndTime,Set<BlogEntry>>() {
                public Set<BlogEntry> create(ilarkesto.base.time.DateAndTime dateAndTime) {
                    return getEntities(new IsDateAndTime(dateAndTime));
                }
            });

    public final Set<BlogEntry> getBlogEntrysByDateAndTime(ilarkesto.base.time.DateAndTime dateAndTime) {
        return blogEntrysByDateAndTimeCache.get(dateAndTime);
    }
    private Set<ilarkesto.base.time.DateAndTime> dateAndTimesCache;

    public final Set<ilarkesto.base.time.DateAndTime> getDateAndTimes() {
        if (dateAndTimesCache == null) {
            dateAndTimesCache = new HashSet<ilarkesto.base.time.DateAndTime>();
            for (BlogEntry e : getEntities()) {
                if (e.isDateAndTimeSet()) dateAndTimesCache.add(e.getDateAndTime());
            }
        }
        return dateAndTimesCache;
    }

    private static class IsDateAndTime implements Predicate<BlogEntry> {

        private ilarkesto.base.time.DateAndTime value;

        public IsDateAndTime(ilarkesto.base.time.DateAndTime value) {
            this.value = value;
        }

        public boolean test(BlogEntry e) {
            return e.isDateAndTime(value);
        }

    }

    // -----------------------------------------------------------
    // - releases
    // -----------------------------------------------------------

    private final Cache<scrum.server.release.Release,Set<BlogEntry>> blogEntrysByReleaseCache = new Cache<scrum.server.release.Release,Set<BlogEntry>>(
            new Cache.Factory<scrum.server.release.Release,Set<BlogEntry>>() {
                public Set<BlogEntry> create(scrum.server.release.Release release) {
                    return getEntities(new ContainsRelease(release));
                }
            });

    public final Set<BlogEntry> getBlogEntrysByRelease(scrum.server.release.Release release) {
        return blogEntrysByReleaseCache.get(release);
    }
    private Set<scrum.server.release.Release> releasesCache;

    public final Set<scrum.server.release.Release> getReleases() {
        if (releasesCache == null) {
            releasesCache = new HashSet<scrum.server.release.Release>();
            for (BlogEntry e : getEntities()) {
                releasesCache.addAll(e.getReleases());
            }
        }
        return releasesCache;
    }

    private static class ContainsRelease implements Predicate<BlogEntry> {

        private scrum.server.release.Release value;

        public ContainsRelease(scrum.server.release.Release value) {
            this.value = value;
        }

        public boolean test(BlogEntry e) {
            return e.containsRelease(value);
        }

    }

    // -----------------------------------------------------------
    // - published
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<BlogEntry>> blogEntrysByPublishedCache = new Cache<Boolean,Set<BlogEntry>>(
            new Cache.Factory<Boolean,Set<BlogEntry>>() {
                public Set<BlogEntry> create(Boolean published) {
                    return getEntities(new IsPublished(published));
                }
            });

    public final Set<BlogEntry> getBlogEntrysByPublished(boolean published) {
        return blogEntrysByPublishedCache.get(published);
    }

    private static class IsPublished implements Predicate<BlogEntry> {

        private boolean value;

        public IsPublished(boolean value) {
            this.value = value;
        }

        public boolean test(BlogEntry e) {
            return value == e.isPublished();
        }

    }

    // --- valueObject classes ---
    @Override
    protected Set<Class> getValueObjectClasses() {
        Set<Class> ret = new HashSet<Class>(super.getValueObjectClasses());
        return ret;
    }

    @Override
    public Map<String, Class> getAliases() {
        Map<String, Class> aliases = new HashMap<String, Class>(super.getAliases());
        return aliases;
    }

    // --- dependencies ---

    scrum.server.project.ProjectDao projectDao;

    public void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    scrum.server.release.ReleaseDao releaseDao;

    public void setReleaseDao(scrum.server.release.ReleaseDao releaseDao) {
        this.releaseDao = releaseDao;
    }

}