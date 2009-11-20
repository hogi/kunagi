package scrum.client.dashboard;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scrum.client.ScrumJs;
import scrum.client.admin.User;
import scrum.client.common.AScrumWidget;
import scrum.client.project.Project;
import scrum.client.project.Requirement;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class TeamTasksWidget extends AScrumWidget {

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
		sb.append("<div class='TeamTasksWidget'>");
		for (User user : project.getTeamMembers()) {
			sb.append("<div class='TeamTasksWidget-user'>");
			sb.append("<span style='color: ").append(user.getColor()).append("; font-weight: bold;'>");
			sb.append(user.getName());
			sb.append("</span> is working on");
			List<Task> tasks = project.getClaimedTasks(user);
			if (tasks.isEmpty()) {
				sb.append(" <span style='color: red; font-weight: bold;'>nothing</span></div>");
				continue;
			}
			Set<Requirement> reqs = new HashSet<Requirement>();
			for (Task task : tasks) {
				reqs.add(task.getRequirement());
			}
			sb.append("<ul>");
			for (Requirement req : reqs) {
				sb.append("<li>");
				sb.append(ScrumJs.createShowEntityByReferenceLink(req.getReference()));
				sb.append(" ").append(req.getLabel());
				sb.append("<ul>");
				for (Task task : req.getClaimedTasks(user)) {
					sb.append("<li>");
					sb.append(ScrumJs.createShowEntityByReferenceLink(task.getReference()));
					sb.append(" ").append(task.getLabel());
					sb.append("</li>");
				}
				sb.append("</ul></li>");
			}
			sb.append("</ul></div>");
		}
		sb.append("</div>");
		html.setHTML(sb.toString());
	}
}
