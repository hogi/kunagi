package scrum.client.core;

import scrum.client.common.AScrumWidget;

public class ServiceCaller extends GServiceCaller {

	private int activeServiceCallCount;
	private AScrumWidget statusWidget;

	public void onServiceCall() {
		activeServiceCallCount++;
		if (statusWidget != null) statusWidget.update();
	}

	public void onServiceCallReturn() {
		activeServiceCallCount--;
		if (activeServiceCallCount < 0) activeServiceCallCount = 0;
		if (statusWidget != null) statusWidget.update();
	}

	public int getActiveServiceCallCount() {
		return activeServiceCallCount;
	}

	public void setStatusWidget(AScrumWidget statusWidget) {
		this.statusWidget = statusWidget;
	}

}
