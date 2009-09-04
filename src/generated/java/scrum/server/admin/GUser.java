// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.admin;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GUser
            extends AUser
            implements ilarkesto.search.Searchable, java.lang.Comparable<User> {

    // --- AEntity ---

    public final UserDao getDao() {
        return userDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("name", this.name);
        properties.put("admin", this.admin);
        properties.put("email", this.email);
        properties.put("currentProjectId", this.currentProjectId);
    }

    public int compareTo(User other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GUser.class);

    public static final String TYPE = "user";


    // -----------------------------------------------------------
    // - Searchable
    // -----------------------------------------------------------

    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getName(), key)) return true;
        if (matchesKey(getEmail(), key)) return true;
        return false;
    }

    // -----------------------------------------------------------
    // - name
    // -----------------------------------------------------------

    private java.lang.String name;

    public final java.lang.String getName() {
        return name;
    }

    public final void setName(java.lang.String name) {
        name = prepareName(name);
        if (isName(name)) return;
        this.name = name;
        fireModified();
    }

    protected java.lang.String prepareName(java.lang.String name) {
        name = Str.removeUnreadableChars(name);
        return name;
    }

    public final boolean isNameSet() {
        return this.name != null;
    }

    public final boolean isName(java.lang.String name) {
        if (this.name == null && name == null) return true;
        return this.name != null && this.name.equals(name);
    }

    protected final void updateName(Object value) {
        setName((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - admin
    // -----------------------------------------------------------

    private boolean admin;

    public final boolean isAdmin() {
        return admin;
    }

    public final void setAdmin(boolean admin) {
        admin = prepareAdmin(admin);
        if (isAdmin(admin)) return;
        this.admin = admin;
        fireModified();
    }

    protected boolean prepareAdmin(boolean admin) {
        return admin;
    }

    public final boolean isAdmin(boolean admin) {
        return this.admin == admin;
    }

    protected final void updateAdmin(Object value) {
        setAdmin((Boolean)value);
    }

    // -----------------------------------------------------------
    // - email
    // -----------------------------------------------------------

    private java.lang.String email;

    public final java.lang.String getEmail() {
        return email;
    }

    public final void setEmail(java.lang.String email) {
        email = prepareEmail(email);
        if (isEmail(email)) return;
        this.email = email;
        fireModified();
    }

    protected java.lang.String prepareEmail(java.lang.String email) {
        email = Str.removeUnreadableChars(email);
        return email;
    }

    public final boolean isEmailSet() {
        return this.email != null;
    }

    public final boolean isEmail(java.lang.String email) {
        if (this.email == null && email == null) return true;
        return this.email != null && this.email.equals(email);
    }

    protected final void updateEmail(Object value) {
        setEmail((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - currentProject
    // -----------------------------------------------------------

    private String currentProjectId;
    private transient scrum.server.project.Project currentProjectCache;

    private void updateCurrentProjectCache() {
        currentProjectCache = this.currentProjectId == null ? null : (scrum.server.project.Project)projectDao.getById(this.currentProjectId);
    }

    public final scrum.server.project.Project getCurrentProject() {
        if (currentProjectCache == null) updateCurrentProjectCache();
        return currentProjectCache;
    }

    public final void setCurrentProject(scrum.server.project.Project currentProject) {
        currentProject = prepareCurrentProject(currentProject);
        if (isCurrentProject(currentProject)) return;
        this.currentProjectId = currentProject == null ? null : currentProject.getId();
        currentProjectCache = currentProject;
        fireModified();
    }

    protected scrum.server.project.Project prepareCurrentProject(scrum.server.project.Project currentProject) {
        return currentProject;
    }

    protected void repairDeadCurrentProjectReference(String entityId) {
        if (entityId.equals(this.currentProjectId)) {
            this.currentProjectId = null;
            fireModified();
        }
    }

    public final boolean isCurrentProjectSet() {
        return this.currentProjectId != null;
    }

    public final boolean isCurrentProject(scrum.server.project.Project currentProject) {
        if (this.currentProjectId == null && currentProject == null) return true;
        return currentProject != null && currentProject.getId().equals(this.currentProjectId);
    }

    protected final void updateCurrentProject(Object value) {
        setCurrentProject(value == null ? null : (scrum.server.project.Project)projectDao.getById((String)value));
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("name")) updateName(value);
            if (property.equals("admin")) updateAdmin(value);
            if (property.equals("email")) updateEmail(value);
            if (property.equals("currentProjectId")) updateCurrentProject(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadCurrentProjectReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        try {
            getCurrentProject();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead currentProject reference");
            repairDeadCurrentProjectReference(this.currentProjectId);
        }
    }


    // -----------------------------------------------------------
    // - dependencies
    // -----------------------------------------------------------

    protected static scrum.server.project.ProjectDao projectDao;

    public static final void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        GUser.projectDao = projectDao;
    }

    protected static UserDao userDao;

    public static final void setUserDao(UserDao userDao) {
        GUser.userDao = userDao;
    }

}