package scrum.client.project;

import scrum.client.Client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

public class ProjectSummaryWidget extends Composite {

	public ProjectSummaryWidget() {
		Project p = Client.project;
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>").append(p.label).append("</h3>");
		sb.append("<em>product owner: </em><strong>").append(p.getOwner().name).append("</strong><br>");
		sb.append("<em>scrum master: </em><strong>").append(Client.project.getMaster().name).append("</strong>");
		initWidget(new HTML(sb.toString()));
	}
}
