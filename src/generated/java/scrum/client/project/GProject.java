









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: scrum.mda.GwtBeanGenerator










package scrum.client.project;

import java.util.*;

public abstract class GProject
            extends scrum.client.common.AEntity {

    public GProject() {}

    public GProject(Map data) {
        super(data);
        updateProperties(data);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Project setLabel(java.lang.String label) {
        this.label = label ;
        propertyChanged("label", label);
        return (Project)this;
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        label  = (java.lang.String) props.get("label");
    }

}
