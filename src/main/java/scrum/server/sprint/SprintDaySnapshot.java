package scrum.server.sprint;

import ilarkesto.base.time.Date;

public class SprintDaySnapshot extends GSprintDaySnapshot {

	public void update() {
		setRemainingWork(getSprint().getRemainingWork());
		setBurnedWork(getSprint().getBurnedWork());
	}

	public Date getDate() {
		return new Date(getDateCrap());
	}

	public void setDate(Date date) {
		setDateCrap(date.toJavaDate());
	}

	public boolean isDate(Date date) {
		return date.equals(getDate());
	}

	public SprintDaySnapshot() {
		super(null);
	}

	public SprintDaySnapshot(SprintDaySnapshot template) {
		super(template);
	}

}
