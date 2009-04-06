package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.NavigatorWidget;
import scrum.client.common.ScrumUtil;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class SidebarWidget extends AWidget {

	private NavigatorWidget navigator;
	private ClipboardWidget clipboard;
	private TrashWidget trash;

	@Override
	protected Widget onInitialization() {

		navigator = new NavigatorWidget();

		navigator.addItem(Img.bundle.project16(), "Project Overview", WorkareaWidget.get().getProjectOverview(),
			new Runnable() {

				public void run() {
					WorkareaWidget.get().showProjectOverview();
				}
			});

		navigator.addItem(Img.bundle.sprint16(), "Sprint Backlog", WorkareaWidget.get().getSprintBacklog(),
			new Runnable() {

				public void run() {
					WorkareaWidget.get().showSprintBacklog();
				}
			});

		navigator.addItem(Img.bundle.requirement16(), "Product Backlog", WorkareaWidget.get().getProductBacklog(),
			new Runnable() {

				public void run() {
					WorkareaWidget.get().showProductBacklog();
				}
			});

		navigator.addItem(Img.bundle.requirement16(), "Quality Backlog", WorkareaWidget.get().getQualityBacklog(),
			new Runnable() {

				public void run() {
					WorkareaWidget.get().showQualityBacklog();
				}
			});

		navigator.addItem(Img.bundle.impediment16(), "Impediment List", WorkareaWidget.get().getImpedimentList(),
			new Runnable() {

				public void run() {
					WorkareaWidget.get().showImpedimentList();
				}
			});

		navigator.addItem(Img.bundle.risk16(), "Risk Management", WorkareaWidget.get().getRiskList(), new Runnable() {

			public void run() {
				WorkareaWidget.get().showRiskList();
			}
		});

		navigator.addItem(Img.bundle.sprint16(), "Next Sprint", WorkareaWidget.get().getNextSprint(), new Runnable() {

			public void run() {
				WorkareaWidget.get().showNextSprint();
			}
		});

		navigator.addItem(Img.bundle.user16(), "User Management", WorkareaWidget.get().getUserList(), new Runnable() {

			public void run() {
				WorkareaWidget.get().showUserList();
			}
		});

		navigator.addItem(Img.bundle.test16(), "WidgetsTester", WorkareaWidget.get().getWidgetsTester(),
			new Runnable() {

				public void run() {
					WorkareaWidget.get().showWidgetsTester();
				}
			});

		trash = new TrashWidget();

		clipboard = new ClipboardWidget();

		FlowPanel sidebar = new FlowPanel();
		sidebar.setStyleName("SidebarWidget");
		sidebar.add(ScrumUtil.createEmptyDiv("beforeNavigatorSpacer"));
		sidebar.add(navigator);
		sidebar.add(trash);
		sidebar.add(clipboard);

		return sidebar;
	}

	public void navigateToSprintBacklog() {
		navigator.select(WorkareaWidget.get().getSprintBacklog());
	}

	@Override
	protected void onUpdate() {
		navigator.update();
		trash.update();
		clipboard.update();
	}

	public static SidebarWidget get() {
		return WorkspaceWidget.get().getSidebar();
	}

}
