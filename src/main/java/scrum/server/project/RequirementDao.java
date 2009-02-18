package scrum.server.project;

public class RequirementDao extends GRequirementDao {

	@Override
	public Requirement newEntityInstance() {
		Requirement requirement = super.newEntityInstance();
		requirement.setLabel(scrum.client.project.Requirement.INIT_LABEL);
		return requirement;
	}

	// --- test data ---

	public void createTestRequirement(Project project, int variant) {
		Integer estimatedWork = 5;

		if (variant == 0) estimatedWork = null;

		Requirement requirement = newEntityInstance();
		requirement.setProject(project);
		requirement.setLabel("Requirement " + variant);
		requirement.setEstimatedWork(estimatedWork);
		saveEntity(requirement);

		if (variant > 0 && variant < 4) {
			requirement.setSprint(project.getCurrentSprint());
		}

		requirement.addTestTasks(variant);
	}

}
