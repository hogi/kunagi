package scrum.server.sprint;

public class SprintDaySnapshot extends GSprintDaySnapshot {

	public void update() {
		setRemainingWork(getSprint().getRemainingWork());
		setBurnedWork(getSprint().getBurnedWork());
	}

	public SprintDaySnapshot() {
		super(null);
	}

	public SprintDaySnapshot(SprintDaySnapshot template) {
		super(template);
	}

	@Override
	public String toString() {
		return getDate() + ": " + getBurnedWork() + ", " + getRemainingWork();
	}

}
