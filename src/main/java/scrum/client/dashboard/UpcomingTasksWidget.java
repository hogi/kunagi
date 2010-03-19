package scrum.client.dashboard;

import java.util.Collections;
import java.util.List;

import scrum.client.ScrumJs;
import scrum.client.common.AScrumWidget;
import scrum.client.issues.Issue;
import scrum.client.project.Project;
import scrum.client.project.Requirement;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class UpcomingTasksWidget extends AScrumWidget {

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

		List<Issue> issues = project.getUnclaimedUrgentIssues();
		Collections.sort(issues, project.getIssuesOrderComparator());
		if (issues.isEmpty()) {
			sb.append("No urgent issues.");
		} else {
			sb.append("Unclaimed urgent issues:");
			sb.append("<ul>");
			for (Issue issue : issues) {
				sb.append("<li>");
				sb.append(issue.toHtml());
				sb.append("</li>");
			}
			sb.append("</ul>");
		}

		int maxTasks = project.getTeamMembers().size() - issues.size();
		int minTasks = 10 - issues.size();
		if (maxTasks < minTasks) maxTasks = minTasks;
		List<Task> tasks = project.getCurrentSprint().getUnclaimedTasks(true);
		Collections.sort(tasks, Task.NUMBER_COMPARATOR);
		if (!tasks.isEmpty()) {
			int taskCount = 0;
			sb.append("Next upcoming Tasks:");
			sb.append("<ul>");
			Requirement req = null;
			for (Task task : tasks) {
				if (!task.isRequirement(req)) {
					if (taskCount > 0) break;
					boolean first = req != null;
					if (first) sb.append("</ul></li>");
					req = task.getRequirement();
					sb.append("<li>");
					sb.append(ScrumJs.createShowEntityByReferenceLink(req.getReference()));
					sb.append(" ").append(req.getLabel());
					sb.append("<ul>");
				}
				sb.append("<li>");
				sb.append(ScrumJs.createShowEntityByReferenceLink(task.getReference()));
				sb.append(" ").append(task.getLabel());
				sb.append("</li>");
				taskCount++;
				if (taskCount == maxTasks) break;
			}
			sb.append("</ul></li>");
			sb.append("</ul></div>");
		}

		sb.append("</div>");
		html.setHTML(sb.toString());
	}
}
