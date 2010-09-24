package scrum.client.communication;

import ilarkesto.core.logging.Log;
import scrum.client.DataTransferObject;
import scrum.client.core.ApplicationStartedEvent;
import scrum.client.core.ApplicationStartedHandler;
import scrum.client.project.Requirement;
import scrum.client.workspace.BlockCollapsedEvent;
import scrum.client.workspace.BlockCollapsedHandler;
import scrum.client.workspace.BlockExpandedEvent;
import scrum.client.workspace.BlockExpandedHandler;

import com.google.gwt.user.client.Timer;

public class Pinger extends GPinger implements ServerDataReceivedHandler, BlockExpandedHandler, BlockCollapsedHandler,
		ApplicationStartedHandler {

	private static Log log = Log.get(Pinger.class);

	public static final int MIN_DELAY = 1000;
	public static final int MAX_DELAY = 5000;

	private Timer timer;
	private int maxDelay = MAX_DELAY;
	private long lastDataReceiveTime = System.currentTimeMillis();

	@Override
	public void onApplicationStarted(ApplicationStartedEvent event) {
		timer = new Timer() {

			@Override
			public void run() {
				new PingServiceCall().execute();
				reschedule();
			}
		};
		reschedule();
	}

	public void shutdown() {
		log.info("Shutting down");
		if (timer == null) return;
		timer.cancel();
		timer = null;
	}

	@Override
	public void onServerDataReceived(ServerDataReceivedEvent event) {
		DataTransferObject data = event.getData();
		if (data.containsEntities() || data.usersStatus != null) {
			lastDataReceiveTime = System.currentTimeMillis();
			reschedule();
		}
	}

	@Override
	public void onBlockCollapsed(BlockCollapsedEvent event) {
		deactivatePowerPolling();
	}

	@Override
	public void onBlockExpanded(BlockExpandedEvent event) {
		Object object = event.getObject();
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
