package scrum.client.tasks;

import ilarkesto.gwt.client.ADropdownViewEditWidget;
import ilarkesto.gwt.client.AFieldValueWidget;
import ilarkesto.gwt.client.AIntegerViewEditWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;

import java.util.HashMap;
import java.util.Map;

import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.impediments.Impediment;
import scrum.client.sprint.Task;

import com.google.gwt.user.client.ui.Widget;

public class TaskWidget extends AScrumWidget {

	private Task task;
	private boolean wideMode;

	public TaskWidget(Task task, boolean wideMode) {
		this.task = task;
		this.wideMode = wideMode;
	}

	@Override
	protected Widget onInitialization() {

		TableBuilder tb = ScrumGwt.createFieldTable();

		tb.addFieldRow("Label", task.getLabelModel(), 3);

		tb.addFieldRow("Description", task.getDescriptionModel(), 3);

		tb.addField("Burned", new AIntegerViewEditWidget() {

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

			@Override
			public boolean isEditable() {
				return task.isEditable();
			}
		});

		tb.addFieldRow("Remaining", new TaskRemainingWorkWidget(task));

		tb.addFieldRow("Owner", new AFieldValueWidget() {

			@Override
			protected void onUpdate() {
				setText(task.getOwner() == null ? null : task.getOwner().getName());
			}
		}, 3);

		tb.addFieldRow("Impediment", new ADropdownViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerItem(task.getImpediment());
			}

			@Override
			protected void onEditorUpdate() {
				Map<String, String> options = new HashMap<String, String>();
				for (Impediment impediment : task.getProject().getOpenImpediments()) {
					options.put(impediment.getId(), impediment.getReferenceAndLabel());
				}
				setOptions(options);
				Impediment impediment = task.getImpediment();
				setSelectedOption(impediment == null ? null : impediment.getId());
			}

			@Override
			protected void onEditorSubmit() {
				String id = getSelectedOption();
				task.setImpediment(id == null ? null : getDao().getImpediment(id));
			}

			@Override
			public boolean isEditable() {
				return task.isEditable();
			}

			@Override
			public String getTooltip() {
				return "Impediment, which is blocking this task.";
			}

		}, 3);

		return wideMode ? TableBuilder.row(20, tb.createTable(), ScrumGwt.createEmoticonsAndComments(task)) : Gwt
				.createFlowPanel(tb.createTable(), ScrumGwt.createEmoticonsAndComments(task));
	}
}
