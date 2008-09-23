package scrum.client.common;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.MouseListenerCollection;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SourcesMouseEvents;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for a block widget, which can be added to a <code>BlockWidgetList</code>.
 * 
 */
public abstract class ABlockWidget extends Composite implements SourcesMouseEvents {

	private SimplePanel panel;
	private boolean extended;
	private boolean inClipboard;

	/**
	 * Provide the content of the block. Depending on properties (ie. <code>isExtended()</code>) a
	 * different implementation can be provided.
	 */
	protected abstract Widget buildContent();

	protected abstract Widget buildToolbar();

	protected abstract String getBlockTitle();

	protected abstract AbstractImagePrototype getIcon();

	public ABlockWidget() {
		panel = new SimplePanel();
		panel.setStyleName("BlockWidget");
		initWidget(panel);
	}

	/**
	 * Indicates if the bock is in extended-mode. This method should by called whithin the
	 * <code>build()</code>-method.
	 */
	public boolean isExtended() {
		return extended;
	}

	public boolean isInClipboard() {
		return inClipboard;
	}

	void setInClipboard(boolean inClipboard) {
		this.inClipboard = inClipboard;
	}

	protected void rebuild() {
		panel.setWidget(build());
	}

	void setExtended(boolean extended) {
		if (this.extended == extended) return;
		this.extended = extended;
		rebuild();
	}

	protected Widget build() {
		Label title = new Label(getBlockTitle());
		title.setStyleName("Block-title");

		VerticalPanel center = new VerticalPanel();
		center.setWidth("100%");
		center.add(title);
		if (!inClipboard) {
			center.add(buildContent());
		}

		HorizontalPanel block = new HorizontalPanel();
		block.setStyleName("Block-block");
		block.setWidth("100%");

		AbstractImagePrototype icon = getIcon();
		if (icon != null) block.add(icon.createImage());

		block.add(center);
		block.setCellWidth(center, "99%");

		Widget toolbar = buildToolbar();
		if (toolbar != null) {
			block.add(toolbar);
		}

		return block;
	}

	// --- dnd-related ---
	MouseListenerCollection mouseListeners;

	public void addMouseListener(MouseListener listener) {
		if (mouseListeners == null) {
			mouseListeners = new MouseListenerCollection();
			sinkEvents(Event.MOUSEEVENTS);
		}
		mouseListeners.add(listener);
	}

	public void removeMouseListener(MouseListener listener) {
		if (mouseListeners != null) {
			mouseListeners.remove(listener);
		}
	}

	@Override
	public void onBrowserEvent(Event event) {
		switch (DOM.eventGetType(event)) {
			case Event.ONMOUSEDOWN:
			case Event.ONMOUSEUP:
			case Event.ONMOUSEMOVE:
			case Event.ONMOUSEOVER:
			case Event.ONMOUSEOUT:
				mouseListeners.fireMouseEvent(this, event);
				break;
		}
	}
}
