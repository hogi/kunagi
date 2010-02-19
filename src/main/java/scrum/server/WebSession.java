package scrum.server;

import ilarkesto.base.time.TimePeriod;
import ilarkesto.di.Context;
import ilarkesto.logging.Logger;
import ilarkesto.webapp.AWebSession;

import javax.servlet.http.HttpServletRequest;

import scrum.client.communication.Pinger;
import scrum.server.admin.User;

public class WebSession extends AWebSession {

	private static final Logger LOG = Logger.get(WebSession.class);

	private TimePeriod TIMEOUT = new TimePeriod(Pinger.MAX_DELAY * 2);
	private User user;

	public WebSession(Context parentContext, HttpServletRequest initialRequest) {
		super(parentContext, initialRequest);
	}

	@Override
	public GwtConversation getGwtConversation() {
		if (gwtConversation == null) {
			gwtConversation = new GwtConversation(this, nextGwtConversationNumber());
			((GwtConversation) gwtConversation).setEmoticonDao(ScrumWebApplication.get().getEmoticonDao());
		}
		return (GwtConversation) gwtConversation;
	}

	public void setUser(User user) {
		LOG.info("User set:", user);
		this.user = user;
		getContext().setName(toString());
	}

	public User getUser() {
		return user;
	}

	@Override
	protected void onInvalidate() {
		if (gwtConversation != null) getGwtConversation().invalidate();
		setUser(null);
		super.onInvalidate();
	}

	@Override
	protected TimePeriod getTimeout() {
		return TIMEOUT;
	}

	@Override
	public String toString() {
		return user == null ? super.toString() : "session:" + user;
	}

}
