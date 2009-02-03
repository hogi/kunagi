package scrum.client.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;

/**
 * Scrollable List of <code>BlockWidget</code>s.
 */
public final class BlockListWidget<B extends ABlockWidget> extends Composite implements Iterable<B> {

	// private static final Logger LOG = Logger.get(BlockListWidget.class);

	private FlexTable table;
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

		initWidget(table);
	}

	void onBlockRebuilt(B block) {}

	public final void setSidebarMode(boolean sidebarMode) {
		this.sidebarMode = sidebarMode;
	}

	public final boolean isSidebarMode() {
		return sidebarMode;
	}

	public final void clear() {
		int count = blocks.size();
		for (int i = 0; i < count; i++) {
			removeRow(0);
		}
	}

	public final void addBlock(B block) {
		block.setListController(controller);
		block.setList(this);

		block.setInClipboard(sidebarMode);
		block.rebuild();

		blocks.add(block);
		table.setWidget(table.getRowCount(), 0, block);
	}

	public final void addBlockAt(int index, B block) {
		block.setListController(controller);
		block.setList(this);

		block.setInClipboard(sidebarMode);
		block.rebuild();

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
		if (sidebarMode) return;

		if (row == selectedRow) return;

		deselect();
		ABlockWidget block = blocks.get(row);

		if (!sidebarMode) {
			block.setExtended(true);
		}
		selectedRow = row;
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
		for (B block : blocks) {
			block.setExtended(false);
		}
		selectedRow = -1;
	}

	public void setController(BlockListController<B> controller) {
		this.controller = controller;
		for (ABlockWidget block : blocks) {
			// LOG.debug(block + ": " + controller.getClass());
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