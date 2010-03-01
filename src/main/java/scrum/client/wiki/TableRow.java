package scrum.client.wiki;

import java.util.ArrayList;
import java.util.List;

public class TableRow extends AWikiElement {

	private List<TableCell> cells = new ArrayList<TableCell>();

	@Override
	String toHtml(HtmlContext context) {
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		for (TableCell cell : cells) {
			sb.append(" ");
			sb.append(cell.toHtml(context));
			sb.append(" ");
		}
		sb.append("</tr>\n");
		return sb.toString();
	}

	public void addCell(TableCell cell) {
		cells.add(cell);
	}

	public List<TableCell> getCells() {
		return cells;
	}

}
