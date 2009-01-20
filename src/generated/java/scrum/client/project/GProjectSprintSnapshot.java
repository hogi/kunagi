









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

    // --- remainingWork ---

    private int remainingWork ;

    public final int getRemainingWork() {
        return this.remainingWork ;
    }

    public final ProjectSprintSnapshot setRemainingWork(int remainingWork) {
        this.remainingWork = remainingWork ;
        propertyChanged("remainingWork", this.remainingWork);
        return (ProjectSprintSnapshot)this;
    }

    public final boolean isRemainingWork(int remainingWork) {
        return equals(this.remainingWork, remainingWork);
    }

    // --- burnedWork ---

    private int burnedWork ;

    public final int getBurnedWork() {
        return this.burnedWork ;
    }

    public final ProjectSprintSnapshot setBurnedWork(int burnedWork) {
        this.burnedWork = burnedWork ;
        propertyChanged("burnedWork", this.burnedWork);
        return (ProjectSprintSnapshot)this;
    }

    public final boolean isBurnedWork(int burnedWork) {
        return equals(this.burnedWork, burnedWork);
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
        projectId = (String) props.get("projectId");
        remainingWork  = (Integer) props.get("remainingWork");
        burnedWork  = (Integer) props.get("burnedWork");
        dateCrap  = (java.util.Date) props.get("dateCrap");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("remainingWork", this.remainingWork);
        properties.put("burnedWork", this.burnedWork);
        properties.put("dateCrap", this.dateCrap);
    }

}
