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

		navigator.addItem(null, "Project Overview", new Runnable() {

			public void run() {
				WorkareaWidget.get().showProjectOverview();
			}
		});

		navigator.addItem(null, "Product Backlog", new Runnable() {

			public void run() {
				WorkareaWidget.get().showProductBacklog();
			}
		});

		navigator.addItem(Img.bundle.sprintIcon16().createImage(), "Sprint Backlog", new Runnable() {

			public void run() {
				WorkareaWidget.get().showSprintBacklog();
			}
		});

		navigator.addItem(Img.bundle.impedimentIcon16().createImage(), "Impediment List", new Runnable() {

			public void run() {
				WorkareaWidget.get().showImpedimentList();
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

	@Override
	protected void onUpdate() {
		navigator.update();
		trash.update();
		clipboard.update();
	}

}
