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

	public boolean isStageIntegration() {
		return p.getBoolean("stage.integration", false);
	}

	public String getSmtpHost() {
		return p.get("smtp.host");
	}

	public String getSmtpUser() {
		return p.get("smtp.user");
	}

	public String getSmtpPassword() {
		return p.get("smtp.password");
	}

	public String getSmtpFrom() {
		return p.get("smtp.from");
	}
}
