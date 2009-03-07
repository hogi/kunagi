package scrum.client.sprint;

import ilarkesto.gwt.client.ATextWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.img.Img;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RequirementInSprintWidget extends AExtensibleBlockWidget<Requirement> implements ClipboardSupport {

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
		setIcon(requirement.isDone() ? Img.bundle.done32() : Img.bundle.requirement32());
		summary.setText(requirement.getSprintBacklogSummary());
		setContent(summary);
		setToolbar(null);
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

		taskList = new BlockListWidget<Task>(TaskWidget.class);

		panel = new FlowPanel();
		panel.add(fields);
		panel.add(taskList);
	}

	@Override
	protected void onExtendedUpdate() {
		setBlockTitle(requirement.getLabel());
		setIcon(requirement.isDone() ? Img.bundle.done32() : Img.bundle.requirement32());
		fields.update();

		taskList.setBlocks(requirement.getTasks());

		setContent(panel);
		setToolbar(createToolbar());
	}

	public Requirement getRequirement() {
		return requirement;
	}

	protected Widget createToolbar() {
		ToolbarWidget toolbar = new ToolbarWidget();

		if (requirement.isDone() && !requirement.isClosed()) {
			toolbar.addButton("Close").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					requirement.setClosed(true);
					SprintBacklogWidget.get().update();
				}
			});
		}

		if (!requirement.isClosed()) {
			toolbar.addButton("Create new Task").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					taskList.addBlock(requirement.createNewTask(), true);
					SprintBacklogWidget.get().update();
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

	public Requirement getItem() {
		return requirement;
	}

}
