package scrum.client.common;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;

public class BlockListWidget extends Composite {

	private FlexTable table;
	private List<BlockWidget> blocks = new ArrayList<BlockWidget>();
	private int selectedRow = -1;

	public BlockListWidget() {
		table = new FlexTable();
		table.setWidth("100%");
		table.addTableListener(new Listener());

		SimplePanel panel = new SimplePanel();
		panel.setStyleName("BlockListWidget");
		panel.add(table);

		initWidget(panel);
	}

	public void addBlock(BlockWidget block) {
		block.rebuild();
		blocks.add(block);
		table.setWidget(table.getRowCount(), 0, block);
	}

	public BlockWidget getSelectedBlock() {
		if (selectedRow < 0) return null;
		return blocks.get(selectedRow);
	}

	public void selectRow(int row) {
		if (row == selectedRow) return;
		deselect();
		BlockWidget block = blocks.get(row);
		block.addStyleName("BlockWidget-selected");
		block.setExtended(true);
		selectedRow = row;
	}

	public void deselect() {
		BlockWidget block = getSelectedBlock();
		if (block == null) return;
		block.removeStyleName("BlockWidget-selected");
		block.setExtended(false);
	}

	class Listener implements TableListener {

		public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
			selectRow(row);
		}
	}

}
