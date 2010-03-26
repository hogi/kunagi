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

		List<Issue> criticalBugs = project.getUnclaimedBugs(2);
		Collections.sort(criticalBugs, project.getIssuesOrderComparator());
		if (!criticalBugs.isEmpty()) {
			sb.append("<span style='color: red; font-weight: bold;'>Unclaimed critical bugs</span>:");
			sb.append("<ul>");
			for (Issue issue : criticalBugs) {
				sb.append("<li>");
				sb.append(issue.toHtml());
				sb.append("</li>");
			}
			sb.append("</ul>");
		}

		List<Issue> severeBugs = project.getUnclaimedBugs(1);
		Collections.sort(severeBugs, project.getIssuesOrderComparator());
		if (!severeBugs.isEmpty()) {
			sb.append("<span style='color: red;'>Unclaimed severe bugs</span>:");
			sb.append("<ul>");
			for (Issue issue : severeBugs) {
				sb.append("<li>");
				sb.append(issue.toHtml());
				sb.append("</li>");
			}
			sb.append("</ul>");
		}

		int maxTasks = project.getTeamMembers().size() - criticalBugs.size() - severeBugs.size();
		int minTasks = 10 - criticalBugs.size();
		if (maxTasks < minTasks) maxTasks = minTasks;
		int taskCount = 0;
		List<Task> tasks = project.getCurrentSprint().getUnclaimedTasks(true);
		Collections.sort(tasks, Task.NUMBER_COMPARATOR);
		if (!tasks.isEmpty()) {
			sb.append("Next upcoming tasks:");
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

		if (taskCount < maxTasks) {
			List<Issue> normalBugs = project.getUnclaimedBugs(0);
			Collections.sort(normalBugs, project.getIssuesOrderComparator());
			if (!normalBugs.isEmpty()) {
				sb.append("<span style='color: black;'>Unclaimed normal bugs</span>:");
				sb.append("<ul>");
				for (Issue issue : normalBugs) {
					sb.append("<li>");
					sb.append(issue.toHtml());
					sb.append("</li>");
					taskCount++;
					if (taskCount == maxTasks) break;
				}
				sb.append("</ul>");
			}
		}

		if (taskCount < maxTasks) {
			List<Issue> minorBugs = project.getUnclaimedBugs(-1);
			Collections.sort(minorBugs, project.getIssuesOrderComparator());
			if (!minorBugs.isEmpty()) {
				sb.append("<span style='color: #555;'>Unclaimed minor bugs</span>:");
				sb.append("<ul>");
				for (Issue issue : minorBugs) {
					sb.append("<li>");
					sb.append(issue.toHtml());
					sb.append("</li>");
					taskCount++;
					if (taskCount == maxTasks) break;
				}
				sb.append("</ul>");
			}
		}

		sb.append("</div>");
		html.setHTML(sb.toString());
	}
}
