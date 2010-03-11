package scrum.client.admin;

import ilarkesto.core.scope.Scope;

import java.util.Map;

public class ProjectUserConfig extends GProjectUserConfig {

	public ProjectUserConfig() {

	}

	@Override
	public boolean isMisconductsEditable() {
		return cm.getProjectContext().getProject().isScrumMaster(Scope.get().getComponent(Auth.class).getUser());
	}

	public ProjectUserConfig(Map data) {
		super(data);
	}

	@Override
	public String toString() {
		return getProject() + " " + getUser();
	}

}
