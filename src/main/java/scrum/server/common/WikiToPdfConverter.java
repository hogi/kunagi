package scrum.server.common;

import ilarkesto.pdf.AParagraph;
import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.ARow;
import ilarkesto.pdf.ATable;
import ilarkesto.pdf.FontStyle;
import scrum.client.wiki.AWikiElement;
import scrum.client.wiki.Code;
import scrum.client.wiki.EntityReference;
import scrum.client.wiki.Header;
import scrum.client.wiki.Highlight;
import scrum.client.wiki.ItemList;
import scrum.client.wiki.Link;
import scrum.client.wiki.Paragraph;
import scrum.client.wiki.Pre;
import scrum.client.wiki.Text;
import scrum.client.wiki.WikiModel;

public class WikiToPdfConverter {

	private WikiModel model;

	private FontStyle defaultFont;
	private FontStyle codeFont;
	private FontStyle referenceFont;
	private FontStyle[] headerFonts = new FontStyle[4];

	public WikiToPdfConverter(WikiModel model) {
		this.model = model;

		defaultFont = new FontStyle().setSize(4);
		codeFont = new FontStyle(defaultFont).setFont("Courier");
		referenceFont = new FontStyle(defaultFont).setFont("Courier").setItalic(true);
		headerFonts[3] = new FontStyle(defaultFont).setSize(defaultFont.getSize() + 0.2f).setBold(true);
		headerFonts[2] = new FontStyle(defaultFont).setSize(headerFonts[3].getSize() + 0.8f).setBold(true);
		headerFonts[1] = new FontStyle(defaultFont).setSize(headerFonts[2].getSize() + 1.0f).setBold(true);
		headerFonts[0] = new FontStyle(defaultFont).setSize(headerFonts[1].getSize() + 1.5f).setBold(true);
	}

	public void build(APdfContainerElement parent) {
		for (AWikiElement element : model.getElements())
			processElement(element, parent);
	}

	private void processElement(AWikiElement element, APdfContainerElement parent) {
		if (element instanceof Paragraph) {
			parent.nl(defaultFont);
			processParagraph((Paragraph) element, parent);
			return;
		}
		if (element instanceof Header) {
			parent.nl(defaultFont);
			processHeader((Header) element, parent);
			return;
		}
		if (element instanceof Pre) {
			parent.nl(defaultFont);
			processPre((Pre) element, parent);
			return;
		}
		if (element instanceof ItemList) {
			parent.nl(defaultFont);
			processItemList((ItemList) element, parent);
			return;
		}
		throw new RuntimeException("Unsupported Wiki-Element: " + element.getClass().getName());
	}

	private void processItemList(ItemList list, APdfContainerElement parent) {
		boolean ordered = list.isOrdered();
		ATable table = parent.table(1, ordered ? 30 : 45);
		int counter = 0;
		for (Paragraph item : list.getItems()) {
			ARow row = table.row();
			counter++;
			row.cell(ordered ? counter + "." : "-", defaultFont);
			processParagraph(item, row.cell());
		}
	}

	private void processPre(Pre pre, APdfContainerElement parent) {
		parent.paragraph().text(pre.getText(), codeFont);
	}

	private void processParagraph(Paragraph element, APdfContainerElement parent) {
		AParagraph paragraph = parent.paragraph();
		for (AWikiElement paragraphElement : element.getElements()) {
			processParagraphElement(paragraphElement, paragraph, defaultFont);
		}
	}

	private void processHeader(Header element, APdfContainerElement parent) {
		parent.paragraph().text(element.getText(), headerFonts[element.getDepth() - 1]).nl();
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
		if (element instanceof EntityReference) {
			processEntityReference((EntityReference) element, parent);
			return;
		}
		if (element instanceof Link) {
			processLink((Link) element, parent);
			return;
		}
		throw new RuntimeException("Unsupported Wiki-Paragraph-Element: " + element.getClass().getName());
	}

	private void processLink(Link link, AParagraph parent) {
		parent.text(link.getLabel(), defaultFont).text(" " + link.getHref(), referenceFont);
	}

	private void processEntityReference(EntityReference ref, AParagraph parent) {
		parent.text(ref.getLabel(), referenceFont);
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
