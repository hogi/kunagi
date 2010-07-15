package scrum.client;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ApplicationInfo implements Serializable, IsSerializable {

	public static final String DEPLOYMENT_STAGE_DEVELOPMENT = "DEVELOPMENT";
	public static final String DEPLOYMENT_STAGE_INTEGRATION = "INTEGRATION";
	public static final String DEPLOYMENT_STAGE_PRODUCTION = "PRODUCTION";

	private String name;
	private String build;
	private String deploymentStage;
	private boolean defaultAdminPassword;

	public ApplicationInfo(String name, String build, String deploymentStage, boolean defaultAdminPassword) {
		this.name = name;
		this.build = build;
		this.deploymentStage = deploymentStage;
		this.defaultAdminPassword = defaultAdminPassword;
	}

	protected ApplicationInfo() {}

	public boolean isDefaultAdminPassword() {
		return defaultAdminPassword;
	}

	public String getName() {
		return name;
	}

	public String getBuild() {
		return build;
	}

	public String getDeploymentStage() {
		return deploymentStage;
	}

	public boolean isProductionStage() {
		return deploymentStage.equals(DEPLOYMENT_STAGE_PRODUCTION);
	}

	public boolean isDevelopmentStage() {
		return deploymentStage.equals(DEPLOYMENT_STAGE_DEVELOPMENT);
	}

	public String getVersionDescription() {
		return name + " | Build " + build + " | " + deploymentStage;
	}

	@Override
	public String toString() {
		return getVersionDescription();
	}

}
