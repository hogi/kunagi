package scrum.client.admin;

import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;

import com.google.gwt.user.client.ui.Widget;

public class UserBlock extends ABlockWidget<User> {

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		User user = getObject();
		header.addMenuAction(new DeleteUserAction(user));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		User user = getObject();
		header.setDragHandle("usr");
		header.setCenter(user.getName());
	}

	@Override
	protected Widget onExtendedInitialization() {
		final User user = getObject();
		FieldsWidget fields = new FieldsWidget();
		fields.add("Name", new TextEditorWidget(user.getNameModel()));
		fields.add("E-Mail", new TextEditorWidget(user.getEmailModel()));
		return fields;
	}

	public static final BlockWidgetFactory<User> FACTORY = new BlockWidgetFactory<User>() {

		public UserBlock createBlock() {
			return new UserBlock();
		}
	};
}
