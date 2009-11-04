package scrum.client.wiki;

import java.util.ArrayList;
import java.util.List;

public class WikiModel {

	private List<AWikiElement> elements = new ArrayList<AWikiElement>();

	public void add(AWikiElement element) {
		elements.add(element);
	}

	public String toHtml() {
		StringBuilder sb = new StringBuilder();
		for (AWikiElement element : elements) {
			sb.append(element.toHtml());
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("WikiModel(");
		for (AWikiElement element : elements) {
			sb.append("\n    ").append(element);
		}
		sb.append("\n)");
		return sb.toString();
	}

}
