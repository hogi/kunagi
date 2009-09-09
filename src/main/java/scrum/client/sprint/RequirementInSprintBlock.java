package scrum.client.sprint;

import ilarkesto.gwt.client.ATextWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.img.Img;
import scrum.client.project.Requirement;
import scrum.client.workspace.Ui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

public class RequirementInSprintBlock extends AExtensibleBlockWidget<Requirement> implements ClipboardSupport {

	private Requirement requirement;

	private BlockListWidget<Task> taskList;
	private FlowPanel panel;
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
	protected void onCollapsedInitialization() {}

	@Override
	protected void onUpdateHead() {
		setBlockTitle(requirement.getReference() + " " + requirement.getLabel());
		setIcon(requirement.isDone() ? Img.bundle.done16() : Img.bundle.requirement16());
		addMenuAction(new CreateTaskInRequirementAction(requirement, Ui.get()));
	}

	@Override
	protected void onExtendedInitialization() {

		fields = new FieldsWidget();
		fields.setAutoUpdateWidget(SprintBacklogWidget.get());
		fields.add("Description", new ATextWidget() {

			@Override
			protected void onUpdate() {
				setText(requirement.getDescription());
			}
		});

		fields.add("Test", new ATextWidget() {

			@Override
			protected void onUpdate() {
				setText(requirement.getTestDescription());
			}
		});

		fields.add("Remainig Work", new ATextWidget() {

			@Override
			protected void onUpdate() {
				setText(requirement.getRemainingWorkAsString());
			}
		});

		taskList = new BlockListWidget<Task>(TaskInRequirementBlock.FACTORY);

		panel = new FlowPanel();
		panel.add(fields);
		panel.add(taskList);
	}

	@Override
	protected void onUpdateBody() {
		fields.update();
		taskList.setBlocks(requirement.getTasks());
		setContent(panel);
	}

	public Requirement getRequirement() {
		return requirement;
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

	public Requirement getItem() {
		return requirement;
	}

	public static BlockWidgetFactory<Requirement> FACTORY = new BlockWidgetFactory<Requirement>() {

		public RequirementInSprintBlock createBlock() {
			return new RequirementInSprintBlock();
		}
	};
}
