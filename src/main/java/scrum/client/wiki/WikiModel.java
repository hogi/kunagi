package scrum.client.wiki;

import java.util.ArrayList;
import java.util.List;

public class WikiModel {

	private List<AWikiElement> elements = new ArrayList<AWikiElement>();

	public void addHeader(String text, int depth) {
		add(new Header(text, depth));
	}

	public void addNewLine() {
		add(new NewLine());
	}

	public void beginParagraph() {
		add(new BeginParagraph());
	}

	public void endParagraph() {
		add(new EndParagraph());
	}

	public void addText(String text) {
		add(new Text(text));
	}

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
