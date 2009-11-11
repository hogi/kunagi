package scrum.client.dashboard;

import java.util.List;

import scrum.client.ScrumJs;
import scrum.client.common.AScrumWidget;
import scrum.client.project.Project;
import scrum.client.risks.Risk;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class HighestRisksWidget extends AScrumWidget {

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

		List<Risk> risks = project.getHighestRisks(5);
		if (!risks.isEmpty()) {
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

		sb.append("</div>");
		html.setHTML(sb.toString());
	}
}
