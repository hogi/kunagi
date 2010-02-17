package scrum.client.dashboard;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;

import java.util.List;

import scrum.client.admin.User;
import scrum.client.common.AScrumWidget;
import scrum.client.journal.ProjectEvent;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class LatestEventsWidget extends AScrumWidget {

	private SimplePanel wrapper;

	@Override
	protected Widget onInitialization() {
		wrapper = new SimplePanel();
		wrapper.setStyleName("LatestEventsWidget");
		return wrapper;
	}

	@Override
	protected void onUpdate() {
		Project project = getCurrentProject();
		List<ProjectEvent> events = project.getLatestProjectEvents(5);

		TableBuilder tb = new TableBuilder();
		tb.setColumnWidths("100px");
		if (!events.isEmpty()) {
			for (ProjectEvent event : events) {
				Widget timeWidget = Gwt.createDiv("LatestEventsWidget-time", event.getDateAndTime().getPeriodToNow()
						.toShortestString()
						+ " ago");
				String html = event.toHtml();
				html = colorUsers(html);
				Widget textWidget = new HTML(html);
				tb.addRow(timeWidget, textWidget);
			}
		}
		wrapper.setWidget(tb.createTable());
	}

	private String colorUsers(String html) {
		Project project = getCurrentProject();
		for (User user : project.getParticipants()) {
			html = html.replace(user.getName(), "<span style='color: "
					+ project.getUserConfig(user).getColor() + ";'>" + user.getName() + "</span>");
		}
		return html;
	}

}
