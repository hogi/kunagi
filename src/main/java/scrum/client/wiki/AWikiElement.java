package scrum.client.wiki;

public abstract class AWikiElement {

	abstract String toHtml(HtmlContext context);

	protected final String escapeHtml(String s) {
		s = s.replace("&", "&amp;");
		s = s.replace("<", "&lt;");
		s = s.replace(">", "&gt;");
		s = s.replace("\"", "&quot;");
		return s;
	}
}
