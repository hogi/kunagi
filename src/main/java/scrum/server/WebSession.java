package scrum.server;

import ilarkesto.di.Context;
import ilarkesto.logging.Logger;
import ilarkesto.webapp.AWebSession;

public class WebSession extends AWebSession {

	private static final Logger LOG = Logger.get(WebSession.class);

	public WebSession(Context parentContext) {
		super(parentContext);
	}

}
