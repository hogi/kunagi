package scrum.client.project;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.editor.RichtextPropertyEditorWidget;
import ilarkesto.gwt.client.editor.TextPropertyEditorWidget;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.FieldsWidget;

import com.google.gwt.user.client.ui.Widget;

public class QualityWidget extends AScrumWidget {

	private Quality quality;

	public QualityWidget(Quality quality) {
		super();
		this.quality = quality;
	}

	@Override
	protected Widget onInitialization() {
		FieldsWidget fields = new FieldsWidget();
		fields.add("Label", new TextPropertyEditorWidget(quality.labelEditor));
		fields.add("Description", new RichtextPropertyEditorWidget(quality.descriptionEditor));
		fields.add("Test", new RichtextPropertyEditorWidget(quality.testDescriptionEditor));

		return Gwt.createFlowPanel(fields, new CommentsWidget(quality));
	}

}
