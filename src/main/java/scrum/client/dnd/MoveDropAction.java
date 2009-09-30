package scrum.client.dnd;

import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;

public class MoveDropAction<T> implements BlockListDropAction<T> {

	public boolean execute(final ABlockWidget block, final BlockListWidget fromList, final int fromIndex,
			final BlockListWidget toList, final int toIndex) {
		if (fromList != toList) return false;
		toList.move(block, toIndex);
		return true;
	}
}
