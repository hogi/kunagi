package scrum.server;

import ilarkesto.base.Utl;
import ilarkesto.logging.Logger;
import scrum.client.service.ScrumService;
import scrum.client.service.ServerData;
import scrum.server.common.AServiceImpl;

public class ScrumServiceImpl extends AServiceImpl implements ScrumService {

	private static final Logger LOG = Logger.get(ScrumServiceImpl.class);

	public ServerData getProject(String projectId) {
		SessionData session = getSessionData();
		getApp().onSelectProject(session, projectId);
		return session.popNextData();
	}

	public ServerData getImpediments() {
		SessionData session = getSessionData();
		getApp().onGetImpediments(session);
		return session.popNextData();
	}

	public ServerData getBacklogItems() {
		SessionData session = getSessionData();
		getApp().onGetBacklogItems(session);
		return session.popNextData();
	}

	public ServerData changeProperty(String entityId, String property, String value) {
		SessionData session = getSessionData();
		getApp().onChangeProperty(session, entityId, property, value);
		return session.popNextData();
	}

	public ServerData sleep(long millis) {
		SessionData session = getSessionData();
		LOG.debug("sleep", millis);
		Utl.sleep(millis);
		return session.popNextData();
	}

}
