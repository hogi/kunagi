package scrum.server.common;

import ilarkesto.pdf.APdfBuilder;
import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.FontStyle;

public abstract class APdfCreator {

	protected FontStyle defaultFont;
	protected FontStyle codeFont;
	protected FontStyle referenceFont;
	protected FontStyle[] headerFonts = new FontStyle[4];

	protected abstract void build(APdfContainerElement pdf);

	protected abstract String getFilename();

	public APdfCreator() {
		defaultFont = new FontStyle().setSize(4);
		codeFont = new FontStyle(defaultFont).setFont("Courier");
		referenceFont = new FontStyle(defaultFont).setFont("Courier").setItalic(true);
		headerFonts[3] = new FontStyle(defaultFont).setSize(defaultFont.getSize() + 0.2f).setBold(true);
		headerFonts[2] = new FontStyle(defaultFont).setSize(headerFonts[3].getSize() + 0.8f).setBold(true);
		headerFonts[1] = new FontStyle(defaultFont).setSize(headerFonts[2].getSize() + 1.0f).setBold(true);
		headerFonts[0] = new FontStyle(defaultFont).setSize(headerFonts[1].getSize() + 1.5f).setBold(true);
	}

	public void createPdf(APdfBuilder pdf) {
		pdf.setDefaultFontStyle(defaultFont);
		build(pdf);
	}

	// --- helper ---

	protected void wiki(APdfContainerElement parent, String wikiCode) {
		WikiToPdfConverter.buildPdf(parent, wikiCode);
	}

}
