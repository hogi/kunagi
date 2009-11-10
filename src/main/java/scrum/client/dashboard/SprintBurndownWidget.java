package scrum.client.dashboard;

import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class SprintBurndownWidget extends AScrumWidget {

	public static final int CHART_WIDTH = 800;
	public static final int CHART_HEIGHT = 270;

	private Image sprintChart;

	@Override
	protected Widget onInitialization() {
		sprintChart = new Image(getChartUrl(), 0, 0, CHART_WIDTH, CHART_HEIGHT);
		return sprintChart;
	}

	@Override
	protected void onUpdate() {
		sprintChart.setUrl(getChartUrl());
	}

	private String getChartUrl() {
		int width = Window.getClientWidth() - 280;
		width = width / 2;
		return getCurrentSprint().getChartUrl(width, CHART_HEIGHT);
	}
}
