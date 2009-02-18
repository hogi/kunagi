package scrum.client;

import scrum.client.common.AGwtEntity;
import scrum.client.project.Project;

public class Dao extends GDao {

	@Override
	protected void onEntityUpdated(AGwtEntity entity) {
		if (ScrumGwtApplication.get().getWorkspace().isProjectOverview()) {
			if (entity instanceof Project) {
				ScrumGwtApplication.get().getWorkspace().getProjectOverview().update();
			}
		}
	}

	@Override
	protected void onEntityDeleted(AGwtEntity entity) {}

}
