package scrum.client.impediments;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DateEditorWidget;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.journal.ChangeHistoryWidget;

import com.google.gwt.user.client.ui.Widget;

public class ImpedimentWidget extends AScrumWidget {

	private Impediment impediment;

	public ImpedimentWidget(Impediment impediment) {
		super();
		this.impediment = impediment;
	}

	@Override
	protected Widget onInitialization() {
		return TableBuilder.row(20, createLeftColumn(), ScrumGwt.createEmoticonsAndComments(impediment));
	}

	private Widget createLeftColumn() {
		TableBuilder tb = ScrumGwt.createFieldTable();
		tb.addFieldRow("Label", impediment.getLabelModel());
		tb.addFieldRow("Date", new DateEditorWidget(impediment.getDateModel()));
		tb.addFieldRow("Description", impediment.getDescriptionModel());
		tb.addFieldRow("Solution", impediment.getSolutionModel());
		tb.addFieldRow("Blocked Tasks", new AFieldValueWidget() {

			@Override
			protected void onUpdate() {
				setContent(ScrumGwt.createToHtmlItemsWidget(impediment.getProject().getCurrentSprint()
						.getTasksBlockedBy(impediment)));
			}
		});
		tb.addRow(new ChangeHistoryWidget(impediment), 2);
		return tb.createTable();
	}
}
