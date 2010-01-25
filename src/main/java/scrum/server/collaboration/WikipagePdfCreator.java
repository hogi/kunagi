package scrum.server.collaboration;

import ilarkesto.pdf.APdfContainerElement;
import scrum.server.common.APdfCreator;
import scrum.server.common.WikiToPdfConverter;

public class WikipagePdfCreator extends APdfCreator {

	private Wikipage wikipage;

	public WikipagePdfCreator(Wikipage wikipage) {
		super();
		this.wikipage = wikipage;
	}

	@Override
	protected void build(APdfContainerElement pdf) {
		WikiToPdfConverter.buildPdf(pdf, wikipage.getText());
	}

	@Override
	protected String getFilename() {
		return wikipage.getName();
	}

}
