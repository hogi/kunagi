package scrum.client.animation;

import scrum.client.common.ABlockWidget;

public class AppearAnimation extends ObservableAnimation {

	private ABlockWidget widget;
	private int height;

	public AppearAnimation(ABlockWidget widget, int height) {
		this.widget = widget;
		this.height = height;
		this.widget.getElement().getStyle().setProperty("visible", "false");
		this.widget.getElement().getStyle().setProperty("height", "0px");
	}

	@Override
	protected void onStart() {
		// this.widget.getElement().getStyle().setProperty("height", "auto");
		// this.height = this.widget.getOffsetHeight();
		// this.widget.getElement().getStyle().setProperty("height", "0px");
		this.widget.getElement().getStyle().setProperty("overflow", "hidden");
		this.widget.getElement().getStyle().setProperty("visible", "true");
	}

	@Override
	protected void onComplete() {
		super.onComplete();
		this.widget.getElement().getStyle().setProperty("height", "auto");
		this.widget.getElement().getStyle().setProperty("overflow", "auto");
		this.fireCompletionEvent();
	}

	@Override
	protected void onUpdate(double progress) {
		this.widget.getElement().getStyle().setProperty("height", (int) (progress * this.height) + "px");
	}

}

// $Log:$