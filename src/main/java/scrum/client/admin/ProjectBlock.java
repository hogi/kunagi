package scrum.client.admin;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.common.ScrumUtil;
import scrum.client.img.Img;
import scrum.client.project.Project;
import scrum.client.workspace.Ui;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

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
		setIcon(Img.bundle.project32());

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

		setContent(content);
		setToolbar(isSelected() ? getToolbar() : null);
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

		User productOwner = project.getProductOwner();
		if (productOwner != null) {
			fields.add("Product Owner", new Label(productOwner.getName()));
		}

		User scrumMaster = project.getScrumMaster();
		if (scrumMaster != null) {
			fields.add("Scrum Master", new Label(scrumMaster.getName()));
		}

		String team = ScrumUtil.toCommataSeperatedString(project.getTeamMembers());
		if (team.length() > 0) {
			fields.add("Team", new Label(team));
		}

		String admins = ScrumUtil.toCommataSeperatedString(project.getAdmins());
		if (admins.length() > 0) {
			fields.add("Project Admins", new Label(admins));
		}

	}

	@Override
	protected void onExtendedUpdate() {
		setBlockTitle(project.getLabel());
		fields.update();
		setContent(fields);
		setToolbar(getToolbar());
	}

	protected Widget getToolbar() {
		if (toolbar == null) {
			toolbar = new ToolbarWidget();
			toolbar.addButton("Open Project").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					select();
				}
			});

			toolbar.addButton(Img.bundle.delete16().createImage(), "Delete").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {

					if (Window
							.confirm("'R you crazy, Jesus? You crazy??\n\nOK - That's what my Ex-Wife said!\nCancel - No, of course not!")) {
						ScrumGwtApplication.get().getDao().deleteProject(project);
						ProjectSelectorWidget.get().getList().removeSelectedRow();
					}
				}
			});
		}

		return toolbar;
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
