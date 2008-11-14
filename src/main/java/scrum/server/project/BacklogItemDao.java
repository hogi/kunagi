package scrum.server.project;

public class BacklogItemDao extends GBacklogItemDao {

	@Override
	public BacklogItem newEntityInstance() {
		BacklogItem backlogItem = super.newEntityInstance();
		backlogItem.setLabel(scrum.client.project.BacklogItem.INIT_LABEL);
		return backlogItem;
	}

}
