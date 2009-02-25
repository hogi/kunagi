package scrum.client.workspace;

import ilarkesto.gwt.client.AWidget;

import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HeaderWidget extends AWidget {

	@Override
	protected Widget onInitialization() {
		// Label label = new Label("Scrum42");
		// label.setStyleName("HeaderWidget");
		// return label;

		// SimplePanel panel = new SimplePanel();
		// panel.setStyleName("HeaderWidget");
		// panel.setWidget(new Label("Scrum42"));
		// return panel;

		Label title = new Label("Scrum42");
		title.setStyleName("title");

		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleName("HeaderWidget");
		panel.setWidth("100%");
		panel.add(title);
		panel.setCellVerticalAlignment(title, HasVerticalAlignment.ALIGN_MIDDLE);
		return panel;
	}

	@Override
	protected void onUpdate() {}

}
