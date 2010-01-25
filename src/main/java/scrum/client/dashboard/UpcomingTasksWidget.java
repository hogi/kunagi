package scrum.client.dashboard;

import java.util.List;

import scrum.client.ScrumJs;
import scrum.client.common.AScrumWidget;
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
		Project project = getCurrentProject();
		int maxTasks = project.getTeamMembers().size();
		if (maxTasks < 10) maxTasks = 10;
		StringBuilder sb = new StringBuilder();
		List<Task> tasks = project.getCurrentSprint().getUnclaimedTasks(true);
		if (!tasks.isEmpty()) {
			sb.append("<div class='UpcomingTasksWidget'>Next upcoming Tasks");
			sb.append("<ul>");
			Requirement req = null;
			int taskCount = 0;
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
			sb.append("</div>");
		}
		html.setHTML(sb.toString());
	}
}
