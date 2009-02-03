









// ----------> GENERATED FILE - DON'T TOUCH! <----------

// generator: ilarkesto.mda.gen.GwtDaoGenerator










package scrum.client;

import java.util.*;
import scrum.client.common.*;

public abstract class GDao
            extends scrum.client.common.AGwtDao {

    // --- User ---

    private Map<String, scrum.client.admin.User> users = new HashMap<String, scrum.client.admin.User>();

    public final boolean containsUser(scrum.client.admin.User user) {
        return users.containsKey(user.getId());
    }

    public final void deleteUser(scrum.client.admin.User user) {
        users.remove(user.getId());
        entityDeleted(user);
    }

    public final void createUser(scrum.client.admin.User user) {
        users.put(user.getId(), user);
        entityCreated(user);
    }

    private final void updateUser(Map data) {
        String id = (String) data.get("id");
        scrum.client.admin.User entity = users.get(id);
        if (entity == null) {
            entity = new scrum.client.admin.User(data);
            users.put(id, entity);
            scrum.client.common.Logger.DEBUG("User received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            scrum.client.common.Logger.DEBUG("User updated: " + entity);
        }
    }

    public final scrum.client.admin.User getUser(String id) {
        scrum.client.admin.User ret = users.get(id);
        if (ret == null) throw new RuntimeException("User does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.admin.User> getUsers(Collection<String> ids) {
        Set<scrum.client.admin.User> ret = new HashSet<scrum.client.admin.User>();
        for (String id : ids) {
            scrum.client.admin.User entity = users.get(id);
            if (entity == null) throw new RuntimeException("User does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.admin.User> getUsers() {
        return new ArrayList<scrum.client.admin.User>(users.values());
    }

    public final List<scrum.client.admin.User> getUsersByName(java.lang.String name) {
        List<scrum.client.admin.User> ret = new ArrayList<scrum.client.admin.User>();
        for (scrum.client.admin.User entity : users.values()) {
            if (entity.isName(name)) ret.add(entity);
        }
        return ret;
    }

    // --- SprintDaySnapshot ---

    private Map<String, scrum.client.sprint.SprintDaySnapshot> sprintDaySnapshots = new HashMap<String, scrum.client.sprint.SprintDaySnapshot>();

    public final boolean containsSprintDaySnapshot(scrum.client.sprint.SprintDaySnapshot sprintDaySnapshot) {
        return sprintDaySnapshots.containsKey(sprintDaySnapshot.getId());
    }

    public final void deleteSprintDaySnapshot(scrum.client.sprint.SprintDaySnapshot sprintDaySnapshot) {
        sprintDaySnapshots.remove(sprintDaySnapshot.getId());
        entityDeleted(sprintDaySnapshot);
    }

    public final void createSprintDaySnapshot(scrum.client.sprint.SprintDaySnapshot sprintDaySnapshot) {
        sprintDaySnapshots.put(sprintDaySnapshot.getId(), sprintDaySnapshot);
        entityCreated(sprintDaySnapshot);
    }

    private final void updateSprintDaySnapshot(Map data) {
        String id = (String) data.get("id");
        scrum.client.sprint.SprintDaySnapshot entity = sprintDaySnapshots.get(id);
        if (entity == null) {
            entity = new scrum.client.sprint.SprintDaySnapshot(data);
            sprintDaySnapshots.put(id, entity);
            scrum.client.common.Logger.DEBUG("SprintDaySnapshot received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            scrum.client.common.Logger.DEBUG("SprintDaySnapshot updated: " + entity);
        }
    }

    public final scrum.client.sprint.SprintDaySnapshot getSprintDaySnapshot(String id) {
        scrum.client.sprint.SprintDaySnapshot ret = sprintDaySnapshots.get(id);
        if (ret == null) throw new RuntimeException("SprintDaySnapshot does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.sprint.SprintDaySnapshot> getSprintDaySnapshots(Collection<String> ids) {
        Set<scrum.client.sprint.SprintDaySnapshot> ret = new HashSet<scrum.client.sprint.SprintDaySnapshot>();
        for (String id : ids) {
            scrum.client.sprint.SprintDaySnapshot entity = sprintDaySnapshots.get(id);
            if (entity == null) throw new RuntimeException("SprintDaySnapshot does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.SprintDaySnapshot> getSprintDaySnapshots() {
        return new ArrayList<scrum.client.sprint.SprintDaySnapshot>(sprintDaySnapshots.values());
    }

    public final List<scrum.client.sprint.SprintDaySnapshot> getSprintDaySnapshotsBySprint(scrum.client.sprint.Sprint sprint) {
        List<scrum.client.sprint.SprintDaySnapshot> ret = new ArrayList<scrum.client.sprint.SprintDaySnapshot>();
        for (scrum.client.sprint.SprintDaySnapshot entity : sprintDaySnapshots.values()) {
            if (entity.isSprint(sprint)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.SprintDaySnapshot> getSprintDaySnapshotsByDate(scrum.client.common.Date date) {
        List<scrum.client.sprint.SprintDaySnapshot> ret = new ArrayList<scrum.client.sprint.SprintDaySnapshot>();
        for (scrum.client.sprint.SprintDaySnapshot entity : sprintDaySnapshots.values()) {
            if (entity.isDate(date)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.SprintDaySnapshot> getSprintDaySnapshotsByBurnedWork(int burnedWork) {
        List<scrum.client.sprint.SprintDaySnapshot> ret = new ArrayList<scrum.client.sprint.SprintDaySnapshot>();
        for (scrum.client.sprint.SprintDaySnapshot entity : sprintDaySnapshots.values()) {
            if (entity.isBurnedWork(burnedWork)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.SprintDaySnapshot> getSprintDaySnapshotsByRemainingWork(int remainingWork) {
        List<scrum.client.sprint.SprintDaySnapshot> ret = new ArrayList<scrum.client.sprint.SprintDaySnapshot>();
        for (scrum.client.sprint.SprintDaySnapshot entity : sprintDaySnapshots.values()) {
            if (entity.isRemainingWork(remainingWork)) ret.add(entity);
        }
        return ret;
    }

    // --- Impediment ---

    private Map<String, scrum.client.impediments.Impediment> impediments = new HashMap<String, scrum.client.impediments.Impediment>();

    public final boolean containsImpediment(scrum.client.impediments.Impediment impediment) {
        return impediments.containsKey(impediment.getId());
    }

    public final void deleteImpediment(scrum.client.impediments.Impediment impediment) {
        impediments.remove(impediment.getId());
        entityDeleted(impediment);
    }

    public final void createImpediment(scrum.client.impediments.Impediment impediment) {
        impediments.put(impediment.getId(), impediment);
        entityCreated(impediment);
    }

    private final void updateImpediment(Map data) {
        String id = (String) data.get("id");
        scrum.client.impediments.Impediment entity = impediments.get(id);
        if (entity == null) {
            entity = new scrum.client.impediments.Impediment(data);
            impediments.put(id, entity);
            scrum.client.common.Logger.DEBUG("Impediment received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            scrum.client.common.Logger.DEBUG("Impediment updated: " + entity);
        }
    }

    public final scrum.client.impediments.Impediment getImpediment(String id) {
        scrum.client.impediments.Impediment ret = impediments.get(id);
        if (ret == null) throw new RuntimeException("Impediment does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.impediments.Impediment> getImpediments(Collection<String> ids) {
        Set<scrum.client.impediments.Impediment> ret = new HashSet<scrum.client.impediments.Impediment>();
        for (String id : ids) {
            scrum.client.impediments.Impediment entity = impediments.get(id);
            if (entity == null) throw new RuntimeException("Impediment does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.impediments.Impediment> getImpediments() {
        return new ArrayList<scrum.client.impediments.Impediment>(impediments.values());
    }

    public final List<scrum.client.impediments.Impediment> getImpedimentsByProject(scrum.client.project.Project project) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isProject(project)) ret.add(entity);
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

    public final List<scrum.client.impediments.Impediment> getImpedimentsBySolved(boolean solved) {
        List<scrum.client.impediments.Impediment> ret = new ArrayList<scrum.client.impediments.Impediment>();
        for (scrum.client.impediments.Impediment entity : impediments.values()) {
            if (entity.isSolved(solved)) ret.add(entity);
        }
        return ret;
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

    // --- ProjectSprintSnapshot ---

    private Map<String, scrum.client.project.ProjectSprintSnapshot> projectSprintSnapshots = new HashMap<String, scrum.client.project.ProjectSprintSnapshot>();

    public final boolean containsProjectSprintSnapshot(scrum.client.project.ProjectSprintSnapshot projectSprintSnapshot) {
        return projectSprintSnapshots.containsKey(projectSprintSnapshot.getId());
    }

    public final void deleteProjectSprintSnapshot(scrum.client.project.ProjectSprintSnapshot projectSprintSnapshot) {
        projectSprintSnapshots.remove(projectSprintSnapshot.getId());
        entityDeleted(projectSprintSnapshot);
    }

    public final void createProjectSprintSnapshot(scrum.client.project.ProjectSprintSnapshot projectSprintSnapshot) {
        projectSprintSnapshots.put(projectSprintSnapshot.getId(), projectSprintSnapshot);
        entityCreated(projectSprintSnapshot);
    }

    private final void updateProjectSprintSnapshot(Map data) {
        String id = (String) data.get("id");
        scrum.client.project.ProjectSprintSnapshot entity = projectSprintSnapshots.get(id);
        if (entity == null) {
            entity = new scrum.client.project.ProjectSprintSnapshot(data);
            projectSprintSnapshots.put(id, entity);
            scrum.client.common.Logger.DEBUG("ProjectSprintSnapshot received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            scrum.client.common.Logger.DEBUG("ProjectSprintSnapshot updated: " + entity);
        }
    }

    public final scrum.client.project.ProjectSprintSnapshot getProjectSprintSnapshot(String id) {
        scrum.client.project.ProjectSprintSnapshot ret = projectSprintSnapshots.get(id);
        if (ret == null) throw new RuntimeException("ProjectSprintSnapshot does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.project.ProjectSprintSnapshot> getProjectSprintSnapshots(Collection<String> ids) {
        Set<scrum.client.project.ProjectSprintSnapshot> ret = new HashSet<scrum.client.project.ProjectSprintSnapshot>();
        for (String id : ids) {
            scrum.client.project.ProjectSprintSnapshot entity = projectSprintSnapshots.get(id);
            if (entity == null) throw new RuntimeException("ProjectSprintSnapshot does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.ProjectSprintSnapshot> getProjectSprintSnapshots() {
        return new ArrayList<scrum.client.project.ProjectSprintSnapshot>(projectSprintSnapshots.values());
    }

    public final List<scrum.client.project.ProjectSprintSnapshot> getProjectSprintSnapshotsByRemainingWork(int remainingWork) {
        List<scrum.client.project.ProjectSprintSnapshot> ret = new ArrayList<scrum.client.project.ProjectSprintSnapshot>();
        for (scrum.client.project.ProjectSprintSnapshot entity : projectSprintSnapshots.values()) {
            if (entity.isRemainingWork(remainingWork)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.ProjectSprintSnapshot> getProjectSprintSnapshotsByProject(scrum.client.project.Project project) {
        List<scrum.client.project.ProjectSprintSnapshot> ret = new ArrayList<scrum.client.project.ProjectSprintSnapshot>();
        for (scrum.client.project.ProjectSprintSnapshot entity : projectSprintSnapshots.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.ProjectSprintSnapshot> getProjectSprintSnapshotsByDate(scrum.client.common.Date date) {
        List<scrum.client.project.ProjectSprintSnapshot> ret = new ArrayList<scrum.client.project.ProjectSprintSnapshot>();
        for (scrum.client.project.ProjectSprintSnapshot entity : projectSprintSnapshots.values()) {
            if (entity.isDate(date)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.ProjectSprintSnapshot> getProjectSprintSnapshotsByBurnedWork(int burnedWork) {
        List<scrum.client.project.ProjectSprintSnapshot> ret = new ArrayList<scrum.client.project.ProjectSprintSnapshot>();
        for (scrum.client.project.ProjectSprintSnapshot entity : projectSprintSnapshots.values()) {
            if (entity.isBurnedWork(burnedWork)) ret.add(entity);
        }
        return ret;
    }

    // --- Task ---

    private Map<String, scrum.client.sprint.Task> tasks = new HashMap<String, scrum.client.sprint.Task>();

    public final boolean containsTask(scrum.client.sprint.Task task) {
        return tasks.containsKey(task.getId());
    }

    public final void deleteTask(scrum.client.sprint.Task task) {
        tasks.remove(task.getId());
        entityDeleted(task);
    }

    public final void createTask(scrum.client.sprint.Task task) {
        tasks.put(task.getId(), task);
        entityCreated(task);
    }

    private final void updateTask(Map data) {
        String id = (String) data.get("id");
        scrum.client.sprint.Task entity = tasks.get(id);
        if (entity == null) {
            entity = new scrum.client.sprint.Task(data);
            tasks.put(id, entity);
            scrum.client.common.Logger.DEBUG("Task received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            scrum.client.common.Logger.DEBUG("Task updated: " + entity);
        }
    }

    public final scrum.client.sprint.Task getTask(String id) {
        scrum.client.sprint.Task ret = tasks.get(id);
        if (ret == null) throw new RuntimeException("Task does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.sprint.Task> getTasks(Collection<String> ids) {
        Set<scrum.client.sprint.Task> ret = new HashSet<scrum.client.sprint.Task>();
        for (String id : ids) {
            scrum.client.sprint.Task entity = tasks.get(id);
            if (entity == null) throw new RuntimeException("Task does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Task> getTasks() {
        return new ArrayList<scrum.client.sprint.Task>(tasks.values());
    }

    public final List<scrum.client.sprint.Task> getTasksByRequirement(scrum.client.project.Requirement requirement) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isRequirement(requirement)) ret.add(entity);
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

    public final List<scrum.client.sprint.Task> getTasksByRemainingWork(int remainingWork) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isRemainingWork(remainingWork)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Task> getTasksByNotice(java.lang.String notice) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isNotice(notice)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Task> getTasksByBurnedWork(int burnedWork) {
        List<scrum.client.sprint.Task> ret = new ArrayList<scrum.client.sprint.Task>();
        for (scrum.client.sprint.Task entity : tasks.values()) {
            if (entity.isBurnedWork(burnedWork)) ret.add(entity);
        }
        return ret;
    }

    // --- Requirement ---

    private Map<String, scrum.client.project.Requirement> requirements = new HashMap<String, scrum.client.project.Requirement>();

    public final boolean containsRequirement(scrum.client.project.Requirement requirement) {
        return requirements.containsKey(requirement.getId());
    }

    public final void deleteRequirement(scrum.client.project.Requirement requirement) {
        requirements.remove(requirement.getId());
        entityDeleted(requirement);
    }

    public final void createRequirement(scrum.client.project.Requirement requirement) {
        requirements.put(requirement.getId(), requirement);
        entityCreated(requirement);
    }

    private final void updateRequirement(Map data) {
        String id = (String) data.get("id");
        scrum.client.project.Requirement entity = requirements.get(id);
        if (entity == null) {
            entity = new scrum.client.project.Requirement(data);
            requirements.put(id, entity);
            scrum.client.common.Logger.DEBUG("Requirement received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            scrum.client.common.Logger.DEBUG("Requirement updated: " + entity);
        }
    }

    public final scrum.client.project.Requirement getRequirement(String id) {
        scrum.client.project.Requirement ret = requirements.get(id);
        if (ret == null) throw new RuntimeException("Requirement does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.project.Requirement> getRequirements(Collection<String> ids) {
        Set<scrum.client.project.Requirement> ret = new HashSet<scrum.client.project.Requirement>();
        for (String id : ids) {
            scrum.client.project.Requirement entity = requirements.get(id);
            if (entity == null) throw new RuntimeException("Requirement does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirements() {
        return new ArrayList<scrum.client.project.Requirement>(requirements.values());
    }

    public final List<scrum.client.project.Requirement> getRequirementsByDescription(java.lang.String description) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isDescription(description)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsByLabel(java.lang.String label) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsBySprint(scrum.client.sprint.Sprint sprint) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isSprint(sprint)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsByTestDescription(java.lang.String testDescription) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isTestDescription(testDescription)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsByClosed(boolean closed) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isClosed(closed)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsByEstimatedWork(java.lang.Integer estimatedWork) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isEstimatedWork(estimatedWork)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Requirement> getRequirementsByProject(scrum.client.project.Project project) {
        List<scrum.client.project.Requirement> ret = new ArrayList<scrum.client.project.Requirement>();
        for (scrum.client.project.Requirement entity : requirements.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    // --- Project ---

    private Map<String, scrum.client.project.Project> projects = new HashMap<String, scrum.client.project.Project>();

    public final boolean containsProject(scrum.client.project.Project project) {
        return projects.containsKey(project.getId());
    }

    public final void deleteProject(scrum.client.project.Project project) {
        projects.remove(project.getId());
        entityDeleted(project);
    }

    public final void createProject(scrum.client.project.Project project) {
        projects.put(project.getId(), project);
        entityCreated(project);
    }

    private final void updateProject(Map data) {
        String id = (String) data.get("id");
        scrum.client.project.Project entity = projects.get(id);
        if (entity == null) {
            entity = new scrum.client.project.Project(data);
            projects.put(id, entity);
            scrum.client.common.Logger.DEBUG("Project received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            scrum.client.common.Logger.DEBUG("Project updated: " + entity);
        }
    }

    public final scrum.client.project.Project getProject(String id) {
        scrum.client.project.Project ret = projects.get(id);
        if (ret == null) throw new RuntimeException("Project does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.project.Project> getProjects(Collection<String> ids) {
        Set<scrum.client.project.Project> ret = new HashSet<scrum.client.project.Project>();
        for (String id : ids) {
            scrum.client.project.Project entity = projects.get(id);
            if (entity == null) throw new RuntimeException("Project does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjects() {
        return new ArrayList<scrum.client.project.Project>(projects.values());
    }

    public final List<scrum.client.project.Project> getProjectsByCurrentSprint(scrum.client.sprint.Sprint currentSprint) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isCurrentSprint(currentSprint)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByProductOwner(scrum.client.admin.User productOwner) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isProductOwner(productOwner)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByLabel(java.lang.String label) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }


    public final List<scrum.client.project.Project> getProjectsByScrumMaster(scrum.client.admin.User scrumMaster) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isScrumMaster(scrumMaster)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.project.Project> getProjectsByDescription(java.lang.String description) {
        List<scrum.client.project.Project> ret = new ArrayList<scrum.client.project.Project>();
        for (scrum.client.project.Project entity : projects.values()) {
            if (entity.isDescription(description)) ret.add(entity);
        }
        return ret;
    }


    // --- Sprint ---

    private Map<String, scrum.client.sprint.Sprint> sprints = new HashMap<String, scrum.client.sprint.Sprint>();

    public final boolean containsSprint(scrum.client.sprint.Sprint sprint) {
        return sprints.containsKey(sprint.getId());
    }

    public final void deleteSprint(scrum.client.sprint.Sprint sprint) {
        sprints.remove(sprint.getId());
        entityDeleted(sprint);
    }

    public final void createSprint(scrum.client.sprint.Sprint sprint) {
        sprints.put(sprint.getId(), sprint);
        entityCreated(sprint);
    }

    private final void updateSprint(Map data) {
        String id = (String) data.get("id");
        scrum.client.sprint.Sprint entity = sprints.get(id);
        if (entity == null) {
            entity = new scrum.client.sprint.Sprint(data);
            sprints.put(id, entity);
            scrum.client.common.Logger.DEBUG("Sprint received: " + entity.getId() + " ("+entity+")");
        } else {
            entity.updateProperties(data);
            scrum.client.common.Logger.DEBUG("Sprint updated: " + entity);
        }
    }

    public final scrum.client.sprint.Sprint getSprint(String id) {
        scrum.client.sprint.Sprint ret = sprints.get(id);
        if (ret == null) throw new RuntimeException("Sprint does not exist: " + id);
        return ret;
    }

    public final Set<scrum.client.sprint.Sprint> getSprints(Collection<String> ids) {
        Set<scrum.client.sprint.Sprint> ret = new HashSet<scrum.client.sprint.Sprint>();
        for (String id : ids) {
            scrum.client.sprint.Sprint entity = sprints.get(id);
            if (entity == null) throw new RuntimeException("Sprint does not exist: " + id);
            ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprints() {
        return new ArrayList<scrum.client.sprint.Sprint>(sprints.values());
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByLabel(java.lang.String label) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isLabel(label)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByEnd(scrum.client.common.Date end) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isEnd(end)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByProject(scrum.client.project.Project project) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isProject(project)) ret.add(entity);
        }
        return ret;
    }

    public final List<scrum.client.sprint.Sprint> getSprintsByBegin(scrum.client.common.Date begin) {
        List<scrum.client.sprint.Sprint> ret = new ArrayList<scrum.client.sprint.Sprint>();
        for (scrum.client.sprint.Sprint entity : sprints.values()) {
            if (entity.isBegin(begin)) ret.add(entity);
        }
        return ret;
    }

    private Collection<Map<String, ? extends AGwtEntity>> entityMaps;

    @Override
    protected final Collection<Map<String, ? extends AGwtEntity>> getEntityMaps() {
        if (entityMaps == null) {
            entityMaps = new ArrayList<Map<String, ? extends AGwtEntity>>();
            entityMaps.add(users);
            entityMaps.add(sprintDaySnapshots);
            entityMaps.add(impediments);
            entityMaps.add(projectSprintSnapshots);
            entityMaps.add(tasks);
            entityMaps.add(requirements);
            entityMaps.add(projects);
            entityMaps.add(sprints);
        }
        return entityMaps;
    }

    @Override
    protected final void updateEntity(String type, Map data) {
        if (type.equals(scrum.client.admin.User.ENTITY_TYPE)) {
            updateUser(data);
            return;
        }
        if (type.equals(scrum.client.sprint.SprintDaySnapshot.ENTITY_TYPE)) {
            updateSprintDaySnapshot(data);
            return;
        }
        if (type.equals(scrum.client.impediments.Impediment.ENTITY_TYPE)) {
            updateImpediment(data);
            return;
        }
        if (type.equals(scrum.client.project.ProjectSprintSnapshot.ENTITY_TYPE)) {
            updateProjectSprintSnapshot(data);
            return;
        }
        if (type.equals(scrum.client.sprint.Task.ENTITY_TYPE)) {
            updateTask(data);
            return;
        }
        if (type.equals(scrum.client.project.Requirement.ENTITY_TYPE)) {
            updateRequirement(data);
            return;
        }
        if (type.equals(scrum.client.project.Project.ENTITY_TYPE)) {
            updateProject(data);
            return;
        }
        if (type.equals(scrum.client.sprint.Sprint.ENTITY_TYPE)) {
            updateSprint(data);
            return;
        }
       throw new RuntimeException("Unsupported type: " + type);
    }

}
