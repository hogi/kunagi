









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtDaoGenerator










package scrum.client;

import java.util.*;

public abstract class GDao
            extends scrum.client.common.AGwtDao {

    // --- BacklogItem ---

    private Map<String, scrum.client.project.BacklogItem> backlogItems = new HashMap<String, scrum.client.project.BacklogItem>();

    public final void updateBacklogItems(Collection<Map> dataList) {
        for (Map data : dataList) {
            String id = (String) data.get("id");
            scrum.client.project.BacklogItem entity = backlogItems.get(id);
            if (entity == null) {
                entity = new scrum.client.project.BacklogItem(data);
                backlogItems.put(id, entity);
                scrum.client.common.Logger.DEBUG("BacklogItem received: " + entity);
            } else {
                entity.updateProperties(data);
                scrum.client.common.Logger.DEBUG("BacklogItem updated: " + entity);
            }
        }
    }

    public final scrum.client.project.BacklogItem getBacklogItem(String id) {
        return backlogItems.get(id);
    }

    public final List<scrum.client.project.BacklogItem> getBacklogItemsByDescription(java.lang.String description) {
        List<scrum.client.project.BacklogItem> ret = new ArrayList<scrum.client.project.BacklogItem>();
        for (scrum.client.project.BacklogItem entity : backlogItems.values()) {
            if (entity.isDescription(description)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.BacklogItem> getBacklogItemsByDone(boolean done) {
        List<scrum.client.project.BacklogItem> ret = new ArrayList<scrum.client.project.BacklogItem>();
        for (scrum.client.project.BacklogItem entity : backlogItems.values()) {
            if (entity.isDone(done)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.BacklogItem> getBacklogItemsByTestDescription(java.lang.String testDescription) {
        List<scrum.client.project.BacklogItem> ret = new ArrayList<scrum.client.project.BacklogItem>();
        for (scrum.client.project.BacklogItem entity : backlogItems.values()) {
            if (entity.isTestDescription(testDescription)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.BacklogItem> getBacklogItemsByProject(scrum.client.project.Project project) {
        List<scrum.client.project.BacklogItem> ret = new ArrayList<scrum.client.project.BacklogItem>();
        for (scrum.client.project.BacklogItem entity : backlogItems.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.BacklogItem> getBacklogItemsByLabel(java.lang.String label) {
        List<scrum.client.project.BacklogItem> ret = new ArrayList<scrum.client.project.BacklogItem>();
        for (scrum.client.project.BacklogItem entity : backlogItems.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.BacklogItem> getBacklogItemsByEffort(java.lang.Integer effort) {
        List<scrum.client.project.BacklogItem> ret = new ArrayList<scrum.client.project.BacklogItem>();
        for (scrum.client.project.BacklogItem entity : backlogItems.values()) {
            if (entity.isEffort(effort)) ret.add(entity);
        }
        return ret;
    }

    // --- Project ---

    private Map<String, scrum.client.project.Project> projects = new HashMap<String, scrum.client.project.Project>();

    public final void updateProjects(Collection<Map> dataList) {
        for (Map data : dataList) {
            String id = (String) data.get("id");
            scrum.client.project.Project entity = projects.get(id);
            if (entity == null) {
                entity = new scrum.client.project.Project(data);
                projects.put(id, entity);
                scrum.client.common.Logger.DEBUG("Project received: " + entity);
            } else {
                entity.updateProperties(data);
                scrum.client.common.Logger.DEBUG("Project updated: " + entity);
            }
        }
    }

    public final scrum.client.project.Project getProject(String id) {
        return projects.get(id);
    }

    public final List<scrum.client.project.Project> getProjectsByLabel(java.lang.String label) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    // --- Impediment ---

    private Map<String, scrum.client.impediments.Impediment> impediments = new HashMap<String, scrum.client.impediments.Impediment>();

    public final void updateImpediments(Collection<Map> dataList) {
        for (Map data : dataList) {
            String id = (String) data.get("id");
            scrum.client.impediments.Impediment entity = impediments.get(id);
            if (entity == null) {
                entity = new scrum.client.impediments.Impediment(data);
                impediments.put(id, entity);
                scrum.client.common.Logger.DEBUG("Impediment received: " + entity);
            } else {
                entity.updateProperties(data);
                scrum.client.common.Logger.DEBUG("Impediment updated: " + entity);
            }
        }
    }

    public final scrum.client.impediments.Impediment getImpediment(String id) {
        return impediments.get(id);
    }

    public final List<scrum.client.impediments.Impediment> getImpedimentsByDescription(java.lang.String description) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isDescription(description)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.impediments.Impediment> getImpedimentsByLabel(java.lang.String label) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.impediments.Impediment> getImpedimentsByProject(scrum.client.project.Project project) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.impediments.Impediment> getImpedimentsBySolved(boolean solved) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isSolved(solved)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.impediments.Impediment> getImpedimentsBySolution(java.lang.String solution) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isSolution(solution)) ret.add(entity);
        }
        return ret;
    }

    // --- Task ---

    private Map<String, scrum.client.sprint.Task> tasks = new HashMap<String, scrum.client.sprint.Task>();

    public final void updateTasks(Collection<Map> dataList) {
        for (Map data : dataList) {
            String id = (String) data.get("id");
            scrum.client.sprint.Task entity = tasks.get(id);
            if (entity == null) {
                entity = new scrum.client.sprint.Task(data);
                tasks.put(id, entity);
                scrum.client.common.Logger.DEBUG("Task received: " + entity);
            } else {
                entity.updateProperties(data);
                scrum.client.common.Logger.DEBUG("Task updated: " + entity);
            }
        }
    }

    public final scrum.client.sprint.Task getTask(String id) {
        return tasks.get(id);
    }

    public final List<scrum.client.sprint.Task> getTasksByEffort(java.lang.Integer effort) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isEffort(effort)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Task> getTasksByLabel(java.lang.String label) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Task> getTasksByBacklogItem(scrum.client.project.BacklogItem backlogItem) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isBacklogItem(backlogItem)) ret.add(entity);
        }
        return ret;
    }

    // --- Sprint ---

    private Map<String, scrum.client.sprint.Sprint> sprints = new HashMap<String, scrum.client.sprint.Sprint>();

    public final void updateSprints(Collection<Map> dataList) {
        for (Map data : dataList) {
            String id = (String) data.get("id");
            scrum.client.sprint.Sprint entity = sprints.get(id);
            if (entity == null) {
                entity = new scrum.client.sprint.Sprint(data);
                sprints.put(id, entity);
                scrum.client.common.Logger.DEBUG("Sprint received: " + entity);
            } else {
                entity.updateProperties(data);
                scrum.client.common.Logger.DEBUG("Sprint updated: " + entity);
            }
        }
    }

    public final scrum.client.sprint.Sprint getSprint(String id) {
        return sprints.get(id);
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByProject(scrum.client.project.Project project) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByLabel(java.lang.String label) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    @Override
    public void handleDataFromServer(DataTransferObject data) {
        super.handleDataFromServer(data);

        Collection<Map> backlogItems = data.getBacklogItems();
        if (backlogItems != null) updateBacklogItems(backlogItems);

        Collection<Map> projects = data.getProjects();
        if (projects != null) updateProjects(projects);

        Collection<Map> impediments = data.getImpediments();
        if (impediments != null) updateImpediments(impediments);

        Collection<Map> tasks = data.getTasks();
        if (tasks != null) updateTasks(tasks);

        Collection<Map> sprints = data.getSprints();
        if (sprints != null) updateSprints(sprints);
    }

}
