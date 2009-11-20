package scrum.client.wiki;

public class Code extends AWikiElement {

	private String text;

	public Code(String text) {
		super();
		this.text = text;
	}

	@Override
	String toHtml() {
		String html = escapeHtml(text);
		html = html.replace("\n", "<br>");
		html = html.replace(" ", "&nbsp;");
		return "<code>" + html + "</code>";
	}

	@Override
	public String toString() {
		return "Code(\"" + text + "\")";
	}

}
