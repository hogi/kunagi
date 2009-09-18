package scrum.client.animation;

import scrum.client.common.ABlockWidget;

import com.google.gwt.user.client.ui.Widget;

public class AppearAnimation extends ABlockWidgetAnimation {

	private int height;
	
	public AppearAnimation(int height, ABlockWidget<?>... widgets) {
		super(widgets);
		this.height = height;
	}

	@Override
	protected void onInit(Widget widget) {
		widget.getElement().getStyle().setProperty("visible", "false");
		widget.getElement().getStyle().setProperty("height", "0px");
	}

	@Override
	protected void onStart(Widget widget) {
		widget.getElement().getStyle().setProperty("overflow", "hidden");
		widget.getElement().getStyle().setProperty("visible", "true");
	}

	@Override
	protected void onComplete(Widget widget) {
		widget.getElement().getStyle().setProperty("height", "auto");
		widget.getElement().getStyle().setProperty("overflow", "auto");
	}

	@Override
	protected void onUpdate(double progress, Widget widget) {
		widget.getElement().getStyle().setProperty("height", (int) (progress * this.height) + "px");
	}

}

// $Log:$