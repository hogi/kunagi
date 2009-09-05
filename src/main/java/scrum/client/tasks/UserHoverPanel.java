package scrum.client.tasks;

import ilarkesto.gwt.client.AWidget;
import scrum.client.GenericPredicate;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.User;
import scrum.client.sprint.Task;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class UserHoverPanel extends AWidget {

	private WhiteboardWidget widget;
	private HorizontalPanel panel;

	public UserHoverPanel(WhiteboardWidget widget) {
		this.widget = widget;
	}

	@Override
	protected Widget onInitialization() {
		panel = new HorizontalPanel();
		Label text = new Label("Highlight user's tasks: ");
		text.addStyleName("UserHoverPanel-label");
		panel.add(text);
		for (final User user : ScrumGwtApplication.get().getDao().getUsers()) {
			final Label label = new Label(user.getName());
			label.setStyleName("UserHoverPanel-label");
			label.addMouseOverHandler(new MouseOverHandler() {

				public void onMouseOver(MouseOverEvent event) {
					widget.setTaskHighlighting(new ByUserPredicate(user));
					label.addStyleName("highlighted");
				}
			});
			label.addMouseOutHandler(new MouseOutHandler() {

				public void onMouseOut(MouseOutEvent event) {
					widget.clearTaskHighlighting();
					label.removeStyleName("highlighted");
				}

			});
			panel.add(label);
		}
		return panel;
	}

	@Override
	protected void onUpdate() {

	}

	private class ByUserPredicate implements GenericPredicate<Task> {

		private final User user;

		public ByUserPredicate(User user) {
			this.user = user;
		}

		public boolean contains(Task element) {
			return element.getOwner() != null && element.getOwner().equals(user);
		}
	}
}
