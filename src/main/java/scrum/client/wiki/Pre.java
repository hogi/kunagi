package scrum.client.wiki;

public class Pre extends AWikiElement {

	private String text;

	public Pre(String text) {
		super();
		this.text = text;
	}

	@Override
	String toHtml() {
		return "<pre>" + text + "</pre>"; // TODO escaping
	}

	@Override
	public String toString() {
		return "Pre(\"" + text + "\")";
	}

}
