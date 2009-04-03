package scrum.server;

import ilarkesto.properties.APropertiesStore;
import ilarkesto.properties.FilePropertiesStore;

public class ScrumConfig {

	// --- dependencies ---

	private APropertiesStore p;

	public ScrumConfig(String applicationDataDir) {
		p = new FilePropertiesStore(applicationDataDir + "/config.properties", true);
	}

	// --- ---

	public boolean isStartupDelete() {
		// TODO set default to 'false', when going productive
		return p.getBoolean("startup.delete", true);
	}

}
