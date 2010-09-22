package scrum.client.wiki;

public class Code extends AWikiElement {

	private String text;

	public Code(String text) {
		super();
		this.text = text;
	}

	@Override
	String toHtml(HtmlContext context) {
		String s = text;
		if (s.length() > 0 && s.startsWith("\n")) s = s.substring(1);
		String html = escapeHtml(s);
		html = html.replace("\n", "<br>");
		html = html.replace(" ", "&nbsp;");

		boolean codeBlock = isBlock();

		StringBuilder sb = new StringBuilder();
		if (codeBlock) sb.append("<div class=\"codeBlock\">");
		sb.append("<code>");
		sb.append(html);
		sb.append("</code>");
		if (codeBlock) sb.append("</div>");
		return sb.toString();
	}

	public boolean isBlock() {
		return text.contains("\n");
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "Code(\"" + text + "\")";
	}

}
