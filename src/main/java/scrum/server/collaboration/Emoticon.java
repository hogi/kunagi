package scrum.server.collaboration;

import ilarkesto.auth.Auth;
import scrum.server.admin.User;

public class Emoticon extends GEmoticon {

	public boolean isVisibleFor(User user) {
		return Auth.isVisible(getParent(), user);
	}

	public boolean isEditableBy(User user) {
		return isOwner(user);
	}

	@Override
	public void ensureIntegrity() {
		if (isEmotionSet() && !scrum.client.collaboration.Emoticon.EMOTIONS.contains(getEmotion())) {
			getDao().deleteEntity(this);
			return;
		}
		super.ensureIntegrity();
	}

}