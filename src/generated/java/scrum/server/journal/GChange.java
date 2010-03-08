// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.EntityGenerator










package scrum.server.journal;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GChange
            extends AEntity
            implements ilarkesto.auth.ViewProtected<scrum.server.admin.User>, java.lang.Comparable<Change> {

    // --- AEntity ---

    public final ChangeDao getDao() {
        return changeDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("parentId", this.parentId);
        properties.put("userId", this.userId);
        properties.put("dateAndTime", this.dateAndTime == null ? null : this.dateAndTime.toString());
        properties.put("key", this.key);
        properties.put("value", this.value);
    }

    public int compareTo(Change other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final ilarkesto.core.logging.Log LOG = ilarkesto.core.logging.Log.get(GChange.class);

    public static final String TYPE = "change";

    // -----------------------------------------------------------
    // - parent
    // -----------------------------------------------------------

    private String parentId;
    private transient ilarkesto.persistence.AEntity parentCache;

    private void updateParentCache() {
        parentCache = this.parentId == null ? null : (ilarkesto.persistence.AEntity)getDaoService().getById(this.parentId);
    }

    public final ilarkesto.persistence.AEntity getParent() {
        if (parentCache == null) updateParentCache();
        return parentCache;
    }

    public final void setParent(ilarkesto.persistence.AEntity parent) {
        parent = prepareParent(parent);
        if (isParent(parent)) return;
        this.parentId = parent == null ? null : parent.getId();
        parentCache = parent;
        fireModified();
    }

    protected ilarkesto.persistence.AEntity prepareParent(ilarkesto.persistence.AEntity parent) {
        return parent;
    }

    protected void repairDeadParentReference(String entityId) {
        if (entityId.equals(this.parentId)) {
            repairMissingMaster();
        }
    }

    public final boolean isParentSet() {
        return this.parentId != null;
    }

    public final boolean isParent(ilarkesto.persistence.AEntity parent) {
        if (this.parentId == null && parent == null) return true;
        return parent != null && parent.getId().equals(this.parentId);
    }

    protected final void updateParent(Object value) {
        setParent(value == null ? null : (ilarkesto.persistence.AEntity)getDaoService().getById((String)value));
    }

    // -----------------------------------------------------------
    // - user
    // -----------------------------------------------------------

    private String userId;
    private transient scrum.server.admin.User userCache;

    private void updateUserCache() {
        userCache = this.userId == null ? null : (scrum.server.admin.User)userDao.getById(this.userId);
    }

    public final scrum.server.admin.User getUser() {
        if (userCache == null) updateUserCache();
        return userCache;
    }

    public final void setUser(scrum.server.admin.User user) {
        user = prepareUser(user);
        if (isUser(user)) return;
        this.userId = user == null ? null : user.getId();
        userCache = user;
        fireModified();
    }

    protected scrum.server.admin.User prepareUser(scrum.server.admin.User user) {
        return user;
    }

    protected void repairDeadUserReference(String entityId) {
        if (entityId.equals(this.userId)) {
            this.userId = null;
            fireModified();
        }
    }

    public final boolean isUserSet() {
        return this.userId != null;
    }

    public final boolean isUser(scrum.server.admin.User user) {
        if (this.userId == null && user == null) return true;
        return user != null && user.getId().equals(this.userId);
    }

    protected final void updateUser(Object value) {
        setUser(value == null ? null : (scrum.server.admin.User)userDao.getById((String)value));
    }

    // -----------------------------------------------------------
    // - dateAndTime
    // -----------------------------------------------------------

    private ilarkesto.base.time.DateAndTime dateAndTime;

    public final ilarkesto.base.time.DateAndTime getDateAndTime() {
        return dateAndTime;
    }

    public final void setDateAndTime(ilarkesto.base.time.DateAndTime dateAndTime) {
        dateAndTime = prepareDateAndTime(dateAndTime);
        if (isDateAndTime(dateAndTime)) return;
        this.dateAndTime = dateAndTime;
        fireModified();
    }

    protected ilarkesto.base.time.DateAndTime prepareDateAndTime(ilarkesto.base.time.DateAndTime dateAndTime) {
        return dateAndTime;
    }

    public final boolean isDateAndTimeSet() {
        return this.dateAndTime != null;
    }

    public final boolean isDateAndTime(ilarkesto.base.time.DateAndTime dateAndTime) {
        if (this.dateAndTime == null && dateAndTime == null) return true;
        return this.dateAndTime != null && this.dateAndTime.equals(dateAndTime);
    }

    protected final void updateDateAndTime(Object value) {
        value = value == null ? null : new ilarkesto.base.time.DateAndTime((String)value);
        setDateAndTime((ilarkesto.base.time.DateAndTime)value);
    }

    // -----------------------------------------------------------
    // - key
    // -----------------------------------------------------------

    private java.lang.String key;

    public final java.lang.String getKey() {
        return key;
    }

    public final void setKey(java.lang.String key) {
        key = prepareKey(key);
        if (isKey(key)) return;
        this.key = key;
        fireModified();
    }

    protected java.lang.String prepareKey(java.lang.String key) {
        key = Str.removeUnreadableChars(key);
        return key;
    }

    public final boolean isKeySet() {
        return this.key != null;
    }

    public final boolean isKey(java.lang.String key) {
        if (this.key == null && key == null) return true;
        return this.key != null && this.key.equals(key);
    }

    protected final void updateKey(Object value) {
        setKey((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - value
    // -----------------------------------------------------------

    private java.lang.String value;

    public final java.lang.String getValue() {
        return value;
    }

    public final void setValue(java.lang.String value) {
        value = prepareValue(value);
        if (isValue(value)) return;
        this.value = value;
        fireModified();
    }

    protected java.lang.String prepareValue(java.lang.String value) {
        value = Str.removeUnreadableChars(value);
        return value;
    }

    public final boolean isValueSet() {
        return this.value != null;
    }

    public final boolean isValue(java.lang.String value) {
        if (this.value == null && value == null) return true;
        return this.value != null && this.value.equals(value);
    }

    protected final void updateValue(Object value) {
        setValue((java.lang.String)value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("parentId")) updateParent(value);
            if (property.equals("userId")) updateUser(value);
            if (property.equals("dateAndTime")) updateDateAndTime(value);
            if (property.equals("key")) updateKey(value);
            if (property.equals("value")) updateValue(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadParentReference(entityId);
        repairDeadUserReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        if (!isParentSet()) {
            repairMissingMaster();
            return;
        }
        try {
            getParent();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead parent reference");
            repairDeadParentReference(this.parentId);
        }
        try {
            getUser();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead user reference");
            repairDeadUserReference(this.userId);
        }
    }

    static ChangeDao changeDao;

    public static final void setChangeDao(ChangeDao changeDao) {
        GChange.changeDao = changeDao;
    }

}