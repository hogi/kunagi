package scrum.client.wiki;

public class Code extends AWikiElement {

	private String text;

	public Code(String text) {
		super();
		this.text = text;
	}

	@Override
	String toHtml() {
		return "<code>" + escapeHtml(text) + "</code>";
	}

	@Override
	public String toString() {
		return "Code(\"" + text + "\")";
	}

}
