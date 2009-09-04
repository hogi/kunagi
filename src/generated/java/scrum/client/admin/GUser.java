// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityGenerator










package scrum.client.admin;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GUser
            extends ilarkesto.gwt.client.AGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GUser() {
    }

    public GUser(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "user";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- name ---

    private java.lang.String name ;

    public final java.lang.String getName() {
        return this.name ;
    }

    public final User setName(java.lang.String name) {
        this.name = name ;
        propertyChanged("name", this.name);
        return (User)this;
    }

    public final boolean isName(java.lang.String name) {
        return equals(this.name, name);
    }

    // --- admin ---

    private boolean admin ;

    public final boolean isAdmin() {
        return this.admin ;
    }

    public final User setAdmin(boolean admin) {
        this.admin = admin ;
        propertyChanged("admin", this.admin);
        return (User)this;
    }

    public final boolean isAdmin(boolean admin) {
        return equals(this.admin, admin);
    }

    // --- email ---

    private java.lang.String email ;

    public final java.lang.String getEmail() {
        return this.email ;
    }

    public final User setEmail(java.lang.String email) {
        this.email = email ;
        propertyChanged("email", this.email);
        return (User)this;
    }

    public final boolean isEmail(java.lang.String email) {
        return equals(this.email, email);
    }

    // --- currentProject ---

    private String currentProjectId;

    public final scrum.client.project.Project getCurrentProject() {
        if (currentProjectId == null) return null;
        return getDao().getProject(this.currentProjectId);
    }

    public final boolean isCurrentProjectSet() {
        return currentProjectId != null;
    }

    public final User setCurrentProject(scrum.client.project.Project currentProject) {
        String id = currentProject == null ? null : currentProject.getId();
        if (equals(this.currentProjectId, id)) return (User) this;
        this.currentProjectId = id;
        propertyChanged("currentProjectId", this.currentProjectId);
        return (User)this;
    }

    public final boolean isCurrentProject(scrum.client.project.Project currentProject) {
        return equals(this.currentProjectId, currentProject);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        name  = (java.lang.String) props.get("name");
        admin  = (Boolean) props.get("admin");
        email  = (java.lang.String) props.get("email");
        currentProjectId = (String) props.get("currentProjectId");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("name", this.name);
        properties.put("admin", this.admin);
        properties.put("email", this.email);
        properties.put("currentProjectId", this.currentProjectId);
    }

}