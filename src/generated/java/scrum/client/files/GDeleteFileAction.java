// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtActionGenerator










package scrum.client.files;

import java.util.*;

public abstract class GDeleteFileAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.files.File file;

    public GDeleteFileAction(scrum.client.files.File file) {
        this.file = file;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public String getId() {
        return ilarkesto.core.base.Str.getSimpleName(getClass()) + '_' + ilarkesto.core.base.Str.toHtmlId(file);
    }

}