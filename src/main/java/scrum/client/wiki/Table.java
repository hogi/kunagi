package scrum.client.wiki;

import java.util.ArrayList;
import java.util.List;

public class Table extends AWikiElement {

	private List<TableRow> rows = new ArrayList<TableRow>();
	private TableRow currentRow;

	@Override
	String toHtml(HtmlContext context) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n<table class='data-table'>\n");
		for (TableRow row : rows) {
			sb.append(row.toHtml(context));
		}
		sb.append("</table>\n");
		return sb.toString();
	}

	public void addCell(Paragraph p, boolean header) {
		if (p == null) return;
		if (currentRow == null) {
			currentRow = new TableRow();
			rows.add(currentRow);
		}
		currentRow.addCell(new TableCell(p, header));
	}

	public void nextRow() {
		currentRow = null;
	}

	public List<TableRow> getRows() {
		return rows;
	}

	public int getColumnCount() {
		int count = 0;
		for (TableRow row : rows) {
			int cells = row.getCells().size();
			if (cells > count) count = cells;
		}
		return count;
	}
}
