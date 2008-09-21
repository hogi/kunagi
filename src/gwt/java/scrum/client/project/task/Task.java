package scrum.client.project.task;

import scrum.client.admin.User;
import scrum.client.common.AEntity;

public class Task extends AEntity {

	public enum State {
		Open, Pwnd, Finished, Test, Closed
	}

	private User owner;

	private String description;

	private int effort;

	private State state;

	public Task(String id) {
		super(id);
		state = State.Open;
	}

	public State getState() {
		return state;
	}

	public String getStateLabel() {
		// TODO i18n
		if (state == null) return "<null>";
		switch (state) {
			case Pwnd:
				return "Owned";
			case Finished:
				return "Finished";
			case Test:
				return "Test";
			case Closed:
				return "Closed";
			default:
				return "Open";
		}
	}

	public void setState(State state) {
		this.state = state;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEffort() {
		return effort;
	}

	public void setEffort(int effort) {
		this.effort = effort;
	}

}
