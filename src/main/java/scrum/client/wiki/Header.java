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
		return "<h" + depth + ">" + text + "</h" + depth + ">"; // TODO escaping
	}

	@Override
	public String toString() {
		return "Text(\"" + text + "\")";
	}

}
