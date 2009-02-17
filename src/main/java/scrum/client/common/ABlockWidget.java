package scrum.client.common;

import scrum.client.ScrumGwtApplication;
import scrum.client.dnd.DndMarkerWidget;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for a block widget, which can be added to a <code>BlockWidgetList</code>.
 * 
 */
@SuppressWarnings("unchecked")
public abstract class ABlockWidget extends Composite {

	private BlockListWidget list;
	private FlowPanel mainPanel;
	private SimplePanel panel;
	private boolean extended;
	private boolean inClipboard;
	private DropController dropController;
	private BlockListController listController;
	private DndMarkerWidget dndMarkerTop = new DndMarkerWidget();
	private DndMarkerWidget dndMarkerBottom = new DndMarkerWidget();

	/**
	 * Provide the content of the block. Depending on properties (ie. <code>isExtended()</code>) a different
	 * implementation can be provided.
	 */
	protected abstract Widget buildContent();

	protected abstract Widget buildToolbar();

	protected abstract String getBlockTitle();

	protected abstract AbstractImagePrototype getIcon16();

	protected abstract AbstractImagePrototype getIcon32();

	public abstract void delete();

	public ABlockWidget() {
		mainPanel = new FlowPanel();
		mainPanel.setStyleName(StyleSheet.ELEMENT_BLOCK_WIDGET_MAIN);

		panel = new SimplePanel();
		panel.setStyleName(StyleSheet.ELEMENT_BLOCK_WIDGET);
		dropController = createDropController();

		mainPanel.add(dndMarkerTop);
		mainPanel.add(panel);
		mainPanel.add(dndMarkerBottom);

		dndMarkerTop.setActive(false);
		dndMarkerBottom.setActive(false);
		initWidget(mainPanel);
	}

	protected void notifyListControllerDataChanged() {
		listController.dataChanged(this);
	}

	public Widget getBorderPanel() {
		return panel;
	}

	protected abstract DropController createDropController();

	public void deactivateDndMarkers() {
		dndMarkerTop.setActive(false);
		dndMarkerBottom.setActive(false);
	}

	public void activateDndMarkerTop() {
		dndMarkerTop.setActive(true);
		dndMarkerBottom.setActive(false);
	}

	public void activateDndMarkerBottom() {
		dndMarkerBottom.setActive(true);
		dndMarkerTop.setActive(false);
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

	void setListController(BlockListController listController) {
		this.listController = listController;
	}

	/**
	 * Indicates if the block is in extended-mode. This method should by called within the
	 * <code>build()</code>-method.
	 */
	public final boolean isExtended() {
		return extended;
	}

	public final boolean isInClipboard() {
		return inClipboard;
	}

	public final void setInClipboard(boolean inClipboard) {
		this.inClipboard = inClipboard;
		// if (inClipboard) {
		// getBorderPanel().removeStyleName(StyleSheet.STATE_BLOCK_WIDGET_SELECTED);
		// extended = false;
		// }
	}

	public final void rebuild() {
		panel.setWidget(build());
		list.onBlockRebuilt(this);
	}

	final void setExtended(boolean extended) {
		if (this.extended == extended) return;
		this.extended = extended;
		if (extended) {
			getBorderPanel().addStyleName(StyleSheet.STATE_BLOCK_WIDGET_SELECTED);
		} else {
			getBorderPanel().removeStyleName(StyleSheet.STATE_BLOCK_WIDGET_SELECTED);
		}
		rebuild();
	}

	protected final Widget build() {
		return buildBlockItemWidget();
	}

	protected final Widget buildBlockItemWidget() {
		Label title = new Label(getBlockTitle());
		title.setStyleName(StyleSheet.ELEMENT_BLOCK_WIDGET_TITLE);

		FlowPanel center = new FlowPanel();
		center.setStyleName(StyleSheet.ELEMENT_BLOCK_WIDGET_CENTER);
		center.add(title);
		center.add(buildContent());

		HorizontalPanel block = new HorizontalPanel();
		block.setSpacing(5);
		block.setStyleName(StyleSheet.ELEMENT_BLOCK_WIDGET_BLOCK);

		block.add(makeDraggable());

		block.add(center);

		// TODO (fha, 30.11.2008): geht das nicht auch im css?
		block.setCellWidth(center, "99%");

		Widget toolbar = buildToolbar();
		if (toolbar != null) {
			block.add(toolbar);
		}

		return block;
	}

	public final Image makeDraggable() {
		Image dragHandle = createDragHandle();
		ScrumGwtApplication.get().getDragController().makeDraggable(this, dragHandle);
		return dragHandle;
	}

	public Image createDragHandle() {
		Image dragHandle = getIcon32().createImage();
		dragHandle.setStyleName(StyleSheet.DRAG_HANDLE);
		return dragHandle;
	}

	public ClipboardItemWidget createClipboardItem() {
		ClipboardItemWidget item = new ClipboardItemWidget(this);

		return item;
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
