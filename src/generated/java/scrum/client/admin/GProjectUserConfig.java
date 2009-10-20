// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtEntityGenerator










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
            extends scrum.client.common.AScrumGwtEntity {

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

    public transient ilarkesto.gwt.client.editor.ATextEditorModel colorModel = new ilarkesto.gwt.client.editor.ATextEditorModel() {

        @Override
        public String getValue() {
            return getColor();
        }

        @Override
        public void setValue(String value) {
            setColor(value);
        }

    };

    // --- misconducts ---

    private int misconducts ;

    public final int getMisconducts() {
        return this.misconducts ;
    }

    public final ProjectUserConfig setMisconducts(int misconducts) {
        if (isMisconducts(misconducts)) return (ProjectUserConfig)this;
        this.misconducts = misconducts ;
        propertyChanged("misconducts", this.misconducts);
        return (ProjectUserConfig)this;
    }

    public final boolean isMisconducts(int misconducts) {
        return equals(this.misconducts, misconducts);
    }

    public transient ilarkesto.gwt.client.editor.AIntegerEditorModel misconductsModel = new ilarkesto.gwt.client.editor.AIntegerEditorModel() {

        @Override
        public Integer getValue() {
            return getMisconducts();
        }

        @Override
        public void increment() {
            setMisconducts(getMisconducts() + 1);
        }

        @Override
        public void decrement() {
            setMisconducts(getMisconducts() - 1);
        }

        @Override
        public void setValue(Integer value) {
            setMisconducts(value);
        }

    };

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        userId = (String) props.get("userId");
        color  = (java.lang.String) props.get("color");
        misconducts  = (Integer) props.get("misconducts");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("userId", this.userId);
        properties.put("color", this.color);
        properties.put("misconducts", this.misconducts);
    }

}