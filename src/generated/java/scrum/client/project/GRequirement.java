// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityGenerator










package scrum.client.project;

import java.util.*;
import ilarkesto.persistence.*;
import ilarkesto.logging.*;
import ilarkesto.base.*;
import ilarkesto.base.time.*;
import ilarkesto.auth.*;
import scrum.client.common.*;
import ilarkesto.gwt.client.*;

public abstract class GRequirement
            extends ilarkesto.gwt.client.AGwtEntity {

    protected scrum.client.Dao getDao() {
        return scrum.client.Dao.get();
    }

    public GRequirement() {
    }

    public GRequirement(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "requirement";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- project ---

    private String projectId;

    public final scrum.client.project.Project getProject() {
        if (projectId == null) return null;
        return getDao().getProject(this.projectId);
    }

    public final Requirement setProject(scrum.client.project.Project project) {
        String id = project == null ? null : project.getId();
        if (equals(this.projectId, id)) return (Requirement) this;
        this.projectId = id;
        propertyChanged("projectId", this.projectId);
        return (Requirement)this;
    }

    public final boolean isProject(scrum.client.project.Project project) {
        return equals(this.projectId, project);
    }

    // --- sprint ---

    private String sprintId;

    public final scrum.client.sprint.Sprint getSprint() {
        if (sprintId == null) return null;
        return getDao().getSprint(this.sprintId);
    }

    public final Requirement setSprint(scrum.client.sprint.Sprint sprint) {
        String id = sprint == null ? null : sprint.getId();
        if (equals(this.sprintId, id)) return (Requirement) this;
        this.sprintId = id;
        propertyChanged("sprintId", this.sprintId);
        return (Requirement)this;
    }

    public final boolean isSprint(scrum.client.sprint.Sprint sprint) {
        return equals(this.sprintId, sprint);
    }

    // --- qualitys ---

    private Set<String> qualitysIds = new HashSet<String>();

    public final java.util.Set<scrum.client.project.Quality> getQualitys() {
        if ( qualitysIds.isEmpty()) return Collections.emptySet();
        return getDao().getQualitys(this.qualitysIds);
    }

    public final void addQuality(scrum.client.project.Quality quality) {
        String id = quality.getId();
        if (qualitysIds.contains(id)) return;
        qualitysIds.add(id);
        propertyChanged("qualitys", this.qualitysIds);
    }

    public final void removeQuality(scrum.client.project.Quality quality) {
        String id = quality.getId();
        if (!qualitysIds.contains(id)) return;
        qualitysIds.remove(id);
        propertyChanged("qualitys", this.qualitysIds);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Requirement setLabel(java.lang.String label) {
        this.label = label ;
        propertyChanged("label", this.label);
        return (Requirement)this;
    }

    public final boolean isLabel(java.lang.String label) {
        return equals(this.label, label);
    }

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final Requirement setDescription(java.lang.String description) {
        this.description = description ;
        propertyChanged("description", this.description);
        return (Requirement)this;
    }

    public final boolean isDescription(java.lang.String description) {
        return equals(this.description, description);
    }

    // --- testDescription ---

    private java.lang.String testDescription ;

    public final java.lang.String getTestDescription() {
        return this.testDescription ;
    }

    public final Requirement setTestDescription(java.lang.String testDescription) {
        this.testDescription = testDescription ;
        propertyChanged("testDescription", this.testDescription);
        return (Requirement)this;
    }

    public final boolean isTestDescription(java.lang.String testDescription) {
        return equals(this.testDescription, testDescription);
    }

    // --- estimatedWork ---

    private java.lang.Integer estimatedWork ;

    public final java.lang.Integer getEstimatedWork() {
        return this.estimatedWork ;
    }

    public final Requirement setEstimatedWork(java.lang.Integer estimatedWork) {
        this.estimatedWork = estimatedWork ;
        propertyChanged("estimatedWork", this.estimatedWork);
        return (Requirement)this;
    }

    public final boolean isEstimatedWork(java.lang.Integer estimatedWork) {
        return equals(this.estimatedWork, estimatedWork);
    }

    // --- closed ---

    private boolean closed ;

    public final boolean isClosed() {
        return this.closed ;
    }

    public final Requirement setClosed(boolean closed) {
        this.closed = closed ;
        propertyChanged("closed", this.closed);
        return (Requirement)this;
    }

    public final boolean isClosed(boolean closed) {
        return equals(this.closed, closed);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        projectId = (String) props.get("projectId");
        sprintId = (String) props.get("sprintId");
        qualitysIds = (Set<String>) props.get("qualitysIds");
        label  = (java.lang.String) props.get("label");
        description  = (java.lang.String) props.get("description");
        testDescription  = (java.lang.String) props.get("testDescription");
        estimatedWork  = (java.lang.Integer) props.get("estimatedWork");
        closed  = (Boolean) props.get("closed");
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("projectId", this.projectId);
        properties.put("sprintId", this.sprintId);
        properties.put("qualitys", this.qualitysIds);
        properties.put("label", this.label);
        properties.put("description", this.description);
        properties.put("testDescription", this.testDescription);
        properties.put("estimatedWork", this.estimatedWork);
        properties.put("closed", this.closed);
    }

}