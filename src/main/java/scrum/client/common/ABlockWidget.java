package scrum.client.common;

import ilarkesto.gwt.client.AWidget;
import scrum.client.dnd.BlockListDndMarkerWidget;
import scrum.client.dnd.DndManager;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
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
public abstract class ABlockWidget<O extends Object> extends AWidget {

	private Label title;
	private FocusPanel iconPanel;
	private HorizontalPanel blockPanel;
	private SimplePanel contentWrapper;
	private SimplePanel toolbarWrapper;

	private BlockListWidget list;
	private FlowPanel mainPanel;
	private SimplePanel panel;
	private boolean selected;
	private BlockListDndMarkerWidget dndMarkerTop = new BlockListDndMarkerWidget();
	private BlockListDndMarkerWidget dndMarkerBottom = new BlockListDndMarkerWidget();

	protected abstract void onBlockInitialization();

	protected abstract void onBlockUpdate();

	protected abstract void setObject(O object);

	protected abstract O getObject();

	public ABlockWidget() {}

	@Override
	protected final Widget onInitialization() {
		contentWrapper = new SimplePanel();
		toolbarWrapper = new SimplePanel();

		title = new Label();
		title.setStyleName("ABlockWidget-title");

		iconPanel = new FocusPanel();

		FlowPanel center = new FlowPanel();
		center.setStyleName("ABlockWidget-center");
		center.add(title);
		center.add(contentWrapper);
		DndManager.get().makeDraggable(this, iconPanel);

		blockPanel = new HorizontalPanel();
		blockPanel.setSpacing(1);
		blockPanel.setStyleName("ABlockWidget-block");
		blockPanel.add(iconPanel);
		blockPanel.setCellWidth(iconPanel, "32px");
		blockPanel.add(center);
		// blockPanel.setCellWidth(center, "99%");
		blockPanel.add(toolbarWrapper);

		panel = new SimplePanel();
		panel.setWidget(blockPanel);
		panel.setStyleName("ABlockWidget");

		mainPanel = new FlowPanel();
		mainPanel.setStyleName("ABlockWidget-main");

		mainPanel.add(dndMarkerTop);
		mainPanel.add(panel);
		mainPanel.add(dndMarkerBottom);

		dndMarkerTop.setActive(false);
		dndMarkerBottom.setActive(false);

		onBlockInitialization();

		return mainPanel;
	}

	@Override
	protected final void onUpdate() {
		onBlockUpdate();
	}

	protected final void setContent(Widget content) {
		contentWrapper.setWidget(content);
	}

	protected final void setToolbar(Widget toolbar) {
		toolbarWrapper.setWidget(toolbar);
	}

	protected final void setBlockTitle(String text) {
		title.setText(text);
	}

	protected final void setIcon(Image icon) {
		iconPanel.setWidget(icon);
	}

	protected final void setIcon(AbstractImagePrototype icon) {
		setIcon(icon.createImage());
	}

	public final String getBlockTitle() {
		return title.getText();
	}

	public Widget getBorderPanel() {
		return panel;
	}

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

	public final BlockListWidget<ABlockWidget> getList() {
		return list;
	}

	final void setList(BlockListWidget list) {
		this.list = list;
	}

	/**
	 * Indicates if the block is in extended-mode. This method should by called within the
	 * <code>build()</code>-method.
	 */
	public final boolean isSelected() {
		return selected;
	}

	/**
	 * This method is only called by BlockListWidget. To select a block on a BlockListWidget call
	 * <code>BlockListWidget.selectBlock(B block)</code> instead.
	 */
	final void setSelected(boolean extended) {
		if (this.selected == extended) return;
		this.selected = extended;
		if (extended) {
			getBorderPanel().addStyleName("ABlockWidget-selected");
		} else {
			getBorderPanel().removeStyleName("ABlockWidget-selected");
		}
		update();
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		if (getList().isDndSorting()) {
			DndManager.get().registerDropTarget(this);
		}
	}

	@Override
	protected void onDetach() {
		DndManager.get().unregisterDropTarget(this);

		if (list != null)
			list.deselect();
		else setSelected(false);

		super.onDetach();
	}
}
