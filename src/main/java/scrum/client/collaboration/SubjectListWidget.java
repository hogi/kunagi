package scrum.client.collaboration;

import ilarkesto.gwt.client.ButtonWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class SubjectListWidget extends AScrumWidget {

	public BlockListWidget<Subject> list;

	@Override
	protected Widget onInitialization() {
		cm.getApp().callRequestImpediments();

		list = new BlockListWidget<Subject>(SubjectBlock.FACTORY);
		list.setAutoSorter(Subject.COMPARATOR);

		PagePanel page = new PagePanel();
		page.addHeader("Forum", new ButtonWidget(new CreateSubjectAction()));
		page.addSection(list);
		return page;
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		list.setObjects(getCurrentProject().getSubjects());
	}

	public void select(Subject subject) {
		list.extendObject(subject);
	}
}
