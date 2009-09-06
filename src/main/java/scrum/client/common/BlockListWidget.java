package scrum.client.common;

import ilarkesto.gwt.client.AWidget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import scrum.client.GenericPredicate;
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
	private int selectedRow = -1;
	private boolean dndSorting = true;
	private Comparator<O> autoSorter;
	private BlockWidgetFactory<O> blockWidgetFactory;
	private BlockMoveObserver<O> moveObserver;
	private BlockListSelectionManager selectionManager;
	private BlockListDropAction<O> dropAction;
	private BlockDndMarkerWidget dndMarkerBottom;

	private GenericPredicate<O> predicate;

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
		for (ABlockWidget<O> block : getBlocks()) {
			block.update();
		}
		if (autoSorter != null) sort(autoSorter);
	}

	public ABlockWidget<O> getBlock(int row) {
		ABlockWidget<O> block = (ABlockWidget<O>) table.getWidget(row, 0);
		assert block != null : "Block at row " + row + " of " + table.getRowCount() + " is null";
		return block;
	}

	public ABlockWidget<O> getBlock(O object) {
		for (ABlockWidget<O> block : getBlocks()) {
			if (block.getObject().equals(object)) return block;
		}
		return null;
	}

	public List<ABlockWidget<O>> getBlocks() {
		int count = table.getRowCount();
		List<ABlockWidget<O>> ret = new ArrayList<ABlockWidget<O>>(count);
		for (int i = 0; i < count; i++) {
			ret.add(getBlock(i));
		}
		return ret;
	}

	public final void sort(Comparator<O> comparator) {
		List<O> objects = getObjects();
		List<O> sortedObjects = new ArrayList<O>(objects);
		Collections.sort(sortedObjects, comparator);
		int size = size();
		for (int i = 0; i < size; i++) {
			O sortedObject = sortedObjects.get(i);
			int index = objects.indexOf(sortedObject);
			if (index != i) {
				move(getBlock(sortedObject), i);
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
		int count = table.getRowCount();
		for (int i = count - 1; i >= 0; i--) {
			table.removeRow(i);
		}
		table.clear();
		assert table.getRowCount() == 0;
	}

	public final void setBlocks(Collection<O> newObjects) {
		initialize();

		Collection<O> objects = getObjects();

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

		if (objects.size() == newObjects.size()) { return; }

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

		assert table.getRowCount() == newObjects.size();
	}

	public final ABlockWidget<O> addBlock(O object) {
		return addBlock(object, false);
	}

	public final ABlockWidget<O> addBlock(O object, boolean select) {
		initialize();

		ABlockWidget<O> block = getBlock(object);
		if (block != null) return block;

		block = blockWidgetFactory.createBlock();
		block.setObject(object);
		block.setList(this);

		addBlock(block, 0);

		if (select) {
			selectObject(object);
		} else {
			block.update();
		}

		return block;
	}

	private ABlockWidget<O> addBlock(ABlockWidget<O> block, int toIndex) {
		if (block == null) throw new IllegalArgumentException("block == null");
		assert !getBlocks().contains(block);

		int oldSize = table.getRowCount();

		table.insertRow(toIndex);
		table.setWidget(toIndex, 0, block);

		assert table.getRowCount() == oldSize + 1;
		assert contains(block.getObject());
		return block;
	}

	public final void move(ABlockWidget<O> block, int toIndex) {
		if (block == null) throw new IllegalArgumentException("block == null");
		int fromIndex = indexOf(block);
		if (fromIndex < 0) throw new IllegalArgumentException("Block not in list: " + block);
		if (fromIndex == toIndex) return;
		int oldSize = table.getRowCount();
		removeRow(fromIndex);
		addBlock(block, toIndex);
		assert oldSize == table.getRowCount();
	}

	public final void drop(ABlockWidget<O> block, int toIndex) {
		if (block == null) throw new IllegalArgumentException("block == null");
		int fromIndex = block.getList().indexOf(block);
		if (dropAction.execute(block, block.getList(), fromIndex, (BlockListWidget<ABlockWidget>) this, toIndex))
			if (moveObserver != null) moveObserver.onBlockMoved();
	}

	public final O getObject(int row) {
		return getBlock(row).getObject();
	}

	public final int size() {
		return table.getRowCount();
	}

	public final int indexOf(ABlockWidget<O> block) {
		return getBlocks().indexOf(block);
	}

	public final int indexOf(Object object) {
		return getObjects().indexOf(object);
	}

	public final O getSelectedObject() {
		if (selectedRow < 0) return null;
		return getObject(selectedRow);
	}

	public final void selectRow(int row) {
		if (row == selectedRow) return;

		if (selectionManager == null) {
			deselect();
		} else {
			selectionManager.deselectAll();
		}

		getBlocks().get(row).setSelected(true);
		selectedRow = row;
	}

	public final void deselectRow(int row) {
		if (selectedRow == -1) return;
		if (row != selectedRow) return;
		getBlocks().get(row).setSelected(false);
		selectedRow = -1;
	}

	public final void deselectObject(O object) {
		deselectRow(indexOf(object));
	}

	public final void scrollToSelectedBlock() {
		if (selectedRow < 0) return;
		getBlocks().get(selectedRow).getElement().scrollIntoView();
	}

	public final void removeObject(Object object) {
		removeRow(indexOf(object));
	}

	public final void removeSelectedRow() {
		removeRow(selectedRow);
	}

	public final void removeRow(int row) {
		if (row < 0 || row >= size()) return;
		int oldSize = table.getRowCount();

		table.removeRow(row);

		assert table.getRowCount() + 1 == oldSize;

		if (selectedRow == row) {
			selectedRow = -1;
		} else if (selectedRow > row) {
			selectedRow--;
		}
	}

	public final void selectObject(O object) {
		int idx = indexOf(object);
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
		return getObjects().contains(object);
	}

	public final void deselect() {
		if (selectedRow == -1) return;
		for (ABlockWidget block : getBlocks()) {
			block.setSelected(false);
		}
		selectedRow = -1;
	}

	private ABlockWidget<O> getPreviousBlock(ABlockWidget<O> block) {
		List<ABlockWidget<O>> blocks = getBlocks();
		int idx = blocks.indexOf(block);
		if (idx < 1) return null;
		return blocks.get(idx - 1);
	}

	private ABlockWidget<O> getNextBlock(ABlockWidget<O> block) {
		List<ABlockWidget<O>> blocks = getBlocks();
		int idx = blocks.indexOf(block);
		if (idx < 0 || idx > blocks.size() - 2) return null;
		return blocks.get(idx + 1);
	}

	public void deactivateDndMarkers() {
		for (ABlockWidget<O> block : getBlocks()) {
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
		List<O> ret = new ArrayList<O>(size());
		for (ABlockWidget<O> block : getBlocks()) {
			ret.add(block.getObject());
		}
		return ret;
	}

	public boolean acceptsDrop(ABlockWidget<O> block) {
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

	private void updateTaskHighlighting() {
		for (ABlockWidget<O> block : getBlocks()) {
			if (predicate != null && predicate.contains(block.getObject()))
				block.addStyleName("highlighted");
			else block.removeStyleName("highlighted");
		}
	}

	public void setTaskHighlighting(GenericPredicate<O> predicate) {
		this.predicate = predicate;
		updateTaskHighlighting();
	}

	public void clearTaskHighlighting() {
		this.predicate = null;
		updateTaskHighlighting();
	}

	public Iterator<O> objectIterator() {
		initialize();
		return new BlockListObjectIterator<O>(table);
	}

	public Iterator<O> widgetIterator() {
		initialize();
		return new BlockListWidgetIterator<O>(table);
	}

	private class BlockListObjectIterator<O> implements Iterator<O> {

		BlockListWidgetIterator<ABlockWidget<O>> widgetIterator;

		public BlockListObjectIterator(FlexTable table) {
			widgetIterator = new BlockListWidgetIterator<ABlockWidget<O>>(table);
		}

		public boolean hasNext() {
			return widgetIterator.hasNext();
		}

		public O next() {
			return widgetIterator.next().getObject();
		}

		public void remove() {
			widgetIterator.remove();
		}
	}

	private class BlockListWidgetIterator<W> implements Iterator<W> {

		int i = -1;
		final FlexTable table;

		public BlockListWidgetIterator(FlexTable table) {
			this.table = table;
		}

		public boolean hasNext() {
			return i < table.getRowCount() - 1;
		}

		public W next() {
			return (W) table.getWidget(++i, 0);
		}

		public void remove() {
			table.removeRow(i);
		}
	}
}