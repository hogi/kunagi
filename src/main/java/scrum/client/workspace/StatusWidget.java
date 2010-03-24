package scrum.client.workspace;

import ilarkesto.core.scope.Scope;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AScrumWidget;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class StatusWidget extends AScrumWidget {

	private ScrumGwtApplication app;
	private Label status;
	private Style statusStyle;
	private long onTime;

	@Override
	protected Widget onInitialization() {
		app = (ScrumGwtApplication) Scope.get().getComponent("app");
		app.setStatusWidget(this);

		status = new Label();
		status.setStyleName("StatusWidget");
		statusStyle = status.getElement().getStyle();

		return status;
	}

	@Override
	protected void onUpdate() {
		int count = app.getActiveServiceCallCount();
		if (count > 0) {
			if (!isOn()) switchOn();
		} else {
			if (isOn()) switchOff();
		}
	}

	private void switchOn() {
		onTime = System.currentTimeMillis();
		statusStyle.setBackgroundColor("#aa6");
	}

	private void switchOff() {
		onTime = 0;
		statusStyle.setBackgroundColor("#666");
	}

	private boolean isOn() {
		return onTime > 0;
	}
}
