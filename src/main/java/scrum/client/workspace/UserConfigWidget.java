package scrum.client.workspace;

import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.FieldsWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserConfigWidget extends AWidget {

	private Widget prevWidget;

	private FieldsWidget fields;

	private ButtonWidget confirmButton;

	private String oldPassword;

	private String newPassword;

	private String newPasswordRepeat;

	@Override
	protected Widget onInitialization() {
		fields = new FieldsWidget();

		fields.add("old password", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(oldPassword == null ? null : "*****");
			}

			@Override
			protected void onEditorUpdate() {}

			@Override
			protected void onEditorSubmit() {
				oldPassword = getEditorText();
			}
		});

		fields.add("new password", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(newPassword == null ? null : "*****");
			}

			@Override
			protected void onEditorUpdate() {}

			@Override
			protected void onEditorSubmit() {
				newPassword = getEditorText();
			}
		});

		fields.add("repeat new password", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(newPasswordRepeat == null ? null : "*****");
			}

			@Override
			protected void onEditorUpdate() {}

			@Override
			protected void onEditorSubmit() {
				newPasswordRepeat = getEditorText();
			}
		});

		FlowPanel mainpanel = new FlowPanel();
		mainpanel.setWidth("100%");
		mainpanel.add(fields);
		confirmButton = new ButtonWidget("save changes");
		confirmButton.addClickHandler(new ConfimChangesClickListener());
		ToolbarWidget toolbar = new ToolbarWidget(true);
		toolbar.add(confirmButton);
		mainpanel.add(toolbar);

		return mainpanel;
	}

	public void setPrevWidget(Widget prevWidget) {
		this.prevWidget = prevWidget;
	}

	@Override
	protected void onUpdate() {
		fields.update();
	}

	class ConfimChangesClickListener implements ClickHandler {

		public void onClick(ClickEvent event) {
			if (oldPassword == null) {
				Ui.get().showError("old password is empty");
			} else if (newPassword == null) {
				Ui.get().showError("new password is empty");
			} else if (newPasswordRepeat == null) {
				Ui.get().showError("new password repeat is empty");
			} else if (newPassword.equals(newPasswordRepeat) == false) {
				Ui.get().showError("the new password repeat did not match the new password.");
			}
			Ui.get().lock("saving changes");
			ScrumGwtApplication.get().callChangePassword(oldPassword, newPassword, new Runnable() {

				public void run() {
					oldPassword = null;
					newPassword = null;
					newPasswordRepeat = null;
					WorkareaWidget.get().show(prevWidget);
				}
			});
		}

	}

}
