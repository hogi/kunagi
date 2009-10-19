package scrum.client;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.RichtextFormater;
import scrum.client.common.AScrumComponent;

public class RichtextConverter extends AScrumComponent implements RichtextFormater {

	@Override
	protected void onInitialization() {
		super.onInitialization();
		ARichtextViewEditWidget.setDefaultRichtextFormater(this);
	}

	public String toHtml(String text) {
		if (Gwt.isEmpty(text)) return text;
		String html = ScrumJs.regexTextToHtml(text);
		html = html.replace("\n", "<br>\n");
		html = html.replace("\r", "");
		return html;
	}

}