package scrum.client.project;

import ilarkesto.gwt.client.ADropdownViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ATextWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;
import scrum.client.sprint.Sprint;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RequirementWidget extends AExtensibleBlockWidget<Requirement> implements TrashSupport, ClipboardSupport {

	private Requirement requirement;

	private Label summary;
	private FieldsWidget fields;

	@Override
	protected Requirement getObject() {
		return requirement;
	}

	@Override
	protected void setObject(Requirement object) {
		this.requirement = object;
	}

	@Override
	protected void onCollapsedInitialization() {
		summary = new Label();
	}

	@Override
	protected void onCollapsedUpdate() {
		setBlockTitle(requirement.getLabel());
		setIcon(requirement.isClosed() ? Img.bundle.done32() : Img.bundle.requirement32());
		summary.setText(requirement.getProductBacklogSummary());
		setContent(summary);
		setToolbar(null);
	}

	@Override
	protected void onExtendedInitialization() {
		fields = new FieldsWidget();
		fields.setAutoUpdateWidget(this);
		fields.add("Label", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(requirement.getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(requirement.getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				requirement.setLabel(getEditorText());
			}

		});

		fields.add("Description", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(requirement.getDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(requirement.getDescription());
			}

			@Override
			protected void onEditorSubmit() {
				requirement.setDescription(getEditorText());
			}

		});

		fields.add("Test", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(requirement.getTestDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(requirement.getTestDescription());
			}

			@Override
			protected void onEditorSubmit() {
				requirement.setTestDescription(getEditorText());
			}

		});

		fields.add("Estimated Work", new ADropdownViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(requirement.getEstimatedWorkAsString());
			}

			@Override
			protected void onEditorUpdate() {
				setOptions("", "1", "2", "3", "5", "8", "13", "21");
				Integer work = requirement.getEstimatedWork();
				setSelectedOption(work == null ? "" : work.toString());
			}

			@Override
			protected void onEditorSubmit() {
				String value = getSelectedOption();
				requirement.setEstimatedWork(value.length() == 0 ? null : Integer.parseInt(value));
			}
		});

		fields.add("Sprint", new ATextWidget() {

			@Override
			protected void onUpdate() {
				Sprint sprint = requirement.getSprint();
				setText(sprint == null ? "-" : sprint.toString());
			}
		});
	}

	@Override
	protected void onExtendedUpdate() {
		setBlockTitle(requirement.getLabel());
		setIcon(requirement.isClosed() ? Img.bundle.done32() : Img.bundle.requirement32());
		fields.update();
		setContent(fields);
		setToolbar(createToolbar());
	}

	protected Widget createToolbar() {
		ToolbarWidget toolbar = new ToolbarWidget();

		if (requirement.getSprint() == null) {
			toolbar.addButton(Img.bundle.delete16().createImage(), "Delete").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					ScrumGwtApplication.get().getProject().deleteRequirement(requirement);
					ProductBacklogWidget.get().list.removeSelectedRow();
				}
			});
		}

		final Sprint currentSprint = ScrumGwtApplication.get().getProject().getCurrentSprint();
		if (currentSprint != null) {
			if (requirement.isSprint(currentSprint)) {
				toolbar.addButton("Remove from Sprint").addClickListener(new ClickListener() {

					public void onClick(Widget sender) {
						requirement.setSprint(null);
						update();
					}
				});
			} else {
				toolbar.addButton(Img.bundle.sprint16().createImage(), "Add to Sprint").addClickListener(
					new ClickListener() {

						public void onClick(Widget sender) {
							requirement.setSprint(currentSprint);
							WorkareaWidget.get().showSprintBacklog(requirement);
							// update();
						}
					});
			}
		}

		if (!requirement.isClosed() && requirement.isDone()) {
			toolbar.addButton(Img.bundle.done16().createImage(), "Close").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					// item.setDone(false);
					update();
				}
			});
		}

		return toolbar;
	}

	public Image getClipboardIcon() {
		return Img.bundle.requirement16().createImage();
	}

	public String getClipboardLabel() {
		return requirement.getLabel();
	}

	public ABlockWidget getClipboardPayload() {
		return this;
	}

	public boolean isTrashable() {
		return requirement.getSprint() == null;
	}

	public void trash() {
		requirement.getProject().deleteRequirement(requirement);
		getList().removeObject(requirement);
	}

	public Requirement getRequirement() {
		return requirement;
	}
}
