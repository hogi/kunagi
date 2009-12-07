package scrum.client.search;

import ilarkesto.gwt.client.Gwt;
import scrum.client.common.AScrumWidget;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SearchInputWidget extends AScrumWidget {

	private TextBox input;

	@Override
	protected Widget onInitialization() {
		input = new TextBox();
		input.addKeyPressHandler(new InputHandler());
		return Gwt.createDiv("SearchInputWidget", input);
	}

	private void submitSearch() {
		cm.getSearch().search(input.getText());
	}

	class InputHandler implements KeyPressHandler {

		public void onKeyPress(KeyPressEvent event) {
			if (event.getCharCode() == KeyCodes.KEY_ENTER) {
				submitSearch();
			}
			event.stopPropagation();
		}

	}

}
