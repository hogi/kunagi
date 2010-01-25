package scrum.client.workspace;

import scrum.client.DndManager;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.dnd.ClipboardSupport;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ClipboardItemWidget extends AScrumWidget {

	private DndManager dndManager = cm.getDndManager();

	private HorizontalPanel panel;
	private Image icon;
	private ClipboardSupport clipboardSupport;
	private ClipboardWidget clipboard;

	public ClipboardItemWidget(ClipboardSupport clipboardSupport) {
		this.clipboardSupport = clipboardSupport;
		this.icon = clipboardSupport.getClipboardIcon();
	}

	@Override
	protected Widget onInitialization() {

		panel = new HorizontalPanel();
		panel.setWidth("100%");
		panel.setSpacing(5);
		panel.setStyleName("ClipboardItemWidget");
		panel.add(icon);
		panel.setCellWidth(icon, "1%");
		panel.add(new Label(clipboardSupport.getClipboardLabel()));

		dndManager.makeDraggable(this, icon);
		return panel;
	}

	@Override
	protected void onUpdate() {}

	public void removeFromClipboard() {
		if (clipboard != null) clipboard.removeItem(this);
	}

	public void setClipboard(ClipboardWidget clipboard) {
		this.clipboard = clipboard;
	}

	public ABlockWidget getPayload() {
		return clipboardSupport.getClipboardPayload();
	}

}
