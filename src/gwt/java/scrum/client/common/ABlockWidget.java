package scrum.client.common;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for a block widget, which can be added to a <code>BlockWidgetList</code>.
 * 
 */
public abstract class ABlockWidget extends Composite {

	private SimplePanel panel;
	private boolean extended;

	/**
	 * Provide the content of the block. Depending on properties (ie. <code>isExtended()</code>) a
	 * different implementation can be provided.
	 */
	protected abstract Widget build();

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

	void rebuild() {
		Widget widget = build();
		panel.clear();
		panel.add(widget);
	}

	void setExtended(boolean extended) {
		if (this.extended == extended) return;
		this.extended = extended;
		rebuild();
	}

}
