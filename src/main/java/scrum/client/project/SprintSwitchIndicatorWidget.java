package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.TimePeriod;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SprintSwitchIndicatorWidget extends AWidget {

	private Label label;

	protected Widget onInitialization() {
		label = new Label("Estimated Sprint Switch");
		label.setStyleName("SprintBorderIndicatorWidget");
		return label;
	}

	public void updateLabel(int sprints, TimePeriod period) {
		initialize();
		String s = String.valueOf(sprints);
		if (sprints == 1) {
			s = "current";
		} else if (sprints == 2) {
			s = "next";
		}
		label.setText("After " + s + " sprint" + (sprints <= 2 ? "" : "s")
				+ (period.toMillis() <= 0 ? "" : ", in " + period.toShortestString()));
	}

}
