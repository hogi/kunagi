package scrum.client;

import ilarkesto.gwt.client.Gwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import scrum.client.common.AScrumComponent;
import scrum.client.common.AScrumGwtEntity;
import scrum.client.project.Project;

public class Search extends AScrumComponent implements SearchResultsChangedListener {

	private SearchResults results = new SearchResults();
	private SearchResultsWidget resultsWidget;
	private String searchText;

	public void search(String text) {
		this.searchText = text;
		log.info("Searching:", searchText);
		results.clear();
		if (Gwt.isEmpty(searchText)) return;

		cm.getApp().callSearch(searchText, new Runnable() {

			public void run() {
				searchClient(searchText);
				getResultsWidget().update();
			}
		});

		if (cm.getProjectContext().isProjectOpen()) {
			cm.getProjectContext().showSearchResults();
		} else {
			// TODO
		}

		searchClient(searchText);
	}

	private void searchClient(String text) {
		String[] keys = parseKeys(text);

		if (cm.getProjectContext().isProjectOpen()) {
			Project project = getCurrentProject();

			results.addEntities(getMatching(project.getRequirements(), keys));
			results.addEntities(getMatching(project.getQualitys(), keys));
			results.addEntities(getMatching(project.getTasks(), keys));
			results.addEntities(getMatching(project.getWikipages(), keys));
			results.addEntities(getMatching(project.getIssues(), keys));
			results.addEntities(getMatching(project.getImpediments(), keys));
			results.addEntities(getMatching(project.getRisks(), keys));
		} else {
			// TODO
		}

	}

	private <T extends AScrumGwtEntity> List<T> getMatching(Collection<T> entities, String[] keys) {
		List<T> ret = new ArrayList<T>();
		for (T entity : entities) {
			if (matchesKeys(entity, keys)) ret.add(entity);
		}
		return ret;
	}

	private boolean matchesKeys(AScrumGwtEntity entity, String[] keys) {
		for (String key : keys) {
			if (!entity.matchesKey(key)) return false;
		}
		return true;
	}

	private String[] parseKeys(String text) {
		List<String> ret = new ArrayList<String>();
		char sep = ' ';
		int idx = text.indexOf(sep);
		while (idx > 0) {
			ret.add(text.substring(0, idx));
			text = text.substring(idx + 1);
			idx = text.indexOf(sep);
		}
		ret.add(text);
		return ret.toArray(new String[ret.size()]);
	}

	public SearchResults getResults() {
		return results;
	}

	public void onSearchResultsChanged() {
		getResultsWidget().update();
	}

	public SearchResultsWidget getResultsWidget() {
		if (resultsWidget == null) resultsWidget = new SearchResultsWidget();
		return resultsWidget;
	}

}