package scrum.client.wiki;

import ilarkesto.gwt.client.Gwt;

import java.util.ArrayList;
import java.util.List;

public class UnorderedList extends AWikiElement {

	private List<Paragraph> items = new ArrayList<Paragraph>();

	public void add(Paragraph item) {
		items.add(item);
	}

	@Override
	String toHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ul>");
		for (Paragraph item : items) {
			sb.append("<li>");
			sb.append(item.toHtml());
			sb.append("</li>");
		}
		sb.append("</ul>");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "UnorderedList(" + Gwt.toString(items) + ")";
	}

}
