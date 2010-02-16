package scrum.client.collaboration;

import ilarkesto.gwt.client.DropdownMenuButtonWidget;
import ilarkesto.gwt.client.TableBuilder;

import java.util.List;

import scrum.client.admin.User;
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

		User currentUser = getCurrentUser();
		List<Emoticon> emoticons = entity.getEmoticons();
		TableBuilder tb = new TableBuilder();
		tb.setWidth(null);
		for (Emoticon emoticon : emoticons) {
			if (emoticon.getOwner() == currentUser) continue;
			tb.add(createEmoticonWidget(emoticon));
		}
		tb.add(createEmoticonEditor());
		wrapper.setWidget(tb.createTable());
	}

	private Widget createEmoticonEditor() {
		Emoticon emoticon = entity.getCurrentUserEmoticon();
		DropdownMenuButtonWidget dropdown = new DropdownMenuButtonWidget();
		dropdown.setLabel("<img src='" + getEmotionImage(emoticon == null ? null : emoticon.getEmotion())
				+ "' style='margin: 1px 0px 0px 0px;'>");
		dropdown.getElement().getStyle().setMarginLeft(2, Unit.PX);
		if (emoticon != null) dropdown.setTitle(emoticon.getEmotionLabel());
		dropdown.addAction(new SetEmoticonAction(entity, null));
		for (String emotion : Emoticon.EMOTIONS) {
			dropdown.addAction(new SetEmoticonAction(entity, emotion));
		}
		return dropdown.update();
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
