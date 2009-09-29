package scrum.client.issues;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.BlockListWidget;
import scrum.client.common.GroupWidget;
import scrum.client.context.ProjectContext;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class IssueListWidget extends AWidget {

	public BlockListWidget<Issue> list;

	@Override
	protected Widget onInitialization() {
		ScrumGwtApplication.get().callRequestIssues();

		list = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		list.setAutoSorter(Issue.REVERSE_DATE_COMPARATOR);

		ToolbarWidget toolbar = new ToolbarWidget(true);
		toolbar.addButton(new CreateIssueAction());

		FlowPanel panel = new FlowPanel();
		panel.add(toolbar);
		panel.add(new HTML("<br>"));
		panel.add(list);

		return new GroupWidget("Issue List", panel);
	}

	@Override
	protected void onUpdate() {
		list.setObjects(ScrumGwtApplication.get().getProject().getIssues());
	}

	public void showIssue(Issue issue) {
		list.extendObject(issue);
	}

	public static IssueListWidget get() {
		return ProjectContext.get().getIssueList();
	}
}
