package scrum.client.issues;

import ilarkesto.gwt.client.ButtonWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class IssueListWidget extends AScrumWidget {

	public BlockListWidget<Issue> list;

	@Override
	protected Widget onInitialization() {
		cm.getApp().callRequestIssues();

		list = new BlockListWidget<Issue>(IssueBlock.FACTORY);
		list.setAutoSorter(Issue.REVERSE_DATE_COMPARATOR);

		PagePanel page = new PagePanel();
		page.addHeader("Issue List", new ButtonWidget(new CreateIssueAction()));
		page.addSection(list);
		return page;
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		list.setObjects(getCurrentProject().getIssues());
	}

	public void showIssue(Issue issue) {
		list.extendObject(issue);
	}

	public void select(Issue issue) {
		list.extendObject(issue);
	}
}
