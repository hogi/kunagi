package scrum.server;

import ilarkesto.base.Utl;
import ilarkesto.logging.Logger;
import scrum.client.DataTransferObject;
import scrum.client.ScrumService;
import scrum.server.common.AServiceImpl;

public class ScrumServiceImpl extends AServiceImpl implements ScrumService {

	private static final Logger LOG = Logger.get(ScrumServiceImpl.class);

	public DataTransferObject getProject(String projectId) {
		SessionData session = getSessionData();
		getApp().onSelectProject(session, projectId);
		return session.popNextData();
	}

	public DataTransferObject getImpediments() {
		SessionData session = getSessionData();
		getApp().onGetImpediments(session);
		return session.popNextData();
	}

	public DataTransferObject getBacklogItems() {
		SessionData session = getSessionData();
		getApp().onGetBacklogItems(session);
		return session.popNextData();
	}

	public DataTransferObject changeProperty(String entityId, String property, String value) {
		SessionData session = getSessionData();
		getApp().onChangeProperty(session, entityId, property, value);
		return session.popNextData();
	}

	public DataTransferObject sleep(long millis) {
		SessionData session = getSessionData();
		LOG.debug("sleep", millis);
		Utl.sleep(millis);
		return session.popNextData();
	}

}
