package scrum.client.wiki;

import ilarkesto.gwt.client.Gwt;

import java.util.ArrayList;
import java.util.List;

public class UnorderedList extends AWikiElement {

	private List<ListItem> items = new ArrayList<ListItem>();

	public void add(ListItem item) {
		items.add(item);
	}

	@Override
	String toHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ul>");
		for (ListItem item : items) {
			sb.append(item.toHtml());
		}
		sb.append("</ul>");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "UnorderedList(" + Gwt.toString(items) + ")";
	}

}
