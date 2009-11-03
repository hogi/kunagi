package scrum.client.wiki;

public class Text extends AWikiElement {

	private String text;

	public Text(String text) {
		super();
		this.text = text;
	}

	@Override
	String toHtml() {
		return text; // TODO escaping
	}

	@Override
	public String toString() {
		return "Text(\"" + text + "\")";
	}

}
