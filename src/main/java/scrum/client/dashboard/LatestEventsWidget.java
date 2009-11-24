package scrum.client.dashboard;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;

import java.util.List;

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
				Widget textWidget = new HTML(cm.getWiki().richtextToHtml(event.getLabel()));
				tb.addRow(timeWidget, textWidget);
			}
		}
		wrapper.setWidget(tb.createTable());
	}
}
