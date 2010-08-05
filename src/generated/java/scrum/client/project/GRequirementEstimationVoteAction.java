// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtActionGenerator










package scrum.client.project;

import java.util.*;

public abstract class GRequirementEstimationVoteAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.project.Requirement requirement;
    protected java.lang.Float estimatedWork;

    public GRequirementEstimationVoteAction(scrum.client.project.Requirement requirement, java.lang.Float estimatedWork) {
        this.requirement = requirement;
        this.estimatedWork = estimatedWork;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public String getId() {
        return ilarkesto.core.base.Str.getSimpleName(getClass()) + '_' + ilarkesto.core.base.Str.toHtmlId(requirement, estimatedWork);
    }

}