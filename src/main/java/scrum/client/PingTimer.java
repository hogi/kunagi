package scrum.client;

import com.google.gwt.user.client.Timer;

public class PingTimer extends Timer {

	@Override
	public void run() {
		ScrumGwtApplication.get().callPing();
	}

}
