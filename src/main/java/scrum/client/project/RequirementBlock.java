package scrum.client.project;

import ilarkesto.gwt.client.ADropdownViewEditWidget;
import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ATextWidget;
import ilarkesto.gwt.client.GwtLogger;
import scrum.client.ClientConstants;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;
import scrum.client.sprint.Sprint;
import scrum.client.workspace.WorkareaWidget;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class RequirementBlock extends AExtensibleBlockWidget<Requirement> implements TrashSupport, ClipboardSupport {

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
		setBlockTitle(createTitle());
		setIcon(getProperIcon());
		createToolbar();
	}

	private AbstractImagePrototype getProperIcon() {

		return Img.bundle.requirement16();

		// AbstractImagePrototype icon = null;
		// if (requirement.isClosed()) {
		// icon = Img.bundle.done32();
		// } else if (requirement.isDone()) {
		// icon = Img.bundle.requirementIsDone32();
		// } else if (requirement.isSprintSet()) {
		// icon = Img.bundle.requirementInSprint32();
		// } else {
		// icon = Img.bundle.requirement32();
		// }
		//
		// return icon;
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
				GwtLogger.DEBUG("Text submitted: <" + getEditorText() + ">");
				requirement.setDescription(getEditorText());
			}

		});

		fields.add("Qualities", new AMultiSelectionViewEditWidget<Quality>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItems(requirement.getQualitys());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(requirement.getProject().getQualitys());
				setEditorSelectedItems(requirement.getQualitys());
			}

			@Override
			protected void onEditorSubmit() {
				requirement.setQualitys(getEditorSelectedItems());
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
				setOptions(ClientConstants.EFFORT_ROW);
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
				setText(requirement.isSprintSet() ? requirement.getSprint().toString() : "-");
			}
		});
	}

	@Override
	protected void onExtendedUpdate() {
		setBlockTitle(createTitle());
		setIcon(getProperIcon());
		fields.update();
		setContent(fields);
		createToolbar();
	}

	private String createTitle() {
		return "[" + requirement.getReference() + "] " + requirement.getLabel()
				+ (requirement.isDirty() ? " [dirty]" : " [clean]");
	}

	protected void createToolbar() {
		if (!requirement.isSprintSet()) {
			addMenuCommand("Delete", new Command() {

				public void execute() {
					ScrumGwtApplication.get().getProject().deleteRequirement(requirement);
					ProductBacklogWidget.get().list.removeSelectedRow();
				}
			});
		}

		final Sprint currentSprint = ScrumGwtApplication.get().getProject().getCurrentSprint();
		if (currentSprint != null) {
			if (requirement.isSprint(currentSprint)) {
				addMenuCommand("Remove from Sprint", new Command() {

					public void execute() {
						requirement.setSprint(null);
						update();
					}
				});
			} else {
				if (requirement.getEstimatedWork() != null) {
					addMenuCommand("Add to Sprint", new Command() {

						public void execute() {
							requirement.setSprint(currentSprint);
							update();
							WorkareaWidget.get().showSprintBacklog(requirement);
						}
					});
				}
			}
		}

		if (!requirement.isClosed() && requirement.isDone()) {
			addMenuCommand("Close", new Command() {

				public void execute() {
					// item.setDone(false);
					update();
				}
			});
		}

		if (!requirement.isDirty()) {
			addMenuCommand("Set dirty", new Command() {

				public void execute() {
					requirement.setDirty(true);
					update();
				}
			});
		} else {
			addMenuCommand("Set clean", new Command() {

				public void execute() {
					requirement.setDirty(false);
					update();
				}
			});
		}
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
		return !requirement.isSprintSet();
	}

	public void trash() {
		requirement.getProject().deleteRequirement(requirement);
		getList().removeObject(requirement);
	}

	public Requirement getRequirement() {
		return requirement;
	}

	public static BlockWidgetFactory<Requirement> FACTORY = new BlockWidgetFactory<Requirement>() {

		public RequirementBlock createBlock() {
			return new RequirementBlock();
		}
	};
}
