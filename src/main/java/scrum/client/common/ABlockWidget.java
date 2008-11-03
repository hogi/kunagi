package scrum.client.common;

import scrum.client.ScrumGwtApplication;
import scrum.client.img.Img;
import scrum.client.service.StyleSheet;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for a block widget, which can be added to a <code>BlockWidgetList</code>.
 * 
 */
public abstract class ABlockWidget extends Composite {

	private SimplePanel panel;
	private boolean extended;
	private boolean inClipboard;
	private Image dragHandle;
	private DropController dropController = getDropController();

	/**
	 * Provide the content of the block. Depending on properties (ie. <code>isExtended()</code>) a different
	 * implementation can be provided.
	 */
	protected abstract Widget buildContent();

	protected abstract Widget buildToolbar();

	protected abstract String getBlockTitle();

	protected abstract AbstractImagePrototype getIcon();

	protected abstract DropController getDropController();

	public abstract void delete();

	public ABlockWidget() {
		panel = new SimplePanel();
		panel.setStyleName(StyleSheet.BLOCK_WIDGET);
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
		title.setStyleName(StyleSheet.BLOCK_TITLE);

		VerticalPanel center = new VerticalPanel();
		center.setWidth("100%");
		center.add(title);
		if (!inClipboard) {
			center.add(buildContent());
		}

		HorizontalPanel block = new HorizontalPanel();
		block.setStyleName(StyleSheet.BLOCK_BLOCK);
		block.setWidth("100%");

		if (dragHandle != null) block.add(dragHandle);

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

	public void makeDraggable() {
		if (dragHandle == null) {
			dragHandle = Img.icons().dragHandleIcon32().createImage();
			dragHandle.setStyleName(StyleSheet.DRAG_HANDLE);
		}
		ScrumGwtApplication.getDragController().makeDraggable(this, dragHandle);
	}

	public Image getDragHandle() {
		return dragHandle;
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		ScrumGwtApplication.getDragController().registerDropController(dropController);
	}

	@Override
	protected void onDetach() {
		ScrumGwtApplication.getDragController().unregisterDropController(dropController);
		super.onDetach();
	}
}
