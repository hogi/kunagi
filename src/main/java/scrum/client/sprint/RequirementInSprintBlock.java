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

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class RequirementInSprintBlock extends AExtensibleBlockWidget<Requirement> implements ClipboardSupport {

	private Requirement requirement;

	private BlockListWidget<Task> taskList;
	private Label summary;
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
	protected void onCollapsedInitialization() {
		summary = new Label();
	}

	@Override
	protected void onCollapsedUpdate() {
		setBlockTitle(requirement.getLabel());
		setIcon(requirement.isDone() ? Img.bundle.done16() : Img.bundle.requirement16());
		createToolbar();
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
	protected void onExtendedUpdate() {
		setBlockTitle(requirement.getLabel());
		setIcon(requirement.isDone() ? Img.bundle.done16() : Img.bundle.requirement16());
		fields.update();

		taskList.setBlocks(requirement.getTasks());

		setContent(panel);
		createToolbar();
	}

	public Requirement getRequirement() {
		return requirement;
	}

	protected void createToolbar() {
		if (requirement.isDone() && !requirement.isClosed()) {
			addMenuCommand("Close", new Command() {

				public void execute() {
					requirement.setClosed(true);
					SprintBacklogWidget.get().update();
				}
			});
		}

		if (!requirement.isClosed()) {
			addMenuCommand("Create new Task", new Command() {

				public void execute() {
					taskList.addBlock(requirement.createNewTask(), true);
					SprintBacklogWidget.get().update();
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

	public Requirement getItem() {
		return requirement;
	}

	public static BlockWidgetFactory<Requirement> FACTORY = new BlockWidgetFactory<Requirement>() {

		public RequirementInSprintBlock createBlock() {
			return new RequirementInSprintBlock();
		}
	};
}
