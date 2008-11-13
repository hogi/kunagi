package scrum.client.test;

import java.util.ArrayList;
import java.util.List;

import scrum.client.ScrumGwtApplication;
import scrum.client.project.BacklogItem;
import scrum.client.sprint.Sprint;
import scrum.client.sprint.Task;
import scrum.client.sprint.TaskListWidget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PortalView extends Composite {

	public PortalView() {
		VerticalPanel vpanel = new VerticalPanel();
		vpanel.setWidth("100%");

		Sprint sprint = ScrumGwtApplication.get().getProject().getCurrentSprint();
		String sprintname = "<no sprint available>";
		if (sprint != null) sprintname = sprint.getLabel();
		vpanel.add(new Label(sprintname));

		vpanel.add(new Label("My tasks"));

		List<Task> mytaskList = new ArrayList<Task>();
		for (BacklogItem backlogItem : ScrumGwtApplication.get().getProject().getBacklogItems()) {
			for (Task task : backlogItem.getTasks()) {
				// if (ScrumGwtApplication.getUser().equals(task.getOwner()))
				mytaskList.add(task);
			}
		}
		if (mytaskList.size() < 1) {
			// TODO highlight
			vpanel.add(new Label("YOU have NO tasks!"));
		} else {
			TaskListWidget mytaskListWidget = new TaskListWidget(mytaskList);
			vpanel.add(mytaskListWidget);
		}

		vpanel.add(new Label("All current sprint tasks"));

		List<Task> allTaskList = new ArrayList<Task>();
		for (BacklogItem backlogItem : ScrumGwtApplication.get().getProject().getBacklogItems()) {
			for (Task task : backlogItem.getTasks()) {
				if (task.getOwner() == null) allTaskList.add(task);
			}
		}
		if (allTaskList.size() < 1) {
			// TODO highlight
			vpanel.add(new Label("There are no more tasks in this sprint!"));
		} else {
			TaskListWidget allTaskListWidget = new TaskListWidget(allTaskList);
			vpanel.add(allTaskListWidget);
		}

		initWidget(vpanel);
	}

}
