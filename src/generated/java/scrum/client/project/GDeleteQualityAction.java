// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtActionGenerator










package scrum.client.project;

import java.util.*;

public abstract class GDeleteQualityAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.project.Quality quality;

    public GDeleteQualityAction(scrum.client.project.Quality quality) {
        this.quality = quality;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public String getId() {
        return ilarkesto.core.base.Str.getSimpleName(getClass()) + '_' + ilarkesto.core.base.Str.toHtmlId(quality);
    }

}