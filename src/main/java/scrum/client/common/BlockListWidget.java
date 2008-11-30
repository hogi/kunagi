package scrum.client.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;

/**
 * Scrollable List of <code>BlockWidget</code>s.
 */
public final class BlockListWidget<B extends ABlockWidget> extends Composite implements Iterable<B> {

	private FlexTable table;
	private ScrollPanel scroller;
	private List<B> blocks = new LinkedList<B>();
	private int selectedRow = -1;
	private boolean sidebarMode;

	private BlockListController<B> controller = new BlockListController<B>();

	public BlockListWidget() {

		table = new FlexTable();
		table.setStyleName(StyleSheet.ELEMENT_BLOCK_LIST_WIDGET_TABLE);
		if (!sidebarMode) {
			table.addTableListener(new Listener());
		}

		scroller = new ScrollPanel();
		scroller.setStyleName(StyleSheet.ELEMENT_BLOCK_LIST_WIDGET);
		scroller.add(table);

		initWidget(scroller);
	}

	void onBlockRebuilt(B block) {}

	public final void setSidebarMode(boolean sidebarMode) {
		this.sidebarMode = sidebarMode;
	}

	public final void clear() {
		int count = blocks.size();
		for (int i = 0; i < count; i++) {
			removeRow(0);
		}
	}

	public final void addBlock(B block) {
		if (sidebarMode) block.setInClipboard(true);
		block.setList(this);
		block.makeDraggable();
		// createDropControllerFor(block);

		block.rebuild();
		block.setListController(controller);
		blocks.add(block);
		table.setWidget(table.getRowCount(), 0, block);
		scroller.scrollToBottom();
	}

	public final void addBlockAt(int index, B block) {
		if (sidebarMode) block.setInClipboard(true);
		block.setList(this);
		block.makeDraggable();
		// createDropControllerFor(block);

		block.rebuild();
		block.setListController(controller);
		blocks.add(index, block);
		table.insertRow(index);
		table.setWidget(index, 0, block);

		selectedRow = (selectedRow >= index) ? selectedRow + 1 : selectedRow;
		if (block.isExtended()) {
			selectBlock(block);
		}
	}

	public final B getBlock(int index) {
		return blocks.get(index);
	}

	public final int size() {
		return blocks.size();
	}

	public final int indexOf(B block) {
		return blocks.indexOf(block);
	}

	public final B getSelectedBlock() {
		if (selectedRow < 0) return null;
		return blocks.get(selectedRow);
	}

	public final int getSelectedBlockId() {
		return selectedRow;
	}

	public final void selectRow(int row) {
		if (row == selectedRow) {
			// deselect();
		} else {
			deselect();
			ABlockWidget block = blocks.get(row);
			block.addStyleName(StyleSheet.STATE_BLOCK_WIDGET_SELECTED);
			if (!sidebarMode) {
				block.setExtended(true);
			}
			selectedRow = row;
		}
	}

	public final void remove(B widget) {
		removeRow(indexOf(widget));
	}

	public final void removeSelectedRow() {
		removeRow(selectedRow);
	}

	public final void removeRow(int row) {
		if (row < 0 || row >= blocks.size()) return;
		blocks.remove(row);
		table.removeRow(row);
		if (selectedRow == row) {
			selectedRow = -1;
		} else if (selectedRow > row) {
			selectedRow--;
		}
	}

	public final void selectBlock(B block) {
		selectRow(blocks.indexOf(block));
	}

	public final void deselect() {
		B block = getSelectedBlock();
		if (block == null) return;
		block.removeStyleName(StyleSheet.STATE_BLOCK_WIDGET_SELECTED);
		selectedRow = -1;
		block.setExtended(false);
	}

	public void setController(BlockListController<B> controller) {
		this.controller = controller;
		for (ABlockWidget block : blocks) {
			System.out.println(block + ": " + controller.getClass());
			block.setListController(controller);
		}
	}

	private final class Listener implements TableListener {

		public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
			selectRow(row);
		}
	}

	public Iterator<B> iterator() {
		return blocks.iterator();
	}
}