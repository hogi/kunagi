package scrum.client.common;

import java.util.List;

import scrum.client.project.BacklogItem;

public interface ListProvider {

	public List<BacklogItem> getList();

}
