package scrum.client.wiki;

public class Image extends AWikiElement {

	private String reference;
	private boolean thumb;
	private boolean thumbAlignmentLeft;

	public Image(String reference, boolean thumb, boolean thumbAlignmentLeft) {
		super();
		this.reference = reference;
		this.thumb = thumb;
		this.thumbAlignmentLeft = thumbAlignmentLeft;
	}

	@Override
	String toHtml(HtmlContext context) {
		boolean external = isExternal();
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
			sb.append(thumbAlignmentLeft ? "left" : "right");
		}
		// TODO alt="label"
		sb.append("\">");
		sb.append("</a>");
		return sb.toString();
	}

	public boolean isExternal() {
		return reference.startsWith("http://") || reference.startsWith("https://");
	}

	public String getReference() {
		return reference;
	}

	public boolean isThumb() {
		return thumb;
	}

	public boolean isThumbAlignmentLeft() {
		return thumbAlignmentLeft;
	}

	@Override
	public String toString() {
		return "Image(" + reference + ")";
	}
}
