package scrum.client.dashboard;

import java.util.Collections;
import java.util.List;

import scrum.client.admin.User;
import scrum.client.common.AScrumWidget;
import scrum.client.issues.Issue;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class UrgentIssuesWidget extends AScrumWidget {

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

		List<Issue> issues = project.getUrgentIssues();
		Collections.sort(issues, project.getIssuesOrderComparator());
		if (issues.isEmpty()) {
			sb.append("No urgent issues.");
		} else {
			sb.append("<ul class='AcceptedIssuesWidget'>");
			for (Issue issue : issues) {
				sb.append("<li>");
				if (issue.isFixed()) {
					sb.append("Fixed by ");
				}
				User owner = issue.getOwner();
				if (owner != null) {
					sb.append("<span style='color: ").append(owner.getColor()).append(";'>");
					sb.append(owner.getName());
					sb.append("</span>: ");
				}
				sb.append(issue.toHtml());
				sb.append("</li>");
			}
			sb.append("</ul>");
		}

		html.setHTML(sb.toString());
	}
}
