package scrum.server;

import ilarkesto.di.Context;
import ilarkesto.gwt.client.DataTransferObject;
import ilarkesto.logging.Logger;
import scrum.server.project.Project;

/**
 * This class represents the users HTTP session on the server.
 * 
 * For each HTTP session there is one instance of this class.
 */
public class SessionData {

	private static final Logger LOG = Logger.get(SessionData.class);

	private Context context;
	private Project project;

	public SessionData() {
		context = Context.get().createSubContext("session");
	}

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
		LOG.info("project selected:", project);
		this.project = project;
	}

	public Context getContext() {
		return context;
	}

}
