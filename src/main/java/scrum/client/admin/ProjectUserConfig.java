package scrum.client.admin;

import java.util.Map;

public class ProjectUserConfig extends GProjectUserConfig {

	public ProjectUserConfig() {

	}

	@Override
	public boolean isMisconductsEditable() {
		return cm.getProjectContext().getProject().isScrumMaster(cm.getAuth().getUser());
	}

	public ProjectUserConfig(Map data) {
		super(data);
	}

	@Override
	public String toString() {
		return getProject() + " " + getUser();
	}

}
