package scrum.client.common;

import ilarkesto.gwt.client.AAction;
import ilarkesto.gwt.client.AGwtEntity;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.HyperlinkWidget;

import java.util.Set;

import scrum.client.admin.User;
import scrum.client.dnd.BlockDndMarkerWidget;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for a block widget, which can be added to a <code>BlockWidgetList</code>.
 * 
 */
@SuppressWarnings("unchecked")
public abstract class ABlockWidget<O> extends AScrumWidget {

	private O object;

	private Label label;
	private FocusPanel iconPanel;
	private HorizontalPanel titlePanel;
	private SimplePanel contentWrapper;
	private HorizontalPanel toolbar;
	private MenuBar menu;
	private String additionalStyleName;
	private BlockListWidget<O> list;
	private FlowPanel mainPanel;
	private FlowPanel panel;
	private boolean extended;
	private BlockDndMarkerWidget dndMarkerTop = new BlockDndMarkerWidget();

	protected abstract void onBlockInitialization();

	protected abstract void onBlockUpdate();

	public ABlockWidget() {}

	protected final void setObject(O object) {
		assert this.object == null;
		assert object != null;
		this.object = object;
	}

	protected final O getObject() {
		return object;
	}

	@Override
	protected final Widget onInitialization() {
		contentWrapper = new SimplePanel();
		contentWrapper.setStyleName("ABlockWidget-content");
		toolbar = new HorizontalPanel();

		label = new Label();
		label.setStyleName("ABlockWidget-title-label");
		label.addClickHandler(new SelectionClickHandler());

		iconPanel = new FocusPanel();

		// FlowPanel center = new FlowPanel();
		// center.setStyleName("ABlockWidget-center");
		// center.add(title);
		// center.add(contentWrapper);
		cm.getDndManager().makeDraggable(this, iconPanel);

		titlePanel = new HorizontalPanel();
		titlePanel.setSpacing(1);
		titlePanel.setStyleName("ABlockWidget-title");
		titlePanel.add(iconPanel);
		titlePanel.setCellWidth(iconPanel, "16px");

		titlePanel.add(label);
		// blockPanel.setCellWidth(center, "99%");
		titlePanel.add(toolbar);
		titlePanel.setCellWidth(toolbar, "1%");

		// ----
		panel = new FlowPanel();
		panel.add(titlePanel);
		panel.add(contentWrapper);
		panel.setStyleName("ABlockWidget-block");

		mainPanel = new FlowPanel();
		mainPanel.setStyleName("ABlockWidget");

		mainPanel.add(dndMarkerTop);
		mainPanel.add(panel);

		dndMarkerTop.setActive(false);

		onBlockInitialization();

		return mainPanel;
	}

	public void setAdditionalStyleName(String additionalStyleName) {
		if (additionalStyleName == null) {
			if (this.additionalStyleName != null) {
				mainPanel.removeStyleName(this.additionalStyleName);
			}
		} else {
			mainPanel.addStyleName(additionalStyleName);
		}
		this.additionalStyleName = additionalStyleName;
	}

	public String getAdditionalStyleName() {
		return additionalStyleName;
	}

	protected void addMenuAction(AScrumAction action) {
		if (menu == null) {
			MenuBar menuBar = new MenuBar();
			menuBar.addStyleName("ABlockWidget-title-menu");

			menu = new MenuBar(true);
			menuBar.addItem("v", menu);
			menuBar.setPopupPosition(MenuBar.PopupPosition.LEFT);
			addToolbarItem(menuBar);
		}
		MenuItem menuItem = new MenuItem(action.getLabel(), action);
		menuItem.setTitle(action.getTooltip());
		menuItem.setVisible(action.isExecutable());
		menu.addItem(menuItem);
	}

	protected void addToolbarAction(AAction action) {
		addToolbarItem(new HyperlinkWidget(action));
	}

	protected void addToolbarItem(Widget toolbarItem) {
		toolbar.add(toolbarItem);
		if (toolbarItem instanceof AWidget) {
			((AWidget) toolbarItem).update();
		}
	}

	@Override
	protected final void onUpdate() {
		toolbar.clear();
		menu = null;

		if (cm.getAuth().isUserLoggedIn()) {
			User me = getCurrentUser();
			if (cm.getProjectContext().isProjectOpen()) {
				O o = getObject();
				if (o instanceof AGwtEntity) {
					Set<User> users = getCurrentProject().getUsersSelecting(((AGwtEntity) o));
					for (User user : users) {
						if (user == me) continue;
						UserOnBlockWidget userOnBlockWidget = new UserOnBlockWidget(user);
						addToolbarItem(userOnBlockWidget);
					}
				}
			}
		}

		onBlockUpdate();
	}

	protected final void setContent(Widget content) {
		contentWrapper.setWidget(content);
	}

	protected final void setBlockTitle(String text) {
		label.setText(text);
	}

	protected final void setIcon(Image icon) {
		iconPanel.setWidget(icon);
	}

	protected final void setIcon(AbstractImagePrototype icon) {
		setIcon(icon.createImage());
	}

	public final String getBlockTitle() {
		return label.getText();
	}

	public Widget getBorderPanel() {
		return panel;
	}

	public void deactivateDndMarkers() {
		dndMarkerTop.setActive(false);
	}

	public void activateDndMarkerTop() {
		dndMarkerTop.setActive(true);
	}

	public final BlockListWidget<O> getList() {
		return list;
	}

	final void setList(BlockListWidget list) {
		this.list = list;
	}

	/**
	 * Indicates if the block is in extended-mode. This method should be called within the
	 * <code>build()</code>-method.
	 */
	public final boolean isExtended() {
		return extended;
	}

	/**
	 * This method is only called by BlockListWidget. To select a block on a BlockListWidget call
	 * <code>BlockListWidget.selectBlock(B block)</code> instead.
	 */
	final void setExtended(boolean extended) {
		if (this.extended == extended) return;
		this.extended = extended;
		if (extended) {
			getBorderPanel().addStyleName("ABlockWidget-block-selected");
		} else {
			getBorderPanel().removeStyleName("ABlockWidget-block-selected");
			setContent(null);
		}

		update();

		if (extended) {
			cm.getEventBus().fireBlockExpanded(getObject());
		} else {
			cm.getEventBus().fireBlockCollapsed(getObject());
		}
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		if (getList().isDndSorting()) {
			cm.getDndManager().registerDropTarget(this);
		}
		if (extended) cm.getEventBus().fireBlockExpanded(getObject());
	}

	@Override
	protected void onUnload() {
		if (extended) cm.getEventBus().fireBlockCollapsed(getObject());
		cm.getDndManager().unregisterDropTarget(this);
		super.onUnload();
	}

	private class SelectionClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			NativeEvent nativeEvent = event.getNativeEvent();
			boolean modifierDown = nativeEvent.getCtrlKey() || nativeEvent.getShiftKey() || nativeEvent.getAltKey();
			list.toggleExtension(getObject(), !modifierDown);
			event.stopPropagation();
		}

	}

	@Override
	public String toString() {
		return "[" + object + "]";
	}

}
