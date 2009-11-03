package scrum.client;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.RichtextFormater;
import scrum.client.common.AScrumComponent;
import scrum.client.wiki.Wiki;

public class RichtextConverter extends AScrumComponent implements RichtextFormater {

	@Override
	protected void onInitialization() {
		super.onInitialization();
		Gwt.setDefaultRichtextFormater(this);
	}

	public String toHtml(String text) {
		if (Gwt.isEmpty(text)) return text;
		return Wiki.toHtml(text);
	}

}