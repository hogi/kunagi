package scrum.client.wiki;

public class Pre extends AWikiElement {

	private String text;

	public Pre(String text) {
		super();
		this.text = text;
	}

	@Override
	String toHtml(HtmlContext context) {
		String html = escapeHtml(text);
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"codeBlock\">");
		sb.append("<pre>");
		sb.append(html);
		sb.append("</pre>");
		sb.append("</div>");
		return sb.toString();
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "Pre(\"" + text + "\")";
	}

}
