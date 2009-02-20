package scrum.client;

import scrum.client.common.AGwtEntity;

public class Dao extends GDao {

	@Override
	protected void onEntityUpdated(AGwtEntity entity) {
	// if (ScrumGwtApplication.get().getWorkspaceWidget().isProjectOverview()) {
	// if (entity instanceof Project) {
	// ScrumGwtApplication.get().getWorkspaceWidget().getProjectOverview().update();
	// }
	// }
	}

	@Override
	protected void onEntityDeleted(AGwtEntity entity) {}

}
