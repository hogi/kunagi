package scrum.client.wiki;

public class Link extends AWikiElement {

	private String url;

	public Link(String url) {
		super();
		this.url = url;
	}

	@Override
	String toHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<a href=\"");
		sb.append(enhance(url));
		sb.append("\" target=\"_blank\">");
		sb.append(shorten(url));
		sb.append("</a>");
		return sb.toString();
	}

	private String enhance(String s) {
		if (s.startsWith("http://")) return s;
		if (s.startsWith("https://")) return s;
		if (s.startsWith("ftp://")) return s;
		return "http://" + s;
	}

	private String shorten(String s) {
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

	@Override
	public String toString() {
		return "EntityReference(" + url + ")";
	}
}
