package scrum.client.common;

import scrum.client.ScrumGwtApplication;
import scrum.client.dnd.DummyDropWidget;

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
	protected BlockListController controller = new BlockListController();
	private DummyDropWidget dummyTop = new DummyDropWidget();
	private DummyDropWidget dummyBottom = new DummyDropWidget();

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

		mainPanel.add(dummyTop);
		mainPanel.add(panel);
		mainPanel.add(dummyBottom);

		dummyTop.setActive(false);
		dummyBottom.setActive(false);
		initWidget(mainPanel);
	}

	public Widget getBorderPanel() {
		return panel;
	}

	protected abstract DropController createDropController();

	public DummyDropWidget getDummyTop() {
		return dummyTop;
	}

	public DummyDropWidget getDummyBottom() {
		return dummyBottom;
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
	 * Indicates if the block is in extended-mode. This method should by called within the
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
		if (inClipboard) {
			getBorderPanel().removeStyleName(StyleSheet.STATE_BLOCK_WIDGET_SELECTED);
			extended = false;
		}
	}

	public final void rebuild() {
		panel.setWidget(build());
		list.onBlockRebuilt(this);
	}

	final void setExtended(boolean extended) {
		if (this.extended == extended) return;
		this.extended = extended;
		rebuild();
	}

	protected final Widget build() {
		return inClipboard ? buildClipboardItemWidget() : buildBlockItemWidget();
	}

	protected final Widget buildClipboardItemWidget() {
		HorizontalPanel mainpanel = new HorizontalPanel();
		mainpanel.add(makeDraggable());
		mainpanel.add(new Label(getBlockTitle()));

		return mainpanel;
	}

	protected final Widget buildBlockItemWidget() {
		Label title = new Label(getBlockTitle());
		title.setStyleName(StyleSheet.ELEMENT_BLOCK_WIDGET_TITLE);

		FlowPanel center = new FlowPanel();
		center.setStyleName(StyleSheet.ELEMENT_BLOCK_WIDGET_CENTER);
		center.add(title);
		center.add(buildContent());

		HorizontalPanel block = new HorizontalPanel();
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
		Image dragHandle = null;
		if (isInClipboard()) {
			dragHandle = getIcon16().createImage();
		} else {
			dragHandle = getIcon32().createImage();
		}
		dragHandle.setStyleName(StyleSheet.DRAG_HANDLE);
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
