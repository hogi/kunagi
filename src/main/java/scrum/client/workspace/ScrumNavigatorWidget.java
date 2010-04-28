package scrum.client.workspace;

import ilarkesto.core.base.Str;
import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.SwitchingNavigatorWidget;

import com.google.gwt.user.client.ui.Widget;

public class ScrumNavigatorWidget extends SwitchingNavigatorWidget {

	public ScrumNavigatorWidget() {
		super(Scope.get().getComponent(Ui.class).getWorkspace().getWorkarea());
	}

	@Override
	protected void showItem(Widget widget) {
		super.showItem(widget);
		String page = Str.getSimpleName(widget.getClass());
		page = page.substring(0, page.length() - 6);
		Scope.get().getComponent(Navigator.class).setPageToken(page);
	}

}
