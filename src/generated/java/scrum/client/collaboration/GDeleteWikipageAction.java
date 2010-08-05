// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.GwtActionGenerator










package scrum.client.collaboration;

import java.util.*;

public abstract class GDeleteWikipageAction
            extends scrum.client.common.AScrumAction {

    protected scrum.client.collaboration.Wikipage wikipage;

    public GDeleteWikipageAction(scrum.client.collaboration.Wikipage wikipage) {
        this.wikipage = wikipage;
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    @Override
    public String getId() {
        return ilarkesto.core.base.Str.getSimpleName(getClass()) + '_' + ilarkesto.core.base.Str.toHtmlId(wikipage);
    }

}