package scrum.server;

import scrum.client.service.Dummy;
import scrum.client.service.ProjectData;
import scrum.client.service.ScrumService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ScrumServiceImpl extends RemoteServiceServlet implements ScrumService {

	public ProjectData getProject(String projectId) {
		System.out.println("service: getProject(" + projectId + ")");
		ProjectData result = new ProjectData();
		result.label = Dummy.moon.getLabel();
		return result;
	}

}
