package scrum.client.impediments;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.editor.DateEditorWidget;
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
		fields.add("Date", new DateEditorWidget(impediment.dateModel));
		fields.add("Description", new RichtextEditorWidget(impediment.descriptionModel));
		fields.add("Solution", new RichtextEditorWidget(impediment.solutionModel));

		return Gwt.createFlowPanel(fields, new CommentsWidget(impediment));
	}

}
