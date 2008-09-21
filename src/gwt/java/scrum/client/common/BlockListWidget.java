package scrum.client.common;

import java.util.ArrayList;
import java.util.List;

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
	private List<ABlockWidget> blocks = new ArrayList<ABlockWidget>();
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
		scroller.setStyleName("BlockListWidget");
		scroller.add(table);

		initWidget(scroller);
	}

	public void addBlock(ABlockWidget block) {
		if (sidebarMode) block.setInClipboard(true);
		block.rebuild();
		blocks.add(block);
		table.setWidget(table.getRowCount(), 0, block);
		scroller.scrollToBottom();
	}

	public ABlockWidget getSelectedBlock() {
		if (selectedRow < 0) return null;
		return blocks.get(selectedRow);
	}

	public void selectRow(int row) {
		if (row == selectedRow) return;
		deselect();
		ABlockWidget block = blocks.get(row);
		block.addStyleName("BlockWidget-selected");
		if (!sidebarMode) {
			block.setExtended(true);
		}
		selectedRow = row;
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
		block.removeStyleName("BlockWidget-selected");
		block.setExtended(false);
	}

	private class Listener implements TableListener {

		public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
			selectRow(row);
		}
	}

}
