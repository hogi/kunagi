package scrum.server.common;

import ilarkesto.pdf.ACell;
import ilarkesto.pdf.AImage;
import ilarkesto.pdf.AParagraph;
import ilarkesto.pdf.APdfContainerElement;
import ilarkesto.pdf.ARow;
import ilarkesto.pdf.ATable;
import ilarkesto.pdf.FontStyle;

import java.awt.Color;

import scrum.client.wiki.AWikiElement;
import scrum.client.wiki.Code;
import scrum.client.wiki.EntityReference;
import scrum.client.wiki.Header;
import scrum.client.wiki.Highlight;
import scrum.client.wiki.Image;
import scrum.client.wiki.ItemList;
import scrum.client.wiki.LineBreak;
import scrum.client.wiki.Link;
import scrum.client.wiki.Paragraph;
import scrum.client.wiki.Pre;
import scrum.client.wiki.Table;
import scrum.client.wiki.TableCell;
import scrum.client.wiki.TableRow;
import scrum.client.wiki.Text;
import scrum.client.wiki.WikiModel;
import scrum.client.wiki.WikiParser;

public class WikiToPdfConverter extends APdfCreator {

	private WikiModel model;
	private PdfContext pdfContext;

	public WikiToPdfConverter(WikiModel model, PdfContext pdfContext) {
		this.model = model;
		this.pdfContext = pdfContext;
	}

	@Override
	protected void build(APdfContainerElement parent) {
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
		if (element instanceof Table) {
			parent.nl(defaultFont);
			processTable((Table) element, parent);
			return;
		}
		throw new RuntimeException("Unsupported Wiki-Element: " + element.getClass().getName());
	}

	private void processTable(Table wikiTable, APdfContainerElement parent) {
		ATable pdfTable = parent.table(wikiTable.getColumnCount());
		pdfTable.setWidth(null);
		pdfTable.setDefaultCellPadding(2f);
		for (TableRow wikiRow : wikiTable.getRows()) {
			ARow pdfRow = pdfTable.row();
			for (TableCell wikiCell : wikiRow.getCells()) {
				ACell pdfCell = pdfRow.cell();
				if (wikiCell.isHeader()) {
					pdfCell.setFontStyle(tableHeaderFont);
					pdfCell.setBackgroundColor(tableHeaderBackground);
					processParagraph(wikiCell.getParagraph(), pdfCell, tableHeaderFont);
				} else {
					processParagraph(wikiCell.getParagraph(), pdfCell);
				}
			}
		}
		pdfTable.createCellBorders(Color.GRAY, 0.2f);
	}

	private void processItemList(ItemList list, APdfContainerElement parent) {
		boolean ordered = list.isOrdered();
		ATable table = parent.table(1, ordered ? 30 : 45);
		int counter = 0;
		for (ItemList.Item item : list.getItems()) {
			ARow row = table.row();
			counter++;
			row.cell(ordered ? counter + "." : "-", defaultFont);
			ACell cell = row.cell();
			processParagraph(item.getParagraph(), cell);
			if (item.containsList()) {
				cell.paragraph().nl(new FontStyle(defaultFont).setSize(0.7f));
				processItemList(item.getList(), cell);
			}
		}
	}

	private void processPre(Pre pre, APdfContainerElement parent) {
		parent.paragraph().text(pre.getText(), codeFont);
	}

	private void processParagraph(Paragraph element, APdfContainerElement parent) {
		processParagraph(element, parent, defaultFont);
	}

	private void processParagraph(Paragraph element, APdfContainerElement parent, FontStyle font) {
		AParagraph paragraph = parent.paragraph();
		for (AWikiElement paragraphElement : element.getElements()) {
			processParagraphElement(paragraphElement, paragraph, font);
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
		if (element instanceof Image) {
			processImage((Image) element, parent);
			return;
		}
		if (element instanceof LineBreak) {
			parent.nl();
			return;
		}
		throw new RuntimeException("Unsupported Wiki-Paragraph-Element: " + element.getClass().getName());
	}

	private void processImage(Image image, AParagraph parent) {
		AImage pdfImage = pdfContext.appendImage(parent, image);
		if (pdfImage == null) return;

		if (image.isThumb()) {
			if (image.isThumbAlignmentLeft()) {
				pdfImage.setAlignLeft();
			} else {
				pdfImage.setAlignRight();
			}
			pdfImage.setScaleByWidth(40f);
		}

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

	public static void buildPdf(APdfContainerElement parent, String code, PdfContext pdfContext) {
		if (code == null) return;
		WikiParser parser = new WikiParser(code);
		WikiModel model = parser.parse(false);
		WikiToPdfConverter converter = new WikiToPdfConverter(model, pdfContext);
		converter.build(parent);
	}

	@Override
	protected String getFilename() {
		return "wiki";
	}

}
