package scrum.client.dnd;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.StyleSheet;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class DummyDropWidget extends Composite {

	private SimplePanel panel;

	private DropController dropController;

	public DummyDropWidget() {
		panel = new SimplePanel();
		panel.setStyleName(StyleSheet.ELEMENT_DUMMY_DROP_WIDGET);

		initWidget(panel);
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
