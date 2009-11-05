package scrum.client.wiki;

import ilarkesto.gwt.client.Gwt;

import java.util.ArrayList;
import java.util.List;

public class ItemList extends AWikiElement {

	private List<Paragraph> items = new ArrayList<Paragraph>();
	boolean ordered;

	public ItemList(boolean ordered) {
		super();
		this.ordered = ordered;
	}

	public void add(Paragraph item) {
		items.add(item);
	}

	@Override
	String toHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append(ordered ? "<ol>" : "<ul>");
		for (Paragraph item : items) {
			sb.append("<li>");
			sb.append(item.toHtml());
			sb.append("</li>");
		}
		sb.append(ordered ? "</ol>" : "</ul>");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "UnorderedList(" + Gwt.toString(items) + ")";
	}

}
