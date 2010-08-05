// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtActionGenerator










package scrum.client.collaboration;

import java.util.*;

public abstract class GDeleteSubjectAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.collaboration.Subject subject;

    public GDeleteSubjectAction(scrum.client.collaboration.Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public String getId() {
        return ilarkesto.core.base.Str.getSimpleName(getClass()) + '_' + ilarkesto.core.base.Str.toHtmlId(subject);
    }

}