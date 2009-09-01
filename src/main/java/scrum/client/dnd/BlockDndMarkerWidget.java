package scrum.client.dnd;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class BlockDndMarkerWidget extends Composite {

	private SimplePanel panel;
	private boolean active;

	public BlockDndMarkerWidget() {
		panel = new SimplePanel();
		panel.setStyleName("BlockDndMarkerWidget");

		initWidget(panel);
	}

	public void setActive(boolean active) {
		if (this.active == active) return;
		this.active = active;
		panel.setStyleName(active ? "BlockDndMarkerWidget-active" : "BlockDndMarkerWidget");
	}

}
