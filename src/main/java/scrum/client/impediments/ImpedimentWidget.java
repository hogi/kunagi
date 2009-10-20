package scrum.client.impediments;

import ilarkesto.gwt.client.ADateViewEditWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.FieldsWidget;

import com.google.gwt.user.client.ui.Widget;

public class ImpedimentWidget extends AScrumWidget {

	private Impediment impediment;

	public ImpedimentWidget(Impediment impediment) {
		super();
		this.impediment = impediment;
	}

	@Override
	protected Widget onInitialization() {
		FieldsWidget fields = new FieldsWidget();

		fields.add("Label", new TextEditorWidget(impediment.labelModel));
		fields.add("Date", new ADateViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerValue(impediment.getDate());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorValue(impediment.getDate());
			}

			@Override
			protected void onEditorSubmit() {
				impediment.setDate(getEditorValue());
			}
		});
		fields.add("Description", new RichtextEditorWidget(impediment.descriptionModel));
		fields.add("Solution", new RichtextEditorWidget(impediment.solutionModel));

		return Gwt.createFlowPanel(fields, new CommentsWidget(impediment));
	}

}
