package scrum.client.service;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ScrumService extends RemoteService {

	ProjectData getProject(String projectId);

}
