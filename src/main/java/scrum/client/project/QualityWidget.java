package scrum.client.project;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.Gwt;
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
		fields.add("Label", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(quality.getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(quality.getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				quality.setLabel(getEditorText());
			}

		});

		fields.add("Description", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(quality.getDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(quality.getDescription());
			}

			@Override
			protected void onEditorSubmit() {
				quality.setDescription(getEditorText());
			}

		});

		fields.add("Test", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(quality.getTestDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(quality.getTestDescription());
			}

			@Override
			protected void onEditorSubmit() {
				quality.setTestDescription(getEditorText());
			}

		});

		return Gwt.createFlowPanel(fields, new CommentsWidget(quality));
	}

}
