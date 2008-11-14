package scrum.client.sprint;

import java.util.Map;

import scrum.client.admin.User;

public class Task extends GTask {

	public enum State {
		Open, Pwnd, Finished, Test, Closed
	}

	private User owner;
	private State state;

	public Task() {
		state = State.Open;
	}

	public Task(Map data) {
		super(data);
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

}
