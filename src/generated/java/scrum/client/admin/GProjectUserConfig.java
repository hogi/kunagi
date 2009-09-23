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

public abstract class GProjectUserConfig
            extends ilarkesto.gwt.client.AGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GProjectUserConfig() {
    }

    public GProjectUserConfig(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "projectUserConfig";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- project ---

    private String projectId;

    public final scrum.client.project.Project getProject() {
        if (projectId == null) return null;
        return getDao().getProject(this.projectId);
    }

    public final boolean isProjectSet() {
        return projectId != null;
    }

    public final ProjectUserConfig setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (ProjectUserConfig) this;
        this.projectId = id;
        propertyChanged("projectId", this.projectId);
        return (ProjectUserConfig)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- user ---

    private String userId;

    public final scrum.client.admin.User getUser() {
        if (userId == null) return null;
        return getDao().getUser(this.userId);
    }

    public final boolean isUserSet() {
        return userId != null;
    }

    public final ProjectUserConfig setUser(scrum.client.admin.User user) {
        String id = user == null ? null : user.getId();
        if (equals(this.userId, id)) return (ProjectUserConfig) this;
        this.userId = id;
        propertyChanged("userId", this.userId);
        return (ProjectUserConfig)this;
    }

    public final boolean isUser(scrum.client.admin.User user) {
        return equals(this.userId, user);
    }

    // --- color ---

    private java.lang.String color ;

    public final java.lang.String getColor() {
        return this.color ;
    }

    public final ProjectUserConfig setColor(java.lang.String color) {
        if (isColor(color)) return (ProjectUserConfig)this;
        this.color = color ;
        propertyChanged("color", this.color);
        return (ProjectUserConfig)this;
    }

    public final boolean isColor(java.lang.String color) {
        return equals(this.color, color);
    }

    // --- selectedEntitysIds ---

    private java.util.Set<java.lang.String> selectedEntitysIds = new java.util.HashSet<java.lang.String>();

    public final java.util.Set<java.lang.String> getSelectedEntitysIds() {
        return new java.util.HashSet<java.lang.String>(selectedEntitysIds);
    }

    public final void setSelectedEntitysIds(java.util.Set<java.lang.String> selectedEntitysIds) {
        if (selectedEntitysIds == null) throw new IllegalArgumentException("null is not allowed");
        if (this.selectedEntitysIds.equals(selectedEntitysIds)) return;
        this.selectedEntitysIds = new java.util.HashSet<java.lang.String>(selectedEntitysIds);
        propertyChanged("selectedEntitysIds", this.selectedEntitysIds);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        userId = (String) props.get("userId");
        color  = (java.lang.String) props.get("color");
        selectedEntitysIds  = (java.util.Set<java.lang.String>) props.get("selectedEntitysIds");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("userId", this.userId);
        properties.put("color", this.color);
        properties.put("selectedEntitysIds", this.selectedEntitysIds);
    }

}