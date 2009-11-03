package scrum.client.wiki;

public class EntityReference extends AWikiElement {

	private String reference;

	public EntityReference(String reference) {
		super();
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "EntityReference(" + reference + ")";
	}
}
