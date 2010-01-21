package scrum.server.collaboration;

import ilarkesto.pdf.APdfBuilder;
import scrum.server.common.WikiToPdfConverter;

public class Wikipage extends GWikipage {

	public void buildPdf(APdfBuilder pdf) {
		WikiToPdfConverter.buildPdf(pdf, getText());
	}

	@Override
	public String toString() {
		return "Wiki-Page [[" + getName() + "]]";
	}

}