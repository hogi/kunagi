package scrum.client.animation;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.animation.client.Animation;

public abstract class AObservableAnimation extends Animation {

	private List<CompletionListener> listeners = new ArrayList<CompletionListener>();

	public void addCompletionListener(CompletionListener l) {
		this.listeners.add(l);
	}

	public void fireCompletionEvent() {
		for (CompletionListener element : this.listeners) {
			element.completionEvent(this);
		}
	}

}
