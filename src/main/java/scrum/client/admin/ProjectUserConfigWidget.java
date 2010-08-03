package scrum.client.admin;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.common.DocumentationWidget;
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
		fields.add("Color", new TextEditorWidget(config.getColorModel()) {

			@Override
			protected void onViewerUpdate() {
				super.onViewerUpdate();
				getViewer().getElement().getStyle().setProperty("color", config.getColor());
			}
		});

		globalUserConfig = new UserConfigWidget();
		PagePanel page = new PagePanel();
		page.addHeader("Personal preferences for current project");
		page.addSection(fields);
		page.addSection(new DocumentationWidget(true, getLocalizer().views().personalPreferences()));
		return Gwt.createFlowPanel(page, globalUserConfig);
	}

}
