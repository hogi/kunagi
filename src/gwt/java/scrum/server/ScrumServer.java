package scrum.server;

import ilarkesto.logging.Logger;

import java.util.HashSet;
import java.util.Set;

public class ScrumServer {

	private static final Logger LOG = Logger.get(ScrumServer.class);

	private Set<SessionData> sessions = new HashSet<SessionData>();

	public void onSessionCreated(SessionData session) {
		LOG.info("Session created");
		sessions.add(session);

	}

}
