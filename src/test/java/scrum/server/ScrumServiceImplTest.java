package scrum.server;

import ilarkesto.base.Str;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import scrum.TestUtil;
import scrum.server.admin.User;

public class ScrumServiceImplTest extends Assert {

	ScrumWebApplication app;
	ScrumServiceImpl service;
	WebSession session;
	GwtConversation conversation;
	User duke;

	@BeforeTest
	public void init() {
		TestUtil.initialize();
		app = TestUtil.getApp();

		duke = app.getUserDao().getUserByName("duke");
		if (duke == null) duke = app.getUserDao().postUser("duke", "geheim");

		service = new ScrumServiceImpl();
		app.autowire(service);

		session = (WebSession) app.createWebSession(null);
		session.setUser(duke);

	}

	@BeforeMethod
	public void initConversation() {
		conversation = (GwtConversation) session.createGwtConversation();
	}

	@Test
	public void loginFail() {
		service.onLogin(conversation, "duke", "bad password");
		assertConversationError("Login failed.");
	}

	@Test
	public void loginSuccess() {
		service.onLogin(conversation, "duke", "geheim");
		assertConversationWithoutErrors();
	}

	private void assertConversationError(String error) {
		List<String> errors = conversation.getNextData().getErrors();
		assertTrue(errors != null && errors.contains(error),
			"Conversation error not found: <" + error + "> in " + Str.format(errors));
	}

	private void assertConversationWithoutErrors() {
		List<String> errors = conversation.getNextData().getErrors();
		assertTrue(errors == null || errors.isEmpty(), "Conversation contains errors: " + Str.format(errors));
	}
}
