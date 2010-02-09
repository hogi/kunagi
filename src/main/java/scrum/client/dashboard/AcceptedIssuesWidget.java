package scrum.client.dashboard;

import java.util.List;

import scrum.client.common.AScrumWidget;
import scrum.client.issues.Issue;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class AcceptedIssuesWidget extends AScrumWidget {

	private HTML html;

	@Override
	protected Widget onInitialization() {
		html = new HTML();
		return html;
	}

	@Override
	protected void onUpdate() {
		Project project = getCurrentProject();
		StringBuilder sb = new StringBuilder();

		List<Issue> issues = project.getAcceptedIssues();
		if (issues.isEmpty()) {
			sb.append("No accepted issues.");
		} else {
			sb.append("<ul class='AcceptedIssuesWidget'>");
			for (Issue impediment : issues) {
				sb.append("<li>");
				sb.append(impediment.toHtml());
				sb.append("</li>");
			}
			sb.append("</ul>");
		}

		html.setHTML(sb.toString());
	}
}
