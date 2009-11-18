package scrum.client.calendar;

import ilarkesto.gwt.client.HyperlinkWidget;
import ilarkesto.gwt.client.TableBuilder;

import java.util.List;

import scrum.client.common.AScrumAction;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class ADateSelectorWidget extends AScrumWidget {

	protected abstract boolean isSelected();

	protected abstract AScrumAction getAction();

	protected abstract List<SimpleEvent> getEvents();

	@Override
	protected final Widget onInitialization() {

		TableBuilder tb = new TableBuilder();
		tb.setCellPadding(2);
		tb.add(new HyperlinkWidget(getAction()));

		List<SimpleEvent> events = getEvents();

		int eventCount = events.size();

		tb.nextRow();
		Label eventLabel;
		if (eventCount > 0) {
			eventLabel = new Label("" + events.size());
		} else {
			eventLabel = new Label(" ");
		}

		eventLabel.setStyleName("DateSelectorWidget-events");
		tb.add(eventLabel);

		Widget panel = tb.createTable();

		if (isSelected()) {
			panel.setStyleName("DateSelectorWidget-selected");
		} else {
			panel.setStyleName("DateSelectorWidget");
		}
		return panel;
	}

}
