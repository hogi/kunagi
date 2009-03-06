// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.EntityGenerator










package scrum.server.admin;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GUser
            extends AUser
            implements java.lang.Comparable<User> {

    // --- AEntity ---

    public final UserDao getDao() {
        return userDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("name", this.name);
    }

    public int compareTo(User other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final Logger LOG = Logger.get(GUser.class);

    public static final String TYPE = "user";

    // -----------------------------------------------------------
    // - name
    // -----------------------------------------------------------

    private java.lang.String name;

    public final java.lang.String getName() {
        return name;
    }

    public final void setName(java.lang.String name) {
        name = prepareName(name);
        if (isName(name)) return;
        this.name = name;
        fireModified();
    }

    protected java.lang.String prepareName(java.lang.String name) {
        name = Str.removeUnreadableChars(name);
        return name;
    }

    public final boolean isNameSet() {
        return this.name != null;
    }

    public final boolean isName(java.lang.String name) {
        if (this.name == null && name == null) return true;
        return this.name != null && this.name.equals(name);
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
    }

    // --- dependencies ---

    protected static UserDao userDao;

    public static final void setUserDao(UserDao userDao) {
        GUser.userDao = userDao;
    }


    // -----------------------------------------------------------
    // - composites
    // -----------------------------------------------------------

}