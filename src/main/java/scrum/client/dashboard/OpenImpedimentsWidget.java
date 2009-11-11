package scrum.client.dashboard;

import java.util.List;

import scrum.client.ScrumJs;
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
		sb.append("<div class='ProjectInfoWidget'>");

		List<Impediment> impediments = project.getOpenImpediments();
		if (!impediments.isEmpty()) {
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

		sb.append("</div>");
		html.setHTML(sb.toString());
	}
}
