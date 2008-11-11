









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: scrum.mda.framework.GwtBeanGenerator










package scrum.client.sprint;

import java.util.*;

public abstract class GSprint
            extends scrum.client.common.AEntity {

    public GSprint() {}

    public GSprint(Map data) {
        super(data);
        updateProperties(data);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Sprint setLabel(java.lang.String label) {
        this.label = label ;
        propertyChanged("label", label);
        return (Sprint)this;
    }

    // --- project ---

    private String projectId;

    // --- update properties by map ---

    public void updateProperties(Map props) {
        label  = (java.lang.String) props.get("label");
        projectId = (String) props.get("id");
    }

}
