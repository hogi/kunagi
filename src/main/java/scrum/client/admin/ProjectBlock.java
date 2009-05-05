package scrum.client.admin;

import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.img.Img;
import scrum.client.project.Project;
import scrum.client.workspace.Ui;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;

public class ProjectBlock extends AExtensibleBlockWidget<Project> {

	private Project project;

	private HTML content;
	private ToolbarWidget toolbar;
	private FieldsWidget fields;

	@Override
	protected Project getObject() {
		return project;
	}

	@Override
	protected void setObject(Project object) {
		this.project = object;
	}

	@Override
	protected void onCollapsedInitialization() {
		content = new HTML();
	}

	@Override
	protected void onCollapsedUpdate() {
		setBlockTitle(project.getLabel());
		setIcon(Img.bundle.project16());

		String description = project.getDescription();
		if (description == null) description = "No description.";
		if (!isSelected()) {
			int idx = description.indexOf('.');
			if (idx > 0) {
				description = description.substring(0, idx + 1);
			}
			if (description.length() > 150) {
				description = description.substring(0, 149) + "...";
			}
		}
		content.setHTML(description);

		setContent(null);

		createToolbar();
	}

	@Override
	protected void onExtendedInitialization() {
		fields = new FieldsWidget();
		fields.setAutoUpdateWidget(this);
		fields.add("Label", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(project.getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(project.getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				project.setLabel(getEditorText());
			}

		});

		fields.add("Description", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(project.getDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(project.getDescription());
			}

			@Override
			protected void onEditorSubmit() {
				project.setDescription(getEditorText());
			}

		});

		fields.add("Participants", new AMultiSelectionViewEditWidget<User>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItems(project.getParticipants());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(project.getParticipantsAvailableForConfiguration());
				setEditorSelectedItems(project.getParticipants());
			}

			@Override
			protected void onEditorSubmit() {
				project.setParticipantsConfigured(getEditorSelectedItems());
			}
		});

		fields.add("Admins", new AMultiSelectionViewEditWidget<User>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItems(project.getAdmins());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(project.getParticipants());
				setEditorSelectedItems(project.getAdmins());
			}

			@Override
			protected void onEditorSubmit() {
				project.setAdmins(getEditorSelectedItems());
			}
		});

		fields.add("Product Owner", new AMultiSelectionViewEditWidget<User>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItems(project.getProductOwners());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(project.getParticipants());
				setEditorSelectedItems(project.getProductOwners());
			}

			@Override
			protected void onEditorSubmit() {
				project.setProductOwners(getEditorSelectedItems());
			}
		});

		fields.add("Scrum Master", new AMultiSelectionViewEditWidget<User>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItems(project.getScrumMasters());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(project.getParticipants());
				setEditorSelectedItems(project.getScrumMasters());
			}

			@Override
			protected void onEditorSubmit() {
				project.setScrumMasters(getEditorSelectedItems());
			}
		});

		fields.add("Development Team", new AMultiSelectionViewEditWidget<User>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItems(project.getTeamMembers());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(project.getParticipants());
				setEditorSelectedItems(project.getTeamMembers());
			}

			@Override
			protected void onEditorSubmit() {
				project.setTeamMembers(getEditorSelectedItems());
			}
		});

	}

	@Override
	protected void onExtendedUpdate() {
		setBlockTitle(project.getLabel());
		fields.update();
		setContent(fields);
		createToolbar();
	}

	protected void createToolbar() {

		addMenuCommand("Open Project", new Command() {

			public void execute() {
				select();
			}
		});

		addMenuCommand("Delete", new Command() {

			public void execute() {
				if (Window
						.confirm("'R you crazy, Jesus? You crazy??\n\nOK - That's what my Ex-Wife said!\nCancel - No, of course not!")) {
					ScrumGwtApplication.get().getDao().deleteProject(project);
					ProjectSelectorWidget.get().getList().removeSelectedRow();
				}
			}
		});
	}

	private void select() {
		Ui.get().lock("Loading project...");
		ScrumGwtApplication.get().setProject(project);
		ScrumGwtApplication.get().callSelectProject(project.getId(), new Runnable() {

			public void run() {
				Ui.get().unlock();
				Ui.get().showWorkspace();
				WorkareaWidget.get().showProjectOverview();
			}
		});
	}

	public Image getClipboardIcon() {
		return Img.bundle.project32().createImage();
	}

	public static BlockWidgetFactory<Project> FACTORY = new BlockWidgetFactory<Project>() {

		public ProjectBlock createBlock() {
			return new ProjectBlock();
		}
	};
}
