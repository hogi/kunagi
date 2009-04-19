package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TaskOverviewWidget extends AWidget {

	@Override
	protected Widget onInitialization() {
		return new Label("tasks");
	}

	@Override
	protected void onUpdate() {}

}
