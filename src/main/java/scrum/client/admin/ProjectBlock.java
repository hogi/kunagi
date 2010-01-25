package scrum.client.admin;

import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.Dao;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.project.DeleteProjectAction;
import scrum.client.project.OpenProjectAction;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.Widget;

public class ProjectBlock extends ABlockWidget<Project> {

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Project project = getObject();
		header.addToolbarAction(new OpenProjectAction(project));
		header.addMenuAction(new DeleteProjectAction(project));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Project project = getObject();
		header.setDragHandle("prj");
		header.setCenter(project.getLabel());
	}

	@Override
	protected Widget onExtendedInitialization() {
		final Project project = getObject();
		FieldsWidget fields = new FieldsWidget();
		fields.add("Label", new TextEditorWidget(project.getLabelModel()));
		fields.add("Description", new RichtextEditorWidget(project.getDescriptionModel()));

		fields.add("Participants", new AMultiSelectionViewEditWidget<User>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItems(project.getParticipants());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(Dao.get().getUsers());
				setEditorSelectedItems(project.getParticipants());
			}

			@Override
			protected void onEditorSubmit() {
				project.setParticipantsConfigured(getEditorSelectedItems());
			}

			@Override
			public boolean isEditable() {
				return project.isEditable();
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

			@Override
			public boolean isEditable() {
				return project.isEditable();
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

			@Override
			public boolean isEditable() {
				return project.isEditable();
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

			@Override
			public boolean isEditable() {
				return project.isEditable();
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

			@Override
			public boolean isEditable() {
				return project.isEditable();
			}
		});

		if (project.isAdmin(getCurrentUser())) {
			fields.add("", Gwt.createServletDownloadLink("backupDownload.zip?projectId=" + project.getId(),
				"Download Backup ZIP"));
		}

		return fields;
	}

	public static final BlockWidgetFactory<Project> FACTORY = new BlockWidgetFactory<Project>() {

		public ProjectBlock createBlock() {
			return new ProjectBlock();
		}
	};
}
