package scrum.client.wiki;

import ilarkesto.core.base.Str;

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
		sb.append("<a class='reference' ");
		String hrefOrOnclick = context.getEntityReferenceHrefOrOnclickAParameter(reference);
		sb.append(hrefOrOnclick);

		String entityLabel = context.getEntityLabelByReference(reference);
		if (!Str.isBlank(entityLabel)) {
			entityLabel = entityLabel.replace("'", "`");
			entityLabel = entityLabel.replace("\"", "`");
			sb.append(" title='").append(entityLabel).append("'");
		}

		sb.append(">");
		sb.append(escapeHtml(label));
		sb.append("</a>");
		return sb.toString();
	}

	public String getLabel() {
		return label;
	}

	public String getReference() {
		return reference;
	}

	@Override
	public String toString() {
		return "EntityReference(" + reference + "," + label + ")";
	}
}
