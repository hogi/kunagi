package scrum.client.dashboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scrum.client.admin.User;
import scrum.client.common.AScrumWidget;
import scrum.client.issues.Issue;
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

		List<User> lazyUsers = new ArrayList<User>();
		for (User user : project.getTeamMembers()) {
			sb.append("<div class='TeamTasksWidget-user'>");

			List<Task> tasks = project.getClaimedTasks(user);
			List<Issue> issues = project.getClaimedBugs(user);
			if (tasks.isEmpty() && issues.isEmpty()) {
				lazyUsers.add(user);
				continue;
			}

			sb.append("<span style='color: ").append(user.getColor()).append(";'>");
			sb.append(user.getName());
			sb.append("</span> is working on");

			sb.append("<ul>");
			for (Issue issue : issues) {
				sb.append("<li>").append(issue.toHtml()).append("</li>");
			}
			List<Requirement> requirements = new ArrayList<Requirement>(getRequirements(tasks));
			Collections.sort(requirements, project.getRequirementsOrderComparator());
			for (Requirement req : requirements) {
				sb.append("<li>");
				sb.append(req.toHtml());
				sb.append("<ul>");
				List<Task> usersTasks = req.getClaimedTasks(user);
				Collections.sort(usersTasks, Task.NUMBER_COMPARATOR);
				for (Task task : usersTasks) {
					sb.append("<li>").append(task.toHtml()).append("</li>");
				}
				sb.append("</ul></li>");
			}
			sb.append("</ul></div>");
		}

		for (User user : lazyUsers) {
			sb.append("<div class='TeamTasksWidget-user'>");
			sb.append("<span style='color: ").append(user.getColor()).append(";'>");
			sb.append(user.getName());
			sb.append("</span> is working on <span style='color: red;'>nothing</span></div>");
		}

		sb.append("</div>");
		html.setHTML(sb.toString());
	}

	private Set<Requirement> getRequirements(List<Task> tasks) {
		Set<Requirement> reqs = new HashSet<Requirement>();
		for (Task task : tasks) {
			reqs.add(task.getRequirement());
		}
		return reqs;
	}

}
