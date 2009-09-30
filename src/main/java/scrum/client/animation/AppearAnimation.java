package scrum.client.animation;

import scrum.client.common.ABlockWidget;

import com.google.gwt.user.client.ui.Widget;

public class AppearAnimation extends ABlockWidgetAnimation {

	private int height;
	private double delayFactor;

	public AppearAnimation(int height, ABlockWidget<?>... widgets) {
		this(0, height, widgets);
	}

	public AppearAnimation(double delayFactor, int height, ABlockWidget<?>... widgets) {
		super(widgets);
		this.delayFactor = delayFactor;
		this.height = height;
	}

	@Override
	protected void onInit(Widget widget) {
		widget.getElement().getStyle().setProperty("visible", "false");
		widget.getElement().getStyle().setProperty("height", "0px");
		widget.getElement().getStyle().setProperty("marginTop", "0px");
		widget.getElement().getStyle().setProperty("marginBottom", "0px");
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
	public void run(int duration) {
		super.run((int) (duration * this.delayFactor));
	}

	@Override
	protected void onUpdate(double progress, Widget widget) {
		progress *= this.delayFactor;
		progress -= (this.delayFactor - 1);
		if (progress <= 0) {
			progress = 0;
		}
		widget.getElement().getStyle().setProperty("height", (int) (progress * this.height) + "px");
	}

}

// $Log:$