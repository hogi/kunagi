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

	public boolean createTestRequirement(Project project, int variant) {

		Requirement requirement = newEntityInstance();
		requirement.setProject(project);

		if (variant == 1) {
			requirement.setLabel("Unsurpassed Concept");
			requirement
					.setDescription("As a Product Owner I want the concept to be unsurpassable so I don't have to worry about ROI anymore.");
			requirement.setEstimatedWork(3f);
		} else if (variant == 2) {
			requirement.setLabel("Amazing Product Backlog");
			requirement
					.setDescription("As a Product Owner I want my Backlog to be amazing so that people stand in awe.");
			requirement.setEstimatedWork(2f);
		} else if (variant == 3) {
			requirement.setLabel("Functional Quality Backlog");
			requirement
					.setDescription("As a Product Owner I want my non-functional Requirements to be functional, so I can use them.");
			requirement.setEstimatedWork(1f);
		} else if (variant == 4) {
			requirement.setLabel("Groundbraking Scrum Backlog");
			requirement
					.setDescription("As a Team member I want the Scrum Backlog to be groundbreaking, so that everybody can see that I am the most important here.");
			requirement.setEstimatedWork(1f);
		} else if (variant == 5) {
			requirement.setLabel("Breathtaking Whiteboard");
			requirement
					.setDescription("As a Team member I want the current state of things to be displayed on a Whiteboard, so I can play around when I am bored.");
			requirement.setEstimatedWork(8f);
		} else if (variant == 6) {
			requirement.setLabel("Empowering Impediment List");
			requirement
					.setDescription("As a Scrum Master I want the Impedimen List to be empowering. Best thing would be self-resolving Impediments.");
			requirement.setEstimatedWork(2f);
		} else if (variant == 7) {
			requirement.setLabel("Divine Risk Management");
			requirement
					.setDescription("As a Team member, I want Risk Management to be devine. Wait, that makes Risk Management superfluous, I guess.");
			requirement.setEstimatedWork(5f);
		} else if (variant == 8) {
			requirement.setLabel("Miraculous Issue Management");
			requirement
					.setDescription("As a User I want Issue Management to be miraculous, so that everybody can have what he wants.");
			requirement.setEstimatedWork(3f);
		} else if (variant == 9) {
			requirement.setLabel("Immaculate Bug Tracking");
			requirement
					.setDescription("As a Team member I want immaculate Bug Tracking. A Bug Tracking containing no bugs would be suitable.");
			requirement.setEstimatedWork(2f);
		} else if (variant == 10) {
			requirement.setLabel("Unbeatable Planning Poker");
			requirement
					.setDescription("As a Team member I want Planning Poker to be really hard, so I can practice my micro skillz.");
			requirement.setEstimatedWork(13f);
		} else if (variant == 11) {
			requirement.setLabel("Enlightening Wiki");
			requirement
					.setDescription("As a User I want the Wiki to enlighten me so that I am enlightened after reading (makes sense, doesn't it?).");
			requirement.setEstimatedWork(5f);
		} else if (variant == 12) {
			requirement.setLabel("Absorbing Discussion Board");
			requirement
					.setDescription("As a User I want the Discussion Board to be absorbing, so that I never have time to do my work.");
			requirement.setEstimatedWork(5f);
		} else if (variant == 13) {
			requirement.setLabel("Irresistable User Interface");
			requirement
					.setDescription("As a User I want the User Interface to be irresistable so that I can experience Orgasmic Joy-of-Use.");
			requirement.setEstimatedWork(20f);
		} else if (variant == 14) {
			requirement.setLabel("Succulent Documentation");
			requirement.setDescription("As a Noob I want Succulent Documentation. Yammy!");
			requirement.setEstimatedWork(40f);
		} else if (variant == 15) {
			requirement.setLabel("Outlasting Collaboration");
			requirement.setDescription("This is still an epic. Nothing to see, really.");
			requirement.setEstimatedWork(100f);
		} else {
			return false;
		}

		saveEntity(requirement);

		if (variant > 0 && variant < 4) {
			requirement.setSprint(project.getCurrentSprint());
		}
		requirement.addTestTasks(variant);
		return true;
	}
}
