package scrum.client.service;

import java.io.Serializable;
import java.util.Set;

public class ProjectDto implements Serializable {

	public String id;
	public String label;
	public Set<UserDto> users;
	public String masterId;
	public String ownerId;
	public Set<String> teamMemberIds;

}
