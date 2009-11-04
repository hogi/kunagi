package scrum.client.wiki;

public class EntityReference extends AWikiElement {

	private String reference;

	public EntityReference(String reference) {
		super();
		this.reference = reference;
	}

	@Override
	String toHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<a onclick='window.scrum.showEntityByReference(\"");
		sb.append(reference);
		sb.append("\")'>");
		sb.append(getLabel());
		sb.append("</a>");
		return sb.toString();
	}

	private String getLabel() {
		if (reference.startsWith("[[")) return reference.substring(2, reference.length() - 2);
		return reference;
	}

	@Override
	public String toString() {
		return "EntityReference(" + reference + ")";
	}
}
