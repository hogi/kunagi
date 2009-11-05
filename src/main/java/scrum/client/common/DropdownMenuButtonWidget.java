package scrum.client.common;

import ilarkesto.gwt.client.AAction;
import ilarkesto.gwt.client.AWidget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

public class DropdownMenuButtonWidget extends AWidget {

	private List<AAction> actions;
	private MenuBar menu;

	@Override
	protected Widget onInitialization() {
		actions = new ArrayList<AAction>();

		menu = new MenuBar(true);

		MenuBar menuBar = new MenuBar();
		menuBar.addItem("Functions V", menu);
		menuBar.setPopupPosition(MenuBar.PopupPosition.LEFT);

		return menuBar;
	}

	@Override
	protected void onUpdate() {
		menu.clearItems();
		for (AAction action : actions) {
			if (!action.isExecutable()) continue;

			MenuItem menuItem;
			if (action.isPermitted()) {
				menuItem = new MenuItem(action.getLabel(), action);
			} else {
				menuItem = new MenuItem(action.getLabel(), (Command) null);
				menuItem.addStyleName("MenuItem-disabled");
			}
			menuItem.setTitle(action.getTooltip());
			menu.addItem(menuItem);
		}
	}

	public void addAction(AAction action) {
		initialize();
		actions.add(action);
	}

}
