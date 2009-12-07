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

	public void search(String text) {
		log.info("Searching:", text);
		results.clear();
		if (Gwt.isEmpty(text)) return;

		cm.getApp().callSearch(text);

		// TODO show result view

		searchClient(text);
	}

	private void searchClient(String text) {
		String[] keys = parseKeys(text);
		Project project = getCurrentProject();

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
		return new String[] { text }; // TODO tokenize
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