package scrum.client.dashboard;

import java.util.List;

import scrum.client.ScrumJs;
import scrum.client.common.AScrumWidget;
import scrum.client.impediments.Impediment;
import scrum.client.journal.ProjectEvent;
import scrum.client.project.Project;
import scrum.client.risks.Risk;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ProjectInfoWidget extends AScrumWidget {

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

		List<Impediment> impediments = project.getOpenImpediments();
		if (!impediments.isEmpty()) {
			sb.append("<h2>Impediments</h2>");
			boolean first = true;
			for (Impediment impediment : impediments) {
				if (first) {
					first = false;
				} else {
					sb.append("<br>");
				}
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append(ScrumJs.createShowEntityByReferenceLink(impediment.getReference()));
				sb.append(" ").append(impediment.getLabel());
			}
		}

		List<Risk> risks = project.getHighestRisks(5);
		if (!risks.isEmpty()) {
			sb.append("<br><br><h2>Highest Risks</h2>");
			boolean first = true;
			for (Risk risk : risks) {
				if (first) {
					first = false;
				} else {
					sb.append("<br>");
				}
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append(ScrumJs.createShowEntityByReferenceLink(risk.getReference()));
				sb.append(" ").append(risk.getLabel());
			}
		}

		List<ProjectEvent> events = project.getLatestEvents(5);
		if (!risks.isEmpty()) {
			sb.append("<br><br><h2>Latest Events</h2>");
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
