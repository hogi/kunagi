// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtActionGenerator










package scrum.client.sprint;

import java.util.*;

public abstract class GCloseTaskAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.sprint.Task task;

    public GCloseTaskAction(scrum.client.sprint.Task task) {
        this.task = task;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

}