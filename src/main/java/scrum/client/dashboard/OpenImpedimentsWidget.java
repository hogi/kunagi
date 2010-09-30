package scrum.client.dashboard;

import java.util.List;

import scrum.client.common.AScrumWidget;
import scrum.client.impediments.Impediment;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class OpenImpedimentsWidget extends AScrumWidget {

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
		sb.append("<ul class='OpenImpedimentsWidget'>");

		List<Impediment> impediments = project.getOpenImpediments();
		if (!impediments.isEmpty()) {
			for (Impediment impediment : impediments) {
				sb.append("<li>");
				boolean blocking = impediment.isBlockingTasksFromCurrentSprint();
				if (blocking) sb.append("<span style='color: red;'>");
				sb.append(impediment.toHtml());
				if (blocking) sb.append("</span>");
				sb.append("</li>");
			}
		}

		sb.append("</ul>");
		html.setHTML(sb.toString());
	}
}
