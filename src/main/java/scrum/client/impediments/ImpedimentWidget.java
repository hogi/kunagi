package scrum.client.impediments;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentWidget extends AExtensibleBlockWidget {

	private Impediment impediment;

	private FieldsWidget fields;
	private Label summary;
	private ToolbarWidget toolbar;

	public ImpedimentWidget(Impediment impediment) {
		this.impediment = impediment;
	}

	@Override
	protected void onCollapsedInitialization() {
		summary = new Label();
	}

	@Override
	protected void onCollapsedUpdate() {
		setBlockTitle(impediment.getLabel());
		setIcon(impediment.isSolved() ? Img.bundle.impedimentSolved32() : Img.bundle.impediment32());
		summary.setText(impediment.getSummary());
		setContent(summary);
		setToolbar(null);
	}

	@Override
	protected void onExtendedInitialization() {
		fields = new FieldsWidget();
		fields.setAutoUpdateWidget(this);

		fields.add("Label", new ATextViewEditWidget() {

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
			}

		});
		fields.add("Description", new ARichtextViewEditWidget() {

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
		fields.add("Solution", new ARichtextViewEditWidget() {

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
	protected void onExtendedUpdate() {
		setBlockTitle(impediment.getLabel());
		setIcon(impediment.isSolved() ? Img.bundle.impedimentSolved32() : Img.bundle.impediment32());
		fields.update();
		setContent(fields);
		setToolbar(getToolbar());
	}

	@Override
	public AbstractImagePrototype getIcon16() {
		// return different icon depending on solved-status
		if (impediment.isSolved()) return Img.bundle.impediment16();
		return Img.bundle.impediment16();
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
	public void delete() {
		ScrumGwtApplication.get().getProject().deleteImpediment(impediment);
		ImpedimentListWidget.get().list.remove(this);
	}
}
