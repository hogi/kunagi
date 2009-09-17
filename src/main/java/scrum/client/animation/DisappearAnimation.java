package scrum.client.animation;

import scrum.client.common.ABlockWidget;

public class DisappearAnimation extends ObservableAnimation {

	private ABlockWidget widget;
	private int height;

	public DisappearAnimation(ABlockWidget widget, int height) {
		this.widget = widget;
		this.height = height;
	}

	@Override
	protected void onStart() {
		this.widget.getElement().getStyle().setProperty("overflow", "hidden");

	}

	@Override
	protected void onComplete() {
		super.onComplete();
		this.widget.getElement().getStyle().setProperty("height", "0px");
		this.widget.getElement().getStyle().setProperty("overflow", "auto");
		this.widget.getElement().getStyle().setProperty("visible", "false");
		this.fireCompletionEvent();
	}

	@Override
	protected void onUpdate(double progress) {
		this.widget.getElement().getStyle().setProperty("height", (int) ((1 - progress) * this.height) + "px");
	}

}

// $Log:$