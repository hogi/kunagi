package scrum.client.wiki;

import ilarkesto.gwt.client.Gwt;

import java.util.ArrayList;
import java.util.List;

public class Paragraph extends AWikiElement {

	private List<AWikiElement> elements = new ArrayList<AWikiElement>();
	private boolean p;

	public Paragraph(boolean p) {
		super();
		this.p = p;
	}

	public void add(AWikiElement element) {
		elements.add(element);
	}

	@Override
	String toHtml(HtmlContext context) {
		StringBuilder sb = new StringBuilder();
		if (p) sb.append("<p>");
		for (AWikiElement element : elements) {
			sb.append(element.toHtml(context));
		}
		if (p) sb.append("</p>");
		return sb.toString();
	}

	public List<AWikiElement> getElements() {
		return elements;
	}

	@Override
	public String toString() {
		return "Paragraph(" + Gwt.toString(elements) + ")";
	}

}
