package scrum.client.risks;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.FieldsWidget;
import scrum.client.img.Img;
import scrum.client.impediments.ImpedimentListWidget;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RiskWidget extends AExtensibleBlockWidget {

	private Risk risk;

	private FieldsWidget fields;
	private Label summary;
	private ToolbarWidget toolbar;

	public RiskWidget(Risk risk) {
		this.risk = risk;
	}

	@Override
	protected void onCollapsedInitialization() {
		summary = new Label();
	}

	@Override
	protected void onCollapsedUpdate() {
		setBlockTitle(risk.getLabel());
		setIcon(Img.bundle.risk32());
		summary.setText(risk.getSummary());
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
				setViewerText(risk.getLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(risk.getLabel());
			}

			@Override
			protected void onEditorSubmit() {
				risk.setLabel(getEditorText());
			}

		});
		fields.add("Description", new ARichtextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(risk.getDescription());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(risk.getDescription());
			}

			@Override
			protected void onEditorSubmit() {
				risk.setDescription(getEditorText());
			}
		});

	}

	@Override
	protected void onExtendedUpdate() {
		setBlockTitle(risk.getLabel());
		setIcon(Img.bundle.risk32());
		fields.update();
		setContent(fields);
		setToolbar(getToolbar());
	}

	@Override
	public AbstractImagePrototype getIcon16() {
		return Img.bundle.risk16();
	}

	protected Widget getToolbar() {
		if (toolbar == null) {

			toolbar = new ToolbarWidget();

			toolbar.addButton(Img.bundle.delete16().createImage(), "Delete").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					ScrumGwtApplication.get().getProject().deleteRisk(risk);
					ImpedimentListWidget.get().list.removeSelectedRow();
				}
			});

		}
		return toolbar;
	}

	@Override
	public void delete() {
		ScrumGwtApplication.get().getProject().deleteRisk(risk);
		getList().remove(this);
	}
}
