package scrum.client.common;

import ilarkesto.gwt.client.AAction;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class BlockHeaderWidget extends AWidget {

	private HorizontalPanel table;
	private FocusPanel dragHandleWrapper;
	private Label centerText;

	private MenuBar menu;
	private int prefixCellCount = 0;
	private List<Label> clickableLabels;

	@Override
	protected Widget onInitialization() {
		dragHandleWrapper = new FocusPanel();
		dragHandleWrapper.setStyleName("BlockHeaderWidget-dragHandle");
		// dragHandleWrapper.setHeight("100%");
		centerText = new Label();
		centerText.setStyleName("BlockHeaderWidget-center");
		centerText.setWidth("100%");

		table = new HorizontalPanel();
		table.setStyleName("BlockHeaderWidget");
		table.setWidth("100%");
		table.add(dragHandleWrapper);
		table.setCellWidth(dragHandleWrapper, "50px");
		table.add(centerText);

		return table;
	}

	public Label insertPrefixLabel(String width) {
		Label label = new Label();
		insertPrefixCell(label, width, true, "BlockHeaderWidget-prefixLabel");

		if (clickableLabels == null) clickableLabels = new ArrayList<Label>(2);
		clickableLabels.add(label);

		return label;
	}

	public void insertPrefixIcon(Image icon) {
		insertPrefixCell(icon, "16px", false, "BlockHeaderWidget-prefixIcon");
	}

	public void insertPrefixCell(Widget widget, String width, boolean nowrap, String additionalStyleName) {
		SimplePanel cell = createCell(widget, nowrap, additionalStyleName);
		prefixCellCount++;
		table.insert(cell, prefixCellCount);
		if (width != null) table.setCellWidth(cell, width);
	}

	public void appendCell(Widget widget, String width, boolean nowrap, String additionalStyleName) {
		SimplePanel cell = createCell(widget, nowrap, additionalStyleName);
		table.add(cell);
		if (width != null) table.setCellWidth(cell, width);
	}

	private SimplePanel createCell(Widget widget, boolean nowrap, String additionalStyleName) {
		SimplePanel wrapper = new SimplePanel();
		wrapper.setStyleName("BlockHeaderWidget-cell");
		wrapper.setHeight("100%");
		if (nowrap) wrapper.getElement().getStyle().setProperty("whiteSpace", "nowrap");
		if (additionalStyleName != null) wrapper.addStyleName(additionalStyleName);
		wrapper.setWidget(widget);
		return wrapper;
	}

	public void addMenuAction(AScrumAction action) {
		if (menu == null) {
			menu = new MenuBar(true);

			MenuBar menuBar = new MenuBar();
			menuBar.addItem("Functions V", menu);
			menuBar.setPopupPosition(MenuBar.PopupPosition.LEFT);
			appendCell(menuBar, "30px", true, null);
		}
		MenuItem menuItem = new MenuItem(action.getLabel(), action);
		menuItem.setTitle(action.getTooltip());
		menuItem.setVisible(action.isExecutable());
		menu.addItem(menuItem);
	}

	public void addToolbarAction(AAction action) {
		appendCell(new ButtonWidget(action), "5px", true, null);
	}

	public void setDragHandle(String text) {
		setDragHandle(new Label(text));
	}

	public void setDragHandle(Widget widget) {
		dragHandleWrapper.setWidget(widget);
	}

	public void setCenter(String text) {
		centerText.setText(text);
	}

	public void addClickHandler(ClickHandler handler) {
		centerText.addClickHandler(handler);
		if (clickableLabels != null) {
			for (Label label : clickableLabels) {
				label.addClickHandler(handler);
			}
		}
	}

	public FocusPanel getDragHandle() {
		return dragHandleWrapper;
	}
}
