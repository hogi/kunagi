package scrum.client;

import ilarkesto.gwt.client.ADataTransferObject;
import ilarkesto.gwt.client.AGwtEntity;

import java.util.HashMap;
import java.util.Map;

import scrum.client.collaboration.ChatMessage;
import scrum.client.project.Requirement;
import scrum.client.sprint.Task;
import scrum.client.workspace.WorkareaWidget;
import scrum.client.workspace.WorkspaceWidget;

import com.google.gwt.user.client.Timer;

public class Dao extends GDao {

	private String entityIdBase;
	private int entityIdCounter;
	private EntityChangeCache cache = new EntityChangeCache();

	public AGwtEntity getEntityByReference(String reference) {
		int number = Integer.parseInt(reference.substring(1));
		if (reference.startsWith("r")) {
			for (Requirement requirement : getRequirements()) {
				if (requirement.isNumber(number)) return requirement;
			}
			return null;
		} else if (reference.startsWith("t")) {
			for (Task task : getTasks()) {
				if (task.isNumber(number)) return task;
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
			if (WorkspaceWidget.get().isWorkareaActive()) {
				WorkareaWidget.get().update();
			}
		}
	}

	// --- remote events (incoming from server) ---

	@Override
	protected void onEntityModifiedRemotely(AGwtEntity entity) {
		if (entity instanceof ChatMessage) {
			ScrumGwtApplication.get().addChatMessage((ChatMessage) entity);
		}
	}

	@Override
	protected void onEntityDeletedRemotely(AGwtEntity entity) {}

	// --- local events ---

	@Override
	protected void onEntityCreatedLocaly(AGwtEntity entity) {
		ScrumGwtApplication.get().callCreateEntity(entity.getEntityType(), entity.createPropertiesMap());
	}

	@Override
	protected void onEntityDeletedLocaly(AGwtEntity entity) {
		ScrumGwtApplication.get().callDeleteEntity(entity.getId());
	}

	@Override
	protected void onEntityPropertyChangedLocaly(AGwtEntity entity, String property, Object value) {
		cache.put(entity.getId(), property, value);
	}

	// --- ---

	@Override
	public String getNewEntityId() {
		if (entityIdBase == null) throw new RuntimeException("No entityIdBase received.");
		return entityIdBase + ++entityIdCounter;
	}

	public void setEntityIdBase(String entityIdBase) {
		this.entityIdBase = entityIdBase;
	}

	public static Dao get() {
		return ScrumGwtApplication.get().getDao();
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
				ScrumGwtApplication.get().callChangeProperties(entry.getKey(), entry.getValue());
			}
			entityProperties = new HashMap<String, Map>(3);
		}

		@Override
		public void run() {
			flush();
		}
	}
}
