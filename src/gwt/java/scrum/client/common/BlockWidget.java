package scrum.client.common;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class BlockWidget extends Composite {

	private SimplePanel panel;
	private boolean extended;

	protected abstract Widget build();

	public BlockWidget() {
		panel = new SimplePanel();
		panel.setStyleName("BlockWidget");
		initWidget(panel);
	}

	public void rebuild() {
		Widget widget = build();
		panel.clear();
		panel.add(widget);
	}

	public boolean isExtended() {
		return extended;
	}

	public void setExtended(boolean extended) {
		if (this.extended == extended) return;
		this.extended = extended;
		rebuild();
	}

}
