package scrum.server.collaboration;

import scrum.server.common.Transient;

public class ChatMessage extends GChatMessage implements Transient {

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		getDao().deleteEntity(this);
	}

}