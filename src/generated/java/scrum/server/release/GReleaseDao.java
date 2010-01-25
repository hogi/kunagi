// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.release;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
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
        releasesByLabelCache.clear();
        labelsCache = null;
        releasesByPublicationDateCache.clear();
        publicationDatesCache = null;
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