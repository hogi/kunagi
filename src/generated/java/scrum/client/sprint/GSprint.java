









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityGenerator










package scrum.client.sprint;

import java.util.*;

public abstract class GSprint
            extends scrum.client.common.AGwtEntity {

    public GSprint() {
    }

    public GSprint(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "sprint";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Sprint setLabel(java.lang.String label) {
        this.label = label ;
        propertyChanged("label", this.label);
        return (Sprint)this;
    }

    public final boolean isLabel(java.lang.String label) {
        return equals(this.label, label);
    }

    // --- project ---

    private String projectId;

    public final scrum.client.project.Project getProject() {
        return getDao().getProject(this.projectId);
    }

    public final Sprint setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (Sprint) this;
        this.projectId = id;
        propertyChanged("project", this.projectId);
        return (Sprint)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- endCrap ---

    private java.util.Date endCrap ;

    public final java.util.Date getEndCrap() {
        return this.endCrap ;
    }

    public final Sprint setEndCrap(java.util.Date endCrap) {
        this.endCrap = endCrap ;
        propertyChanged("endCrap", this.endCrap);
        return (Sprint)this;
    }

    public final boolean isEndCrap(java.util.Date endCrap) {
        return equals(this.endCrap, endCrap);
    }

    // --- beginCrap ---

    private java.util.Date beginCrap ;

    public final java.util.Date getBeginCrap() {
        return this.beginCrap ;
    }

    public final Sprint setBeginCrap(java.util.Date beginCrap) {
        this.beginCrap = beginCrap ;
        propertyChanged("beginCrap", this.beginCrap);
        return (Sprint)this;
    }

    public final boolean isBeginCrap(java.util.Date beginCrap) {
        return equals(this.beginCrap, beginCrap);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        label  = (java.lang.String) props.get("label");
        projectId = (String) props.get("projectId");
        endCrap  = (java.util.Date) props.get("endCrap");
        beginCrap  = (java.util.Date) props.get("beginCrap");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("label", this.label);
        properties.put("projectId", this.projectId);
        properties.put("endCrap", this.endCrap);
        properties.put("beginCrap", this.beginCrap);
    }

}
