package scrum.client.project;

import ilarkesto.gwt.client.FloatingFlowPanel;
import ilarkesto.gwt.client.Gwt;

import java.util.ArrayList;
import java.util.List;

import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class EstimationBarWidget extends AScrumWidget {

	private FloatingFlowPanel flowPanel;
	private static int factor = 3;

	private List<Integer> getEstimations() {
		List<Integer> estimations = new ArrayList<Integer>();
		estimations.add(8);
		estimations.add(27);
		return estimations;
	}

	private int getFirstExpectedSprint() {
		return 4;
	}

	@Override
	protected Widget onInitialization() {
		flowPanel = new FloatingFlowPanel();
		List<Integer> estimations = getEstimations();
		int first = getFirstExpectedSprint();

		for (int i = 0; i < estimations.size(); i++) {

			String bar;
			if ((i + first) % 2 == 0) {
				bar = "bar0";
			} else {
				bar = "bar1";
			}
			Widget w = Gwt.createEmptyDiv("EstimationBarWidget-" + bar);
			w.setWidth((factor * estimations.get(i)) + "px");
			flowPanel.add(w);
		}

		String tip;

		if (estimations.size() <= 1) {
			if (first == 0) {
				tip = "estimated this Sprint";
			} else if (first == 1) {
				tip = "estimated next Sprint";
			} else {
				tip = "estimated in " + first + " Sprints";
			}
		} else {
			tip = "estimated in " + first + " to " + (first + estimations.size() - 1) + " Sprints";
		}

		flowPanel.getElement().setTitle(tip);

		return flowPanel;
	}
}
