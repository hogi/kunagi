package scrum.client.service;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ScrumService extends RemoteService {

	ServerData getProject(String projectId);

	ServerData getImpediments();

	ServerData getBacklogItems();

}
