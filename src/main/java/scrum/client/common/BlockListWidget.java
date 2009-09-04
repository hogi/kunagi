package scrum.client.common;

import ilarkesto.gwt.client.AWidget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import scrum.client.dnd.BlockDndMarkerWidget;
import scrum.client.dnd.BlockListDropAction;
import scrum.client.dnd.DndManager;
import scrum.client.dnd.MoveDropAction;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * List of <code>BlockWidget</code>s.
 */
public final class BlockListWidget<O extends Object> extends AWidget {

	// private static final Logger LOG = Logger.get(BlockListWidget.class);

	private FlexTable table;
	private List<ABlockWidget<O>> blocks = new LinkedList<ABlockWidget<O>>();
	private List<O> objects = new LinkedList<O>();
	private int selectedRow = -1;
	private boolean dndSorting = true;
	private Comparator<O> autoSorter;
	private BlockWidgetFactory<O> blockWidgetFactory;
	private BlockMoveObserver<O> moveObserver;
	private BlockListSelectionManager selectionManager;
	private BlockListDropAction<O> dropAction;
	private BlockDndMarkerWidget dndMarkerBottom;

	public BlockListWidget(BlockWidgetFactory<O> blockWidgetFactory) {
		this(blockWidgetFactory, new MoveDropAction<O>());
	}

	public BlockListWidget(BlockWidgetFactory<O> blockWidgetFactory, BlockListDropAction<O> dropAction) {
		this.dropAction = dropAction;
		this.blockWidgetFactory = blockWidgetFactory;

		dndMarkerBottom = new BlockDndMarkerWidget();
	}

	public void setSelectionManager(BlockListSelectionManager selectionManager) {
		this.selectionManager = selectionManager;
		selectionManager.add(this);
	}

	@Override
	protected Widget onInitialization() {
		table = new FlexTable();
		table.setCellPadding(0);
		table.setCellSpacing(0);
		table.setStyleName("BlockListWidget-table");
		table.addClickHandler(new TableClickHandler());

		FlowPanel panel = new FlowPanel();
		panel.setStyleName("BlockListWidget");
		panel.add(table);
		panel.add(dndMarkerBottom);
		return panel;
	}

	@Override
	protected void onUpdate() {
		for (ABlockWidget<O> block : blocks) {
			block.update();
		}
		if (autoSorter != null) sort(autoSorter);
	}

	public final void sort(Comparator<O> comparator) {
		List<O> sortedObjects = new ArrayList<O>(objects);
		Collections.sort(sortedObjects, comparator);
		int size = size();
		for (int i = 0; i < size; i++) {
			O sortedObject = sortedObjects.get(i);
			int index = objects.indexOf(sortedObject);
			if (index != i) {
				move(blocks.get(index), i);
			}
		}
	}

	public void setMoveObserver(BlockMoveObserver<O> orderObserver) {
		this.moveObserver = orderObserver;
	}

