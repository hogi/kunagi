package scrum.server.sprint;

import scrum.server.common.BurndownSnapshot;

public class SprintDaySnapshot extends GSprintDaySnapshot implements BurndownSnapshot {

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
