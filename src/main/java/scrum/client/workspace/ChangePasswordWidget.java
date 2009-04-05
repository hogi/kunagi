package scrum.client.workspace;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import scrum.client.common.FieldsWidget;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ChangePasswordWidget extends AWidget {

	private FieldsWidget fields;

	@Override
	protected Widget onInitialization() {
		fields = new FieldsWidget();

		fields.add("Old password", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {

			}

			@Override
			protected void onEditorUpdate() {

			}

			@Override
			protected void onEditorSubmit() {

			}
		});

		fields.add("New password", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {

			}

			@Override
			protected void onEditorUpdate() {

			}

			@Override
			protected void onEditorSubmit() {

			}
		});

		VerticalPanel mainpanel = new VerticalPanel();
		mainpanel.add(fields);

		return mainpanel;
	}

	@Override
	protected void onUpdate() {
		fields.update();
	}

}
