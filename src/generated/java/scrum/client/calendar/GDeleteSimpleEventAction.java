// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtActionGenerator










package scrum.client.calendar;

import java.util.*;

public abstract class GDeleteSimpleEventAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.calendar.SimpleEvent simpleEvent;

    public GDeleteSimpleEventAction(scrum.client.calendar.SimpleEvent simpleEvent) {
        this.simpleEvent = simpleEvent;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public String getId() {
        return ilarkesto.core.base.Str.getSimpleName(getClass()) + '_' + ilarkesto.core.base.Str.toHtmlId(simpleEvent);
    }

}