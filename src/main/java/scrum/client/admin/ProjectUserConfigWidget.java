package scrum.client.admin;

import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.Gwt;
import scrum.client.common.AScrumWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class ProjectUserConfigWidget extends AScrumWidget {

	private FieldsWidget fields;
	private UserConfigWidget globalUserConfig;

	@Override
	protected Widget onInitialization() {
		final ProjectUserConfig config = getCurrentUser().getProjectConfig();

		fields = new FieldsWidget();
		fields.add("Color", new ATextViewEditWidget() {

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

		globalUserConfig = new UserConfigWidget();
		PagePanel page = new PagePanel();
		page.addHeader("Project Preferences");
		page.addSection(fields);

		return Gwt.createFlowPanel(page, globalUserConfig);
	}

}
