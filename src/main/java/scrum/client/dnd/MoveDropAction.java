package scrum.client.dnd;

import scrum.client.animation.AppearAnimation;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;

public class MoveDropAction<T> implements BlockListDropAction<T> {

	public boolean execute(ABlockWidget block, BlockListWidget fromList, int fromIndex, BlockListWidget toList,
			int toIndex) {
		if (fromList != toList) return false;
		AppearAnimation ani = new AppearAnimation(block, block.getOffsetHeight());
		toList.move(block, toIndex);
		ani.run(400);
		return true;
	}
}
