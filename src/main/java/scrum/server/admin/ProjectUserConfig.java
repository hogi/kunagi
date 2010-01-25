package scrum.server.admin;


public class ProjectUserConfig extends GProjectUserConfig {

	@Override
	public String toString() {
		return getProject() + " " + getUser();
	}

}