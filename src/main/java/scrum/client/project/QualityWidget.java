package scrum.client.project;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
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
		fields.add("Label", new TextEditorWidget(quality.labelModel));
		fields.add("Description", new RichtextEditorWidget(quality.descriptionModel));
		fields.add("Test", new RichtextEditorWidget(quality.testDescriptionModel));

		return Gwt.createFlowPanel(fields, new CommentsWidget(quality));
	}

}
