package scrum.client.workspace;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public abstract class AClipboardItemWidget extends Composite {

	private HorizontalPanel mainpanel;

	private Image image;

	public AClipboardItemWidget() {
		mainpanel = new HorizontalPanel();

		image = getIcon().createImage();
		mainpanel.add(image);
		mainpanel.add(new Label(getLabel()));

		initWidget(mainpanel);
	}

	public Image getDragHandle() {
		return image;
	}

	public abstract AbstractImagePrototype getIcon();

	protected abstract String getLabel();

}
