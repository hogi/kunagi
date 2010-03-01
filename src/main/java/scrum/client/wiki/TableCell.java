package scrum.client.wiki;

public class TableCell extends AWikiElement {

	private Paragraph paragraph;

	public TableCell(Paragraph paragraph) {
		super();
		this.paragraph = paragraph;
	}

	@Override
	String toHtml(HtmlContext context) {
		StringBuilder sb = new StringBuilder();
		sb.append("<td>");
		sb.append(paragraph.toHtml(context));
		sb.append("</td>");
		return sb.toString();
	}

	public Paragraph getParagraph() {
		return paragraph;
	}

}
