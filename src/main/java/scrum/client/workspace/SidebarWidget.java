package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.NavigatorWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.StyleSheet;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SidebarWidget extends AWidget {

	private NavigatorWidget navigator;

	@Override
	protected Widget onInitialization() {
		SimplePanel title = new SimplePanel();
		title.setStyleName("title");
		title.setWidget(new Label("Scrum42"));

		navigator = new NavigatorWidget();

		navigator.addItem(null, "Project Overview", new Runnable() {

			public void run() {
				WorkareaWidget.get().showProjectOverview();
			}
		});

		navigator.addItem(null, "Product Backlog", new Runnable() {

			public void run() {
				Ui.get().lock("Loading Backlog Items...");
				ScrumGwtApplication.get().callRequestRequirements(new Runnable() {

					public void run() {
						WorkareaWidget.get().showProductBacklog();
					}
				});
			}
		});

		navigator.addItem(Img.bundle.sprintIcon16().createImage(), "Sprint Backlog", new Runnable() {

			public void run() {
				Ui.get().lock("Loading Backlog Items...");
				ScrumGwtApplication.get().callRequestRequirements(new Runnable() {

					public void run() {
						Ui.get().lock("Loading Sprint...");
						ScrumGwtApplication.get().callRequestCurrentSprint(new Runnable() {

							public void run() {
								WorkareaWidget.get().showSprintBacklog();
							}
						});
					}
				});
			}
		});

		navigator.addItem(Img.bundle.impedimentIcon16().createImage(), "Impediment List", new Runnable() {

			public void run() {
				Ui.get().lock("Loading Backlog Items...");
				ScrumGwtApplication.get().callRequestRequirements(new Runnable() {

					public void run() {
						Ui.get().lock("Loading impediments...");
						ScrumGwtApplication.get().callRequestImpediments(new Runnable() {

							public void run() {
								WorkareaWidget.get().showImpedimentList();
							}
						});
					}
				});
			}
		});

		VerticalPanel sidebar = new VerticalPanel();
		sidebar.setStyleName(StyleSheet.ELEMENT_SIDEBAR_WIDGET);
		sidebar.add(title);
		sidebar.add(navigator);
		sidebar.add(new HTML("&nbsp;"));
		sidebar.add(new ClipboardWidget());

		return sidebar;
	}

	@Override
	protected void onUpdate() {
		navigator.update();
	}

}
