package scrum.client;

import scrum.client.common.AScrumComponent;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.Timer;

public class Pinger extends AScrumComponent implements ServerDataReceivedListener, BlockExpandedListener,
		BlockCollapsedListener {

	public static final int MIN_DELAY = 1000;
	public static final int MAX_DELAY = 5000;

	private Timer timer;
	private int maxDelay = MAX_DELAY;
	private long lastDataReceiveTime = System.currentTimeMillis();

	@Override
	protected void onInitialization() {
		timer = new Timer() {

			@Override
			public void run() {
				cm.getApp().callPing();
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

	public void onBlockCollapsed(Object object) {
		deactivatePowerPolling();
	}

	public void onBlockExpanded(Object object) {
		if (object instanceof Requirement) {
			Requirement requirement = (Requirement) object;
			if (requirement.isWorkEstimationVotingActive()) activatePowerPolling();
		}
	}

	public void reschedule() {
		if (timer == null) return;
		long idle = System.currentTimeMillis() - lastDataReceiveTime;
		idle = (int) (idle * 0.15);
		if (idle < MIN_DELAY) idle = MIN_DELAY;
		if (idle > maxDelay) idle = maxDelay;
		timer.scheduleRepeating((int) idle);
	}

	private void activatePowerPolling() {
		maxDelay = MIN_DELAY;
		log.debug("PowerPolling activated");
	}

	private void deactivatePowerPolling() {
		if (maxDelay == MAX_DELAY) return;
		maxDelay = MAX_DELAY;
		log.debug("PowerPolling deactivated");
	}

}
