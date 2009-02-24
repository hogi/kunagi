package scrum.client.common;

import scrum.client.dnd.DndManager;
import scrum.client.workspace.ClipboardWidget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class ClipboardItemWidget extends Composite {

	private FlowPanel mainPanel;

	private HorizontalPanel panel;

	private Image icon;

	private ABlockWidget src;

	private ClipboardWidget clipboard;

	public ClipboardItemWidget(ABlockWidget src) {
		this.src = src;
		this.icon = src.getIcon16().createImage();

		mainPanel = new FlowPanel();
		mainPanel.setStyleName(StyleSheet.ELEMENT_BLOCK_WIDGET_MAIN);

		rebuild();

		initWidget(mainPanel);
		DndManager.get().getDragController().makeDraggable(this, this.icon);
	}

	protected void rebuild() {
		// mainPanel.remove(panel);

		panel = new HorizontalPanel();
		panel.add(icon);
		panel.add(new Label(src.getBlockTitle()));

		mainPanel.add(panel);
	}

	public void removeFromClipboard() {
		if (clipboard != null) clipboard.removeItem(this);
	}

	public void setClipboard(ClipboardWidget clipboard) {
		this.clipboard = clipboard;
	}

	public ABlockWidget getSource() {
		return src;
	}

}
