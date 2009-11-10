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
		sprintChart = new Image();
		return sprintChart;
	}

	@Override
	protected void onUpdate() {
		int width = getChartWidth();
		sprintChart.setWidth(width + "px");
		sprintChart.setUrl(getChartUrl(width));
	}

	private String getChartUrl(int width) {
		return getCurrentSprint().getChartUrl(sprintChart.getWidth(), CHART_HEIGHT);
	}

	private int getChartWidth() {
		int width = Window.getClientWidth() - 280;
		width = width / 2;
		return width;
	}
}
