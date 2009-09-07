package scrum.client.impediments;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ToolbarWidget;
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

public class ImpedimentBlock extends AExtensibleBlockWidget<Impediment> implements TrashSupport, ClipboardSupport {

	private Impediment impediment;

	private FieldsWidget fields;
	private Label summary;
	private ToolbarWidget toolbar;

	@Override
	protected Impediment getObject() {
		return impediment;
	}

	@Override
	protected void setObject(Impediment object) {
		this.impediment = object;
	}

	@Override
	protected void onCollapsedInitialization() {
		summary = new Label();
	}

	@Override
	protected void onHeadUpdate() {
		setBlockTitle(impediment.getLabel());
		setIcon(Img.bundle.impediment16());
		// summary.setText(impediment.getSummary());
		// setContent(summary);
		createToolbar();
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
	protected void onContentUpdate() {
		fields.update();
		setContent(fields);
	}

	public String getClipboardLabel() {
		return impediment.getLabel();
	}

	public Image getClipboardIcon() {
		return Img.bundle.impediment16().createImage();
	}

	public ABlockWidget getClipboardPayload() {
		return this;
	}

	private void createToolbar() {
		addMenuCommand("Delete", new Command() {

			public void execute() {
				ScrumGwtApplication.get().getProject().deleteImpediment(impediment);
				ImpedimentListWidget.get().list.removeSelectedRow();
			}
		});

		if (!impediment.isSolved()) {
			// impediment not solved -> add [Solve] button
			addMenuCommand("Mark Solved", new Command() {

				public void execute() {
					impediment.setSolved();
					update();
				}
			});
		} else {
			// impediment not solved -> add [Unsolve] button
			addMenuCommand("Mark Unsolved", new Command() {

				public void execute() {
					impediment.setSolveDate(null);
					update();
				}
			});
		}
	}

	public boolean isTrashable() {
		return true;
	}

	public void trash() {
		impediment.getProject().deleteImpediment(impediment);
		getList().removeObject(impediment);
	}

	public static BlockWidgetFactory<Impediment> FACTORY = new BlockWidgetFactory<Impediment>() {

		public ImpedimentBlock createBlock() {
			return new ImpedimentBlock();
		}
	};
}
