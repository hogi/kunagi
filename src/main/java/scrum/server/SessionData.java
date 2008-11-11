package scrum.server;

import scrum.client.DataTransferObject;
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
	private DataTransferObject nextData = new DataTransferObject();

	DataTransferObject popNextData() {
		synchronized (nextData) {
			DataTransferObject ret = nextData;
			nextData = new DataTransferObject();
			return ret;
		}
	}

	DataTransferObject getNextData() {
		return nextData;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
