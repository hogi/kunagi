package scrum.client.workspace;

import ilarkesto.core.scope.Scope;
import scrum.client.common.AScrumWidget;
import scrum.client.core.ServiceCaller;
import scrum.client.test.ScrumStatusWidget;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CommunicationIndicatorWidget extends AScrumWidget {

	private ServiceCaller serviceCaller;

	private FocusPanel focusPanel;
	private Label status;
	private Style statusStyle;

	private long onTime;

	@Override
	protected Widget onInitialization() {
		serviceCaller = Scope.get().getComponent(ServiceCaller.class);
		serviceCaller.setStatusWidget(this);

		status = new Label();
		status.setStyleName("StatusWidget");
		statusStyle = status.getElement().getStyle();

		focusPanel = new FocusPanel(status);
		focusPanel.addClickHandler(new StatusClickHandler());

		return focusPanel;
	}

	@Override
	protected void onUpdate() {
		int count = serviceCaller.getActiveServiceCallCount();
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

	class StatusClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			Scope.get().getComponent(Ui.class).getWorkspace().getWorkarea().show(new ScrumStatusWidget());
			focusPanel.setFocus(false);
		}

	}

}
