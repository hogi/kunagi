package scrum.server.admin;

import ilarkesto.fp.Predicate;

public class SystemConfigDao extends GSystemConfigDao {

	public SystemConfig getSystemConfig() {
		SystemConfig config = getEntity(new Predicate<SystemConfig>() {

			public boolean test(SystemConfig e) {
				return true;
			}
		});

		if (config == null) {
			config = newEntityInstance();
			saveEntity(config);
		}

		return config;
	}

}