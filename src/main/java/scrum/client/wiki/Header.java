package scrum.client.wiki;

public class Header extends AWikiElement {

	private int depth;
	private String text;

	public Header(String text, int depth) {
		super();
		this.text = text;
		this.depth = depth;
	}

	@Override
	String toHtml() {
		return "<h" + depth + ">" + escapeHtml(text) + "</h" + depth + ">";
	}

	@Override
	public String toString() {
		return "Text(\"" + text + "\")";
	}

	public int getDepth() {
		return depth;
	}

	public String getText() {
		return text;
	}

}
