package scrum.server.common;

import ilarkesto.base.time.Date;

public interface BurndownSnapshot {

	public Date getDate();

	public int getBurnedWork();

	public int getRemainingWork();

}
