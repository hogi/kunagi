package scrum.client.risks;

import ilarkesto.gwt.client.ADropdownViewEditWidget;
import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.FieldsWidget;

import com.google.gwt.user.client.ui.Widget;

public class RiskWidget extends AScrumWidget {

	private Risk risk;

	public RiskWidget(Risk risk) {
		super();
		this.risk = risk;
	}

	@Override
	protected Widget onInitialization() {
		FieldsWidget fields = new FieldsWidget();

		fields.add("Label", new TextEditorWidget(risk.getLabelModel()).switchToEditModeIfNull());
		fields.add("Description", new RichtextEditorWidget(risk.getDescriptionModel()));
		fields.add("Mitigation Plans", new RichtextEditorWidget(risk.getMitigationPlansModel()));
		fields.add("Impact", new ADropdownViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(risk.getImpactLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setOptions(RiskComputer.getImpacts());
				setSelectedOption(String.valueOf(risk.getImpact()));
			}

			@Override
			protected void onEditorSubmit() {
				risk.setImpact(Integer.parseInt(getSelectedOption()));
			}
		}.setEditable(risk.getPriorityEditPredicate()));
		fields.add("Probability", new ADropdownViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(risk.getProbabilityLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setOptions(RiskComputer.getProbabilities());
				setSelectedOption(String.valueOf(risk.getProbability()));
			}

			@Override
			protected void onEditorSubmit() {
				risk.setProbability(Integer.parseInt(getSelectedOption()));
			}
		}.setEditable(risk.getPriorityEditPredicate()));
		fields.add("Priority", new AFieldValueWidget() {

			@Override
			protected void onUpdate() {
				setText(risk.getPriorityLabel());
			}
		});

		return TableBuilder.row(20, fields, new CommentsWidget(risk));
	}

}
