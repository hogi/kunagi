package scrum.client.collaboration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scrum.client.admin.User;
import scrum.client.common.AScrumWidget;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class UsersStatusWidget extends AScrumWidget {

	private FlowPanel containerPanel;
	private Map<User, UserStatusWidget> userWidgets = new HashMap<User, UserStatusWidget>();

	@Override
	protected Widget onInitialization() {

		containerPanel = new FlowPanel();
		containerPanel.setStyleName("UsersStatusWidget");

		return containerPanel;
	}

	@Override
	protected void onUpdate() {
		Project project = getCurrentProject();
		if (project == null) return;
		List<User> users = new ArrayList<User>(project.getParticipants());
		Collections.sort(users, User.ONLINE_OFFLINE_COMPARATOR);
		containerPanel.clear();
		for (User user : users) {
			UserStatusWidget widget = userWidgets.get(user);
			if (widget == null) {
				widget = new UserStatusWidget(user);
				userWidgets.put(user, widget);
			}
			containerPanel.add(widget);
			widget.update();
		}
	}

}
