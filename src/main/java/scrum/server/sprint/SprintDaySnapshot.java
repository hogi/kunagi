package scrum.server.sprint;

import scrum.server.common.BurndownSnapshot;

public class SprintDaySnapshot extends GSprintDaySnapshot implements BurndownSnapshot {

	public void update() {
		setRemainingWork(getSprint().getRemainingWork());
		setBurnedWork(getSprint().getBurnedWork());
	}

	@Override
	public String toString() {
		return getDate() + ": " + getBurnedWork() + ", " + getRemainingWork();
	}

}
