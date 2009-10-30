package scrum.client.impediments;

import ilarkesto.gwt.client.TableBuilder;
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
		fields.add("Label", new TextEditorWidget(impediment.getLabelModel()));
		fields.add("Date", new DateEditorWidget(impediment.getDateModel()));
		fields.add("Description", new RichtextEditorWidget(impediment.getDescriptionModel()));
		fields.add("Solution", new RichtextEditorWidget(impediment.getSolutionModel()));

		return TableBuilder.row(20, fields, new CommentsWidget(impediment));
	}

}
