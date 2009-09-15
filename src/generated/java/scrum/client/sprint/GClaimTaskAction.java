// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtActionGenerator










package scrum.client.sprint;

import java.util.*;

public abstract class GClaimTaskAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.sprint.Task task;

    public GClaimTaskAction(scrum.client.sprint.Task task) {
        this.task = task;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

}