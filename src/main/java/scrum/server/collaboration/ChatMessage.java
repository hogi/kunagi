package scrum.server.collaboration;

import scrum.server.admin.User;
import scrum.server.common.Transient;

public class ChatMessage extends GChatMessage implements Transient {

	public boolean isVisibleFor(User user) {
		return getProject().isVisibleFor(user);
	}

	public boolean isEditableBy(User user) {
		return false;
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		getDao().deleteEntity(this);
	}

}