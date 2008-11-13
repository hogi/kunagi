package scrum.server.project;

public class BacklogItemDao extends GBacklogItemDao {

	@Override
	public BacklogItem newEntityInstance() {
		BacklogItem backlogItem = super.newEntityInstance();
		backlogItem.setLabel(scrum.client.project.BacklogItem.INIT_LABEL);
		return backlogItem;
	}

	public BacklogItem postBacklogItem(Project project) {
		BacklogItem backlogItem = newEntityInstance();
		backlogItem.setProject(project);
		saveEntity(backlogItem);
		return backlogItem;
	}

}
