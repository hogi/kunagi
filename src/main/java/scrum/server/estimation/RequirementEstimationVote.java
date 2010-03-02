package scrum.server.estimation;

import scrum.server.admin.User;
import scrum.server.project.Project;

public class RequirementEstimationVote extends GRequirementEstimationVote {

	public Project getProject() {
		return getRequirement().getProject();
	}

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}
}