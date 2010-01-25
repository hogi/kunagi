package scrum.client.dashboard;

import java.util.List;

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
		sb.append("<ul class='HighestRisksWidget'>");

		List<Risk> risks = project.getHighestRisks(5);
		if (!risks.isEmpty()) {
			for (Risk risk : risks) {
				sb.append("<li>");
				sb.append(risk.toHtml());
				sb.append("</li>");
			}
		}

		sb.append("</ul>");
		html.setHTML(sb.toString());
	}
}
