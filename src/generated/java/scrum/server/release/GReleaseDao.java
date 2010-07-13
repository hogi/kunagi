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
        releasesByParentReleaseCache.clear();
        parentReleasesCache = null;
        releasesBySprintCache.clear();
        sprintsCache = null;
        releasesByNumberCache.clear();
        numbersCache = null;
        releasesByLabelCache.clear();
        labelsCache = null;
        releasesByNoteCache.clear();
        notesCache = null;
        releasesByReleaseDateCache.clear();
        releaseDatesCache = null;
        releasesByReleasedCache.clear();
        releasesByReleaseNotesCache.clear();
        releaseNotessCache = null;
        releasesByScmTagCache.clear();
        scmTagsCache = null;
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
    // - parentRelease
    // -----------------------------------------------------------

    private final Cache<scrum.server.release.Release,Set<Release>> releasesByParentReleaseCache = new Cache<scrum.server.release.Release,Set<Release>>(
            new Cache.Factory<scrum.server.release.Release,Set<Release>>() {
                public Set<Release> create(scrum.server.release.Release parentRelease) {
                    return getEntities(new IsParentRelease(parentRelease));
                }
            });

    public final Set<Release> getReleasesByParentRelease(scrum.server.release.Release parentRelease) {
        return releasesByParentReleaseCache.get(parentRelease);
    }
    private Set<scrum.server.release.Release> parentReleasesCache;

    public final Set<scrum.server.release.Release> getParentReleases() {
        if (parentReleasesCache == null) {
            parentReleasesCache = new HashSet<scrum.server.release.Release>();
            for (Release e : getEntities()) {
                if (e.isParentReleaseSet()) parentReleasesCache.add(e.getParentRelease());
            }
        }
        return parentReleasesCache;
    }

    private static class IsParentRelease implements Predicate<Release> {

        private scrum.server.release.Release value;

        public IsParentRelease(scrum.server.release.Release value) {
            this.value = value;
        }

        public boolean test(Release e) {
            return e.isParentRelease(value);
        }

    }

    // -----------------------------------------------------------
    // - sprints
    // -----------------------------------------------------------

    private final Cache<scrum.server.sprint.Sprint,Set<Release>> releasesBySprintCache = new Cache<scrum.server.sprint.Sprint,Set<Release>>(
            new Cache.Factory<scrum.server.sprint.Sprint,Set<Release>>() {
                public Set<Release> create(scrum.server.sprint.Sprint sprint) {
                    return getEntities(new ContainsSprint(sprint));
                }
            });

    public final Set<Release> getReleasesBySprint(scrum.server.sprint.Sprint sprint) {
        return releasesBySprintCache.get(sprint);
    }
    private Set<scrum.server.sprint.Sprint> sprintsCache;

    public final Set<scrum.server.sprint.Sprint> getSprints() {
        if (sprintsCache == null) {
            sprintsCache = new HashSet<scrum.server.sprint.Sprint>();
            for (Release e : getEntities()) {
                sprintsCache.addAll(e.getSprints());
            }
        }
        return sprintsCache;
    }

    private static class ContainsSprint implements Predicate<Release> {

        private scrum.server.sprint.Sprint value;

        public ContainsSprint(scrum.server.sprint.Sprint value) {
            this.value = value;
        }

        public boolean test(Release e) {
            return e.containsSprint(value);
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
    // - releaseDate
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.Date,Set<Release>> releasesByReleaseDateCache = new Cache<ilarkesto.base.time.Date,Set<Release>>(
            new Cache.Factory<ilarkesto.base.time.Date,Set<Release>>() {
                public Set<Release> create(ilarkesto.base.time.Date releaseDate) {
                    return getEntities(new IsReleaseDate(releaseDate));
                }
            });

    public final Set<Release> getReleasesByReleaseDate(ilarkesto.base.time.Date releaseDate) {
        return releasesByReleaseDateCache.get(releaseDate);
    }
    private Set<ilarkesto.base.time.Date> releaseDatesCache;

    public final Set<ilarkesto.base.time.Date> getReleaseDates() {
        if (releaseDatesCache == null) {
            releaseDatesCache = new HashSet<ilarkesto.base.time.Date>();
            for (Release e : getEntities()) {
                if (e.isReleaseDateSet()) releaseDatesCache.add(e.getReleaseDate());
            }
        }
        return releaseDatesCache;
    }

    private static class IsReleaseDate implements Predicate<Release> {

        private ilarkesto.base.time.Date value;

        public IsReleaseDate(ilarkesto.base.time.Date value) {
            this.value = value;
        }

        public boolean test(Release e) {
            return e.isReleaseDate(value);
        }

    }

    // -----------------------------------------------------------
    // - released
    // -----------------------------------------------------------

    private final Cache<Boolean,Set<Release>> releasesByReleasedCache = new Cache<Boolean,Set<Release>>(
            new Cache.Factory<Boolean,Set<Release>>() {
                public Set<Release> create(Boolean released) {
                    return getEntities(new IsReleased(released));
                }
            });

    public final Set<Release> getReleasesByReleased(boolean released) {
        return releasesByReleasedCache.get(released);
    }

    private static class IsReleased implements Predicate<Release> {

        private boolean value;

        public IsReleased(boolean value) {
            this.value = value;
        }

        public boolean test(Release e) {
            return value == e.isReleased();
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

    // -----------------------------------------------------------
    // - scmTag
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Release>> releasesByScmTagCache = new Cache<java.lang.String,Set<Release>>(
            new Cache.Factory<java.lang.String,Set<Release>>() {
                public Set<Release> create(java.lang.String scmTag) {
                    return getEntities(new IsScmTag(scmTag));
                }
            });

    public final Set<Release> getReleasesByScmTag(java.lang.String scmTag) {
        return releasesByScmTagCache.get(scmTag);
    }
    private Set<java.lang.String> scmTagsCache;

    public final Set<java.lang.String> getScmTags() {
        if (scmTagsCache == null) {
            scmTagsCache = new HashSet<java.lang.String>();
            for (Release e : getEntities()) {
                if (e.isScmTagSet()) scmTagsCache.add(e.getScmTag());
            }
        }
        return scmTagsCache;
    }

    private static class IsScmTag implements Predicate<Release> {

        private java.lang.String value;

        public IsScmTag(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Release e) {
            return e.isScmTag(value);
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

    scrum.server.sprint.SprintDao sprintDao;

    public void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        this.sprintDao = sprintDao;
    }

}