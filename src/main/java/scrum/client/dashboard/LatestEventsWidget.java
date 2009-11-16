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
		sb.append("<ul class='LatestEventsWidget'>");

		List<ProjectEvent> events = project.getLatestEvents(5);
		if (!events.isEmpty()) {
			for (ProjectEvent event : events) {
				sb.append("<li>");
				sb.append(cm.getWiki().richtextToHtml(event.getLabel()));
				sb.append("</li>");
			}
		}

		sb.append("</ul>");
		html.setHTML(sb.toString());
	}
}
