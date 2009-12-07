package scrum.client;

import java.util.ArrayList;
import java.util.List;

import scrum.client.project.Requirement;

public class SearchResults {

	private static ComponentManager cm = ComponentManager.get();

	private List<Requirement> requirements = new ArrayList<Requirement>();

	public void clear() {
		requirements.clear();
		cm.getEventBus().fireSearchResultsChanged();
	}

	public void addRequirements(List<Requirement> requirements) {
		boolean changed = this.requirements.addAll(requirements);
		if (changed) cm.getEventBus().fireSearchResultsChanged();
	}

}
