package scrum.client.wiki;

public class TableCell extends AWikiElement {

	private Paragraph paragraph;
	private boolean header;

	public TableCell(Paragraph paragraph, boolean header) {
		super();
		this.paragraph = paragraph;
		this.header = header;
	}

	@Override
	String toHtml(HtmlContext context) {
		StringBuilder sb = new StringBuilder();
		sb.append(header ? "<th>" : "<td>");
		sb.append(paragraph.toHtml(context));
		sb.append(header ? "</th>" : "</td>");
		return sb.toString();
	}

	public Paragraph getParagraph() {
		return paragraph;
	}

	public boolean isHeader() {
		return header;
	}

}
