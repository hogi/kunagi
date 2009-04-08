package scrum.client;

import ilarkesto.gwt.client.ADataTransferObject;
import ilarkesto.gwt.client.AGwtEntity;

import java.util.HashMap;
import java.util.Map;

import scrum.client.workspace.WorkareaWidget;
import scrum.client.workspace.WorkspaceWidget;

public class Dao extends GDao {

	private String entityIdBase;
	private int entityIdCounter;

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
	protected void onEntityModifiedRemotely(AGwtEntity entity) {}

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
		String id = entity.getId();
		Map properties = new HashMap();
		properties.put(property, value);
		ScrumGwtApplication.get().callChangeProperties(id, properties);
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

}
