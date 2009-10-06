package scrum.client.project;

import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ATextWidget;
import ilarkesto.gwt.client.GwtLogger;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

public class RequirementBlock extends AExtensibleBlockWidget<Requirement> implements TrashSupport, ClipboardSupport {

	private FlowPanel container = new FlowPanel();
	private Requirement requirement;
	private FieldsWidget fields;
	private CommentsWidget commentsWidget;

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
		setIcon(Img.bundle.requirement16());
	}

	@Override
	protected void onUpdateHead() {
		setBlockTitle(requirement.getLongLabel());
		String style = null;
		if (requirement.isClosed()) {
			style = "RequirementBlock-closed";
		} else if (requirement.isInCurrentSprint()) {
			style = "RequirementBlock-inCurrentSprint";
		} else if (!requirement.isValidForSprint()) {
			style = "RequirementBlock-invalidForSprint";
		}
		setAdditionalStyleName(style);
		addMenuAction(new AddRequirementToCurrentSprintAction(requirement));
		addMenuAction(new RemoveRequirementFromSprintAction(requirement));
		addMenuAction(new CloseRequirementAction(requirement));
		addMenuAction(new ReopenRequirementAction(requirement));
		addMenuAction(new SetRequirementDirtyAction(requirement));
		addMenuAction(new SetRequirementCleanAction(requirement));
		addMenuAction(new DeleteRequirementAction(requirement));
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

		fields.add("Estimated Work", new RequirementEstimatedWorkWidget(requirement));

		fields.add("Sprint", new ATextWidget() {

			@Override
			protected void onUpdate() {
				setText(requirement.isSprintSet() ? requirement.getSprint().toString() : "-");
			}
		});

		container.add(fields);

		commentsWidget = new CommentsWidget(requirement);
		container.add(commentsWidget);
	}

	@Override
	protected void onUpdateBody() {
		fields.update();
		commentsWidget.update();
		setContent(container);
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

	public AScrumAction getTrashAction() {
		return new DeleteRequirementAction(requirement);
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
