// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtActionGenerator










package scrum.client.admin;

import java.util.*;

public abstract class GDeleteUserAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.admin.User user;

    public GDeleteUserAction(scrum.client.admin.User user) {
        this.user = user;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public String getId() {
        return ilarkesto.core.base.Str.getSimpleName(getClass()) + '_' + ilarkesto.core.base.Str.toHtmlId(user);
    }

}