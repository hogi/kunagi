package scrum.client;

import java.util.List;
import java.util.Map;

import scrum.client.common.AScrumGwtEntity;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SearchResultsWidget extends AScrumWidget {

	private SearchResults searchResults;

	@Override
	protected Widget onInitialization() {
		searchResults = cm.getSearch().getResults();
		return empty();
	}

	@Override
	protected void onUpdate() {
		if (searchResults.isEmpty()) {
			replaceContent(empty());
			return;
		}
		PagePanel page = new PagePanel();

		for (Map.Entry<String, List<AScrumGwtEntity>> entry : searchResults.getEntitiesGrouped().entrySet()) {
			String title = entry.getKey();
			page.addHeader(title);
			StringBuilder html = new StringBuilder();
			html.append("<ul>");
			for (AScrumGwtEntity entity : entry.getValue()) {
				html.append("<li>");
				html.append(entity.toString());
				html.append("</li>");
			}
			html.append("</html>");
			page.add(new HTML(html.toString()));
		}

		replaceContent(page);
	}

	private PagePanel empty() {
		return PagePanel.createSimple("Search Results", new Label("Nothing matches your input."));
	}

}
