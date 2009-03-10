package scrum.client.risks;

import ilarkesto.gwt.client.ADropdownViewEditWidget;
import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.ATextWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.dnd.ClipboardSupport;
import scrum.client.dnd.TrashSupport;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RiskBlock extends AExtensibleBlockWidget<Risk> implements TrashSupport, ClipboardSupport {

	private Risk risk;

	private FieldsWidget fields;
	private Label summary;
	private ToolbarWidget toolbar;

	@Override
	protected Risk getObject() {
		return risk;
	}

	@Override
	protected void setObject(Risk object) {
		this.risk = object;
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
		fields.add("Impact", new ADropdownViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(risk.getImpactLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setOptions(RiskComputer.getImpacts());
				setSelectedOption(String.valueOf(risk.getImpact()));
			}

			@Override
			protected void onEditorSubmit() {
				risk.setImpact(Integer.parseInt(getSelectedOption()));
			}
		});
		fields.add("Probability", new ADropdownViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(risk.getProbabilityLabel());
			}

			@Override
			protected void onEditorUpdate() {
				setOptions(RiskComputer.getProbabilities());
				setSelectedOption(String.valueOf(risk.getProbability()));
			}

			@Override
			protected void onEditorSubmit() {
				risk.setProbability(Integer.parseInt(getSelectedOption()));
			}
		});
		fields.add("Priority", new ATextWidget() {

			@Override
			protected void onUpdate() {
				setText(risk.getPriorityLabel());
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

	public Image getClipboardIcon() {
		return Img.bundle.risk16().createImage();
	}

	public String getClipboardLabel() {
		return risk.getLabel();
	}

	public ABlockWidget getClipboardPayload() {
		return this;
	}

	protected Widget getToolbar() {
		if (toolbar == null) {

			toolbar = new ToolbarWidget();

			toolbar.addButton(Img.bundle.delete16().createImage(), "Delete").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					ScrumGwtApplication.get().getProject().deleteRisk(risk);
					getList().removeSelectedRow();
				}
			});

		}
		return toolbar;
	}

	public Risk getRisk() {
		return risk;
	}

	public boolean isTrashable() {
		return true;
	}

	public void trash() {
		risk.getProject().deleteRisk(risk);
		getList().removeObject(risk);
	}

	public static BlockWidgetFactory<Risk> FACTORY = new BlockWidgetFactory<Risk>() {

		public RiskBlock createBlock() {
			return new RiskBlock();
		}
	};
}
