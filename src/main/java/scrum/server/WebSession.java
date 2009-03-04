package scrum.server;

import ilarkesto.di.Context;
import ilarkesto.logging.Logger;
import ilarkesto.webapp.AWebSession;

import javax.servlet.http.HttpServletRequest;

public class WebSession extends AWebSession {

	private static final Logger LOG = Logger.get(WebSession.class);

	public WebSession(Context parentContext, HttpServletRequest initialRequest) {
		super(parentContext, initialRequest);
	}

}
