package scrum.client;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.ADataTransferObject;
import ilarkesto.gwt.client.AGwtEntity;

import java.util.HashMap;
import java.util.Map;

import scrum.client.calendar.SimpleEvent;
import scrum.client.collaboration.Chat;
import scrum.client.collaboration.ChatMessage;
import scrum.client.collaboration.Subject;
import scrum.client.files.File;
import scrum.client.impediments.Impediment;
import scrum.client.issues.Issue;
import scrum.client.project.Quality;
import scrum.client.project.Requirement;
import scrum.client.risks.Risk;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.Timer;

public class Dao extends GDao {

	private static final Dao INSTANCE = new Dao();

	private EntityChangeCache cache = new EntityChangeCache();
	private ScrumGwtApplication app;
	private EventBus eventBus;

	private Dao() {}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	public void setApp(ScrumGwtApplication app) {
		this.app = app;
	}

	public void clearProjectEntities() {
		clearChatMessages();
		clearImpediments();
		clearQualitys();
		clearRequirements();
		clearRisks();
		clearSprints();
		clearTasks();
		clearComments();
		clearWikipages();
		clearIssues();
		clearSimpleEvents();
		clearProjectUserConfigs();
		clearProjectEvents();
	}

	public AGwtEntity getEntityByReference(String reference) {
		int number = Integer.parseInt(reference.substring(Requirement.REFERENCE_PREFIX.length()));
		if (reference.startsWith(Requirement.REFERENCE_PREFIX)) {
			for (Requirement e : getRequirements()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(Task.REFERENCE_PREFIX)) {
			for (Task e : getTasks()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(Quality.REFERENCE_PREFIX)) {
			for (Quality e : getQualitys()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(Issue.REFERENCE_PREFIX)) {
			for (Issue e : getIssues()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(Risk.REFERENCE_PREFIX)) {
			for (Risk e : getRisks()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(Impediment.REFERENCE_PREFIX)) {
			for (Impediment e : getImpediments()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(File.REFERENCE_PREFIX)) {
			for (File e : getFiles()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(Subject.REFERENCE_PREFIX)) {
			for (Subject e : getSubjects()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else if (reference.startsWith(SimpleEvent.REFERENCE_PREFIX)) {
			for (SimpleEvent e : getSimpleEvents()) {
				if (e.isNumber(number)) return e;
			}
			return null;
		} else {
			throw new RuntimeException("Unsupported entity reference: " + reference);
		}
	}

	@Override
	public void handleDataFromServer(ADataTransferObject data) {
		super.handleDataFromServer(data);
		if (data.containsEntities() || data.containsDeletedEntities()) {
			eventBus.fireVisibleDataChanged();
		}
	}

	// --- remote events (incoming from server) ---

	@Override
	protected void onEntityModifiedRemotely(AGwtEntity entity) {
		if (entity instanceof ChatMessage) {
			Scope.get().getComponent(Chat.class).addChatMessage((ChatMessage) entity);
		}
		if (entity instanceof File) {
			eventBus.fireFileUploaded((File) entity);
		}
	}

	@Override
	protected void onEntityDeletedRemotely(AGwtEntity entity) {}

	// --- local events ---

	@Override
	protected void onEntityCreatedLocaly(AGwtEntity entity) {
		app.callCreateEntity(entity.getEntityType(), entity.createPropertiesMap());
	}

	@Override
	protected void onEntityDeletedLocaly(AGwtEntity entity) {
		app.callDeleteEntity(entity.getId());
	}

	@Override
	protected void onEntityPropertyChangedLocaly(AGwtEntity entity, String property, Object value) {
		cache.put(entity.getId(), property, value);
	}

	// --- ---

	public static Dao get() {
		return INSTANCE;
	}

	private class EntityChangeCache extends Timer {

		private Map<String, Map> entityProperties = new HashMap<String, Map>(3);

		public void put(String entityId, String property, Object value) {
			Map entity = entityProperties.get(entityId);
			if (entity == null) {
				entity = new HashMap();
				entityProperties.put(entityId, entity);
			}
			entity.put(property, value);
			schedule(1000);
		}

		private void flush() {
			for (Map.Entry<String, Map> entry : entityProperties.entrySet()) {
				app.callChangeProperties(entry.getKey(), entry.getValue());
			}
			entityProperties = new HashMap<String, Map>(3);
		}

		@Override
		public void run() {
			flush();
		}
	}
}
