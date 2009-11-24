package scrum.client.risks;

import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.DropdownEditorWidget;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class RiskWidget extends AScrumWidget {

	private Risk risk;

	public RiskWidget(Risk risk) {
		super();
		this.risk = risk;
	}

	@Override
	protected Widget onInitialization() {
		TableBuilder tb = new TableBuilder();
		tb.setCellPadding(2);

		tb.addFieldRow("Label", risk.getLabelModel());
		tb.addFieldRow("Description", risk.getDescriptionModel());
		tb.addFieldRow("Mitigation Plans", risk.getMitigationPlansModel());
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

		return TableBuilder.row(20, tb.createTable(), new CommentsWidget(risk));
	}

}
