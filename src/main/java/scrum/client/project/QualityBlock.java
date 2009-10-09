package scrum.client.project;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.Gwt;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class QualityBlock extends AExtensibleBlockWidget<Quality> implements TrashSupport, ClipboardSupport {

	private Quality quality;

	@Override
	protected Quality getObject() {
		return quality;
	}

	@Override
	protected void setObject(Quality object) {
		this.quality = object;
	}

	@Override
	protected void onCollapsedInitialization() {
		setIcon(Img.bundle.requirement16());
	}

	@Override
	protected void onUpdateHead() {
		setBlockTitle(quality.getReference() + " " + quality.getLabel());
		addMenuAction(new DeleteQualityAction(quality));
	}

	@Override
	protected Widget onExtendedInitialization() {
		FieldsWidget fields = new FieldsWidget();
		fields.add("Label", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(quality.getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(quality.getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				quality.setLabel(getEditorText());
			}

		});

		fields.add("Description", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(quality.getDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(quality.getDescription());
			}

			@Override
			protected void onEditorSubmit() {
				quality.setDescription(getEditorText());
			}

		});

		fields.add("Test", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(quality.getTestDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(quality.getTestDescription());
			}

			@Override
			protected void onEditorSubmit() {
				quality.setTestDescription(getEditorText());
			}

		});

		return Gwt.createFlowPanel(fields, new CommentsWidget(quality));
	}

	public Image getClipboardIcon() {
		return Img.bundle.requirement16().createImage();
	}

	public String getClipboardLabel() {
		return quality.getLabel();
	}

	public ABlockWidget getClipboardPayload() {
		return this;
	}

	public AScrumAction getTrashAction() {
		return new DeleteQualityAction(quality);
	}

	public Quality getQuality() {
		return quality;
	}

	public static BlockWidgetFactory<Quality> FACTORY = new BlockWidgetFactory<Quality>() {

		public QualityBlock createBlock() {
			return new QualityBlock();
		}
	};
}
