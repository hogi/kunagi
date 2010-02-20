package scrum.client.communication;

import ilarkesto.core.logging.Log;
import scrum.client.ApplicationStartListener;
import scrum.client.BlockCollapsedListener;
import scrum.client.BlockExpandedListener;
import scrum.client.DataTransferObject;
import scrum.client.ScrumGwtApplication;
import scrum.client.ServerDataReceivedListener;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.Timer;

public class Pinger implements ServerDataReceivedListener, BlockExpandedListener, BlockCollapsedListener,
		ApplicationStartListener {

	private static Log log = Log.get(Pinger.class);

	public static final int MIN_DELAY = 1000;
	public static final int MAX_DELAY = 5000;

	private Timer timer;
	private int maxDelay = MAX_DELAY;
	private long lastDataReceiveTime = System.currentTimeMillis();

	ScrumGwtApplication app;

	public void onApplicationStart() {
		timer = new Timer() {

			@Override
			public void run() {
				app.callPing();
				reschedule();
			}
		};
		reschedule();
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
		lastDataReceiveTime = System.currentTimeMillis();
		log.debug("PowerPolling deactivated");
	}

}
