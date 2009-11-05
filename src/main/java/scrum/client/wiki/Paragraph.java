package scrum.client.wiki;

import ilarkesto.gwt.client.Gwt;

import java.util.ArrayList;
import java.util.List;

public class Paragraph extends AWikiElement {

	private List<AWikiElement> items = new ArrayList<AWikiElement>();
	private boolean p;

	public Paragraph(boolean p) {
		super();
		this.p = p;
	}

	public void add(AWikiElement item) {
		items.add(item);
	}

	@Override
	String toHtml() {
		StringBuilder sb = new StringBuilder();
		if (p) sb.append("<p>");
		for (AWikiElement item : items) {
			sb.append(item.toHtml());
		}
		if (p) sb.append("</p>");
		return sb.toString();
	}

	public List<AWikiElement> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Paragraph(" + Gwt.toString(items) + ")";
	}

}
