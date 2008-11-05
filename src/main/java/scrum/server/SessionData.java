package scrum.server;

import scrum.client.service.ServerData;
import scrum.server.project.Project;

/**
 * This class represents the users HTTP session on the server.
 * 
 * For each HTTP session there is one instance of this class.
 */
public class SessionData {

	private Project project;

	/**
	 * Data that will be transferred to the client at the next request.
	 */
	private ServerData nextData = new ServerData();

	ServerData popNextData() {
		synchronized (nextData) {
			ServerData ret = nextData;
			nextData = new ServerData();
			return ret;
		}
	}

	ServerData getNextData() {
		return nextData;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
