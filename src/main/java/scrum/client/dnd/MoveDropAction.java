package scrum.client.dnd;

import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;

public class MoveDropAction<T> implements BlockListDropAction<T> {

	public boolean execute(ABlockWidget block, BlockListWidget fromList, int fromIndex, BlockListWidget toList,
			int toIndex) {
		if (fromList != toList) return false;

		fromList.removeRow(fromIndex);
		toList.addBlock(block, toIndex);

		return true;
	}
}
