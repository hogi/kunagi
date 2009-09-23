package scrum.client.admin;

import java.util.Map;

public class ProjectUserConfig extends GProjectUserConfig {

	public ProjectUserConfig() {

	}

	public ProjectUserConfig(Map data) {
		super(data);
	}

	@Override
	public String toString() {
		return getProject() + " " + getUser();
	}

}
