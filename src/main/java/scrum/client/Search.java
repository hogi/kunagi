package scrum.client;

import ilarkesto.gwt.client.Gwt;
import scrum.client.common.AScrumComponent;

public class Search extends AScrumComponent {

	public void search(String text) {
		if (Gwt.isEmpty(text)) return;
		log.info("Searching:", text);
		cm.getApp().callSearch(text);
		// TODO show result view
		// TODO local search
	}

}