package scrum.server;

import ilarkesto.base.Tm;
import ilarkesto.base.time.TimePeriod;
import ilarkesto.core.logging.Log;
import ilarkesto.di.Context;
import ilarkesto.gwt.server.AGwtConversation;
import ilarkesto.webapp.AWebSession;

import javax.servlet.http.HttpServletRequest;

import scrum.server.admin.User;

public class WebSession extends AWebSession {

	private static final Log LOG = Log.get(WebSession.class);

	private TimePeriod TIMEOUT = new TimePeriod(Tm.HOUR);
	private User user;

	public WebSession(Context parentContext, HttpServletRequest initialRequest) {
		super(parentContext, initialRequest);
	}

	@Override
	public GwtConversation getGwtConversation(int conversationNumber) {
		return (GwtConversation) super.getGwtConversation(conversationNumber);
	}

	@Override
	public AGwtConversation createGwtConversation() {
		GwtConversation gwtConversation = new GwtConversation(this, nextGwtConversationNumber());
		gwtConversation.setEmoticonDao(ScrumWebApplication.get().getEmoticonDao());

		if (user != null) gwtConversation.sendUserScopeDataToClient(user);

		return gwtConversation;
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
