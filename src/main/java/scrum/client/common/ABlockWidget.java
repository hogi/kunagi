package scrum.client.common;

import scrum.client.ScrumGwtApplication;
import scrum.client.img.Img;

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

	private BlockListWidget list;
	private SimplePanel panel;
	private boolean extended;
	private boolean inClipboard;
	private Image dragHandle;
	private DropController dropController;
	protected BlockListController controller = new BlockListController<ABlockWidget>();

	/**
	 * Provide the content of the block. Depending on properties (ie. <code>isExtended()</code>) a different
	 * implementation can be provided.
	 */
	protected abstract Widget buildContent();

	protected abstract Widget buildToolbar();

	protected abstract String getBlockTitle();

	protected abstract AbstractImagePrototype getIcon();

	public abstract void delete();

	public ABlockWidget() {
		panel = new SimplePanel();
		panel.setStyleName(StyleSheet.ELEMENT_BLOCK_WIDGET);
		dropController = createDropController();
		initWidget(panel);
	}

	protected DropController createDropController() {
		return null;
	}

	public final BlockListWidget getList() {
		return list;
	}

	final void setList(BlockListWidget list) {
		this.list = list;
	}

	public final boolean isDropSupported() {
		return dropController != null;
	}

	public void setListController(BlockListController listController) {
		this.controller = listController;
	}

	/**
	 * Indicates if the bock is in extended-mode. This method should by called whithin the
	 * <code>build()</code>-method.
	 */
	public final boolean isExtended() {
		return extended;
	}

	public final boolean isInClipboard() {
		return inClipboard;
	}

	final void setInClipboard(boolean inClipboard) {
		this.inClipboard = inClipboard;
	}

	protected final void rebuild() {
		panel.setWidget(build());
		list.onBlockRebuilt(this);
	}

	final void setExtended(boolean extended) {
		if (this.extended == extended) return;
		this.extended = extended;
		rebuild();
	}

	protected final Widget build() {
		Label title = new Label(getBlockTitle());
		title.setStyleName(StyleSheet.ELEMENT_BLOCK_WIDGET_TITLE);

		VerticalPanel center = new VerticalPanel();
		center.setStyleName(StyleSheet.ELEMENT_BLOCK_WIDGET_CENTER);
		center.add(title);
		if (!inClipboard) {
			center.add(buildContent());
		}

		HorizontalPanel block = new HorizontalPanel();
		block.setStyleName(StyleSheet.ELEMENT_BLOCK_WIDGET_BLOCK);

		if (dragHandle != null) block.add(dragHandle);

		AbstractImagePrototype icon = getIcon();
		if (icon != null) block.add(icon.createImage());

		block.add(center);

		// TODO (fha, 30.11.2008): geht das nicht auch im css?
		block.setCellWidth(center, "99%");

		Widget toolbar = buildToolbar();
		if (toolbar != null) {
			block.add(toolbar);
		}

		return block;
	}

	public final void makeDraggable() {
		if (dragHandle == null) {
			dragHandle = Img.icons().dragHandleIcon32().createImage();
			dragHandle.setStyleName(StyleSheet.DRAG_HANDLE);
		}
		ScrumGwtApplication.get().getDragController().makeDraggable(this, dragHandle);
	}

	public Image getDragHandle() {
		return dragHandle;
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		if (dropController != null)
			ScrumGwtApplication.get().getDragController().registerDropController(dropController);
	}

	@Override
	protected void onDetach() {
		if (dropController != null)
			ScrumGwtApplication.get().getDragController().unregisterDropController(dropController);
		super.onDetach();
	}
}
