// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.files;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GFileDao
            extends ilarkesto.persistence.ADao<File> {

    public final String getEntityName() {
        return File.TYPE;
    }

    public final Class getEntityClass() {
        return File.class;
    }

    public Set<File> getEntitiesVisibleForUser(final scrum.server.admin.User user) {
        return getEntities(new Predicate<File>() {
            public boolean test(File e) {
                return Auth.isVisible(e, user);
            }
        });
    }

    // --- clear caches ---
    public void clearCaches() {
        filesByProjectCache.clear();
        projectsCache = null;
        filesByFilenameCache.clear();
        filenamesCache = null;
        filesByUploadTimeCache.clear();
        uploadTimesCache = null;
        filesByLabelCache.clear();
        labelsCache = null;
        filesByNumberCache.clear();
        numbersCache = null;
        filesByNoteCache.clear();
        notesCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof File) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof File) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<File>> filesByProjectCache = new Cache<scrum.server.project.Project,Set<File>>(
            new Cache.Factory<scrum.server.project.Project,Set<File>>() {
                public Set<File> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<File> getFilesByProject(scrum.server.project.Project project) {
        return filesByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (File e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<File> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(File e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - filename
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<File>> filesByFilenameCache = new Cache<java.lang.String,Set<File>>(
            new Cache.Factory<java.lang.String,Set<File>>() {
                public Set<File> create(java.lang.String filename) {
                    return getEntities(new IsFilename(filename));
                }
            });

    public final Set<File> getFilesByFilename(java.lang.String filename) {
        return filesByFilenameCache.get(filename);
    }
    private Set<java.lang.String> filenamesCache;

    public final Set<java.lang.String> getFilenames() {
        if (filenamesCache == null) {
            filenamesCache = new HashSet<java.lang.String>();
            for (File e : getEntities()) {
                if (e.isFilenameSet()) filenamesCache.add(e.getFilename());
            }
        }
        return filenamesCache;
    }

    private static class IsFilename implements Predicate<File> {

        private java.lang.String value;

        public IsFilename(java.lang.String value) {
            this.value = value;
        }

        public boolean test(File e) {
            return e.isFilename(value);
        }

    }

    // -----------------------------------------------------------
    // - uploadTime
    // -----------------------------------------------------------

    private final Cache<ilarkesto.base.time.DateAndTime,Set<File>> filesByUploadTimeCache = new Cache<ilarkesto.base.time.DateAndTime,Set<File>>(
            new Cache.Factory<ilarkesto.base.time.DateAndTime,Set<File>>() {
                public Set<File> create(ilarkesto.base.time.DateAndTime uploadTime) {
                    return getEntities(new IsUploadTime(uploadTime));
                }
            });

    public final Set<File> getFilesByUploadTime(ilarkesto.base.time.DateAndTime uploadTime) {
        return filesByUploadTimeCache.get(uploadTime);
    }
    private Set<ilarkesto.base.time.DateAndTime> uploadTimesCache;

    public final Set<ilarkesto.base.time.DateAndTime> getUploadTimes() {
        if (uploadTimesCache == null) {
            uploadTimesCache = new HashSet<ilarkesto.base.time.DateAndTime>();
            for (File e : getEntities()) {
                if (e.isUploadTimeSet()) uploadTimesCache.add(e.getUploadTime());
            }
        }
        return uploadTimesCache;
    }

    private static class IsUploadTime implements Predicate<File> {

        private ilarkesto.base.time.DateAndTime value;

        public IsUploadTime(ilarkesto.base.time.DateAndTime value) {
            this.value = value;
        }

        public boolean test(File e) {
            return e.isUploadTime(value);
        }

    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<File>> filesByLabelCache = new Cache<java.lang.String,Set<File>>(
            new Cache.Factory<java.lang.String,Set<File>>() {
                public Set<File> create(java.lang.String label) {
                    return getEntities(new IsLabel(label));
                }
            });

    public final Set<File> getFilesByLabel(java.lang.String label) {
        return filesByLabelCache.get(label);
    }
    private Set<java.lang.String> labelsCache;

    public final Set<java.lang.String> getLabels() {
        if (labelsCache == null) {
            labelsCache = new HashSet<java.lang.String>();
            for (File e : getEntities()) {
                if (e.isLabelSet()) labelsCache.add(e.getLabel());
            }
        }
        return labelsCache;
    }

    private static class IsLabel implements Predicate<File> {

        private java.lang.String value;

        public IsLabel(java.lang.String value) {
            this.value = value;
        }

        public boolean test(File e) {
            return e.isLabel(value);
        }

    }

    // -----------------------------------------------------------
    // - number
    // -----------------------------------------------------------

    private final Cache<Integer,Set<File>> filesByNumberCache = new Cache<Integer,Set<File>>(
            new Cache.Factory<Integer,Set<File>>() {
                public Set<File> create(Integer number) {
                    return getEntities(new IsNumber(number));
                }
            });

    public final Set<File> getFilesByNumber(int number) {
        return filesByNumberCache.get(number);
    }
    private Set<Integer> numbersCache;

    public final Set<Integer> getNumbers() {
        if (numbersCache == null) {
            numbersCache = new HashSet<Integer>();
            for (File e : getEntities()) {
                numbersCache.add(e.getNumber());
            }
        }
        return numbersCache;
    }

    private static class IsNumber implements Predicate<File> {

        private int value;

        public IsNumber(int value) {
            this.value = value;
        }

        public boolean test(File e) {
            return e.isNumber(value);
        }

    }

    // -----------------------------------------------------------
    // - note
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<File>> filesByNoteCache = new Cache<java.lang.String,Set<File>>(
            new Cache.Factory<java.lang.String,Set<File>>() {
                public Set<File> create(java.lang.String note) {
                    return getEntities(new IsNote(note));
                }
            });

    public final Set<File> getFilesByNote(java.lang.String note) {
        return filesByNoteCache.get(note);
    }
    private Set<java.lang.String> notesCache;

    public final Set<java.lang.String> getNotes() {
        if (notesCache == null) {
            notesCache = new HashSet<java.lang.String>();
            for (File e : getEntities()) {
                if (e.isNoteSet()) notesCache.add(e.getNote());
            }
        }
        return notesCache;
    }

    private static class IsNote implements Predicate<File> {

        private java.lang.String value;

        public IsNote(java.lang.String value) {
            this.value = value;
        }

        public boolean test(File e) {
            return e.isNote(value);
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