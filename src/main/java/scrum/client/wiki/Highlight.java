package scrum.client.wiki;

import ilarkesto.gwt.client.Gwt;

public class Highlight extends Paragraph {

	private boolean em;
	private boolean strong;

	public Highlight(boolean em, boolean strong) {
		super(false);
		this.em = em;
		this.strong = strong;
	}

	@Override
	String toHtml(HtmlContext context) {
		StringBuilder sb = new StringBuilder();
		if (strong) sb.append("<strong>");
		if (em) sb.append("<em>");
		sb.append(super.toHtml(context));
		if (em) sb.append("</em>");
		if (strong) sb.append("</strong>");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Highlight(" + Gwt.toString(getElements()) + ")";
	}

}
