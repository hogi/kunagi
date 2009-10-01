package scrum.client.animation;

import ilarkesto.gwt.client.animation.AObservableAnimation;
import scrum.client.common.ABlockWidget;

import com.google.gwt.user.client.ui.Widget;

public abstract class ABlockWidgetAnimation extends AObservableAnimation {

	private ABlockWidget<?>[] widgets;

	public ABlockWidgetAnimation(ABlockWidget<?>... widgets) {
		this.widgets = widgets;
		for (Widget widget : this.widgets) {
			onInit(widget);
		}
	}

	protected abstract void onInit(Widget widget);

	@Override
	protected final void onStart() {
		for (Widget widget : this.widgets) {
			onStart(widget);
		}
	}

	protected abstract void onStart(Widget widget);

	@Override
	protected final void onComplete() {
		super.onComplete();
		for (Widget widget : this.widgets) {
			onComplete(widget);
		}
		this.fireCompletionEvent();
	}

	protected abstract void onComplete(Widget widget);

	@Override
	protected final void onUpdate(double progress) {
		for (Widget widget : this.widgets) {
			onUpdate(progress, widget);
		}
	}

	protected abstract void onUpdate(double progress, Widget widget);

}