package scrum.server;

import ilarkesto.auth.WrongPasswordException;
import ilarkesto.base.PermissionDeniedException;
import ilarkesto.base.Str;
import ilarkesto.base.Utl;
import ilarkesto.persistence.AEntity;
import ilarkesto.testng.ATest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import scrum.TestUtil;
import scrum.client.DataTransferObject;
import scrum.client.admin.SystemMessage;
import scrum.server.admin.User;
import scrum.server.project.Project;

public class ScrumServiceImplTest extends ATest {

	ScrumWebApplication app;
	ScrumServiceImpl service;
	WebSession sessionForAdmin;
	WebSession session;
	GwtConversation conversationForAdmin;
	GwtConversation conversation;
	User admin;
	User duke;

	@BeforeTest
	public void init() {
		TestUtil.initialize();
		app = TestUtil.getApp();

		admin = app.getUserDao().getUserByName("admin");
		if (admin == null) {
			admin = app.getUserDao().postUser("admin");
			admin.setAdmin(true);
		}

		duke = app.getUserDao().getUserByName("duke");
		if (duke == null) duke = app.getUserDao().postUser("duke");
		duke.setEmail("duke@kunagi.org");
		duke.setEmailVerified(true);

		service = new ScrumServiceImpl();
		service.setWebApplication(app);
		app.autowire(service);

		session = (WebSession) app.createWebSession(null);
		session.setUser(duke);

		sessionForAdmin = (WebSession) app.createWebSession(null);
		sessionForAdmin.setUser(admin);
	}

	@BeforeMethod
	public void initConversations() {
		conversation = (GwtConversation) session.createGwtConversation();
		conversation.getNextData().clear();
		conversationForAdmin = (GwtConversation) sessionForAdmin.createGwtConversation();
		conversationForAdmin.getNextData().clear();
	}

	@Test
	public void loginFail() {
		service.onLogin(conversation, "duke", "bad password");
		assertConversationError(conversation, "Login failed.");
	}

	@Test
	public void loginSuccess() {
		duke.setPassword("geheim");
		service.onLogin(conversation, "duke", "geheim");
		assertConversationWithoutErrors(conversation);
	}

	@Test
	public void createExampleProject() {
		service.onCreateExampleProject(conversation);
		assertConversationWithoutErrors(conversation);
		assertEquals(conversation.getNextData().getEntities().size(), 1);
		Project project = getEntityByType(Project.class);
		assertStartsWith(project.getLabel(), "Example Project");
		assertTrue(project.containsAdmin(duke));
		assertTrue(project.containsParticipant(duke));
		assertTrue(project.containsProductOwner(duke));
		assertTrue(project.containsScrumMaster(duke));
		assertTrue(project.containsTeamMember(duke));
		app.getProjectDao().deleteEntity(project);
	}

	@Test
	public void search() {
		service.onSearch(conversation, "something");
		assertConversationWithoutErrors(conversation);
	}

	@Test(expectedExceptions = PermissionDeniedException.class)
	public void updateSystemMessage() {
		SystemMessage systemMessage = new SystemMessage();
		systemMessage.setText("Alert!");
		systemMessage.setActive(true);
		service.onUpdateSystemMessage(conversation, systemMessage);
		assertConversationWithoutErrors(conversation);
	}

	@Test
	public void updateSystemMessageAdmin() {
		SystemMessage systemMessage = new SystemMessage();
		systemMessage.setText("Alert!");
		systemMessage.setActive(true);
		service.onUpdateSystemMessage(conversationForAdmin, systemMessage);
		assertConversationWithoutErrors(conversationForAdmin);
	}

	@Test
	public void setSelectedEntitysIds() {
		Set<String> ids = Utl.toSet("1", "2");
		service.onSetSelectedEntitysIds(conversation, ids);
		assertConversationWithoutErrors(conversation);
	}

	@Test
	public void resetPassword() {
		service.onResetPassword(conversation, duke.getId());
		assertConversationWithoutErrors(conversation);
	}

	@Test
	public void requestNewPassword() {
		duke.setPassword(scrum.client.admin.User.INITIAL_PASSWORD);
		service.onRequestNewPassword(conversation, "duke");
		assertConversationWithoutErrors(conversation);
		assertFalse(duke.matchesPassword(scrum.client.admin.User.INITIAL_PASSWORD));
	}

	@Test
	public void changePassword() {
		duke.setPassword("geheim");
		service.onChangePassword(conversation, "geheim", "supergeheim");
	}

	@Test(expectedExceptions = WrongPasswordException.class)
	public void changePasswordFail() {
		duke.setPassword("geheim");
		service.onChangePassword(conversation, "wrong", "supergeheim");
		assertConversationWithoutErrors(conversation);
		assertTrue(duke.matchesPassword("supergeheim"));
	}

	@Test
	public void createEntity() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("id", UUID.randomUUID().toString());
		properties.put("name", "anonymous");
		service.onCreateEntity(conversation, "user", properties);
		assertConversationWithoutErrors(conversation);
		User anonymous = app.getUserDao().getUserByName("anonymous");
		assertNotNull(anonymous);
		app.getUserDao().deleteEntity(anonymous);
	}

	@Test
	public void deleteEntity() {
		User anonymous = app.getUserDao().postUser("daemon");
		service.onDeleteEntity(conversationForAdmin, anonymous.getId());
		assertConversationWithoutErrors(conversationForAdmin);
		assertNull(app.getUserDao().getUserByName("daemon"));
	}

	// --- helpers ---

	private <E extends AEntity> E getEntityByType(Class<E> type) {
		DataTransferObject dto = conversation.getNextData();
		if (!dto.containsEntities()) return null;
		for (Map entity : dto.getEntities()) {
			if (type.getSimpleName().toLowerCase().equals(entity.get("@type"))) {
				String id = (String) entity.get("id");
				E e = (E) app.getDaoService().getEntityById(id);
				return e;
			}
		}
		return null;
	}

	private static <E extends AEntity> E createEntity(Class<E> type, Map properties) {
		E entity;
		try {
			entity = type.newInstance();
		} catch (InstantiationException ex) {
			throw new RuntimeException(ex);
		} catch (IllegalAccessException ex) {
			throw new RuntimeException(ex);
		}
		entity.updateProperties(properties);
		return entity;
	}

	private static void assertConversationError(GwtConversation conversation, String error) {
		List<String> errors = conversation.getNextData().getErrors();
		assertTrue(errors != null && errors.contains(error),
			"Conversation error not found: <" + error + "> in " + Str.format(errors));
	}

	private static void assertConversationWithoutErrors(GwtConversation conversation) {
		List<String> errors = conversation.getNextData().getErrors();
		assertTrue(errors == null || errors.isEmpty(), "Conversation contains errors: " + Str.format(errors));
	}

}
