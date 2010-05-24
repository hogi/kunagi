// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.legacy.generator.EntityGenerator










package scrum.server.release;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.core.logging.Log;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;

public abstract class GRelease
            extends AEntity
            implements ilarkesto.auth.ViewProtected<scrum.server.admin.User>, ilarkesto.search.Searchable, java.lang.Comparable<Release> {

    // --- AEntity ---

    public final ReleaseDao getDao() {
        return releaseDao;
    }

    protected void repairDeadDatob(ADatob datob) {
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("parentReleaseId", this.parentReleaseId);
        properties.put("sprintsIds", this.sprintsIds);
        properties.put("number", this.number);
        properties.put("label", this.label);
        properties.put("note", this.note);
        properties.put("releaseDate", this.releaseDate == null ? null : this.releaseDate.toString());
        properties.put("released", this.released);
        properties.put("releaseNotes", this.releaseNotes);
    }

    public int compareTo(Release other) {
        return toString().toLowerCase().compareTo(other.toString().toLowerCase());
    }

    private static final ilarkesto.core.logging.Log LOG = ilarkesto.core.logging.Log.get(GRelease.class);

    public static final String TYPE = "release";


    // -----------------------------------------------------------
    // - Searchable
    // -----------------------------------------------------------

    public boolean matchesKey(String key) {
        if (super.matchesKey(key)) return true;
        if (matchesKey(getLabel(), key)) return true;
        if (matchesKey(getNote(), key)) return true;
        if (matchesKey(getReleaseNotes(), key)) return true;
        return false;
    }

    // -----------------------------------------------------------
    // - project
    // -----------------------------------------------------------

    private String projectId;
    private transient scrum.server.project.Project projectCache;

    private void updateProjectCache() {
        projectCache = this.projectId == null ? null : (scrum.server.project.Project)projectDao.getById(this.projectId);
    }

    public final String getProjectId() {
        return this.projectId;
    }

    public final scrum.server.project.Project getProject() {
        if (projectCache == null) updateProjectCache();
        return projectCache;
    }

    public final void setProject(scrum.server.project.Project project) {
        project = prepareProject(project);
        if (isProject(project)) return;
        this.projectId = project == null ? null : project.getId();
        projectCache = project;
        fireModified();
    }

    protected scrum.server.project.Project prepareProject(scrum.server.project.Project project) {
        return project;
    }

    protected void repairDeadProjectReference(String entityId) {
        if (entityId.equals(this.projectId)) {
            repairMissingMaster();
        }
    }

    public final boolean isProjectSet() {
        return this.projectId != null;
    }

    public final boolean isProject(scrum.server.project.Project project) {
        if (this.projectId == null && project == null) return true;
        return project != null && project.getId().equals(this.projectId);
    }

    protected final void updateProject(Object value) {
        setProject(value == null ? null : (scrum.server.project.Project)projectDao.getById((String)value));
    }

    // -----------------------------------------------------------
    // - parentRelease
    // -----------------------------------------------------------

    private String parentReleaseId;
    private transient scrum.server.release.Release parentReleaseCache;

    private void updateParentReleaseCache() {
        parentReleaseCache = this.parentReleaseId == null ? null : (scrum.server.release.Release)releaseDao.getById(this.parentReleaseId);
    }

    public final String getParentReleaseId() {
        return this.parentReleaseId;
    }

    public final scrum.server.release.Release getParentRelease() {
        if (parentReleaseCache == null) updateParentReleaseCache();
        return parentReleaseCache;
    }

    public final void setParentRelease(scrum.server.release.Release parentRelease) {
        parentRelease = prepareParentRelease(parentRelease);
        if (isParentRelease(parentRelease)) return;
        this.parentReleaseId = parentRelease == null ? null : parentRelease.getId();
        parentReleaseCache = parentRelease;
        fireModified();
    }

    protected scrum.server.release.Release prepareParentRelease(scrum.server.release.Release parentRelease) {
        return parentRelease;
    }

    protected void repairDeadParentReleaseReference(String entityId) {
        if (entityId.equals(this.parentReleaseId)) {
            this.parentReleaseId = null;
            fireModified();
        }
    }

    public final boolean isParentReleaseSet() {
        return this.parentReleaseId != null;
    }

    public final boolean isParentRelease(scrum.server.release.Release parentRelease) {
        if (this.parentReleaseId == null && parentRelease == null) return true;
        return parentRelease != null && parentRelease.getId().equals(this.parentReleaseId);
    }

    protected final void updateParentRelease(Object value) {
        setParentRelease(value == null ? null : (scrum.server.release.Release)releaseDao.getById((String)value));
    }

    // -----------------------------------------------------------
    // - sprints
    // -----------------------------------------------------------

    private java.util.Set<String> sprintsIds = new java.util.HashSet<String>();

    public final java.util.Set<scrum.server.sprint.Sprint> getSprints() {
        return (java.util.Set) sprintDao.getByIdsAsSet(this.sprintsIds);
    }

    public final void setSprints(Collection<scrum.server.sprint.Sprint> sprints) {
        sprints = prepareSprints(sprints);
        if (sprints == null) sprints = Collections.emptyList();
        java.util.Set<String> ids = getIdsAsSet(sprints);
        if (this.sprintsIds.equals(ids)) return;
        this.sprintsIds = ids;
        fireModified();
    }

    protected Collection<scrum.server.sprint.Sprint> prepareSprints(Collection<scrum.server.sprint.Sprint> sprints) {
        return sprints;
    }

    protected void repairDeadSprintReference(String entityId) {
        if (this.sprintsIds.remove(entityId)) fireModified();
    }

    public final boolean containsSprint(scrum.server.sprint.Sprint sprint) {
        if (sprint == null) return false;
        return this.sprintsIds.contains(sprint.getId());
    }

    public final int getSprintsCount() {
        return this.sprintsIds.size();
    }

    public final boolean isSprintsEmpty() {
        return this.sprintsIds.isEmpty();
    }

    public final boolean addSprint(scrum.server.sprint.Sprint sprint) {
        if (sprint == null) throw new IllegalArgumentException("sprint == null");
        boolean added = this.sprintsIds.add(sprint.getId());
        if (added) fireModified();
        return added;
    }

    public final boolean addSprints(Collection<scrum.server.sprint.Sprint> sprints) {
        if (sprints == null) throw new IllegalArgumentException("sprints == null");
        boolean added = false;
        for (scrum.server.sprint.Sprint sprint : sprints) {
            added = added | this.sprintsIds.add(sprint.getId());
        }
        if (added) fireModified();
        return added;
    }

    public final boolean removeSprint(scrum.server.sprint.Sprint sprint) {
        if (sprint == null) throw new IllegalArgumentException("sprint == null");
        if (this.sprintsIds == null) return false;
        boolean removed = this.sprintsIds.remove(sprint.getId());
        if (removed) fireModified();
        return removed;
    }

    public final boolean removeSprints(Collection<scrum.server.sprint.Sprint> sprints) {
        if (sprints == null) return false;
        if (sprints.isEmpty()) return false;
        boolean removed = false;
        for (scrum.server.sprint.Sprint _element: sprints) {
            removed = removed | removeSprint(_element);
        }
        return removed;
    }

    public final boolean clearSprints() {
        if (this.sprintsIds.isEmpty()) return false;
        this.sprintsIds.clear();
        fireModified();
        return true;
    }

    protected final void updateSprints(Object value) {
        Collection<String> ids = (Collection<String>) value;
        setSprints((java.util.Set) sprintDao.getByIdsAsSet(ids));
    }

    // -----------------------------------------------------------
    // - number
    // -----------------------------------------------------------

    private int number;

    public final int getNumber() {
        return number;
    }

    public final void setNumber(int number) {
        number = prepareNumber(number);
        if (isNumber(number)) return;
        this.number = number;
        fireModified();
    }

    protected int prepareNumber(int number) {
        return number;
    }

    public final boolean isNumber(int number) {
        return this.number == number;
    }

    protected final void updateNumber(Object value) {
        setNumber((Integer)value);
    }

    // -----------------------------------------------------------
    // - label
    // -----------------------------------------------------------

    private java.lang.String label;

    public final java.lang.String getLabel() {
        return label;
    }

    public final void setLabel(java.lang.String label) {
        label = prepareLabel(label);
        if (isLabel(label)) return;
        this.label = label;
        fireModified();
    }

    protected java.lang.String prepareLabel(java.lang.String label) {
        label = Str.removeUnreadableChars(label);
        return label;
    }

    public final boolean isLabelSet() {
        return this.label != null;
    }

    public final boolean isLabel(java.lang.String label) {
        if (this.label == null && label == null) return true;
        return this.label != null && this.label.equals(label);
    }

    protected final void updateLabel(Object value) {
        setLabel((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - note
    // -----------------------------------------------------------

    private java.lang.String note;

    public final java.lang.String getNote() {
        return note;
    }

    public final void setNote(java.lang.String note) {
        note = prepareNote(note);
        if (isNote(note)) return;
        this.note = note;
        fireModified();
    }

    protected java.lang.String prepareNote(java.lang.String note) {
        note = Str.removeUnreadableChars(note);
        return note;
    }

    public final boolean isNoteSet() {
        return this.note != null;
    }

    public final boolean isNote(java.lang.String note) {
        if (this.note == null && note == null) return true;
        return this.note != null && this.note.equals(note);
    }

    protected final void updateNote(Object value) {
        setNote((java.lang.String)value);
    }

    // -----------------------------------------------------------
    // - releaseDate
    // -----------------------------------------------------------

    private ilarkesto.base.time.Date releaseDate;

    public final ilarkesto.base.time.Date getReleaseDate() {
        return releaseDate;
    }

    public final void setReleaseDate(ilarkesto.base.time.Date releaseDate) {
        releaseDate = prepareReleaseDate(releaseDate);
        if (isReleaseDate(releaseDate)) return;
        this.releaseDate = releaseDate;
        fireModified();
    }

    protected ilarkesto.base.time.Date prepareReleaseDate(ilarkesto.base.time.Date releaseDate) {
        return releaseDate;
    }

    public final boolean isReleaseDateSet() {
        return this.releaseDate != null;
    }

    public final boolean isReleaseDate(ilarkesto.base.time.Date releaseDate) {
        if (this.releaseDate == null && releaseDate == null) return true;
        return this.releaseDate != null && this.releaseDate.equals(releaseDate);
    }

    protected final void updateReleaseDate(Object value) {
        value = value == null ? null : new ilarkesto.base.time.Date((String)value);
        setReleaseDate((ilarkesto.base.time.Date)value);
    }

    // -----------------------------------------------------------
    // - released
    // -----------------------------------------------------------

    private boolean released;

    public final boolean isReleased() {
        return released;
    }

    public final void setReleased(boolean released) {
        released = prepareReleased(released);
        if (isReleased(released)) return;
        this.released = released;
        fireModified();
    }

    protected boolean prepareReleased(boolean released) {
        return released;
    }

    public final boolean isReleased(boolean released) {
        return this.released == released;
    }

    protected final void updateReleased(Object value) {
        setReleased((Boolean)value);
    }

    // -----------------------------------------------------------
    // - releaseNotes
    // -----------------------------------------------------------

    private java.lang.String releaseNotes;

    public final java.lang.String getReleaseNotes() {
        return releaseNotes;
    }

    public final void setReleaseNotes(java.lang.String releaseNotes) {
        releaseNotes = prepareReleaseNotes(releaseNotes);
        if (isReleaseNotes(releaseNotes)) return;
        this.releaseNotes = releaseNotes;
        fireModified();
    }

    protected java.lang.String prepareReleaseNotes(java.lang.String releaseNotes) {
        releaseNotes = Str.removeUnreadableChars(releaseNotes);
        return releaseNotes;
    }

    public final boolean isReleaseNotesSet() {
        return this.releaseNotes != null;
    }

    public final boolean isReleaseNotes(java.lang.String releaseNotes) {
        if (this.releaseNotes == null && releaseNotes == null) return true;
        return this.releaseNotes != null && this.releaseNotes.equals(releaseNotes);
    }

    protected final void updateReleaseNotes(Object value) {
        setReleaseNotes((java.lang.String)value);
    }

    public void updateProperties(Map<?, ?> properties) {
        for (Map.Entry entry : properties.entrySet()) {
            String property = (String) entry.getKey();
            if (property.equals("id")) continue;
            Object value = entry.getValue();
            if (property.equals("projectId")) updateProject(value);
            if (property.equals("parentReleaseId")) updateParentRelease(value);
            if (property.equals("sprintsIds")) updateSprints(value);
            if (property.equals("number")) updateNumber(value);
            if (property.equals("label")) updateLabel(value);
            if (property.equals("note")) updateNote(value);
            if (property.equals("releaseDate")) updateReleaseDate(value);
            if (property.equals("released")) updateReleased(value);
            if (property.equals("releaseNotes")) updateReleaseNotes(value);
        }
    }

    protected void repairDeadReferences(String entityId) {
        super.repairDeadReferences(entityId);
        repairDeadProjectReference(entityId);
        repairDeadParentReleaseReference(entityId);
        if (this.sprintsIds == null) this.sprintsIds = new java.util.HashSet<String>();
        repairDeadSprintReference(entityId);
    }

    // --- ensure integrity ---

    public void ensureIntegrity() {
        super.ensureIntegrity();
        if (!isProjectSet()) {
            repairMissingMaster();
            return;
        }
        try {
            getProject();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead project reference");
            repairDeadProjectReference(this.projectId);
        }
        try {
            getParentRelease();
        } catch (EntityDoesNotExistException ex) {
            LOG.info("Repairing dead parentRelease reference");
            repairDeadParentReleaseReference(this.parentReleaseId);
        }
        if (this.sprintsIds == null) this.sprintsIds = new java.util.HashSet<String>();
        Set<String> sprints = new HashSet<String>(this.sprintsIds);
        for (String entityId : sprints) {
            try {
                sprintDao.getById(entityId);
            } catch (EntityDoesNotExistException ex) {
                LOG.info("Repairing dead sprint reference");
                repairDeadSprintReference(entityId);
            }
        }
    }


    // -----------------------------------------------------------
    // - dependencies
    // -----------------------------------------------------------

    static scrum.server.project.ProjectDao projectDao;

    public static final void setProjectDao(scrum.server.project.ProjectDao projectDao) {
        GRelease.projectDao = projectDao;
    }

    static scrum.server.sprint.SprintDao sprintDao;

    public static final void setSprintDao(scrum.server.sprint.SprintDao sprintDao) {
        GRelease.sprintDao = sprintDao;
    }

    static ReleaseDao releaseDao;

    public static final void setReleaseDao(ReleaseDao releaseDao) {
        GRelease.releaseDao = releaseDao;
    }

}