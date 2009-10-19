package scrum.client;

import ilarkesto.gwt.client.ADataTransferObject;
import ilarkesto.gwt.client.AGwtEntity;

import java.util.HashMap;
import java.util.Map;

import scrum.client.collaboration.ChatMessage;
import scrum.client.impediments.Impediment;
import scrum.client.issues.Issue;
import scrum.client.project.Quality;
import scrum.client.project.Requirement;
import scrum.client.risks.Risk;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.Timer;

public class Dao extends GDao implements LogoutListener, ProjectClosedListener {

	private EntityChangeCache cache = new EntityChangeCache();
	private ComponentManager cm;

	// --- dependencies ---

	private Ui ui;

	public void setUi(Ui ui) {
		this.ui = ui;
	}

	// --- ---

	Dao() {}

	@Override
	protected void onInitialization() {
		super.onInitialization();
		cm = ComponentManager.get();
	}

	public void onProjectClosed() {
		clearChatMessages();
		clearImpediments();
		clearQualitys();
		clearRequirements();
		clearRisks();
		clearSprints();
		clearTasks();
		clearComments();
		clearWikipages();
	}

	public void onLogout() {
		clearAllEntities();
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
		} else {
			throw new RuntimeException("Unsupported entity reference: " + reference);
		}
	}

	@Override
	public void handleDataFromServer(ADataTransferObject data) {
		super.handleDataFromServer(data);
		if (data.containsEntities()) {
			cm.getEventBus().fireVisibleDataChanged();
		}
	}

	// --- remote events (incoming from server) ---

	@Override
	protected void onEntityModifiedRemotely(AGwtEntity entity) {
		if (entity instanceof ChatMessage) {
			cm.getChat().addChatMessage((ChatMessage) entity);
		}
	}

	@Override
	protected void onEntityDeletedRemotely(AGwtEntity entity) {}

	// --- local events ---

	@Override
	protected void onEntityCreatedLocaly(AGwtEntity entity) {
		cm.getApp().callCreateEntity(entity.getEntityType(), entity.createPropertiesMap());
	}

	@Override
	protected void onEntityDeletedLocaly(AGwtEntity entity) {
		cm.getApp().callDeleteEntity(entity.getId());
	}

	@Override
	protected void onEntityPropertyChangedLocaly(AGwtEntity entity, String property, Object value) {
		cache.put(entity.getId(), property, value);
	}

	// --- ---

	public static Dao get() {
		return ComponentManager.get().getDao();
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
				cm.getApp().callChangeProperties(entry.getKey(), entry.getValue());
			}
			entityProperties = new HashMap<String, Map>(3);
		}

		@Override
		public void run() {
			flush();
		}
	}
}
