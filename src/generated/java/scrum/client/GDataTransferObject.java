









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtDataTransferObjectGenerator










package scrum.client;

import java.util.*;

public abstract class GDataTransferObject
            extends scrum.client.ADataTransferObject {

    // --- BacklogItem ---

    private Map<String, Map> backlogItems;

    public final Collection<Map> getBacklogItems() {
        return backlogItems == null ? null : backlogItems.values();
    }

    public synchronized final void addBacklogItem(Map data) {
        if (backlogItems == null) backlogItems = new HashMap<String, Map>();
        String id = (String) data.get("id");
        backlogItems.put(id, data);
    }

    public final void addBacklogItems(List<Map> backlogItems) {
        for (Map entity : backlogItems) addBacklogItem (entity);
    }

    // --- Project ---

    private Map<String, Map> projects;

    public final Collection<Map> getProjects() {
        return projects == null ? null : projects.values();
    }

    public synchronized final void addProject(Map data) {
        if (projects == null) projects = new HashMap<String, Map>();
        String id = (String) data.get("id");
        projects.put(id, data);
    }

    public final void addProjects(List<Map> projects) {
        for (Map entity : projects) addProject (entity);
    }

    // --- Impediment ---

    private Map<String, Map> impediments;

    public final Collection<Map> getImpediments() {
        return impediments == null ? null : impediments.values();
    }

    public synchronized final void addImpediment(Map data) {
        if (impediments == null) impediments = new HashMap<String, Map>();
        String id = (String) data.get("id");
        impediments.put(id, data);
    }

    public final void addImpediments(List<Map> impediments) {
        for (Map entity : impediments) addImpediment (entity);
    }

    // --- Task ---

    private Map<String, Map> tasks;

    public final Collection<Map> getTasks() {
        return tasks == null ? null : tasks.values();
    }

    public synchronized final void addTask(Map data) {
        if (tasks == null) tasks = new HashMap<String, Map>();
        String id = (String) data.get("id");
        tasks.put(id, data);
    }

    public final void addTasks(List<Map> tasks) {
        for (Map entity : tasks) addTask (entity);
    }

    // --- Sprint ---

    private Map<String, Map> sprints;

    public final Collection<Map> getSprints() {
        return sprints == null ? null : sprints.values();
    }

    public synchronized final void addSprint(Map data) {
        if (sprints == null) sprints = new HashMap<String, Map>();
        String id = (String) data.get("id");
        sprints.put(id, data);
    }

    public final void addSprints(List<Map> sprints) {
        for (Map entity : sprints) addSprint (entity);
    }

}
