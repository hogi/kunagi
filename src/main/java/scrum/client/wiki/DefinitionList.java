package scrum.client.wiki;

import ilarkesto.gwt.client.Gwt;

import java.util.ArrayList;
import java.util.List;

public class DefinitionList extends AWikiElement {

	private List<Paragraph> items = new ArrayList<Paragraph>();

	public void add(Paragraph item) {
		items.add(item);
	}

	@Override
	String toHtml(HtmlContext context) {
		StringBuilder sb = new StringBuilder();
		sb.append("<dl>");
		for (Paragraph item : items) {
			sb.append("<li>");
			sb.append(item.toHtml(context));
			sb.append("</li>");
		}
		sb.append("</dl>");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "DefinitionList(" + Gwt.toString(items) + ")";
	}

}
