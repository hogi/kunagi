package scrum.client.collaboration;

import scrum.client.common.AScrumAction;
import scrum.client.common.AScrumGwtEntity;

public class SetEmoticonAction extends AScrumAction {

	private AScrumGwtEntity entity;
	private String emotion;

	public SetEmoticonAction(AScrumGwtEntity entity, String emotion) {
		super();
		this.entity = entity;
		this.emotion = emotion;
	}

	@Override
	public String getLabel() {
		return Emoticon.getEmotionLabel(emotion);
	}

	@Override
	protected void onExecute() {
		entity.setCurrentUserEmoticon(emotion);
	}

}
