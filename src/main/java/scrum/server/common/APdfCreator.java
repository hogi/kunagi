package scrum.server.common;

import ilarkesto.pdf.APdfBuilder;
import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.FontStyle;

import java.awt.Color;

public abstract class APdfCreator {

	protected FontStyle defaultFont;
	protected FontStyle tableHeaderFont;
	protected FontStyle codeFont;
	protected FontStyle referenceFont;
	protected FontStyle fieldLabelFont;
	protected FontStyle[] headerFonts = new FontStyle[4];
	protected Color tableHeaderBackground = Color.LIGHT_GRAY;

	protected abstract void build(APdfContainerElement pdf);

	protected abstract String getFilename();

	public APdfCreator() {
		defaultFont = new FontStyle().setSize(3);
		tableHeaderFont = new FontStyle(defaultFont).setBold(true);
		codeFont = new FontStyle(defaultFont).setFont("Courier");
		referenceFont = new FontStyle(defaultFont).setFont("Courier").setItalic(true);
		fieldLabelFont = new FontStyle(defaultFont).setItalic(true);
		headerFonts[3] = new FontStyle(defaultFont).setSize(defaultFont.getSize() + 0.2f).setBold(true);
		headerFonts[2] = new FontStyle(defaultFont).setSize(headerFonts[3].getSize() + 0.7f).setBold(true);
		headerFonts[1] = new FontStyle(defaultFont).setSize(headerFonts[2].getSize() + 0.7f).setBold(true);
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
