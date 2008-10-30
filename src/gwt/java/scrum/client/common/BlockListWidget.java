package scrum.client.common;

import java.util.LinkedList;
import java.util.List;

import scrum.client.service.StyleSheet;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;

/**
 * Scrollable List of <code>BlockWidget</code>s.
 */
public class BlockListWidget extends Composite {

	private FlexTable table;
	private ScrollPanel scroller;
	private List<ABlockWidget> blocks = new LinkedList<ABlockWidget>();
	private int selectedRow = -1;
	private boolean sidebarMode;

	public BlockListWidget() {
		this(false);
	}

	public BlockListWidget(boolean sidebarMode) {
		this.sidebarMode = sidebarMode;

		table = new FlexTable();
		table.setWidth("100%");
		if (!sidebarMode) {
			table.addTableListener(new Listener());
		}

		scroller = new ScrollPanel();
		scroller.setWidth("100%");
		scroller.setHeight("100%");
		scroller.setStyleName(StyleSheet.BLOCK_LIST_WIDGET);
		scroller.add(table);

		initWidget(scroller);
	}

	public void addBlock(ABlockWidget block) {
		if (sidebarMode) block.setInClipboard(true);

		block.makeDraggable();
		// createDropControllerFor(block);

		block.rebuild();
		blocks.add(block);
		table.setWidget(table.getRowCount(), 0, block);
		scroller.scrollToBottom();
	}

	public void addBlockAt(int index, ABlockWidget block) {
		if (sidebarMode) block.setInClipboard(true);

		block.makeDraggable();
		// createDropControllerFor(block);

		block.rebuild();
		blocks.add(index, block);
		table.insertRow(index);
		table.setWidget(index, 0, block);
	}

	public ABlockWidget getBlock(int index) {
		return blocks.get(index);
	}

	public int size() {
		return blocks.size();
	}

	public int indexOf(ABlockWidget block) {
		return blocks.indexOf(block);
	}

	public ABlockWidget getSelectedBlock() {
		if (selectedRow < 0) return null;
		return blocks.get(selectedRow);
	}

	public void selectRow(int row) {
		if (row == selectedRow) return;
		deselect();
		ABlockWidget block = blocks.get(row);
		block.addStyleName(StyleSheet.BLOCK_WIDGET_SELECTED);
		if (!sidebarMode) {
			block.setExtended(true);
		}
		selectedRow = row;
	}

	public void remove(ABlockWidget widget) {
		removeRow(indexOf(widget));
	}

	public void removeSelectedRow() {
		removeRow(selectedRow);
	}

	public void removeRow(int row) {
		if (row < 0 || row >= blocks.size()) return;
		blocks.remove(row);
		table.removeRow(row);
		if (selectedRow == row) {
			selectedRow = -1;
		} else if (selectedRow > row) {
			selectedRow--;
		}
	}

	public void selectBlock(ABlockWidget block) {
		selectRow(blocks.indexOf(block));
	}

	public void deselect() {
		ABlockWidget block = getSelectedBlock();
		if (block == null) return;
		block.removeStyleName(StyleSheet.BLOCK_WIDGET_SELECTED);
		block.setExtended(false);
	}

	private class Listener implements TableListener {

		public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
			selectRow(row);
		}
	}
}