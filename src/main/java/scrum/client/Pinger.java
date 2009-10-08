package scrum.client;

import ilarkesto.gwt.client.AComponent;

import com.google.gwt.user.client.Timer;

public class Pinger extends AComponent implements ServerDataReceivedListener {

	public static final int MIN_DELAY = 1000;
	public static final int MAX_DELAY = 15000;

	private Timer timer;
	private long lastDataReceiveTime = System.currentTimeMillis();

	@Override
	protected void onInitialization() {
		timer = new Timer() {

			@Override
			public void run() {
				ScrumGwtApplication.get().callPing();
				reschedule();
			}
		};
	}

	public void onServerDataReceived(DataTransferObject data) {
		if (data.containsEntities() || data.usersStatus != null) {
			lastDataReceiveTime = System.currentTimeMillis();
			reschedule();
		}
	}

	public void reschedule() {
		if (timer == null) return;
		long idle = System.currentTimeMillis() - lastDataReceiveTime;
		idle = (int) (idle * 0.2);
		if (idle < MIN_DELAY) idle = MIN_DELAY;
		if (idle > MAX_DELAY) idle = MAX_DELAY;
		timer.scheduleRepeating((int) idle);
	}

}
