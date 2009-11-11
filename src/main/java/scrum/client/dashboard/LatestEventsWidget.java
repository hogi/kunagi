package scrum.client.dashboard;

import java.util.List;

import scrum.client.common.AScrumWidget;
import scrum.client.journal.ProjectEvent;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class LatestEventsWidget extends AScrumWidget {

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
		sb.append("<div class='ProjectInfoWidget'>");

		List<ProjectEvent> events = project.getLatestEvents(5);
		if (!events.isEmpty()) {
			boolean first = true;
			for (ProjectEvent event : events) {
				if (first) {
					first = false;
				} else {
					sb.append("<br>");
				}
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append(" ").append(event.getLabel());
			}
		}

		sb.append("</div>");
		html.setHTML(sb.toString());
	}
}
