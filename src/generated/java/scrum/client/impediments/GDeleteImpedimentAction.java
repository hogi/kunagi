// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtActionGenerator










package scrum.client.impediments;

import java.util.*;

public abstract class GDeleteImpedimentAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.impediments.Impediment impediment;

    public GDeleteImpedimentAction(scrum.client.impediments.Impediment impediment) {
        this.impediment = impediment;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public String getId() {
        return ilarkesto.core.base.Str.getSimpleName(getClass()) + '_' + ilarkesto.core.base.Str.toHtmlId(impediment);
    }

}