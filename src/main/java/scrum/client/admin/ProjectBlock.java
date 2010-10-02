package scrum.client.admin;

import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.TextEditorWidget;

import java.util.List;

import scrum.client.Dao;
import scrum.client.ScrumGwt;
import scrum.client.common.ABlockWidget;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.project.DeleteProjectAction;
import scrum.client.project.OpenProjectAction;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ProjectBlock extends ABlockWidget<Project> {

	private Label lastOpenedLabel;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Project project = getObject();
		lastOpenedLabel = header.appendCenterSuffix("");
		header.addToolbarAction(new OpenProjectAction(project));
		header.addMenuAction(new DeleteProjectAction(project));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Project project = getObject();
		header.setDragHandle("prj");
		header.setCenter(project.getLabel());
		lastOpenedLabel.setText(ScrumGwt.getPeriodToAsShortestString("last opened ",
			project.getLastOpenedDateAndTime(), " ago"));
	}

	@Override
	protected Widget onExtendedInitialization() {
		final Project project = getObject();

		TableBuilder tb = ScrumGwt.createFieldTable();
		tb.addFieldRow("Name", new TextEditorWidget(project.getLabelModel()));

		tb.addFieldRow("Support Email", project.getSupportEmailModel());

		tb.addFieldRow("Vision", new TextEditorWidget(project.getVisionModel()));

		tb.addFieldRow("Participants", new AMultiSelectionViewEditWidget<User>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItems(project.getParticipants());
			}

			@Override
			protected void onEditorUpdate() {
				List<User> users = Dao.get().getUsersByDisabled(false);
				users.addAll(project.getParticipants());
				setEditorItems(users);
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

		tb.addFieldRow("Admins", new AMultiSelectionViewEditWidget<User>() {

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

		addRolesFieldRows(project, tb);

		if (project.isAdmin(getCurrentUser())) {
			tb.addFieldRow("",
				Gwt.createServletDownloadLink("backupDownload.zip?projectId=" + project.getId(), "Download Backup ZIP"));
		}

		return tb.createTable();
	}

	public static void addRolesFieldRows(final Project project, TableBuilder tb) {
		tb.addFieldRow("Product Owner", new AMultiSelectionViewEditWidget<User>() {

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

		tb.addFieldRow("Scrum Master", new AMultiSelectionViewEditWidget<User>() {

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

		tb.addFieldRow("Development Team", new AMultiSelectionViewEditWidget<User>() {

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
	}

	public static final BlockWidgetFactory<Project> FACTORY = new BlockWidgetFactory<Project>() {

		@Override
		public ProjectBlock createBlock() {
			return new ProjectBlock();
		}
	};
}
