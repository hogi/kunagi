// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtActionGenerator










package scrum.client.project;

import java.util.*;

public abstract class GRequirementEstimationVoteAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.project.Requirement requirement;
    protected java.lang.Integer estimatedWork;

    public GRequirementEstimationVoteAction(scrum.client.project.Requirement requirement, java.lang.Integer estimatedWork) {
        this.requirement = requirement;
        this.estimatedWork = estimatedWork;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

}