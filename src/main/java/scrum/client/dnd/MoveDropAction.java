package scrum.client.dnd;

import scrum.client.animation.AObservableAnimation;
import scrum.client.animation.AppearAnimation;
import scrum.client.animation.CompletionListener;
import scrum.client.animation.DisappearAnimation;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;

public class MoveDropAction<T> implements BlockListDropAction<T> {

	public boolean execute(final ABlockWidget block, final BlockListWidget fromList, final int fromIndex,
			final BlockListWidget toList, final int toIndex) {
		if (fromList != toList) return false;
		final int height = block.getOffsetHeight();
		DisappearAnimation aniDisappear = new DisappearAnimation(height, block);
		aniDisappear.addCompletionListener(new CompletionListener() {

			public void completionEvent(AObservableAnimation source) {
				AppearAnimation aniAppear = new AppearAnimation(height, block);
				toList.move(block, toIndex);
				aniAppear.run(180);
			}
		});
		aniDisappear.run(180);

		return true;
	}
}
