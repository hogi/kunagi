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
		return p.getBoolean("startup.delete", false);
	}

	public boolean isDeleteOldProjects() {
		return p.getBoolean("deleteOldProjects", false);
	}

	public boolean isDeleteDisabledUsers() {
		return p.getBoolean("deleteDisabledUsers", false);
	}

	public boolean isStageIntegration() {
		return p.getBoolean("stage.integration", false);
	}

	public String getInitialPassword() {
		return p.get("initialPassword", scrum.client.admin.User.INITIAL_PASSWORD);
	}

	public boolean isLoggingDebug() {
		return p.getBoolean("logging.debug", false);
	}

}
