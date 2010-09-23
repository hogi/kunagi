// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtActionGenerator










package scrum.client.collaboration;

import java.util.*;

public abstract class GPublishCommentAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.collaboration.Comment comment;

    public GPublishCommentAction(scrum.client.collaboration.Comment comment) {
        this.comment = comment;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public String getId() {
        return ilarkesto.core.base.Str.getSimpleName(getClass()) + '_' + ilarkesto.core.base.Str.toHtmlId(comment);
    }

}