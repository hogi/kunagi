package scrum.client.collaboration;

import ilarkesto.gwt.client.LabelProvider;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import scrum.client.common.AScrumGwtEntity;

public class Emoticon extends GEmoticon {

	public static transient final List<String> EMOTIONS = Arrays.asList("angry", "happy");

	public Emoticon(AScrumGwtEntity parent, String emotion) {
		setOwner(cm.getAuth().getUser());
		setParent(parent);
		setEmotion(emotion);
	}

	public Emoticon(Map data) {
		super(data);
	}

	public static String getEmotionLabel(String emotion) {
		return emotion;
	}

	@Override
	public String toString() {
		return getOwner() + ":" + getEmotion();
	}

	public transient static LabelProvider<String> EMOTION_LABEL_PROVIDER = new LabelProvider<String>() {

		public String getLabel(String emotion) {
			if (emotion == null) return null;
			return getEmotionLabel(emotion);
		}
	};

}
