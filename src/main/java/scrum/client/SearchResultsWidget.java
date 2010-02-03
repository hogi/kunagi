package scrum.client;

import java.util.List;
import java.util.Map;

import scrum.client.collaboration.Wikipage;
import scrum.client.common.AScrumGwtEntity;
import scrum.client.common.AScrumWidget;
import scrum.client.files.File;
import scrum.client.impediments.Impediment;
import scrum.client.issues.Issue;
import scrum.client.project.Quality;
import scrum.client.project.Requirement;
import scrum.client.risks.Risk;
import scrum.client.sprint.Task;
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
			String title = getTitle(entry.getKey());
			page.addHeader(title);
			StringBuilder html = new StringBuilder();
			html.append("<ul>");
			for (AScrumGwtEntity entity : entry.getValue()) {
				html.append("<li>");
				html.append(entity.toHtml());
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

	private String getTitle(String key) {
		if (key.equals(Requirement.class.getName())) {
			return "Stories";
		} else if (key.equals(Quality.class.getName())) {
			return "Qualities";
		} else if (key.equals(Task.class.getName())) {
			return "Tasks";
		} else if (key.equals(Wikipage.class.getName())) {
			return "Wiki Pages";
		} else if (key.equals(Issue.class.getName())) {
			return "Issues";
		} else if (key.equals(Impediment.class.getName())) {
			return "Impediments";
		} else if (key.equals(File.class.getName())) {
			return "Files";
		} else if (key.equals(Risk.class.getName())) { return "Risks"; }
		return key;
	}

}
