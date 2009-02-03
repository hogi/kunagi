package scrum.client.dnd;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.StyleSheet;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class DndMarkerWidget extends Composite {

	private SimplePanel panel;

	private DropController dropController;

	public DndMarkerWidget() {
		panel = new SimplePanel();
		panel.setStyleName(StyleSheet.ELEMENT_DND_MARKER_WIDGET);

		initWidget(panel);
	}

	public void setActive(boolean active) {
		panel.setStyleName(active ? StyleSheet.ELEMENT_DND_MARKER_WIDGET_ACTIVE : StyleSheet.ELEMENT_DND_MARKER_WIDGET);
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		if (dropController != null)
			ScrumGwtApplication.get().getDragController().registerDropController(dropController);
	}

	@Override
	protected void onDetach() {
		if (dropController != null)
			ScrumGwtApplication.get().getDragController().unregisterDropController(dropController);
		super.onDetach();
	}

}
