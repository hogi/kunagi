package scrum.client.admin;

import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.Dao;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.img.Img;
import scrum.client.project.DeleteProjectAction;
import scrum.client.project.OpenProjectAction;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.Widget;

public class ProjectBlock extends AExtensibleBlockWidget<Project> {

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.project16());
	}

	@Override
	protected void onUpdateHead() {
		Project project = getObject();
		setBlockTitle(project.getLabel());
		addToolbarAction(new OpenProjectAction(project));
		addMenuAction(new DeleteProjectAction(project));
	}

	@Override
	protected Widget onExtendedInitialization() {
		final Project project = getObject();
		FieldsWidget fields = new FieldsWidget();
		fields.add("Label", new TextEditorWidget(project.labelModel));
		fields.add("Description", new RichtextEditorWidget(project.descriptionModel));

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

		return fields;
	}

	public static final BlockWidgetFactory<Project> FACTORY = new BlockWidgetFactory<Project>() {

		public ProjectBlock createBlock() {
			return new ProjectBlock();
		}
	};
}
