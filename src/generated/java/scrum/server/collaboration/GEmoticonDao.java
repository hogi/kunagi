// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.DaoGenerator










package scrum.server.collaboration;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import ilarkesto.fp.*;

public abstract class GEmoticonDao
            extends ilarkesto.persistence.ADao<Emoticon> {

    public final String getEntityName() {
        return Emoticon.TYPE;
    }

    public final Class getEntityClass() {
        return Emoticon.class;
    }

    // --- clear caches ---
    public void clearCaches() {
        emoticonsByParentCache.clear();
        parentsCache = null;
        emoticonsByOwnerCache.clear();
        ownersCache = null;
        emoticonsByEmotionCache.clear();
        emotionsCache = null;
    }

    @Override
    public void entityDeleted(EntityEvent event) {
        super.entityDeleted(event);
        if (event.getEntity() instanceof Emoticon) {
            clearCaches();
        }
    }

    @Override
    public void entitySaved(EntityEvent event) {
        super.entitySaved(event);
        if (event.getEntity() instanceof Emoticon) {
            clearCaches();
        }
    }

    // -----------------------------------------------------------
    // - parent
    // -----------------------------------------------------------

    private final Cache<ilarkesto.persistence.AEntity,Set<Emoticon>> emoticonsByParentCache = new Cache<ilarkesto.persistence.AEntity,Set<Emoticon>>(
            new Cache.Factory<ilarkesto.persistence.AEntity,Set<Emoticon>>() {
                public Set<Emoticon> create(ilarkesto.persistence.AEntity parent) {
                    return getEntities(new IsParent(parent));
                }
            });

    public final Set<Emoticon> getEmoticonsByParent(ilarkesto.persistence.AEntity parent) {
        return emoticonsByParentCache.get(parent);
    }
    private Set<ilarkesto.persistence.AEntity> parentsCache;

    public final Set<ilarkesto.persistence.AEntity> getParents() {
        if (parentsCache == null) {
            parentsCache = new HashSet<ilarkesto.persistence.AEntity>();
            for (Emoticon e : getEntities()) {
                if (e.isParentSet()) parentsCache.add(e.getParent());
            }
        }
        return parentsCache;
    }

    private static class IsParent implements Predicate<Emoticon> {

        private ilarkesto.persistence.AEntity value;

        public IsParent(ilarkesto.persistence.AEntity value) {
            this.value = value;
        }

        public boolean test(Emoticon e) {
            return e.isParent(value);
        }

    }

    // -----------------------------------------------------------
    // - owner
    // -----------------------------------------------------------

    private final Cache<scrum.server.admin.User,Set<Emoticon>> emoticonsByOwnerCache = new Cache<scrum.server.admin.User,Set<Emoticon>>(
            new Cache.Factory<scrum.server.admin.User,Set<Emoticon>>() {
                public Set<Emoticon> create(scrum.server.admin.User owner) {
                    return getEntities(new IsOwner(owner));
                }
            });

    public final Set<Emoticon> getEmoticonsByOwner(scrum.server.admin.User owner) {
        return emoticonsByOwnerCache.get(owner);
    }
    private Set<scrum.server.admin.User> ownersCache;

    public final Set<scrum.server.admin.User> getOwners() {
        if (ownersCache == null) {
            ownersCache = new HashSet<scrum.server.admin.User>();
            for (Emoticon e : getEntities()) {
                if (e.isOwnerSet()) ownersCache.add(e.getOwner());
            }
        }
        return ownersCache;
    }

    private static class IsOwner implements Predicate<Emoticon> {

        private scrum.server.admin.User value;

        public IsOwner(scrum.server.admin.User value) {
            this.value = value;
        }

        public boolean test(Emoticon e) {
            return e.isOwner(value);
        }

    }

    // -----------------------------------------------------------
    // - emotion
    // -----------------------------------------------------------

    private final Cache<java.lang.String,Set<Emoticon>> emoticonsByEmotionCache = new Cache<java.lang.String,Set<Emoticon>>(
            new Cache.Factory<java.lang.String,Set<Emoticon>>() {
                public Set<Emoticon> create(java.lang.String emotion) {
                    return getEntities(new IsEmotion(emotion));
                }
            });

    public final Set<Emoticon> getEmoticonsByEmotion(java.lang.String emotion) {
        return emoticonsByEmotionCache.get(emotion);
    }
    private Set<java.lang.String> emotionsCache;

    public final Set<java.lang.String> getEmotions() {
        if (emotionsCache == null) {
            emotionsCache = new HashSet<java.lang.String>();
            for (Emoticon e : getEntities()) {
                if (e.isEmotionSet()) emotionsCache.add(e.getEmotion());
            }
        }
        return emotionsCache;
    }

    private static class IsEmotion implements Predicate<Emoticon> {

        private java.lang.String value;

        public IsEmotion(java.lang.String value) {
            this.value = value;
        }

        public boolean test(Emoticon e) {
            return e.isEmotion(value);
        }

    }

    // --- valueObject classes ---
    @Override
    protected Set<Class> getValueObjectClasses() {
        Set<Class> ret = new HashSet<Class>(super.getValueObjectClasses());
        return ret;
    }

    @Override
    public Map<String, Class> getAliases() {
        Map<String, Class> aliases = new HashMap<String, Class>(super.getAliases());
        return aliases;
    }

    // --- dependencies ---

}