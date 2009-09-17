package scrum.client.dnd;

import scrum.client.animation.AppearAnimation;
import scrum.client.animation.CompletionListener;
import scrum.client.animation.DisappearAnimation;
import scrum.client.animation.ObservableAnimation;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockListWidget;

public class MoveDropAction<T> implements BlockListDropAction<T> {

	public boolean execute(final ABlockWidget block, final BlockListWidget fromList, final int fromIndex,
			final BlockListWidget toList, final int toIndex) {
		if (fromList != toList) return false;
		final int height = block.getOffsetHeight();
		DisappearAnimation aniDisappear = new DisappearAnimation(block, height);
		aniDisappear.addCompletionListener(new CompletionListener() {

			public void completionEvent(ObservableAnimation source) {
				AppearAnimation aniAppear = new AppearAnimation(block, height);
				toList.move(block, toIndex);
				aniAppear.run(180);
			}
		});
		aniDisappear.run(180);

		return true;
	}
}
