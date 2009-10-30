package scrum.client.project;

import ilarkesto.gwt.client.AWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SprintBorderIndicatorWidget extends AWidget {

	protected Widget onInitialization() {
		Label label = new Label("Estimated Sprint Switch");
		label.setStyleName("SprintBorderIndicatorWidget");
		return label;
	}

}
