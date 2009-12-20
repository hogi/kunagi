package scrum.client;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.RichtextFormater;
import scrum.client.collaboration.Wikipage;
import scrum.client.common.AScrumComponent;
import scrum.client.wiki.ScrumHtmlContext;
import scrum.client.wiki.WikiModel;
import scrum.client.wiki.WikiParser;

import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle;

public class Wiki extends AScrumComponent implements RichtextFormater {

	@Override
	protected void onInitialization() {
		super.onInitialization();
		Gwt.setDefaultRichtextFormater(this);
	}

	public String getTemplate(String name) {
		Wikipage page = getCurrentProject().getWikipage("template:" + name);
		return page == null ? null : page.getText();
	}

	public String richtextToHtml(String text) {
		if (Gwt.isEmpty(text)) return text;
		return toHtml(text);
	}

	public static String toHtml(String wiki) {
		WikiParser parser = new WikiParser(wiki);
		WikiModel model = parser.parse();
		return model.toHtml(new ScrumHtmlContext());
	}

	public SuggestOracle createPagesSuggestOracle() {
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		for (Wikipage page : getCurrentProject().getWikipages()) {
			oracle.add(page.getName());
		}
		return oracle;
	}

}
