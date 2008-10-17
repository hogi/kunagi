package scrum.client.common;

import java.util.LinkedList;
import java.util.List;

import scrum.client.ScrumGwtApplication;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.allen_sauer.gwt.dnd.client.util.CoordinateLocation;
import com.allen_sauer.gwt.dnd.client.util.WidgetArea;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;

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
		scroller.setStyleName("BlockListWidget");
		scroller.add(table);

		initWidget(scroller);
	}

	public void addBlock(ABlockWidget block) {
		if (sidebarMode) block.setInClipboard(true);

		block.makeDraggable();
		createDropControllerFor(block);

		block.rebuild();
		blocks.add(block);
		table.setWidget(table.getRowCount(), 0, block);
		scroller.scrollToBottom();
	}

	public void addBlockAt(int index, ABlockWidget block) {
		if (sidebarMode) block.setInClipboard(true);

		block.makeDraggable();
		createDropControllerFor(block);

		block.rebuild();
		// for (int i = blocks.size() - 1; i >= index; i--) {
		// blocks.add(i + 1, blocks.get(i));
		// }
		blocks.add(index, block);
		table.insertRow(index);
		table.setWidget(index, 0, block);
		// scroller.scrollToBottom();
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

	private DropController createDropControllerFor(final ABlockWidget block) {
		DropController dropController = new DropController() {

			public Widget getDropTarget() {
				return block;
			}

			public void onDrop(DragContext context) {
				WidgetArea area = new WidgetArea(block, null);
				CoordinateLocation location = new CoordinateLocation(context.mouseX, context.mouseY);

				ScrumGwtApplication.getDragController().unregisterDropController(this);
				// removeRow(indexOf((ABlockWidget) context.draggable));
				if (isHigher(area, location)) {
					addBlockAt(blocks.indexOf(block), (ABlockWidget) context.draggable);
				} else {
					addBlockAt(blocks.indexOf(block) + 1, (ABlockWidget) context.draggable);
				}
			}

			private String pos = null;
			private final String HIGHER = "top";
			private final String LOWER = "bottom";

			public void onEnter(DragContext context) {
				System.out.println("Entering block " + blocks.indexOf(block) + ".");
			}

			public void onLeave(DragContext context) {
				System.out.println("Leaving block " + blocks.indexOf(block) + ".");
			}

			public void onMove(DragContext context) {
				WidgetArea area = new WidgetArea(block, null);
				CoordinateLocation location = new CoordinateLocation(context.mouseX, context.mouseY);
				String newPos = (isHigher(area, location)) ? HIGHER : LOWER;
				if (newPos != pos) {
					pos = newPos;
					System.out.println(pos);
				}
			}

			public void onPreviewDrop(DragContext context) throws VetoDragException {}

			private boolean isHigher(WidgetArea area, CoordinateLocation location) {
				int mid = area.getCenter().getTop();
				return location.getTop() < mid;
			}
		};
		ScrumGwtApplication.getDragController().registerDropController(dropController);
		return dropController;
	}
}