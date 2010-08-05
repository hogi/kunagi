// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.EntityGenerator










package scrum.server.collaboration;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GEmoticon
            extends AEntity
            implements ilarkesto.auth.ViewProtected<scrum.server.admin.User>, java.lang.Comparable<Emoticon> {

    // --- AEntity ---

    public final EmoticonDao getDao() {
        return emoticonDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("parentId", this.parentId);
        properties.put("ownerId", this.ownerId);
        properties.put("emotion", this.emotion);
    }

    public int compareTo(Emoticon other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final ilarkesto.core.logging.Log LOG = ilarkesto.core.logging.Log.get(GEmoticon.class);

    public static final String TYPE = "emoticon";

    // -----------------------------------------------------------
    // - parent
    // -----------------------------------------------------------

    private String parentId;
    private transient ilarkesto.persistence.AEntity parentCache;

    private void updateParentCache() {
        parentCache = this.parentId == null ? null : (ilarkesto.persistence.AEntity)getDaoService().getById(this.parentId);
    }

    public final String getParentId() {
        return this.parentId;
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
        fireModified("parent="+parent);
    }

    protected ilarkesto.persistence.AEntity prepareParent(ilarkesto.persistence.AEntity parent) {
        return parent;
    }

    protected void repairDeadParentReference(String entityId) {
        if (this.parentId == null || entityId.equals(this.parentId)) {
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
    // - owner
    // -----------------------------------------------------------

    private String ownerId;
    private transient scrum.server.admin.User ownerCache;

    private void updateOwnerCache() {
        ownerCache = this.ownerId == null ? null : (scrum.server.admin.User)userDao.getById(this.ownerId);
    }

    public final String getOwnerId() {
        return this.ownerId;
    }

    public final scrum.server.admin.User getOwner() {
        if (ownerCache == null) updateOwnerCache();
        return ownerCache;
    }

    public final void setOwner(scrum.server.admin.User owner) {
        owner = prepareOwner(owner);
        if (isOwner(owner)) return;
        this.ownerId = owner == null ? null : owner.getId();
        ownerCache = owner;
        fireModified("owner="+owner);
    }

    protected scrum.server.admin.User prepareOwner(scrum.server.admin.User owner) {
        return owner;
    }

    protected void repairDeadOwnerReference(String entityId) {
        if (this.ownerId == null || entityId.equals(this.ownerId)) {
            setOwner(null);
        }
    }

    public final boolean isOwnerSet() {
        return this.ownerId != null;
    }

    public final boolean isOwner(scrum.server.admin.User owner) {
        if (this.ownerId == null && owner == null) return true;
        return owner != null && owner.getId().equals(this.ownerId);
    }

    protected final void updateOwner(Object value) {
        setOwner(value == null ? null : (scrum.server.admin.User)userDao.getById((String)value));
    }

    // -----------------------------------------------------------
    // - emotion
    // -----------------------------------------------------------

    private java.lang.String emotion;

    public final java.lang.String getEmotion() {
        return emotion;
    }

    public final void setEmotion(java.lang.String emotion) {
        emotion = prepareEmotion(emotion);
        if (isEmotion(emotion)) return;
        this.emotion = emotion;
        fireModified("emotion="+emotion);
    }

    protected java.lang.String prepareEmotion(java.lang.String emotion) {
        emotion = Str.removeUnreadableChars(emotion);
        return emotion;
    }

    public final boolean isEmotionSet() {
        return this.emotion != null;
    }

    public final boolean isEmotion(java.lang.String emotion) {
        if (this.emotion == null && emotion == null) return true;
        return this.emotion != null && this.emotion.equals(emotion);
    }

    protected final void updateEmotion(Object value) {
        setEmotion((java.lang.String)value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("parentId")) updateParent(value);
            if (property.equals("ownerId")) updateOwner(value);
            if (property.equals("emotion")) updateEmotion(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadParentReference(entityId);
        repairDeadOwnerReference(entityId);
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
            getOwner();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead owner reference");
            repairDeadOwnerReference(this.ownerId);
        }
    }

    static EmoticonDao emoticonDao;

    public static final void setEmoticonDao(EmoticonDao emoticonDao) {
        GEmoticon.emoticonDao = emoticonDao;
    }

}