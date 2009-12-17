package scrum.client.estimation;

import java.util.Map;

import scrum.client.admin.User;
import scrum.client.project.Requirement;

public class RequirementEstimationVote extends GRequirementEstimationVote {

	public RequirementEstimationVote(Requirement requirement, User user) {
		setRequirement(requirement);
		setUser(user);
	}

	public RequirementEstimationVote(Map data) {
		super(data);
	}

}