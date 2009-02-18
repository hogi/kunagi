package scrum.client.workspace;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.PanelWidget;
import scrum.client.common.StyleSheet;
import scrum.client.common.ToolbarWidget;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SidebarWidget extends Composite {

	public SidebarWidget() {
		VerticalPanel sidebar = new VerticalPanel();
		sidebar.setStyleName(StyleSheet.ELEMENT_SIDEBAR_WIDGET);

		ToolbarWidget toolbar = new ToolbarWidget();

		toolbar.addButton("Project Overview").addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				ScrumGwtApplication.get().getWorkspace().showProjectOverview();
			}

		});

		toolbar.addButton("Product Backlog").addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				ScrumGwtApplication.get().getWorkspace().lock("Loading Backlog Items...");
				ScrumGwtApplication.get().callRequestRequirements(new Runnable() {

					public void run() {
						ScrumGwtApplication.get().getWorkspace().showBacklog();
					}
				});
			}
		});

		toolbar.addButton(Img.bundle.sprintIcon16().createImage(), "Sprint Backlog").addClickListener(
			new ClickListener() {

				public void onClick(Widget sender) {
					ScrumGwtApplication.get().getWorkspace().lock("Loading Sprint...");
					ScrumGwtApplication.get().callRequestCurrentSprint(new Runnable() {

						public void run() {
							ScrumGwtApplication.get().getWorkspace().showSprint();
						}
					});
				}

			});

		toolbar.addButton(Img.bundle.impedimentIcon16().createImage(), "Impediments").addClickListener(
			new ClickListener() {

				public void onClick(Widget sender) {
					ScrumGwtApplication.get().getWorkspace().lock("Loading impediments...");
					ScrumGwtApplication.get().callRequestImpediments(new Runnable() {

						public void run() {
							ScrumGwtApplication.get().getWorkspace().showImpediments();
						}
					});
				}
			});

		toolbar.addButton("Test").addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				ScrumGwtApplication.get().getWorkspace().showTest();
			}
		});

		sidebar.add(new PanelWidget("Navigation", toolbar));
		sidebar.add(new HTML("&nbsp;"));
		sidebar.add(new PanelWidget("Clipboard", new ClipboardWidget()));

		initWidget(sidebar);
	}
}
