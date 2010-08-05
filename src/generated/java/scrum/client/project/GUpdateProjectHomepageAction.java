// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtActionGenerator










package scrum.client.project;

import java.util.*;

public abstract class GUpdateProjectHomepageAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.project.Project project;

    public GUpdateProjectHomepageAction(scrum.client.project.Project project) {
        this.project = project;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public String getId() {
        return ilarkesto.core.base.Str.getSimpleName(getClass()) + '_' + ilarkesto.core.base.Str.toHtmlId(project);
    }

}