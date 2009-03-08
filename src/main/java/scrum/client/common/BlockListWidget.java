package scrum.client.common;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.GwtLogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
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

	public BlockListWidget(BlockWidgetFactory<O> blockWidgetFactory) {
		this.blockWidgetFactory = blockWidgetFactory;
	}

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
				moveBlock(blocks.get(index), i);
			}
		}
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

		if (objects.size() == newObjects.size()) return;

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

	public final void moveBlock(ABlockWidget<O> block, int toIndex) {
		if (block == null) throw new IllegalArgumentException("block == null");
		int fromIndex = indexOf(block);
		GwtLogger.DEBUG("moving block from", fromIndex, "to", toIndex);

		blocks.remove(fromIndex);
		blocks.add(toIndex, block);

		objects.remove(fromIndex);
		objects.add(toIndex, block.getObject());

		table.removeRow(fromIndex);
		table.insertRow(toIndex);
		table.setWidget(toIndex, 0, block);
	}

	public final O getObject(int index) {
		return objects.get(index);
	}

	public final int size() {
		return blocks.size();
	}

	public final int indexOf(ABlockWidget block) {
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

		if (row == selectedRow) return;

		deselect();

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

	public final void selectObject(Object object) {
		selectRow(objects.indexOf(object));
	}

	public final void deselect() {
		for (ABlockWidget block : blocks) {
			block.setSelected(false);
		}
		selectedRow = -1;
	}

	private final class Listener implements TableListener {

		public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
			selectRow(row);
		}
	}

	public List<O> getObjects() {
		return objects;
	}
}