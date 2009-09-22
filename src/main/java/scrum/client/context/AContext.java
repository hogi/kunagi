package scrum.client.context;

import com.google.gwt.user.client.ui.Widget;

public abstract class AContext {

	public abstract Widget getSidebarWidget();

	public abstract Widget getWorkareaWidget();

	@Override
	public String toString() {
		return getClass().getName();
	}
}
