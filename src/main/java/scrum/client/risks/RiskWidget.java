package scrum.client.risks;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DropdownEditorWidget;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.journal.ChangeHistoryWidget;

import com.google.gwt.user.client.ui.Widget;

public class RiskWidget extends AScrumWidget {

	private Risk risk;

	public RiskWidget(Risk risk) {
		super();
		this.risk = risk;
	}

	@Override
	protected Widget onInitialization() {
		TableBuilder tb = ScrumGwt.createFieldTable();

		tb.addFieldRow("Label", risk.getLabelModel());
		tb.addFieldRow("Description", risk.getDescriptionModel());
		tb.addFieldRow("Impact", new DropdownEditorWidget<Integer>(risk.getImpactModel(),
				RiskComputer.IMPACT_LABEL_PROVIDER));
		tb.addFieldRow("Probability", new DropdownEditorWidget<Integer>(risk.getProbabilityModel(),
				RiskComputer.PROBABILITY_LABEL_PROVIDER));
		tb.addFieldRow("Priority", new AFieldValueWidget() {

			@Override
			protected void onUpdate() {
				setText(risk.getPriorityLabel());
			}
		});
		tb.addFieldRow("Impact Mitigation", risk.getImpactMitigationModel());
		tb.addFieldRow("Probability Mitigation", risk.getProbabilityMitigationModel());
		tb.addRow(new ChangeHistoryWidget(risk), 2);

		return TableBuilder.row(20, tb.createTable(), ScrumGwt.createEmoticonsAndComments(risk));
	}

}
