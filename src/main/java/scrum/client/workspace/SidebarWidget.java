package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;

import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class SidebarWidget extends AWidget {

	private SimplePanel wrapper;
	private Widget content;

	@Override
	protected Widget onInitialization() {
		setHeight100();
		wrapper = new SimplePanel();
		wrapper.setHeight("100%");
		return wrapper;
	}

	@Override
	protected void onUpdate() {
		wrapper.setWidget(content);
		AWidget.update(content);
	}

	public void show(Widget widget) {
		content = widget;
		update();
	}

}
