package scrum.client.collaboration;

import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class SubjectWidget extends AScrumWidget {

	private Subject subject;

	public SubjectWidget(Subject impediment) {
		super();
		this.subject = impediment;
	}

	@Override
	protected Widget onInitialization() {
		TableBuilder tb = ScrumGwt.createFieldTable();
		tb.addFieldRow("Label", subject.getLabelModel());
		tb.addFieldRow("Text", subject.getTextModel());

		return TableBuilder.row(20, tb.createTable(), new CommentsWidget(subject));
	}

}
