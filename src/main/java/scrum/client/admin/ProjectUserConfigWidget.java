package scrum.client.admin;

import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.FieldsWidget;

import com.google.gwt.user.client.ui.Widget;

public class ProjectUserConfigWidget extends AWidget {

	FieldsWidget fieldsWidget;

	@Override
	protected Widget onInitialization() {
		final ProjectUserConfig config = ScrumGwtApplication.get().getUser().getProjectConfig();

		fieldsWidget = new FieldsWidget();
		fieldsWidget.add("Color", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(config.getColor());
				getViewer().getElement().getStyle().setProperty("color", config.getColor());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(config.getColor());
			}

			@Override
			protected void onEditorSubmit() {
				config.setColor(getEditorText());
			}
		});

		return fieldsWidget;
	}

	@Override
	protected void onUpdate() {
		fieldsWidget.update();
	}

}
