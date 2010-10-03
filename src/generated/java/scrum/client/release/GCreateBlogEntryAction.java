// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtActionGenerator










package scrum.client.release;

import java.util.*;

public abstract class GCreateBlogEntryAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.release.Release release;

    public GCreateBlogEntryAction(scrum.client.release.Release release) {
        this.release = release;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public String getId() {
        return ilarkesto.core.base.Str.getSimpleName(getClass()) + '_' + ilarkesto.core.base.Str.toHtmlId(release);
    }

}