package scrum.server.admin;

import java.util.ArrayList;
import java.util.List;

public class SystemConfigDao extends GSystemConfigDao {

	public SystemConfig getSystemConfig() {
		List<SystemConfig> all = new ArrayList<SystemConfig>(getEntities());

		if (all.isEmpty()) {
			SystemConfig config = newEntityInstance();
			saveEntity(config);
			return config;
		}

		while (all.size() > 1) {
			SystemConfig config = all.get(0);
			deleteEntity(config);
			all.remove(config);
		}

		return all.get(0);
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		getSystemConfig();
	}

}