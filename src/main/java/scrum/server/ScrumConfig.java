package scrum.server;

import ilarkesto.properties.APropertiesStore;
import ilarkesto.properties.FilePropertiesStore;

public class ScrumConfig {

	// --- dependencies ---

	private APropertiesStore p;
	private String applicationDataDir;

	public ScrumConfig(String applicationDataDir) {
		this.applicationDataDir = applicationDataDir;
		p = new FilePropertiesStore(applicationDataDir + "/config.properties", true);
	}

	// --- ---

	public String getGoogleAnalyticsId() {
		return p.get("google.analytics.id");
	}

	public String getFileRepositoryPath() {
		return p.get("fileRepository.path", applicationDataDir + "/files");
	}

	public boolean isStartupDelete() {
		// TODO set default to 'false', when going productive
		return p.getBoolean("startup.delete", true);
	}

	public boolean isStageIntegration() {
		return p.getBoolean("stage.integration", false);
	}
}
