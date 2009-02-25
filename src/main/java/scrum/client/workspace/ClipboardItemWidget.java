package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.dnd.DndManager;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ClipboardItemWidget extends AWidget {

	private HorizontalPanel panel;

	private Image icon;

	private ABlockWidget src;

	private ClipboardWidget clipboard;

	public ClipboardItemWidget(ABlockWidget src) {
		this.src = src;
		this.icon = src.getIcon16().createImage();
	}

	@Override
	protected Widget onInitialization() {

		panel = new HorizontalPanel();
		panel.setWidth("100%");
		panel.setSpacing(5);
		panel.setStyleName("ClipboardItemWidget");
		panel.add(icon);
		panel.setCellWidth(icon, "1%");
		panel.add(new Label(src.getBlockTitle()));

		DndManager.get().getDragController().makeDraggable(this, this.icon);
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

	public ABlockWidget getSource() {
		return src;
	}

}
