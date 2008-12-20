









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityGenerator










package scrum.client.project;

import java.util.*;

public abstract class GProjectSprintSnapshot
            extends scrum.client.common.AGwtEntity {

    public GProjectSprintSnapshot() {
    }

    public GProjectSprintSnapshot(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "projectSprintSnapshot";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- burndown ---

    private int burndown ;

    public final int getBurndown() {
        return this.burndown ;
    }

    public final ProjectSprintSnapshot setBurndown(int burndown) {
        this.burndown = burndown ;
        propertyChanged("burndown", this.burndown);
        return (ProjectSprintSnapshot)this;
    }

    public final boolean isBurndown(int burndown) {
        return equals(this.burndown, burndown);
    }

    // --- effort ---

    private int effort ;

    public final int getEffort() {
        return this.effort ;
    }

    public final ProjectSprintSnapshot setEffort(int effort) {
        this.effort = effort ;
        propertyChanged("effort", this.effort);
        return (ProjectSprintSnapshot)this;
    }

    public final boolean isEffort(int effort) {
        return equals(this.effort, effort);
    }

    // --- project ---

    private String projectId;

    public final scrum.client.project.Project getProject() {
        return getDao().getProject(this.projectId);
    }

    public final ProjectSprintSnapshot setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (ProjectSprintSnapshot) this;
        this.projectId = id;
        propertyChanged("project", this.projectId);
        return (ProjectSprintSnapshot)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- dateCrap ---

    private java.util.Date dateCrap ;

    public final java.util.Date getDateCrap() {
        return this.dateCrap ;
    }

    public final ProjectSprintSnapshot setDateCrap(java.util.Date dateCrap) {
        this.dateCrap = dateCrap ;
        propertyChanged("dateCrap", this.dateCrap);
        return (ProjectSprintSnapshot)this;
    }

    public final boolean isDateCrap(java.util.Date dateCrap) {
        return equals(this.dateCrap, dateCrap);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        burndown  = (Integer) props.get("burndown");
        effort  = (Integer) props.get("effort");
        projectId = (String) props.get("projectId");
        dateCrap  = (java.util.Date) props.get("dateCrap");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("burndown", this.burndown);
        properties.put("effort", this.effort);
        properties.put("projectId", this.projectId);
        properties.put("dateCrap", this.dateCrap);
    }

}
