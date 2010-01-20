package scrum.server.common;

import ilarkesto.pdf.AParagraph;
import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.FontStyle;
import scrum.client.wiki.AWikiElement;
import scrum.client.wiki.Code;
import scrum.client.wiki.Header;
import scrum.client.wiki.Highlight;
import scrum.client.wiki.Paragraph;
import scrum.client.wiki.Text;
import scrum.client.wiki.WikiModel;

public class WikiToPdfConverter {

	private WikiModel model;

	private FontStyle defaultFont;
	private FontStyle codeFont;
	private FontStyle[] headerFonts = new FontStyle[6];

	public WikiToPdfConverter(WikiModel model) {
		this.model = model;

		defaultFont = new FontStyle().setSize(4);
		codeFont = new FontStyle(defaultFont).setFont("Courier");
		headerFonts[0] = new FontStyle(defaultFont).setSize(8.0f).setBold(true);
		headerFonts[1] = new FontStyle(defaultFont).setSize(6.5f).setBold(true);
		headerFonts[2] = new FontStyle(defaultFont).setSize(5.5f).setBold(true);
		headerFonts[3] = new FontStyle(defaultFont).setSize(5.0f).setBold(true);
		headerFonts[4] = new FontStyle(defaultFont).setSize(4.5f).setBold(true);
		headerFonts[5] = new FontStyle(defaultFont).setSize(4.2f).setBold(true);
	}

	public void build(APdfContainerElement parent) {
		for (AWikiElement element : model.getElements())
			processElement(element, parent);
	}

	private void processElement(AWikiElement element, APdfContainerElement parent) {
		if (element instanceof Paragraph) {
			processParagraph((Paragraph) element, parent);
			return;
		}
		if (element instanceof Header) {
			processHeader((Header) element, parent);
			return;
		}
		throw new RuntimeException("Unsupported Wiki-Element: " + element.getClass().getName());
	}

	private void processParagraph(Paragraph element, APdfContainerElement parent) {
		AParagraph paragraph = parent.paragraph().setDefaultFontStyle(defaultFont).nl();
		for (AWikiElement paragraphElement : element.getElements()) {
			processParagraphElement(paragraphElement, paragraph, defaultFont);
		}
	}

	private void processHeader(Header element, APdfContainerElement parent) {
		parent.paragraph().setDefaultFontStyle(headerFonts[element.getDepth() - 1]).text(element.getText()).nl();
	}

	private void processParagraphElement(AWikiElement element, AParagraph parent, FontStyle fontStyle) {
		if (element instanceof Text) {
			processText((Text) element, parent, fontStyle);
			return;
		}
		if (element instanceof Highlight) {
			processHighlight((Highlight) element, parent, fontStyle);
			return;
		}
		if (element instanceof Code) {
			processCode((Code) element, parent);
			return;
		}
		throw new RuntimeException("Unsupported Wiki-Paragraph-Element: " + element.getClass().getName());
	}

	private void processCode(Code code, AParagraph parent) {
		parent.text(code.getText(), codeFont);
	}

	private void processHighlight(Highlight highlight, AParagraph parent, FontStyle fontStyle) {
		FontStyle newFontStyle = new FontStyle(fontStyle);
		if (highlight.isEm()) newFontStyle.setItalic(true);
		if (highlight.isStrong()) newFontStyle.setBold(true);
		for (AWikiElement element : highlight.getElements()) {
			processParagraphElement(element, parent, newFontStyle);
		}
	}

	private void processText(Text text, AParagraph parent, FontStyle fontStyle) {
		parent.text(text.getText(), fontStyle);
	}

}
