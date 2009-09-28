package scrum.client.impediments;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.Image;

public class ImpedimentBlock extends AExtensibleBlockWidget<Impediment> implements TrashSupport, ClipboardSupport {

	private Impediment impediment;

	private FieldsWidget fields;

	@Override
	protected Impediment getObject() {
		return impediment;
	}

	@Override
	protected void setObject(Impediment object) {
		this.impediment = object;
	}

	@Override
	protected void onCollapsedInitialization() {}

	@Override
	protected void onUpdateHead() {
		setBlockTitle("[" + impediment.getDate() + "] " + impediment.getLabel());
		setIcon(Img.bundle.impediment16());
		addMenuAction(new DeleteImpedimentAction(impediment));
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
	protected void onUpdateBody() {
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

	public AScrumAction getTrashAction() {
		return new DeleteImpedimentAction(impediment);
	}

	public static BlockWidgetFactory<Impediment> FACTORY = new BlockWidgetFactory<Impediment>() {

		public ImpedimentBlock createBlock() {
			return new ImpedimentBlock();
		}
	};

}
