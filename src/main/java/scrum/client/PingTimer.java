package scrum.client;

import ilarkesto.gwt.client.GwtLogger;

import com.google.gwt.user.client.Timer;

public class PingTimer extends Timer {

	public static final int MIN_DELAY = 1000;
	public static final int MAX_DELAY = 15000;

	public PingTimer() {
		scheduleRepeating(MAX_DELAY);
	}

	@Override
	public void run() {
		ScrumGwtApplication.get().callPing();
		schedule();
	}

	public void schedule() {
		long idle = System.currentTimeMillis() - ScrumGwtApplication.get().getLastDataReceiveTime();
		idle = (int) (idle * 0.2);
		if (idle < MIN_DELAY) idle = MIN_DELAY;
		if (idle > MAX_DELAY) idle = MAX_DELAY;
		GwtLogger.DEBUG("new:", idle);
	}

}
