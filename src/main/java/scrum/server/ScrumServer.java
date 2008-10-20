package scrum.server;

import ilarkesto.base.Utl;
import ilarkesto.logging.Logger;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import scrum.client.project.Project;
import scrum.client.service.ServerData;

public class ScrumServer {

	private static final Logger LOG = Logger.get(ScrumServer.class);
	private static final ScrumServer INSTANCE = new ScrumServer();

	private Set<SessionData> sessions = new HashSet<SessionData>();
	private DataStore dataStore = new DataStore();

	public void onProjectRequested(SessionData session, String id) {
		LOG.info("Project", id, "requested");
		ServerData data = session.getNextData();

		Map<String, ?> project = dataStore.getProject(id);
		if (project == null) {
			data.errors.add("Project does not exist: " + id);
			return;
		}

		data.project = Utl.subMap((Map<String, String>) project, Project.ID, Project.LABEL);
	}

	public void onSessionCreated(SessionData session) {
		LOG.info("Session created");
		sessions.add(session);
	}

	public static ScrumServer get() {
		return INSTANCE;
	}

}
