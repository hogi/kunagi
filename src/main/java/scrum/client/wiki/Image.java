package scrum.client.wiki;

public class Image extends AWikiElement {

	private String reference;
	private boolean thumb;
	private boolean left;

	public Image(String reference, boolean thumb, boolean left) {
		super();
		this.reference = reference;
		this.thumb = thumb;
		this.left = left;
	}

	@Override
	String toHtml(HtmlContext context) {
		boolean external = reference.startsWith("http://");
		StringBuilder sb = new StringBuilder();
		if (external) {
			sb.append("<a href=\"");
			sb.append(reference);
			sb.append("\" target=\"_blank\">");
		} else {
			sb.append("<a onclick='window.scrum.showEntityByReference(\"");
			sb.append(reference);
			sb.append("\")'>");
		}
		sb.append("<img src=\"");
		if (external) {
			sb.append(reference);
		} else {
			sb.append(context.getDownloadUrlByReference(reference));
		}
		if (thumb) {
			sb.append("\" width=\"100px\" align=\"");
			sb.append(left ? "left" : "right");
		}
		// TODO alt="label"
		sb.append("\">");
		sb.append("</a>");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Image(" + reference + ")";
	}
}
