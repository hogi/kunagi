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
	String toHtml(HtmlContext context) {
		StringBuilder sb = new StringBuilder();
		sb.append(ordered ? "<ol>" : "<ul>");
		for (Paragraph item : items) {
			sb.append("<li>");
			sb.append(item.toHtml(context));
			sb.append("</li>");
		}
		sb.append(ordered ? "</ol>" : "</ul>");
		return sb.toString();
	}

	public List<Paragraph> getItems() {
		return items;
	}

	public boolean isOrdered() {
		return ordered;
	}

	@Override
	public String toString() {
		return "ItemList(" + Gwt.toString(items) + ")";
	}

}
