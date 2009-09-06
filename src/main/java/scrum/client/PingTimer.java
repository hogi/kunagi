package scrum.client;

import ilarkesto.gwt.client.GwtLogger;

import com.google.gwt.user.client.Timer;

public class PingTimer extends Timer {

	@Override
	public void run() {
		ScrumGwtApplication.get().callPing();
		schedule();
	}

	public void schedule() {
		long idle = System.currentTimeMillis() - ScrumGwtApplication.get().getLastDataReceiveTime();
		if (idle < 500) idle = 500;
		if (idle > 15000) idle = 15000;
		GwtLogger.DEBUG("rescheduling to ", idle);
		scheduleRepeating((int) idle);
	}

}
