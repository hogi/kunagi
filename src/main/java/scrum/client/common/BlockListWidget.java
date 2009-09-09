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
public final class BlockListWidget<O> extends AWidget {

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
	private FlowPanel panel;
	private GenericPredicate<O> predicate;
	private boolean sortingFlag;

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

		panel = new FlowPanel();
		panel.setStyleName("BlockListWidget");
		panel.add(table);
		panel.add(dndMarkerBottom);
		return panel;
	}

	public void addAdditionalStyleName(String styleName) {
		initialize();
		panel.addStyleName(styleName);
	}

	public void removeAdditionalStyleName(String styleName) {
		initialize();
		panel.removeStyleName(styleName);
	}

	@Override
	protected void onUpdate() {
		for (Iterator<ABlockWidget<O>> it = widgetIterator(); it.hasNext();) {
			it.next().update();
		}
		if (autoSorter != null) sort(autoSorter);
	}

	public ABlockWidget<O> getBlock(int row) {
		ABlockWidget<O> block = (ABlockWidget<O>) table.getWidget(row, 0);
		assert block != null : "Block at row " + row + " of " + table.getRowCount() + " is null";
		return block;
	}

	public ABlockWidget<O> getBlock(O object) {
		for (Iterator<ABlockWidget<O>> it = widgetIterator(); it.hasNext();) {
			ABlockWidget<O> block = it.next();
			if (block.getObject().equals(object)) return block;
		}
		return null;
	}

	public final void sort(Comparator<O> comparator) {
		sortingFlag = true;
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
		sortingFlag = false;
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

	public final void setObjects(Collection<O> newObjects) {
		initialize();

		Collection<O> objects = getObjects();

		// add new objects if not already existing
		List<O> objectsToAdd = null;
		for (O newObject : newObjects) {
			if (objects.contains(newObject)) continue;
			if (objectsToAdd == null) objectsToAdd = new ArrayList<O>();
			objectsToAdd.add(newObject);
		}
		if (objectsToAdd != null) addObjects((O[]) objectsToAdd.toArray());

		if (objects.size() != newObjects.size()) {
			// remove existing objects, which are not in the new list
			List<O> objectsToRemove = null;
			for (O object : objects) {
				if (newObjects.contains(object)) continue;
				if (objectsToRemove == null) objectsToRemove = new ArrayList<O>();
				objectsToRemove.add(object);
			}
			if (objectsToRemove != null) {
				for (O object : objectsToRemove)
					removeObjects(object);
			}
		}

		assert table.getRowCount() == newObjects.size();

		update();
	}

	public final void addObject(O object, boolean select) {
		addObjects(object);

		if (select) selectObject(object);
	}

	public final void addObjects(O... objects) {
		initialize();

		ABlockWidget<O>[] blocks = new ABlockWidget[objects.length];
		for (int i = 0; i < blocks.length; i++) {
			assert objects[i] != null;
			blocks[i] = blockWidgetFactory.createBlock();
			blocks[i].setObject(objects[i]);
			blocks[i].setList(this);
		}

		addBlocks(0, blocks);

		if (autoSorter != null) sort(autoSorter);
	}

	private void addBlocks(int toIndex, ABlockWidget<O>... blocks) {
		int oldSize = table.getRowCount();

		for (int i = 0; i < blocks.length; i++) {
			assert blocks[i] != null;
			table.insertRow(toIndex + i);
			table.setWidget(toIndex + i, 0, blocks[i]);
			blocks[i].update();
		}

		assert table.getRowCount() == oldSize + blocks.length;

		if (toIndex <= selectedRow) selectedRow++;
	}

	public final void move(ABlockWidget<O> block, int toRow) {
		if (block == null) throw new IllegalArgumentException("block == null");
		int fromRow = indexOf(block);
		if (fromRow < 0) throw new IllegalArgumentException("Block not in list: " + block);
		if (fromRow == toRow) return;
		boolean selected = selectedRow == fromRow;
		int oldSize = table.getRowCount();
		removeRows(fromRow);
		addBlocks(toRow, block);
		if (selected) selectedRow = toRow;

		assert oldSize == table.getRowCount();

		if (!sortingFlag) {
			if (moveObserver != null) moveObserver.onBlockMoved();
			if (autoSorter != null) sort(autoSorter);
		}
	}

	public final void drop(ABlockWidget<O> block, int toIndex) {
		if (block == null) throw new IllegalArgumentException("block == null");
		int fromIndex = block.getList().indexOf(block);
		dropAction.execute(block, block.getList(), fromIndex, this, toIndex);
	}

	public final O getObject(int row) {
		return getBlock(row).getObject();
	}

	public final int size() {
		return table.getRowCount();
	}

	public final int indexOf(ABlockWidget<O> block) {
		return indexOf(block.getObject());
	}

	public final int indexOf(O object) {
		for (BlockListObjectIterator<O> it = objectIterator(); it.hasNext();)
			if (it.next().equals(object)) return it.getIndex();
		return -1;
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

		getBlock(row).setSelected(true);
		selectedRow = row;
	}

	public final void deselectRow(int row) {
		if (selectedRow == -1) return;
		if (row != selectedRow) return;
		getBlock(row).setSelected(false);
		selectedRow = -1;
	}

	public final void deselectObject(O object) {
		deselectRow(indexOf(object));
	}

	public final void scrollToSelectedBlock() {
		if (selectedRow < 0) return;
		getBlock(selectedRow).getElement().scrollIntoView();
	}

	public final void removeObjects(O... objects) {
		int[] rows = new int[objects.length];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = indexOf(objects[i]);
		}
		removeRows(rows);
	}

	public final void removeSelectedRow() {
		removeRows(selectedRow);
	}

	public final void removeRows(int... rows) {
		int oldSize = table.getRowCount();

		for (int row : rows) {
			if (row < 0 || row >= size()) return;

			table.removeRow(row);

			if (selectedRow == row) {
				selectedRow = -1;
			} else if (selectedRow > row) {
				selectedRow--;
			}
		}

		assert table.getRowCount() == oldSize - rows.length;
	}

	public final void selectObject(O object) {
		int idx = indexOf(object);
		selectRow(idx);
		assert isSelected(object);
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

	public final boolean contains(ABlockWidget<O> block) {
		return contains(block.getObject());
	}

	public final boolean contains(O object) {
		for (Iterator<O> it = objectIterator(); it.hasNext();)
			if (it.next().equals(object)) return true;
		return false;
	}

	public final void deselect() {
		if (selectedRow == -1) return;
		for (Iterator<ABlockWidget<O>> it = widgetIterator(); it.hasNext();) {
			it.next().setSelected(false);
		}
		selectedRow = -1;
	}

	private ABlockWidget<O> getPreviousBlock(ABlockWidget<O> block) {
		int idx = indexOf(block);
		if (idx < 1) return null;
		return getBlock(idx - 1);
	}

	private ABlockWidget<O> getNextBlock(ABlockWidget<O> block) {
		int idx = indexOf(block);
		if (idx < 0 || idx > size() - 2) return null;
		return getBlock(idx + 1);
	}

	public void deactivateDndMarkers() {
		for (Iterator<ABlockWidget<O>> it = widgetIterator(); it.hasNext();) {
			it.next().deactivateDndMarkers();
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
		for (Iterator<O> it = objectIterator(); it.hasNext();) {
			ret.add(it.next());
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
		for (Iterator<ABlockWidget<O>> it = widgetIterator(); it.hasNext();) {
			ABlockWidget<O> block = it.next();
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

	public BlockListObjectIterator<O> objectIterator() {
		initialize();
		return new BlockListObjectIterator<O>(table);
	}

	public Iterator<ABlockWidget<O>> widgetIterator() {
		initialize();
		return new BlockListWidgetIterator<ABlockWidget<O>>(table);
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

		public int getIndex() {
			return widgetIterator.getIndex();
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

		public int getIndex() {
			return i;
		}
	}

}