package scrum.client.dnd;

import scrum.client.common.StyleSheet;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class BlockListDndMarkerWidget extends Composite {

	private SimplePanel panel;

	public BlockListDndMarkerWidget() {
		panel = new SimplePanel();
		panel.setStyleName(StyleSheet.ELEMENT_DND_MARKER_WIDGET);

		initWidget(panel);
	}

	public void setActive(boolean active) {
		panel.setStyleName(active ? StyleSheet.ELEMENT_DND_MARKER_WIDGET_ACTIVE : StyleSheet.ELEMENT_DND_MARKER_WIDGET);
	}

}
