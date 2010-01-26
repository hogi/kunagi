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

	private Requirement requirement;

	public EstimationBarWidget(Requirement requirement) {
		this.requirement = requirement;
	}

	@Override
	protected void onUpdate() {
		EstimationBar bar = requirement == null ? null : requirement.getEstimationBar();
		if (bar == null) bar = new EstimationBar(0, new ArrayList<Float>());
		flowPanel.clear();
		List<Float> estimations = bar.getWorkPerSprint();
		int sprintOffset = bar.getSprintOffset();

		for (int i = 0; i < estimations.size(); i++) {

			int barIndex;
			if ((i + sprintOffset) % 2 == 0) {
				barIndex = 0;
			} else {
				barIndex = 1;
			}
			Widget w = Gwt.createEmptyDiv("EstimationBarWidget-bar" + barIndex);
			w.setHeight("6px");
			w.setWidth((factor * estimations.get(i)) + "px");
			flowPanel.add(w);
		}

		String tip;

		int requiredSprints = estimations.size() <= 1 ? sprintOffset + 1 : sprintOffset + estimations.size();
		tip = "Expected  to be completed after " + requiredSprints + " sprint" + (requiredSprints == 1 ? "." : "s.");

		flowPanel.getElement().setTitle(tip);
	}

	@Override
	protected Widget onInitialization() {
		flowPanel = new FloatingFlowPanel();
		return Gwt.createFloatingFlowPanelRight(Gwt.createDiv("EstimationBarWidget", flowPanel));
	}
}
