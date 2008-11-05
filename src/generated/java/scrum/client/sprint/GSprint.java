









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: scrum.mda.GwtBeanGenerator










package scrum.client.sprint;

import java.util.*;

public abstract class GSprint
            extends scrum.client.common.AEntity {

    public GSprint() {}

    public GSprint(Map data) {
        super(data);
    }

    // --- project ---

    private String projectId;

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Sprint setLabel(java.lang.String label) {
        this.label = label ;
        return (Sprint)this;
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("id");
        label  = (java.lang.String) props.get("label");
    }

}
