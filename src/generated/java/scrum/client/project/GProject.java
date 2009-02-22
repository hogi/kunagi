









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtEntityGenerator










package scrum.client.project;

import java.util.*;
import ilarkesto.auth.*;
import ilarkesto.logging.*;
import ilarkesto.base.time.*;
import ilarkesto.base.*;
import ilarkesto.persistence.*;

public abstract class GProject
            extends scrum.client.common.AGwtEntity {

    public GProject() {
    }

    public GProject(Map data) {
        super(data);
        updateProperties(data);
    }

    public static final String ENTITY_TYPE = "project";

    @Override
    public final String getEntityType() {
        return ENTITY_TYPE;
    }

    // --- admins ---

    private Set<String> adminsIds = new HashSet<String>();

    public final java.util.Set<scrum.client.admin.User> getAdmins() {
        if ( adminsIds.isEmpty()) return Collections.emptySet();
        return getDao().getUsers(this.adminsIds);
    }

    public final void addAdmin(scrum.client.admin.User admin) {
        String id = admin.getId();
        if (adminsIds.contains(id)) return;
        adminsIds.add(id);
        propertyChanged("admins", this.adminsIds);
    }

    public final void removeAdmin(scrum.client.admin.User admin) {
        String id = admin.getId();
        if (!adminsIds.contains(id)) return;
        adminsIds.remove(id);
        propertyChanged("admins", this.adminsIds);
    }

    // --- teamMembers ---

    private Set<String> teamMembersIds = new HashSet<String>();

    public final java.util.Set<scrum.client.admin.User> getTeamMembers() {
        if ( teamMembersIds.isEmpty()) return Collections.emptySet();
        return getDao().getUsers(this.teamMembersIds);
    }

    public final void addTeamMember(scrum.client.admin.User teamMember) {
        String id = teamMember.getId();
        if (teamMembersIds.contains(id)) return;
        teamMembersIds.add(id);
        propertyChanged("teamMembers", this.teamMembersIds);
    }

    public final void removeTeamMember(scrum.client.admin.User teamMember) {
        String id = teamMember.getId();
        if (!teamMembersIds.contains(id)) return;
        teamMembersIds.remove(id);
        propertyChanged("teamMembers", this.teamMembersIds);
    }

    // --- nextSprint ---

    private String nextSprintId;

    public final scrum.client.sprint.Sprint getNextSprint() {
        if (nextSprintId == null) return null;
        return getDao().getSprint(this.nextSprintId);
    }

    public final Project setNextSprint(scrum.client.sprint.Sprint nextSprint) {
        String id = nextSprint == null ? null : nextSprint.getId();
        if (equals(this.nextSprintId, id)) return (Project) this;
        this.nextSprintId = id;
        propertyChanged("nextSprintId", this.nextSprintId);
        return (Project)this;
    }

    public final boolean isNextSprint(scrum.client.sprint.Sprint nextSprint) {
        return equals(this.nextSprintId, nextSprint);
    }

    // --- scrumMaster ---

    private String scrumMasterId;

    public final scrum.client.admin.User getScrumMaster() {
        if (scrumMasterId == null) return null;
        return getDao().getUser(this.scrumMasterId);
    }

    public final Project setScrumMaster(scrum.client.admin.User scrumMaster) {
        String id = scrumMaster == null ? null : scrumMaster.getId();
        if (equals(this.scrumMasterId, id)) return (Project) this;
        this.scrumMasterId = id;
        propertyChanged("scrumMasterId", this.scrumMasterId);
        return (Project)this;
    }

    public final boolean isScrumMaster(scrum.client.admin.User scrumMaster) {
        return equals(this.scrumMasterId, scrumMaster);
    }

    // --- label ---

    private java.lang.String label ;

    public final java.lang.String getLabel() {
        return this.label ;
    }

    public final Project setLabel(java.lang.String label) {
        this.label = label ;
        propertyChanged("label", this.label);
        return (Project)this;
    }

    public final boolean isLabel(java.lang.String label) {
        return equals(this.label, label);
    }

    // --- end ---

    private scrum.client.common.Date end ;

    public final scrum.client.common.Date getEnd() {
        return this.end ;
    }

    public final Project setEnd(scrum.client.common.Date end) {
        this.end = end ;
        propertyChanged("end", this.end);
        return (Project)this;
    }

    public final boolean isEnd(scrum.client.common.Date end) {
        return equals(this.end, end);
    }

    // --- currentSprint ---

    private String currentSprintId;

    public final scrum.client.sprint.Sprint getCurrentSprint() {
        if (currentSprintId == null) return null;
        return getDao().getSprint(this.currentSprintId);
    }

    public final Project setCurrentSprint(scrum.client.sprint.Sprint currentSprint) {
        String id = currentSprint == null ? null : currentSprint.getId();
        if (equals(this.currentSprintId, id)) return (Project) this;
        this.currentSprintId = id;
        propertyChanged("currentSprintId", this.currentSprintId);
        return (Project)this;
    }

    public final boolean isCurrentSprint(scrum.client.sprint.Sprint currentSprint) {
        return equals(this.currentSprintId, currentSprint);
    }

    // --- description ---

    private java.lang.String description ;

    public final java.lang.String getDescription() {
        return this.description ;
    }

    public final Project setDescription(java.lang.String description) {
        this.description = description ;
        propertyChanged("description", this.description);
        return (Project)this;
    }

    public final boolean isDescription(java.lang.String description) {
        return equals(this.description, description);
    }

    // --- productOwner ---

    private String productOwnerId;

    public final scrum.client.admin.User getProductOwner() {
        if (productOwnerId == null) return null;
        return getDao().getUser(this.productOwnerId);
    }

    public final Project setProductOwner(scrum.client.admin.User productOwner) {
        String id = productOwner == null ? null : productOwner.getId();
        if (equals(this.productOwnerId, id)) return (Project) this;
        this.productOwnerId = id;
        propertyChanged("productOwnerId", this.productOwnerId);
        return (Project)this;
    }

    public final boolean isProductOwner(scrum.client.admin.User productOwner) {
        return equals(this.productOwnerId, productOwner);
    }

    // --- begin ---

    private scrum.client.common.Date begin ;

    public final scrum.client.common.Date getBegin() {
        return this.begin ;
    }

    public final Project setBegin(scrum.client.common.Date begin) {
        this.begin = begin ;
        propertyChanged("begin", this.begin);
        return (Project)this;
    }

    public final boolean isBegin(scrum.client.common.Date begin) {
        return equals(this.begin, begin);
    }

    // --- update properties by map ---

    public void updateProperties(Map props) {
        adminsIds = (Set<String>) props.get("adminsIds");
        teamMembersIds = (Set<String>) props.get("teamMembersIds");
        nextSprintId = (String) props.get("nextSprintId");
        scrumMasterId = (String) props.get("scrumMasterId");
        label  = (java.lang.String) props.get("label");
        String endAsString = (String) props.get("end");
        end  =  endAsString == null ? null : new scrum.client.common.Date(endAsString);
        currentSprintId = (String) props.get("currentSprintId");
        description  = (java.lang.String) props.get("description");
        productOwnerId = (String) props.get("productOwnerId");
        String beginAsString = (String) props.get("begin");
        begin  =  beginAsString == null ? null : new scrum.client.common.Date(beginAsString);
    }

    @Override
    public void storeProperties(Map properties) {
        super.storeProperties(properties);
        properties.put("admins", this.adminsIds);
        properties.put("teamMembers", this.teamMembersIds);
        properties.put("nextSprintId", this.nextSprintId);
        properties.put("scrumMasterId", this.scrumMasterId);
        properties.put("label", this.label);
        properties.put("end", this.end == null ? null : this.end.toString());
        properties.put("currentSprintId", this.currentSprintId);
        properties.put("description", this.description);
        properties.put("productOwnerId", this.productOwnerId);
        properties.put("begin", this.begin == null ? null : this.begin.toString());
    }

}
