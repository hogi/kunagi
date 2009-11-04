package scrum.client.project;

import ilarkesto.gwt.client.TableBuilder;
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
		fields.add("Label", new TextEditorWidget(quality.getLabelModel(), quality.getEditPredicate()));
		fields.add("Description", new RichtextEditorWidget(quality.getDescriptionModel(), quality.getEditPredicate()));
		fields.add("Test", new RichtextEditorWidget(quality.getTestDescriptionModel(), quality.getEditPredicate()));

		return TableBuilder.row(20, fields, new CommentsWidget(quality));
	}

}
