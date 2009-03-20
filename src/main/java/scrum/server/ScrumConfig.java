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

	public boolean isDemoMode() {
		return p.getBoolean("demoMode", true);
	}

}
