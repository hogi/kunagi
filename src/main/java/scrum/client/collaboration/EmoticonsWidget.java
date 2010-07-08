package scrum.client.collaboration;

import ilarkesto.gwt.client.TableBuilder;

import java.util.List;

import scrum.client.common.AScrumGwtEntity;
import scrum.client.common.AScrumWidget;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class EmoticonsWidget extends AScrumWidget {

	private AScrumGwtEntity entity;

	private SimplePanel wrapper;

	public EmoticonsWidget(AScrumGwtEntity entity) {
		super();
		this.entity = entity;
	}

	@Override
	protected Widget onInitialization() {
		wrapper = new SimplePanel();
		wrapper.setStyleName("EmoticonsWidget");
		return wrapper;
	}

	@Override
	protected void onUpdate() {
		wrapper.clear();

		List<Emoticon> emoticons = entity.getEmoticons();
		TableBuilder tb = new TableBuilder();
		tb.setWidth(null);
		for (Emoticon emoticon : emoticons) {
			// if (emoticon.getOwner() == currentUser) continue;
			tb.add(createEmoticonWidget(emoticon));
		}
		// tb.add(createEmoticonEditor());
		wrapper.setWidget(tb.createTable());
	}

	private Widget createEmoticonWidget(Emoticon emoticon) {
		Image img = new Image(getEmotionImage(emoticon.getEmotion()), 0, 0, 16, 16);
		img.setTitle(emoticon.getTooltip());
		Style imgStyle = img.getElement().getStyle();
		imgStyle.setMarginLeft(1, Unit.PX);
		imgStyle.setMarginTop(1, Unit.PX);
		return img;
	}

	private String getEmotionImage(String emotion) {
		if (emotion == null) emotion = "none";
		return "emoticons/" + emotion + ".png";
	}
}
