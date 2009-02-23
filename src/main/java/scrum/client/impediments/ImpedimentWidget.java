package scrum.client.impediments;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.dnd.BlockListDropController;
import scrum.client.img.Img;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentWidget extends ABlockWidget {

	private Impediment impediment;

	private ItemFieldsWidget fieldsWidget;
	private Label summary;
	private ToolbarWidget toolbar;

	private ATextViewEditWidget label;
	private ARichtextViewEditWidget description;
	private ARichtextViewEditWidget solution;

	public ImpedimentWidget(Impediment impediment) {
		this.impediment = impediment;
	}

	@Override
	protected void onBlockInitialization() {
		summary = new Label();

		fieldsWidget = new ItemFieldsWidget();

		label = fieldsWidget.addField("Label", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(impediment.getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(impediment.getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				impediment.setLabel(getEditorText());
				update();
			}

		});
		description = fieldsWidget.addField("Description", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(impediment.getDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(impediment.getDescription());
			}

			@Override
			protected void onEditorSubmit() {
				impediment.setDescription(getEditorText());
			}
		});
		solution = fieldsWidget.addField("Solution", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(impediment.getSolution());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(impediment.getSolution());
			}

			@Override
			protected void onEditorSubmit() {
				impediment.setSolution(getEditorText());
			}
		});

	}

	@Override
	protected void onBlockUpdate() {
		setBlockTitle(impediment.getLabel());
		setIcon(impediment.isSolved() ? Img.bundle.impedimentSolvedIcon32() : Img.bundle.impedimentIcon32());
		if (!isSelected()) {
			summary.setText(impediment.getSummary());
			setContent(summary);
			setToolbar(null);
			return;
		}
		label.update();
		description.update();
		solution.update();
		setContent(fieldsWidget);
		setToolbar(getToolbar());
	}

	@Override
	protected AbstractImagePrototype getIcon16() {
		// return different icon depending on solved-status
		if (impediment.isSolved()) return Img.bundle.impedimentSolvedIcon16();
		return Img.bundle.impedimentIcon16();
	}

	protected Widget getToolbar() {
		if (toolbar == null) {

			toolbar = new ToolbarWidget();

			toolbar.addButton(Img.bundle.delete16().createImage(), "Delete").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					ScrumGwtApplication.get().getProject().deleteImpediment(impediment);
					ImpedimentListWidget.get().list.removeSelectedRow();
				}
			});

			if (!impediment.isSolved()) {
				// impediment not solved -> add [Solve] button
				toolbar.addButton(Img.bundle.done16().createImage(), "Mark Solved").addClickListener(
					new ClickListener() {

						public void onClick(Widget sender) {
							impediment.setSolved();
							update();
						}
					});
			} else {
				// impediment not solved -> add [Unsolve] button
				toolbar.addButton("Mark Unsolved").addClickListener(new ClickListener() {

					public void onClick(Widget sender) {
						impediment.setSolveDate(null);
						update();
					}
				});
			}
		}

		return toolbar;
	}

	@Override
	protected DropController createDropController() {
		return new BlockListDropController(this, ImpedimentListWidget.get().list);
	}

	@Override
	public void delete() {
		ScrumGwtApplication.get().getProject().deleteImpediment(impediment);
		ImpedimentListWidget.get().list.remove(this);
	}
}
