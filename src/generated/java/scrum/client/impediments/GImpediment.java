









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: scrum.mda.GwtBeanGenerator










package scrum.client.impediments;

import java.util.*;

public abstract class GImpediment
            extends scrum.client.common.AEntity {

    public GImpediment() {}

    public GImpediment(Map data) {
        super(data);
        updateProperties(data);
    }

    // --- solved ---

    private boolean solved ;

    public final boolean isSolved() {
        return this.solved ;
    }

    public final Impediment setSolved(boolean solved) {
        this.solved = solved ;
        propertyChanged("solved", toString(solved));
        return (Impediment)this;
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Impediment setLabel(java.lang.String label) {
        this.label = label ;
        propertyChanged("label", label);
        return (Impediment)this;
    }

    // --- project ---

    private String projectId;

    // --- solution ---

    private java.lang.String solution ;

    public final java.lang.String getSolution() {
        return this.solution ;
    }

    public final Impediment setSolution(java.lang.String solution) {
        this.solution = solution ;
        propertyChanged("solution", solution);
        return (Impediment)this;
    }

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final Impediment setDescription(java.lang.String description) {
        this.description = description ;
        propertyChanged("description", description);
        return (Impediment)this;
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        solved  = (Boolean) props.get("solved");
        label  = (java.lang.String) props.get("label");
        projectId = (String) props.get("id");
        solution  = (java.lang.String) props.get("solution");
        description  = (java.lang.String) props.get("description");
    }

}
