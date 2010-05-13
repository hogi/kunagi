package scrum.server.sprint;

import ilarkesto.fp.Predicate;

import java.util.Set;

import scrum.server.project.Project;
import scrum.server.project.Requirement;

public class TaskDao extends GTaskDao {

	public Set<Task> getTasksByProject(final Project project) {
		return getEntities(new Predicate<Task>() {

			public boolean test(Task t) {
				return t.isProject(project);
			}
		});
	}

	public Task getTaskByNumber(final int number, final Project project) {
		return getEntity(new Predicate<Task>() {

			public boolean test(Task t) {
				return t.isNumber(number) && t.isProject(project);
			}
		});
	}

	@Override
	public Task newEntityInstance() {
		Task task = super.newEntityInstance();
		task.setRemainingWork(scrum.client.sprint.Task.INIT_EFFORT);
		return task;
	}

	public Set<Task> getTasksBySprint(final Sprint sprint) {
		return getEntities(new Predicate<Task>() {

			public boolean test(Task task) {
				return task.isSprint(sprint);
			}
		});
	}

	// --- test data ---

	public boolean createTestTask(Requirement requirement, int variant) {
		Task task = newEntityInstance();
		task.setRequirement(requirement);

		if (requirement.getLabel() == "Unsurpassed Concept") {
			if (variant == 1) {
				task.setLabel("Create Concept");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 2) {
				task.setLabel("Make Sure All Others Are Inferior");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Amazing Product Backlog") {
			if (variant == 1) {
				task.setLabel("Creation");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 2) {
				task.setLabel("Intelligent Design");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 3) {
				task.setLabel("Deletion");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Functional Quality Backlog") {
			if (variant == 1) {
				task.setLabel("Copy And Paste Product Backlog");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 2) {
				task.setLabel("Marry Storys and Qualitys");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Groundbraking Scrum Backlog") {
			if (variant == 1) {
				task.setLabel("Create Tasks");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 2) {
				task.setLabel("[insert Task here]");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Breathtaking Whiteboard") {
			if (variant == 1) {
				task.setLabel("Buy Whiteboard");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 2) {
				task.setLabel("Buy Postits");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 3) {
				task.setLabel("Hire Monkey");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 4) {
				task.setLabel("Teach Monkey to Move Postits");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Empowering Impediment List") {
			if (variant == 1) {
				task.setLabel("Pave The Roads");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Divine Risk Management") {
			if (variant == 1) {
				task.setLabel("Prepare for Risk Management");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 2) {
				task.setLabel("Discover Risks");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 2) {
				task.setLabel("Mitigate Risks");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Miraculous Issue Management") {
			if (variant == 1) {
				task.setLabel("Wait for Issues");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 2) {
				task.setLabel("Assign Issues to Someone Else");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Immaculate Bug Tracking") {
			if (variant == 1) {
				task.setLabel("Go Get Bug Spray Already");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Unbeatable Planning Poker") {
			if (variant == 1) {
				task.setLabel("Cheat");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Enlightening Wiki") {
			if (variant == 1) {
				task.setLabel("Link To Wikipedia, Maybe?");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Absorbing Discussion Board") {
			if (variant == 1) {
				task.setLabel("Reinvent The Wheel");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 2) {
				task.setLabel("Make The Wheel Absorbing");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Irresistable User Interface") {
			if (variant == 1) {
				task.setLabel("Use Web 2.0");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 2) {
				task.setLabel("Use AJAX");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 2) {
				task.setLabel("Use Buzzwords");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Succulent Documentation") {
			if (variant == 1) {
				task.setLabel("Go From Door To Door");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 2) {
				task.setLabel("Preach Unsurpassed Concept");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else if (requirement.getLabel() == "Outlasting Collaboration") {
			if (variant == 1) {
				task.setLabel("Upload Brains");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else if (variant == 2) {
				task.setLabel("Start Talking");
				task.setRemainingWork(1);
				task.setBurnedWork(0);
			} else {
				return false;
			}
		} else {
			return false;
		}

		saveEntity(task);
		return true;
	}

}
