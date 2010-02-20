// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.collaboration;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GWikipageDao
            extends ilarkesto.persistence.ADao<Wikipage> {

    public final String getEntityName() {
        return Wikipage.TYPE;
    }

    public final Class getEntityClass() {
        return Wikipage.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        wikipagesByProjectCache.clear();
        projectsCache = null;
        wikipagesByNameCache.clear();
        namesCache = null;
        wikipagesByTextCache.clear();
        textsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Wikipage) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Wikipage) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private final Cache<scrum.server.project.Project,Set<Wikipage>> wikipagesByProjectCache = new Cache<scrum.server.project.Project,Set<Wikipage>>(
            new Cache.Factory<scrum.server.project.Project,Set<Wikipage>>() {
                public Set<Wikipage> create(scrum.server.project.Project project) {
                    return getEntities(new IsProject(project));
                }
            });

    public final Set<Wikipage> getWikipagesByProject(scrum.server.project.Project project) {
        return wikipagesByProjectCache.get(project);
    }
    private Set<scrum.server.project.Project> projectsCache;

    public final Set<scrum.server.project.Project> getProjects() {
        if (projectsCache == null) {
            projectsCache = new HashSet<scrum.server.project.Project>();
            for (Wikipage e : getEntities()) {
                if (e.isProjectSet()) projectsCache.add(e.getProject());
            }
        }
        return projectsCache;
    }

    private static class IsProject implements Predicate<Wikipage> {

        private scrum.server.project.Project value;

        public IsProject(scrum.server.project.Project value) {
            this.value = value;
        }

        public boolean test(Wikipage e) {
            return e.isProject(value);
        }

    }

    // -----------------------------------------------------------
    // - name
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Wikipage>> wikipagesByNameCache = new Cache<java.lang.String,Set<Wikipage>>(
            new Cache.Factory<java.lang.String,Set<Wikipage>>() {
                public Set<Wikipage> create(java.lang.String name) {
                    return getEntities(new IsName(name));
                }
            });

    public final Set<Wikipage> getWikipagesByName(java.lang.String name) {
        return wikipagesByNameCache.get(name);
    }
    private Set<java.lang.String> namesCache;

    public final Set<java.lang.String> getNames() {
        if (namesCache == null) {
            namesCache = new HashSet<java.lang.String>();
            for (Wikipage e : getEntities()) {
                if (e.isNameSet()) namesCache.add(e.getName());
            }
        }
        return namesCache;
    }

    private static class IsName implements Predicate<Wikipage> {

        private java.lang.String value;

        public IsName(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Wikipage e) {
            return e.isName(value);
        }

    }

    // -----------------------------------------------------------
    // - text
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Wikipage>> wikipagesByTextCache = new Cache<java.lang.String,Set<Wikipage>>(
            new Cache.Factory<java.lang.String,Set<Wikipage>>() {
                public Set<Wikipage> create(java.lang.String text) {
                    return getEntities(new IsText(text));
                }
            });

    public final Set<Wikipage> getWikipagesByText(java.lang.String text) {
        return wikipagesByTextCache.get(text);
    }
    private Set<java.lang.String> textsCache;

    public final Set<java.lang.String> getTexts() {
        if (textsCache == null) {
            textsCache = new HashSet<java.lang.String>();
            for (Wikipage e : getEntities()) {
                if (e.isTextSet()) textsCache.add(e.getText());
            }
        }
        return textsCache;
    }

    private static class IsText implements Predicate<Wikipage> {

        private java.lang.String value;

        public IsText(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Wikipage e) {
            return e.isText(value);
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