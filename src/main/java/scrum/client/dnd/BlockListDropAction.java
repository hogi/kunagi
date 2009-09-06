package scrum.client.dnd;

import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;

public interface BlockListDropAction<T> {

	public boolean execute(ABlockWidget<T> block, BlockListWidget<T> fromList, int fromIndex,
			BlockListWidget<T> toList, int toIndex);
}
