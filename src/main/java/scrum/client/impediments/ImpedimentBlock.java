package scrum.client.impediments;

import ilarkesto.gwt.client.ADateViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.Gwt;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.Widget;

public class ImpedimentBlock extends AExtensibleBlockWidget<Impediment> implements TrashSupport {

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.impediment16());
	}

	@Override
	protected void onUpdateHead() {
		Impediment impediment = getObject();
		setBlockTitle(impediment.getReference() + " [" + impediment.getDate() + "] " + impediment.getLabel());
		String style = null;
		if (impediment.isClosed()) {
			style = "ImpedimentBlock-closed";
		} else {
			style = "ImpedimentBlock-open";
		}
		setAdditionalStyleName(style);
		setIcon(Img.bundle.impediment16());
		addMenuAction(new CloseImpedimentAction(impediment));
		addMenuAction(new DeleteImpedimentAction(impediment));
	}

	@Override
	protected Widget onExtendedInitialization() {
		final Impediment impediment = getObject();

		FieldsWidget fields = new FieldsWidget();

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
		fields.add("Date", new ADateViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerValue(impediment.getDate());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorValue(impediment.getDate());
			}

			@Override
			protected void onEditorSubmit() {
				impediment.setDate(getEditorValue());
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

		return Gwt.createFlowPanel(fields, new CommentsWidget(impediment));
	}

	public AScrumAction getTrashAction() {
		return new DeleteImpedimentAction(getObject());
	}

	public static BlockWidgetFactory<Impediment> FACTORY = new BlockWidgetFactory<Impediment>() {

		public ImpedimentBlock createBlock() {
			return new ImpedimentBlock();
		}

	};

}
