package scrum.client.project;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.ToolbarWidget;
import scrum.client.common.editable.AEditableListBoxWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.common.editable.AEditableTextareaWidget;
import scrum.client.dnd.BlockListDropController;
import scrum.client.img.Img;
import scrum.client.sprint.Sprint;
import scrum.client.workspace.WorkspaceWidget;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RequirementWidget extends ABlockWidget {

	private Requirement requirement;

	public RequirementWidget(Requirement item) {
		this.requirement = item;
	}

	@Override
	protected Widget buildContent() {
		if (!isExtended()) { return new Label(requirement.getProductBacklogSummary()); }

		ItemFieldsWidget fieldsWidget = new ItemFieldsWidget();
		fieldsWidget.addField("Label", new AEditableTextWidget() {

			@Override
			protected String getText() {
				return requirement.getLabel();
			}

			@Override
			protected void setText(String text) {
				requirement.setLabel(text);
				rebuild();
			}

		});

		fieldsWidget.addField("Description", new AEditableTextareaWidget() {

			@Override
			protected String getText() {
				return requirement.getDescription();
			}

			@Override
			protected void setText(String text) {
				requirement.setDescription(text);
				rebuild();
			}

		});

		fieldsWidget.addField("Test", new AEditableTextareaWidget() {

			@Override
			protected String getText() {
				return requirement.getTestDescription();
			}

			@Override
			protected void setText(String text) {
				requirement.setTestDescription(text);
				rebuild();
			}

		});

		fieldsWidget.addField("Estimated Work", new AEditableListBoxWidget() {

			@Override
			protected String getText() {
				Integer effort = requirement.getEstimatedWork();
				return effort == null ? "No estimation." : effort.toString() + " "
						+ requirement.getProject().getEffortUnit();
			}

			@Override
			protected String[] getSelectableValues() {
				return new String[] { "", "1", "2", "3", "5", "8", "13", "21" };
			}

			@Override
			protected String getSelectedValue() {
				Integer effort = requirement.getEstimatedWork();
				return effort == null ? "" : effort.toString();
			}

			@Override
			protected void setValue(String value) {
				requirement.setEstimatedWork(value.length() == 0 ? null : Integer.parseInt(value));
				rebuild();
			}

		});

		return fieldsWidget;
	}

	@Override
	protected Widget buildToolbar() {
		if (!isExtended()) return null;
		ToolbarWidget toolbar = new ToolbarWidget();

		toolbar.addButton(Img.bundle.delete16().createImage(), "Delete").addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				ScrumGwtApplication.get().getProject().deleteRequirement(requirement);
				WorkspaceWidget.backlog.list.removeSelectedRow();
			}
		});

		final Sprint currentSprint = ScrumGwtApplication.get().getProject().getCurrentSprint();
		if (currentSprint != null) {
			if (requirement.isSprint(currentSprint)) {
				toolbar.addButton("Remove from Sprint").addClickListener(new ClickListener() {

					public void onClick(Widget sender) {
						requirement.setSprint(null);
						rebuild();
					}
				});
			} else {
				toolbar.addButton(Img.bundle.sprintIcon16().createImage(), "Add to Sprint").addClickListener(
					new ClickListener() {

						public void onClick(Widget sender) {
							requirement.setSprint(currentSprint);
							rebuild();
						}
					});
			}
		}

		if (!requirement.isClosed() && requirement.isDone()) {
			toolbar.addButton(Img.bundle.done16().createImage(), "Close").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					// item.setDone(false);
					rebuild();
				}
			});
		}

		return toolbar;
	}

	@Override
	protected String getBlockTitle() {
		return requirement.getLabel();
	}

	@Override
	protected AbstractImagePrototype getIcon16() {
		if (requirement.isClosed()) return Img.bundle.storyDoneIcon16();
		return Img.bundle.storyIcon16();
	}

	@Override
	protected AbstractImagePrototype getIcon32() {
		if (requirement.isClosed()) return Img.bundle.storyDoneIcon32();
		return Img.bundle.storyIcon32();
	}

	@Override
	public void delete() {
		ScrumGwtApplication.get().getProject().deleteRequirement(requirement);
		WorkspaceWidget.backlog.list.remove(this);
	}

	@Override
	protected DropController createDropController() {
		return new BlockListDropController(this, WorkspaceWidget.backlog.list);
	}

	public Requirement getRequirement() {
		return requirement;
	}
}
