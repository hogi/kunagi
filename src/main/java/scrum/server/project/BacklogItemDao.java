package scrum.server.project;

public class BacklogItemDao extends GBacklogItemDao {

	public BacklogItem postBacklogItem(Project project) {
		BacklogItem backlogItem = newEntityInstance();
		backlogItem.setProject(project);
		backlogItem.setLabel(scrum.client.project.BacklogItem.INIT_LABEL);
		saveEntity(backlogItem);
		return backlogItem;
	}

}
