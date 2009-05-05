package scrum.client.sprint;

import ilarkesto.gwt.client.AIntegerViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class TaskInRequirementBlock extends AExtensibleBlockWidget<Task> implements TrashSupport, ClipboardSupport {

	private Task task;

	private Label summary;
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
	protected void onCollapsedInitialization() {
		summary = new Label();
	}

	@Override
	protected void onCollapsedUpdate() {
		setBlockTitle(task.getLabel());
		setIcon(task.isDone() ? Img.bundle.done16() : Img.bundle.task16());
		createToolbar();
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
				setViewerText(task.getNotice());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(task.getNotice());
			}

			@Override
			protected void onEditorSubmit() {
				task.setNotice(getEditorText());
			}

		});

		owner = new Label();
		fields.add("Owner", owner);

	}

	@Override
	protected void onExtendedUpdate() {
		setBlockTitle(task.getLabel());
		setIcon(task.isDone() ? Img.bundle.done16() : Img.bundle.task16());
		fields.update();
		owner.setText(task.getOwner() == null ? "No owner specified." : task.getOwner().getName());
		setContent(fields);
		createToolbar();
	}

	public Task getTask() {
		return task;
	}

	protected void createToolbar() {
		if (!task.isDone()) {
			addMenuCommand("Own", new Command() {

				public void execute() {
					task.setOwner(ScrumGwtApplication.get().getUser());
					SprintBacklogWidget.get().update();
				}
			});
		}

		if (isTrashable()) {
			addMenuCommand("Delete", new Command() {

				public void execute() {
					trash();
					SprintBacklogWidget.get().update();
				}
			});
		}

		if (!task.isDone()) {
			addMenuCommand("Done", new Command() {

				public void execute() {
					task.setDone();
					SprintBacklogWidget.get().update();
				}
			});
		}
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
