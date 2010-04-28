// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.DaoGenerator










package scrum.server.release;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GReleaseDao
            extends ilarkesto.persistence.ADao<Release> {

    public final String getEntityName() {
        return Release.TYPE;
    }

    public final Class getEntityClass() {
        return Release.class;
    }

    public Set<Release> getEntitiesVisibleForUser(final scrum.server.admin.User user) {
        return getEntities(new Predicate<Release>() {
            public boolean test(Release e) {
                return Auth.isVisible(e, user);
            }
        });
    }

    // --- clear caches ---
    public void clearCaches() {
        releasesByProjectCache.clear();
        projectsCache = null;
        releasesByNumberCache.clear();
        numbersCache = null;
        releasesByLabelCache.clear();
        labelsCache = null;
        releasesByNoteCache.clear();
        notesCache = null;
        releasesByPublicationDateCache.clear();
        publicationDatesCache = null;
        releasesByPublishedCache.clear();
        releasesByReleaseNotesCache.clear();
        releaseNotessCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Release) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Release) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<Release>> releasesByProjectCache = new Cache<scrum.server.project.Project,Set<Release>>(
            new Cache.Factory<scrum.server.project.Project,Set<Release>>() {
                public Set<Release> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<Release> getReleasesByProject(scrum.server.project.Project project) {
        return releasesByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (Release e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<Release> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(Release e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - number
    // -----------------------------------------------------------

    private final Cache<Integer,Set<Release>> releasesByNumberCache = new Cache<Integer,Set<Release>>(
            new Cache.Factory<Integer,Set<Release>>() {
                public Set<Release> create(Integer number) {
                    return getEntities(new IsNumber(number));
                }
            });

    public final Set<Release> getReleasesByNumber(int number) {
        return releasesByNumberCache.get(number);
    }
    private Set<Integer> numbersCache;

    public final Set<Integer> getNumbers() {
        if (numbersCache == null) {
            numbersCache = new HashSet<Integer>();
            for (Release e : getEntities()) {
                numbersCache.add(e.getNumber());
            }
        }
        return numbersCache;
    }

    private static class IsNumber implements Predicate<Release> {

        private int value;

        public IsNumber(int value) {
            this.value = value;
        }

        public boolean test(Release e) {
            return e.isNumber(value);
        }

    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Release>> releasesByLabelCache = new Cache<java.lang.String,Set<Release>>(
            new Cache.Factory<java.lang.String,Set<Release>>() {
                public Set<Release> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<Release> getReleasesByLabel(java.lang.String label) {
        return releasesByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (Release e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<Release> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Release e) {
            return e.isLabel(value);
        }

    }

    // -----------------------------------------------------------
    // - note
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Release>> releasesByNoteCache = new Cache<java.lang.String,Set<Release>>(
            new Cache.Factory<java.lang.String,Set<Release>>() {
                public Set<Release> create(java.lang.String note) {
                    return getEntities(new IsNote(note));
                }
            });

    public final Set<Release> getReleasesByNote(java.lang.String note) {
        return releasesByNoteCache.get(note);
    }
    private Set<java.lang.String> notesCache;

    public final Set<java.lang.String> getNotes() {
        if (notesCache == null) {
            notesCache = new HashSet<java.lang.String>();
            for (Release e : getEntities()) {
                if (e.isNoteSet()) notesCache.add(e.getNote());
            }
        }
        return notesCache;
    }

    private static class IsNote implements Predicate<Release> {

        private java.lang.String value;

        public IsNote(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Release e) {
            return e.isNote(value);
        }

    }

    // -----------------------------------------------------------
    // - publicationDate
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.Date,Set<Release>> releasesByPublicationDateCache = new Cache<ilarkesto.base.time.Date,Set<Release>>(
            new Cache.Factory<ilarkesto.base.time.Date,Set<Release>>() {
                public Set<Release> create(ilarkesto.base.time.Date publicationDate) {
                    return getEntities(new IsPublicationDate(publicationDate));
                }
            });

    public final Set<Release> getReleasesByPublicationDate(ilarkesto.base.time.Date publicationDate) {
        return releasesByPublicationDateCache.get(publicationDate);
    }
    private Set<ilarkesto.base.time.Date> publicationDatesCache;

    public final Set<ilarkesto.base.time.Date> getPublicationDates() {
        if (publicationDatesCache == null) {
            publicationDatesCache = new HashSet<ilarkesto.base.time.Date>();
            for (Release e : getEntities()) {
                if (e.isPublicationDateSet()) publicationDatesCache.add(e.getPublicationDate());
            }
        }
        return publicationDatesCache;
    }

    private static class IsPublicationDate implements Predicate<Release> {

        private ilarkesto.base.time.Date value;

        public IsPublicationDate(ilarkesto.base.time.Date value) {
            this.value = value;
        }

        public boolean test(Release e) {
            return e.isPublicationDate(value);
        }

    }

    // -----------------------------------------------------------
    // - published
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<Release>> releasesByPublishedCache = new Cache<Boolean,Set<Release>>(
            new Cache.Factory<Boolean,Set<Release>>() {
                public Set<Release> create(Boolean published) {
                    return getEntities(new IsPublished(published));
                }
            });

    public final Set<Release> getReleasesByPublished(boolean published) {
        return releasesByPublishedCache.get(published);
    }

    private static class IsPublished implements Predicate<Release> {

        private boolean value;

        public IsPublished(boolean value) {
            this.value = value;
        }

        public boolean test(Release e) {
            return value == e.isPublished();
        }

    }

    // -----------------------------------------------------------
    // - releaseNotes
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Release>> releasesByReleaseNotesCache = new Cache<java.lang.String,Set<Release>>(
            new Cache.Factory<java.lang.String,Set<Release>>() {
                public Set<Release> create(java.lang.String releaseNotes) {
                    return getEntities(new IsReleaseNotes(releaseNotes));
                }
            });

    public final Set<Release> getReleasesByReleaseNotes(java.lang.String releaseNotes) {
        return releasesByReleaseNotesCache.get(releaseNotes);
    }
    private Set<java.lang.String> releaseNotessCache;

    public final Set<java.lang.String> getReleaseNotess() {
        if (releaseNotessCache == null) {
            releaseNotessCache = new HashSet<java.lang.String>();
            for (Release e : getEntities()) {
                if (e.isReleaseNotesSet()) releaseNotessCache.add(e.getReleaseNotes());
            }
        }
        return releaseNotessCache;
    }

    private static class IsReleaseNotes implements Predicate<Release> {

        private java.lang.String value;

        public IsReleaseNotes(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Release e) {
            return e.isReleaseNotes(value);
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

}