	public final void setAutoSorter(Comparator<O> autoSorter) {
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

	public final void setBlocks(Collection<O> newObjects) {
		initialize();

		// add new objects if not already existing
		List<O> objectsToAdd = null;
		for (O newObject : newObjects) {
			if (objects.contains(newObject)) continue;
			if (objectsToAdd == null) objectsToAdd = new ArrayList<O>();
			objectsToAdd.add(newObject);
		}
		if (objectsToAdd != null) {
			for (O newObject : objectsToAdd)
				addBlock(newObject);
		}

		if (objects.size() == newObjects.size()) {
			update();
			return;
		}

		// remove existing objects, which are not in the new list
		List<O> objectsToRemove = null;
		for (O object : objects) {
			if (newObjects.contains(object)) continue;
			if (objectsToRemove == null) objectsToRemove = new ArrayList<O>();
			objectsToRemove.add(object);
		}
		if (objectsToRemove != null) {
			for (O object : objectsToRemove)
				removeObject(object);
		}

		update();
	}

	public final ABlockWidget<O> addBlock(O object) {
		return addBlock(object, false);
	}

	public final ABlockWidget<O> addBlock(O object, boolean select) {
		initialize();

		ABlockWidget<O> block = blockWidgetFactory.createBlock();
		block.setObject(object);
		block.setList(this);

		objects.add(object);
		blocks.add(block);
		table.setWidget(table.getRowCount(), 0, block);
		block.update();

		if (select) selectObject(object);

		return block;
	}

	public ABlockWidget<O> addBlock(ABlockWidget<O> block, int toIndex) {
		blocks.add(toIndex, block);
		objects.add(toIndex, block.getObject());
		table.insertRow(toIndex);
		table.setWidget(toIndex, 0, block);
		return block;
	}

	public final void move(ABlockWidget<O> block, int toIndex) {
		if (block == null) throw new IllegalArgumentException("block == null");
		int fromIndex = block.getList().indexOf(block);
		if (fromIndex == toIndex) return;
		removeRow(fromIndex);
		addBlock(block, toIndex);
	}

	public final void drop(ABlockWidget<O> block, int toIndex) {
		if (block == null) throw new IllegalArgumentException("block == null");
		int fromIndex = block.getList().indexOf(block);
		if (dropAction.execute(block, block.getList(), fromIndex, (BlockListWidget<ABlockWidget>) this, toIndex))
			if (moveObserver != null) moveObserver.onBlockMoved();
	}

	public final O getObject(int index) {
		return objects.get(index);
	}

	public final int size() {
		return blocks.size();
	}

	public final int indexOf(ABlockWidget<O> block) {
		return blocks.indexOf(block);
	}

	public final int indexOf(Object object) {
		return objects.indexOf(object);
	}

	public final O getSelectedObject() {
		if (selectedRow < 0) return null;
		return getObject(selectedRow);
	}

	public final void selectRow(int row) {
		if (row == selectedRow) { return; }

		if (selectionManager == null) {
			deselect();
		} else {
			selectionManager.deselectAll();
		}

		blocks.get(row).setSelected(true);
		selectedRow = row;
	}

	public final void scrollToSelectedBlock() {
		if (selectedRow < 0) return;
		blocks.get(selectedRow).getElement().scrollIntoView();
	}

	public final void removeObject(Object object) {
		removeRow(indexOf(object));
	}

	public final void removeSelectedRow() {
		removeRow(selectedRow);
	}

	public final void removeRow(int row) {
		if (row < 0 || row >= size()) return;
		blocks.remove(row);
		objects.remove(row);
		table.removeRow(row);
		if (selectedRow == row) {
			selectedRow = -1;
		} else if (selectedRow > row) {
			selectedRow--;
		}
	}

	public final void selectObject(O object) {
		int idx = objects.indexOf(object);
		selectRow(idx);
	}

	public final void toggleSelection(O object) {
		if (isSelected(object)) {
			deselect();
		} else {
			selectObject(object);
		}
	}

	public final boolean isSelected(O object) {
		return getSelectedObject() == object;
	}

	public final boolean contains(Object object) {
		return objects.contains(object);
	}

	public final void deselect() {
		if (selectedRow == -1) return;
		for (ABlockWidget block : blocks) {
			block.setSelected(false);
		}
		selectedRow = -1;
	}

	private ABlockWidget<O> getPreviousBlock(ABlockWidget<O> block) {
		int idx = blocks.indexOf(block);
		if (idx < 1) return null;
		return blocks.get(idx - 1);
	}

	private ABlockWidget<O> getNextBlock(ABlockWidget<O> block) {
		int idx = blocks.indexOf(block);
		if (idx < 0 || idx > blocks.size() - 2) return null;
		return blocks.get(idx + 1);
	}

	public void deactivateDndMarkers() {
		for (ABlockWidget<O> block : blocks) {
			block.deactivateDndMarkers();
		}
		dndMarkerBottom.setActive(false);
	}

	public void deactivateDndMarkers(ABlockWidget<O> block) {
		block.deactivateDndMarkers();
		ABlockWidget<O> previous = getPreviousBlock(block);
		if (previous != null) previous.deactivateDndMarkers();
		ABlockWidget<O> next = getNextBlock(block);
		if (next != null) next.deactivateDndMarkers();
		dndMarkerBottom.setActive(false);
	}

	public void activateDndMarkerBefore(ABlockWidget<O> block) {
		ABlockWidget<O> previous = getPreviousBlock(block);
		if (previous != null) previous.deactivateDndMarkers();
		ABlockWidget<O> next = getNextBlock(block);
		if (next != null) next.deactivateDndMarkers();
		dndMarkerBottom.setActive(false);

		block.activateDndMarkerTop();
	}

	public void activateDndMarkerAfter(ABlockWidget<O> block) {
		deactivateDndMarkers(block);
		ABlockWidget<O> next = getNextBlock(block);
		if (next == null) {
			dndMarkerBottom.setActive(true);
		} else {
			next.activateDndMarkerTop();
		}
	}

	public void activateDrop() {
		dndMarkerBottom.setActive(true);
	}

	public void deactivateDrop() {
		dndMarkerBottom.setActive(false);
	}

	private final class TableClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
		// Cell cell = table.getCellForEvent(event);
		// if (cell == null) return;
		// int row = cell.getRowIndex();
		// if (row == selectedRow) {
		// // nop
		// } else {
		// selectRow(row);
		// }
		}

	}

	public List<O> getObjects() {
		return objects;
	}

	public boolean acceptsDrop(ABlockWidget block) {
		return this.blockWidgetFactory.isSameType(block);
		// return this == block.getList();
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		DndManager.get().registerDropTarget(this);
	}

	@Override
	protected void onUnload() {
		DndManager.get().unregisterDropTarget(this);
		super.onUnload();
	}

}