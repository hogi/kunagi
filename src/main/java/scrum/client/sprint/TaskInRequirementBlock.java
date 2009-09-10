package scrum.client.sprint;

import ilarkesto.gwt.client.AIntegerViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;
import scrum.client.tasks.ClaimTaskAction;
import scrum.client.tasks.CloseTaskAction;
import scrum.client.tasks.DeleteTaskAction;
import scrum.client.tasks.ReopenTaskAction;
import scrum.client.tasks.UnclaimTaskAction;
import scrum.client.workspace.Ui;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class TaskInRequirementBlock extends AExtensibleBlockWidget<Task> implements TrashSupport, ClipboardSupport {

	private Task task;

	private Label owner;
	private FieldsWidget fields;

	@Override
	protected Task getObject() {
		return task;
	}

	@Override
	protected void setObject(Task object) {
		this.task = object;
	}

	@Override
	protected void onCollapsedInitialization() {}

	@Override
	protected void onUpdateHead() {
		setBlockTitle(task.getLongLabel());
		setIcon(task.isDone() ? Img.bundle.done16() : Img.bundle.task16());
		addMenuAction(new ClaimTaskAction(task, Ui.get()));
		addMenuAction(new CloseTaskAction(task, Ui.get()));
		addMenuAction(new ReopenTaskAction(task, Ui.get()));
		addMenuAction(new UnclaimTaskAction(task, Ui.get()));
		addMenuAction(new DeleteTaskAction(task, Ui.get()));
	}

	@Override
	protected void onExtendedInitialization() {
		fields = new FieldsWidget();
		fields.setAutoUpdateWidget(SprintBacklogWidget.get());

		fields.add("Label", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(task.getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setViewerText(task.getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				task.setLabel(getEditorText());
			}

		});

		fields.add("Burned Work", new AIntegerViewEditWidget() {

			@Override
			protected void onIntegerViewerUpdate() {
				setViewerValue(task.getBurnedWork(), "hours");
			}

			@Override
			protected void onEditorUpdate() {
				setEditorValue(task.getBurnedWork());
			}

			@Override
			protected void onEditorSubmit() {
				Integer value = getEditorValue(0);
				if (value == null) value = 0;
				int previous = task.getBurnedWork();
				int diff = value - previous;
				task.setBurnedWork(value);
				task.adjustRemainingWork(diff);
			}

			@Override
			protected void onMinusClicked() {
				task.decrementBurnedWork();
				task.adjustRemainingWork(-1);
			}

			@Override
			protected void onPlusClicked() {
				task.incrementBurnedWork();
				task.adjustRemainingWork(1);
			}
		});

		fields.add("Remaining Work", new AIntegerViewEditWidget() {

			@Override
			protected void onIntegerViewerUpdate() {
				setViewerValue(task.getRemainingWork(), "hours");
			}

			@Override
			protected void onEditorUpdate() {
				setEditorValue(task.getRemainingWork());
			}

			@Override
			protected void onEditorSubmit() {
				task.setRemainingWork(getEditorValue(1));
			}

			@Override
			protected void onMinusClicked() {
				task.decrementRemainingWork();
			}

			@Override
			protected void onPlusClicked() {
				task.incrementRemainingWork();
			}

		});

		fields.add("Note", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(task.getDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(task.getDescription());
			}

			@Override
			protected void onEditorSubmit() {
				task.setDescription(getEditorText());
			}

		});

		owner = new Label();
		fields.add("Owner", owner);

	}

	@Override
	protected void onUpdateBody() {
		fields.update();
		owner.setText(task.getOwner() == null ? "No owner specified." : task.getOwner().getName());
		setContent(fields);
	}

	public Task getTask() {
		return task;
	}

	public Image getClipboardIcon() {
		return Img.bundle.task16().createImage();
	}

	public String getClipboardLabel() {
		return task.getLabel();
	}

	public ABlockWidget getClipboardPayload() {
		return this;
	}

	public boolean isTrashable() {
		return true;
	}

	public void trash() {
		task.getRequirement().deleteTask(task);
		SprintBacklogWidget.get().update();
	}

	public static BlockWidgetFactory<Task> FACTORY = new BlockWidgetFactory<Task>() {

		public TaskInRequirementBlock createBlock() {
			return new TaskInRequirementBlock();
		}
	};
}
