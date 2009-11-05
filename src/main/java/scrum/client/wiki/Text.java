package scrum.client.wiki;

public class Text extends AWikiElement {

	private String text;

	public Text(String text) {
		super();
		this.text = text;
	}

	@Override
	String toHtml() {
		return escapeHtml(text);
	}

	@Override
	public String toString() {
		return "Text(\"" + text + "\")";
	}

	public static final Text SPACE = new Text(" ");

}
