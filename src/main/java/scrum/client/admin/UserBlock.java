package scrum.client.admin;

import ilarkesto.gwt.client.ATextViewEditWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class UserBlock extends AExtensibleBlockWidget<User> implements TrashSupport, ClipboardSupport {

	private User user;

	private FieldsWidget fields;
	private Label summary;

	@Override
	protected User getObject() {
		return user;
	}

	@Override
	protected void setObject(User object) {
		this.user = object;
	}

	@Override
	protected void onCollapsedInitialization() {
		summary = new Label();
	}

	@Override
	protected void onCollapsedUpdate() {
		setBlockTitle(user.getName());
		setIcon(Img.bundle.user16());
		summary.setText(user.getName());
		setContent(null);
		createToolbar();
	}

	@Override
	protected void onExtendedInitialization() {
		fields = new FieldsWidget();
		fields.setAutoUpdateWidget(this);

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

	}

	@Override
	protected void onExtendedUpdate() {
		setBlockTitle(user.getName());
		setIcon(Img.bundle.user16());
		fields.update();
		setContent(fields);
		createToolbar();
	}

	public Image getClipboardIcon() {
		return Img.bundle.user16().createImage();
	}

	public String getClipboardLabel() {
		return user.getName();
	}

	public ABlockWidget getClipboardPayload() {
		return this;
	}

	protected void createToolbar() {
		addToolbarButton("Delete").addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				ScrumGwtApplication.get().getDao().deleteUser(user);
				getList().removeSelectedRow();
			}
		});

		addToolbarButton("Reset password").addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				ScrumGwtApplication.get().callResetPassword(user.getId(), new Runnable() {

					public void run() {
					// TODO Auto-generated method stub

					}
				});
			}
		});
	}

	public User getUser() {
		return user;
	}

	public boolean isTrashable() {
		return true;
	}

	public void trash() {
		ScrumGwtApplication.get().getDao().deleteUser(user);
		getList().removeObject(user);
	}

	public static BlockWidgetFactory<User> FACTORY = new BlockWidgetFactory<User>() {

		public UserBlock createBlock() {
			return new UserBlock();
		}
	};
}
