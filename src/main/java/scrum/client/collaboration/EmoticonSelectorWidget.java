package scrum.client.collaboration;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.common.AScrumGwtEntity;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.VisibleDataChangedEvent;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class EmoticonSelectorWidget extends AScrumWidget {

	private AScrumGwtEntity entity;

	private SimplePanel wrapper;

	public EmoticonSelectorWidget(AScrumGwtEntity entity) {
		super();
		this.entity = entity;
	}

	@Override
	protected Widget onInitialization() {
		wrapper = new SimplePanel();
		wrapper.setStyleName("EmoticonSelectorWidget");
		return wrapper;
	}

	@Override
	protected void onUpdate() {
		Emoticon currentEmoticon = entity.getCurrentUserEmoticon();

		TableBuilder tb = new TableBuilder();
		tb.setWidth(null);
		for (String emotion : Emoticon.EMOTIONS) {
			boolean current = currentEmoticon != null && emotion.equals(currentEmoticon.getEmotion());
			tb.add(createEmoticonWidget(emotion, current));
		}
		tb.add(Gwt.createSpacer(10, 1));
		tb.add(createEmoticonWidget(null, currentEmoticon == null || currentEmoticon.getEmotion() == null));
		wrapper.setWidget(tb.createTable());
	}

	private Widget createEmoticonWidget(String emotion, boolean selected) {
		Image img = new Image(getEmotionImage(emotion), 0, 0, 16, 16);
		Style imgStyle = img.getElement().getStyle();
		imgStyle.setMarginLeft(1, Unit.PX);
		imgStyle.setMarginTop(1, Unit.PX);

		FocusPanel panel = new FocusPanel(img);
		panel.setStyleName("EmoticonSelectorWidget-emoticon");
		if (selected) {
			panel.addStyleName("EmoticonSelectorWidget-emoticon-selected");
		} else {
			panel.addClickHandler(new SelectEmotionClickHandler(emotion));
		}
		return panel;
	}

	private String getEmotionImage(String emotion) {
		if (emotion == null) emotion = "none";
		return "emoticons/" + emotion + ".png";
	}

	class SelectEmotionClickHandler implements ClickHandler {

		private String emotion;

		public SelectEmotionClickHandler(String emotion) {
			super();
			this.emotion = emotion;
		}

		public void onClick(ClickEvent event) {
			entity.setCurrentUserEmoticon(emotion);
			new VisibleDataChangedEvent().fireInCurrentScope();
		}
	}
}
