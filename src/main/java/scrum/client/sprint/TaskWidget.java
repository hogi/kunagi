package scrum.client.sprint;

import ilarkesto.gwt.client.AIntegerViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.img.Img;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TaskWidget extends ABlockWidget {

	private Task task;

	private ATextViewEditWidget description;
	private AIntegerViewEditWidget burnedWork;
	private AIntegerViewEditWidget remainingWork;
	private ARichtextViewEditWidget note;
	private Label summary;
	private ItemFieldsWidget fieldsWidget;
	private ToolbarWidget toolbar;

	public TaskWidget(Task task) {
		this.task = task;
	}

	@Override
	protected void onBlockInitialization() {
		summary = new Label();

		fieldsWidget = new ItemFieldsWidget();

		description = fieldsWidget.addField("Description", new ATextViewEditWidget() {

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

		burnedWork = fieldsWidget.addField("Burned Work", new AIntegerViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
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
				notifyListControllerDataChanged();
				update();
			}

			@Override
			protected void onMinusClicked() {
				task.decrementBurnedWork();
				task.adjustRemainingWork(-1);
				notifyListControllerDataChanged();
				update();
			}

			@Override
			protected void onPlusClicked() {
				task.incrementBurnedWork();
				task.adjustRemainingWork(1);
				notifyListControllerDataChanged();
				update();
			}
		});

		remainingWork = fieldsWidget.addField("Remaining Work", new AIntegerViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerValue(task.getRemainingWork(), "hours");
			}

			@Override
			protected void onEditorUpdate() {
				setEditorValue(task.getRemainingWork());
			}

			@Override
			protected void onEditorSubmit() {
				task.setRemainingWork(getEditorValue(1));
				notifyListControllerDataChanged();
				update();
			}

			@Override
			protected void onMinusClicked() {
				task.decrementRemainingWork();
				notifyListControllerDataChanged();
				update();
			}

			@Override
			protected void onPlusClicked() {
				task.incrementRemainingWork();
				notifyListControllerDataChanged();
				update();
			}

		});

		note = fieldsWidget.addField("Note", new ARichtextViewEditWidget() {

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

		fieldsWidget.addField("Owner", new Label(task.getOwner() == null ? "No owner specified." : task.getOwner()
				.getName()));

	}

	@Override
	protected void onBlockUpdate() {
		setBlockTitle(task.getLabel());
		setIcon(task.isDone() ? Img.bundle.taskDoneIcon32() : Img.bundle.taskIcon32());
		if (!isSelected()) {
			summary.setText(task.getSummary());
			setContent(summary);
			setToolbar(null);
			return;
		}

		description.update();
		burnedWork.update();
		remainingWork.update();
		note.update();

		setContent(fieldsWidget);
		setToolbar(getToolbar());
	}

	public Task getTask() {
		return task;
	}

	@Override
	protected DropController createDropController() {
		return null;
	}

	protected Widget getToolbar() {
		if (toolbar == null) {
			toolbar = new ToolbarWidget();
			if (!task.isDone()) {
				toolbar.addButton("Own").addClickListener(new ClickListener() {

					public void onClick(Widget sender) {
						task.setOwner(ScrumGwtApplication.get().getUser());
						update();
					}

				});
			}

			toolbar.addButton(Img.bundle.delete16().createImage(), "Delete").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					delete();
				}

			});

			if (!task.isDone()) {
				toolbar.addButton(Img.bundle.done16().createImage(), "Done").addClickListener(new ClickListener() {

					public void onClick(Widget sender) {
						task.setDone();
						notifyListControllerDataChanged();
						update();
					}

				});
			}
		}
		return toolbar;
	}

	@Override
	protected AbstractImagePrototype getIcon16() {
		if (task.isDone()) return Img.bundle.taskDoneIcon16();
		return Img.bundle.taskIcon16();
	}

	@Override
	public void delete() {
		task.getRequirement().deleteTask(task);
		notifyListControllerDataChanged();
	}
}
