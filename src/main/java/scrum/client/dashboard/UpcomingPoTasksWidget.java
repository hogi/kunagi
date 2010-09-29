package scrum.client.dashboard;

import java.util.Collections;
import java.util.List;

import scrum.client.common.AScrumWidget;
import scrum.client.issues.Issue;
import scrum.client.project.Project;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class UpcomingPoTasksWidget extends AScrumWidget {

	private HTML html;

	@Override
	protected Widget onInitialization() {
		html = new HTML();
		return html;
	}

	@Override
	protected void onUpdate() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='UpcomingTasksWidget'>");

		Project project = getCurrentProject();

		List<Issue> fixedBugs = project.getFixedBugs();
		Collections.sort(fixedBugs, project.getIssuesOrderComparator());
		if (!fixedBugs.isEmpty()) {
			sb.append("Fixed bugs to review:");
			sb.append("<ul>");
			for (Issue issue : fixedBugs) {
				sb.append("<li>");
				sb.append(issue.toHtml());
				sb.append("</li>");
			}
			sb.append("</ul>");
		}

		List<Requirement> decidableRequirements = project.getCurrentSprint().getDecidableUndecidedRequirements();
		Collections.sort(decidableRequirements, project.getRequirementsOrderComparator());
		if (!decidableRequirements.isEmpty()) {
			sb.append("Completed Stories to review:");
			sb.append("<ul>");
			for (Requirement requirement : decidableRequirements) {
				sb.append("<li>");
				sb.append(requirement.toHtml());
				sb.append("</li>");
			}
			sb.append("</ul>");
		}

		sb.append("</div>");
		html.setHTML(sb.toString());
	}
}
