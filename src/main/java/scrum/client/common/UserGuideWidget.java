package scrum.client.common;

import ilarkesto.gwt.client.AAction;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.HyperlinkWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class UserGuideWidget extends AScrumWidget {

	private boolean enabled;
	private String text;

	private FlowPanel panel;

	public UserGuideWidget(boolean enabled, String text) {
		super();
		this.enabled = enabled;
		this.text = text;
	}

	@Override
	protected Widget onInitialization() {
		AAction toggleAction = enabled ? new DeactivateAction() : new ActivateAction();

		panel = new FlowPanel();
		panel.setStyleName("UserGuideWidget");
		panel.add(Gwt.createDiv("UserGuideWidget-header", new HyperlinkWidget(toggleAction)));
		if (enabled) {
			panel.add(Gwt.createDiv("UserGuideWidget-content", new HTML(text)));
		}

		return panel;
	}

	private class ActivateAction extends AScrumAction {

		@Override
		public String getLabel() {
			return "User Guide";
		}

		@Override
		protected void onExecute() {
			enabled = true;
			reset();
		}

	}

	private class DeactivateAction extends AScrumAction {

		@Override
		public String getLabel() {
			return "User Guide";
		}

		@Override
		protected void onExecute() {
			enabled = false;
			reset();
		}

	}
}
