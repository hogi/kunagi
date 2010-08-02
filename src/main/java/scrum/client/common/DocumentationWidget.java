package scrum.client.common;

import ilarkesto.gwt.client.Gwt;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class DocumentationWidget extends AScrumWidget {

	private boolean enabled;
	private String text;

	private SimplePanel panel;

	public DocumentationWidget(boolean enabled, String text) {
		super();
		this.enabled = enabled;
		this.text = text;
	}

	@Override
	protected Widget onInitialization() {
		Widget content = enabled ? new HTML(text) : new Label("show");
		panel = Gwt.createDiv("DocumentationWidget", content);
		return panel;
	}

}
