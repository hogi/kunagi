// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtActionGenerator










package scrum.client.risks;

import java.util.*;

public abstract class GDeleteRiskAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.risks.Risk risk;

    public GDeleteRiskAction(scrum.client.risks.Risk risk) {
        this.risk = risk;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public String getId() {
        return ilarkesto.core.base.Str.getSimpleName(getClass()) + '_' + ilarkesto.core.base.Str.toHtmlId(risk);
    }

}