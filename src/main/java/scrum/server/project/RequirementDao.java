package scrum.server.project;

import ilarkesto.fp.Predicate;

public class RequirementDao extends GRequirementDao {

	@Override
	public Requirement newEntityInstance() {
		Requirement requirement = super.newEntityInstance();
		requirement.setLabel("New Story");
		return requirement;
	}

	public Requirement getRequirementByNumber(final int number, final Project project) {
		return getEntity(new Predicate<Requirement>() {

			public boolean test(Requirement r) {
				return r.isNumber(number) && r.isProject(project);
			}
		});
	}

	// --- test data ---

	public void createTestRequirement(Project project, int variant) {
		Float estimatedWork = 5f;

		if (variant == 0) estimatedWork = null;

		Requirement requirement = newEntityInstance();
		requirement.setProject(project);
		requirement.setLabel("Story " + (('A') + variant - 1));
		requirement.setEstimatedWork(estimatedWork);
		saveEntity(requirement);

		if (variant > 0 && variant < 4) {
			requirement.setSprint(project.getCurrentSprint());
		}

		requirement.addTestTasks(variant);
	}
}
