package scrum.client.common;


import ilarkesto.gwt.client.DataTransferObject;
import ilarkesto.gwt.client.GwtLogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scrum.client.ScrumGwtApplication;

public abstract class AGwtDao {

	protected abstract Collection<Map<String, ? extends AGwtEntity>> getEntityMaps();

	protected abstract void updateEntity(String type, Map data);

	public void handleDataFromServer(DataTransferObject data) {
		if (data.containsEntities()) {
			for (Map entityData : data.getEntities()) {
				updateEntity((String) entityData.get("@type"), entityData);
			}
		}
		if (data.containsDeletedEntities()) {
			List<String> deletedEntities = new ArrayList<String>(data.getDeletedEntities());
			for (Map<String, ? extends AGwtEntity> map : getEntityMaps()) {
				for (String entityId : new ArrayList<String>(deletedEntities)) {
					AGwtEntity entity = map.remove(entityId);
					if (entity != null) {
						deletedEntities.remove(entityId);
						GwtLogger.DEBUG("deleted:", entity.getEntityType() + ":", entity);
					}
				}
			}
		}
	}

	protected void entityCreated(AGwtEntity entity) {
		ScrumGwtApplication.get().callCreateEntity(entity.getEntityType(), entity.createPropertiesMap());
		entity.setCreated();
	}

	protected void entityDeleted(AGwtEntity entity) {
		ScrumGwtApplication.get().callDeleteEntity(entity.getId());
	}

	public final void entityPropertyChanged(AGwtEntity entity, String property, Object value) {
		String id = entity.getId();
		Map properties = new HashMap();
		properties.put(property, value);
		ScrumGwtApplication.get().callChangeProperties(id, properties);
	}

}
