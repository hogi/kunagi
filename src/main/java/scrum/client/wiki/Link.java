package scrum.client.wiki;

public class Link extends AWikiElement {

	private String href;
	private String label;

	public Link(String href, String label) {
		super();
		this.href = enhance(href);
		this.label = label;
	}

	public Link(String href) {
		this(href, shorten(href));
	}

	@Override
	String toHtml(HtmlContext context) {
		StringBuilder sb = new StringBuilder();
		sb.append("<a href=\"");
		sb.append(enhance(href));
		sb.append("\" target=\"_blank\">");
		sb.append(escapeHtml(label));
		sb.append("</a>");
		return sb.toString();
	}

	private String enhance(String s) {
		if (s.startsWith("http://")) return s;
		if (s.startsWith("https://")) return s;
		if (s.startsWith("ftp://")) return s;
		if (s.startsWith("mailto://")) return s;
		if (s.startsWith("apt://")) return s;
		return "http://" + s;
	}

	private static String shorten(String s) {
		if (s.startsWith("http://")) {
			s = s.substring(7);
		} else if (s.startsWith("https://")) {
			s = s.substring(7);
		} else if (s.startsWith("ftp://")) {
			s = s.substring(6);
		}
		if (s.startsWith("www.")) {
			s = s.substring(4);
		}
		if (s.length() > 20) {
			s = s.substring(0, 20) + "...";
		}
		return s;
	}

	public String getHref() {
		return href;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return "EntityReference(" + href + ")";
	}
}
