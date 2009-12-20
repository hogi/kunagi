package scrum.client.wiki;

public class EntityReference extends AWikiElement {

	private String reference;
	private String label;

	public EntityReference(String reference, String label) {
		super();
		this.reference = reference;
		this.label = label;
	}

	public EntityReference(String reference) {
		this(reference, reference);
	}

	@Override
	String toHtml(HtmlContext context) {
		StringBuilder sb = new StringBuilder();
		sb.append("<a onclick='window.scrum.showEntityByReference(\"");
		sb.append(reference);
		sb.append("\")'>");
		sb.append(escapeHtml(label));
		sb.append("</a>");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "EntityReference(" + reference + "," + label + ")";
	}
}
