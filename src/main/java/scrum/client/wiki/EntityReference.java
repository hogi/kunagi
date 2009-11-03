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
		sb.append(reference);
		sb.append("</a>");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "EntityReference(" + reference + ")";
	}
}
