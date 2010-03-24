package scrum.client.common;

import ilarkesto.gwt.client.AAction;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.DropdownMenuButtonWidget;
import ilarkesto.gwt.client.Gwt;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class BlockHeaderWidget extends AWidget {

	private HorizontalPanel table;
	private FocusPanel dragHandleWrapper;
	private FocusPanel centerFocusPanel;
	private FlowPanel centerWrapper;
	private Label centerText;

	private DropdownMenuButtonWidget menu;
	private int prefixCellCount = 0;
	private int suffixCellCount = 0;

	@Override
	protected Widget onInitialization() {
		dragHandleWrapper = new FocusPanel();
		dragHandleWrapper.setStyleName("BlockHeaderWidget-dragHandle");
		// dragHandleWrapper.setHeight("100%");

		centerText = Gwt.createInline(null);
		centerText.setStyleName("BlockHeaderWidget-center-text");

		centerWrapper = new FlowPanel();
		centerWrapper.setStyleName("BlockHeaderWidget-center");
		centerWrapper.setWidth("100%");
		centerWrapper.add(centerText);

		centerFocusPanel = new FocusPanel(centerWrapper);
		centerFocusPanel.setHeight("100%");

		table = new HorizontalPanel();
		table.setStyleName("BlockHeaderWidget");
		table.setWidth("100%");
		table.add(dragHandleWrapper);
		table.setCellWidth(dragHandleWrapper, "50px");
		table.add(centerFocusPanel);

		return table;
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		centerFocusPanel.setFocus(false);
	}

	public Label appendCenterSuffix(String text) {
		Label label = Gwt.createInline(text);
		label.setStyleName("BlockHeaderWidget-centerSuffix");
		centerWrapper.add(label);
		return label;
	}

	public Label insertPrefixLabel(String width, boolean secondary) {
		Label label = new Label();
		insertPrefixCell(label, width, true, "BlockHeaderWidget-prefixLabel", secondary);

		return label;
	}

	public SimplePanel insertPrefixIcon() {
		SimplePanel cell = insertPrefixCell(null, "16px", false, "BlockHeaderWidget-prefixIcon", false);
		cell.setHeight("16px");
		cell.getElement().getStyle().setMarginTop(2, Unit.PX);
		return cell;
	}

	public SimplePanel insertSuffixCell(Widget widget, String width, boolean nowrap, String additionalStyleName,
			boolean secondary) {
		SimplePanel cell = createCell(widget, nowrap, additionalStyleName);
		if (secondary) cell.addStyleName("BlockHeaderWidget-cell-secondary");
		suffixCellCount++;

		table.insert(cell, prefixCellCount + 1 + suffixCellCount);
		if (width != null) table.setCellWidth(cell, width);

		return cell;
	}

	public SimplePanel insertPrefixCell(Widget widget, String width, boolean nowrap, String additionalStyleName,
			boolean secondary) {
		SimplePanel cell = createCell(widget, nowrap, additionalStyleName);
		if (secondary) cell.addStyleName("BlockHeaderWidget-cell-secondary");
		prefixCellCount++;
		table.insert(cell, prefixCellCount);
		if (width != null) {
			table.setCellWidth(cell, width);
			cell.setWidth(width);
		}
		return cell;
	}

	public void appendCell(Widget widget, String width, boolean nowrap, boolean alignRight, String additionalStyleName) {
		SimplePanel cell = createCell(widget, nowrap, additionalStyleName);
		table.add(cell);
		if (alignRight) table.setCellHorizontalAlignment(cell, HorizontalPanel.ALIGN_RIGHT);
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
			menu = new DropdownMenuButtonWidget();
			appendCell(menu, "30px", true, false, null);
		}
		menu.addAction(action);
	}

	public void addToolbarAction(AAction action) {
		appendCell(new ButtonWidget(action), "5px", true, false, null);
	}

	public void setDragHandle(String text) {
		setDragHandle(new Label(text));
	}

	public void setDragHandle(Widget widget) {
		dragHandleWrapper.setWidget(widget);
	}

	public void setCenter(String text) {
		if (Gwt.isEmpty(text)) text = "<new>";
		centerText.setText(text);
	}

	public void addClickHandler(ClickHandler handler) {
		centerFocusPanel.addClickHandler(handler);
	}

	public FocusPanel getDragHandle() {
		return dragHandleWrapper;
	}
}
