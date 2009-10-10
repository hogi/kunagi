package scrum.client.issues;

import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class IssueListWidget extends AScrumWidget {

	public BlockListWidget<Issue> list;
	private ToolbarWidget toolbar;

	@Override
	protected Widget onInitialization() {
		ScrumGwtApplication.get().callRequestIssues();

		list = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		list.setAutoSorter(Issue.REVERSE_DATE_COMPARATOR);

		toolbar = new ToolbarWidget();
		toolbar.addButton(new CreateIssueAction());

		PagePanel page = new PagePanel();
		page.addHeader("Issue List");
		page.addSection(toolbar);
		page.addSection(list);
		return page;
	}

	@Override
	protected void onUpdate() {
		toolbar.update();
		list.setObjects(getCurrentProject().getIssues());
	}

	public void showIssue(Issue issue) {
		list.extendObject(issue);
	}

	public void select(Issue issue) {
		list.extendObject(issue);
	}
}
