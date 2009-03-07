package scrum.client.project;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class AttributeWidget extends AExtensibleBlockWidget implements TrashSupport, ClipboardSupport {

	private Attribute attribute;

	private Label summary;
	private FieldsWidget fields;

	public AttributeWidget(Attribute item) {
		this.attribute = item;
	}

	@Override
	protected void onCollapsedInitialization() {
		summary = new Label();
	}

	@Override
	protected void onCollapsedUpdate() {
		setBlockTitle(attribute.getLabel());
		setIcon(Img.bundle.requirement32());
		summary.setText(attribute.getLabel());
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
				setViewerText(attribute.getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(attribute.getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				attribute.setLabel(getEditorText());
			}

		});

		fields.add("Description", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(attribute.getDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(attribute.getDescription());
			}

			@Override
			protected void onEditorSubmit() {
				attribute.setDescription(getEditorText());
			}

		});

		fields.add("Test", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(attribute.getTestDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(attribute.getTestDescription());
			}

			@Override
			protected void onEditorSubmit() {
				attribute.setTestDescription(getEditorText());
			}

		});
	}

	@Override
	protected void onExtendedUpdate() {
		setBlockTitle(attribute.getLabel());
		// Img.bundle.requirement32();
		fields.update();
		setContent(fields);
		setToolbar(createToolbar());
	}

	protected Widget createToolbar() {
		ToolbarWidget toolbar = new ToolbarWidget();

		toolbar.addButton(Img.bundle.delete16().createImage(), "Delete").addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				ScrumGwtApplication.get().getProject().deleteAttribute(attribute);
				AttributeBacklogWidget.get().list.removeSelectedRow();
			}
		});

		return toolbar;
	}

	public Image getClipboardIcon() {
		return Img.bundle.requirement16().createImage();
	}

	public String getClipboardLabel() {
		return attribute.getLabel();
	}

	public ABlockWidget getClipboardPayload() {
		return this;
	}

	public boolean isTrashable() {
		return true;
	}

	public void trash() {
		attribute.getProject().deleteAttribute(attribute);
		getList().remove(this);
	}

	public Attribute getAttribute() {
		return attribute;
	}
}
