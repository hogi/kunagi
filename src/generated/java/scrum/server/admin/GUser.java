









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.admin;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;

public abstract class GUser
            extends AUser {

    // --- AEntity ---

    public final UserDao getDao() {
        return userDao;
    }

    protected void repairDeadValueObject(AValueObject valueObject) {
    }

    private static final Logger LOG = Logger.get(GUser.class);

    public static final String TYPE = "user";

    // --- copy constructor ---
    public GUser(GUser template) {
        super(template);
        if (template==null) return;

    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
    }


    // -----------------------------------------------------------
    // - composites
    // -----------------------------------------------------------

    // --- dependencies ---

    protected static UserDao userDao;

    public static final void setUserDao(UserDao userDao) {
        GUser.userDao = userDao;
    }

}
