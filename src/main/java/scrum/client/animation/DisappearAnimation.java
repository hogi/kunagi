package scrum.client.animation;

import scrum.client.common.ABlockWidget;

import com.google.gwt.user.client.ui.Widget;

public class DisappearAnimation extends ABlockWidgetAnimation {

	private int height;

	public DisappearAnimation(int height, ABlockWidget<?>... widgets) {
		super(widgets);
		this.height = height;
	}

	@Override
	protected void onComplete(Widget widget) {
		widget.getElement().getStyle().setProperty("height", "0px");
		widget.getElement().getStyle().setProperty("overflow", "auto");
		widget.getElement().getStyle().setProperty("visible", "false");
	}

	@Override
	protected void onInit(Widget widget) {}

	@Override
	protected void onStart(Widget widget) {
		widget.getElement().getStyle().setProperty("overflow", "hidden");
	}

	@Override
	protected void onUpdate(double progress, Widget widget) {
		widget.getElement().getStyle().setProperty("height", (int) ((1 - progress) * this.height) + "px");
	}

}

// $Log:$