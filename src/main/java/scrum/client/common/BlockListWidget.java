package scrum.client.common;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.GwtLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * List of <code>BlockWidget</code>s.
 */
public final class BlockListWidget<B extends ABlockWidget> extends AWidget implements Iterable<B> {

	// private static final Logger LOG = Logger.get(BlockListWidget.class);

	private FlexTable table;
	private List<B> blocks = new LinkedList<B>();
	private int selectedRow = -1;
	private boolean dndSorting = true;
	private Comparator<B> autoSorter;

	@Override
	protected Widget onInitialization() {
		table = new FlexTable();
		table.setCellPadding(0);
		table.setCellSpacing(0);
		table.setStyleName(StyleSheet.ELEMENT_BLOCK_LIST_WIDGET_TABLE);
		table.addTableListener(new Listener());

		return table;
	}

	@Override
	protected void onUpdate() {
		for (B block : blocks) {
			block.update();
		}
		if (autoSorter != null) sort(autoSorter);
	}

	public final void sort(Comparator<B> comparator) {
		List<B> sortedBlocks = new ArrayList<B>(blocks);
		Collections.sort(sortedBlocks, comparator);
		int size = size();
		for (int i = 0; i < size; i++) {
			B listBlock = sortedBlocks.get(i);
			B tableBlock = (B) table.getWidget(i, 0);
			GwtLogger.DEBUG(i, ">", listBlock, "|", tableBlock);
			if (listBlock != tableBlock) {
				moveBlock(listBlock, i);
			}
		}
	}

	public final void setAutoSorter(Comparator<B> autoSorter) {
		this.autoSorter = autoSorter;
		if (autoSorter != null) setDndSorting(false);
	}

	public final boolean isDndSorting() {
		return dndSorting;
	}

	public final void setDndSorting(boolean dndSorting) {
		this.dndSorting = dndSorting;
		if (dndSorting) setAutoSorter(null);
	}

	public final void clear() {
		initialize();
		int count = size();
		for (int i = 0; i < count; i++) {
			removeRow(0);
		}
	}

	public final B addBlock(B block) {
		block.setList(this);

		blocks.add(block);
		table.setWidget(table.getRowCount(), 0, block);
		block.update();

		return block;
	}

	public final void moveBlock(B block, int toIndex) {
		if (block == null) throw new IllegalArgumentException("block == null");
		int fromIndex = indexOf(block);
		GwtLogger.DEBUG("moving block from", fromIndex, "to", toIndex);

		blocks.remove(fromIndex);
		blocks.add(toIndex, block);

		table.removeRow(fromIndex);
		table.insertRow(toIndex);
		table.setWidget(toIndex, 0, block);
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
		return getBlock(selectedRow);
	}

	public final void selectRow(int row) {

		if (row == selectedRow) return;

		deselect();
		ABlockWidget block = getBlock(row);

		block.setSelected(true);
		selectedRow = row;
	}

	public final void scrollToSelectedBlock() {
		ScrumUtil.scrollTo(getSelectedBlock());
	}

	public final void remove(B widget) {
		removeRow(indexOf(widget));
	}

	public final void removeSelectedRow() {
		removeRow(selectedRow);
	}

	public final void removeRow(int row) {
		if (row < 0 || row >= size()) return;
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
			block.setSelected(false);
		}
		selectedRow = -1;
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