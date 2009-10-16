package scrum.client.admin;

import ilarkesto.gwt.client.ATextViewEditWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.Widget;

public class UserBlock extends AExtensibleBlockWidget<User> {

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.user16());
	}

	@Override
	protected void onUpdateHead() {
		User user = getObject();
		setBlockTitle(user.getName());
		setContent(null);
		addMenuAction(new DeleteUserAction(user));
	}

	@Override
	protected Widget onExtendedInitialization() {
		final User user = getObject();
		FieldsWidget fields = new FieldsWidget();

		fields.add("Name", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(user.getName());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(user.getName());
			}

			@Override
			protected void onEditorSubmit() {
				user.setName(getEditorText());
			}

		});

		fields.add("E-Mail", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(user.getEmail());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(user.getEmail());
			}

			@Override
			protected void onEditorSubmit() {
				user.setEmail(getEditorText());
			}

		});

		return fields;
	}

	public static final BlockWidgetFactory<User> FACTORY = new BlockWidgetFactory<User>() {

		public UserBlock createBlock() {
			return new UserBlock();
		}
	};
}
