// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.gwt.GwtComponentsGenerator










package scrum.client;

import java.util.*;

public abstract class GComponents
            extends ilarkesto.gwt.client.AComponents<EventBus, Dao> {

    GComponents(EventBus eventBus, Dao dao) {
        super(eventBus, dao);
    }

    // --- auth ---

    private Auth auth;

    public final Auth getAuth() {
        if (auth == null) {
            auth = new Auth();
            initializeAuth(auth);
        }
        return auth;
    }

    protected void initializeAuth(Auth auth) {
        ilarkesto.gwt.client.GwtLogger.DEBUG("Initializung component:", auth);
        getEventBus().addListener(auth);
    }

}