package scrum.client.project;

import scrum.client.service.Service;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

public class ProjectSummaryWidget extends Composite {

	public ProjectSummaryWidget() {
		Project p = Service.getProject();
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>").append(p.getLabel()).append("</h3>");
		sb.append("<em>product owner: </em><strong>").append(p.getOwner().getName()).append("</strong><br>");
		sb.append("<em>scrum master: </em><strong>").append(Service.getProject().getMaster().getName()).append(
			"</strong>");
		initWidget(new HTML(sb.toString()));
	}
}
