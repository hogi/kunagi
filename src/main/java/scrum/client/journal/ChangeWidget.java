package scrum.client.journal;

import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ChangeWidget extends AScrumWidget {

	private Change change;

	public ChangeWidget(Change change) {
		super();
		this.change = change;
	}

	@Override
	protected Widget onInitialization() {
		return new Label(change.toString());
	}

}
