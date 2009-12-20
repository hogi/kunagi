package scrum.client.wiki;

public class BeginParagraph extends AWikiElement {

	@Override
	String toHtml(HtmlContext context) {
		return "<p>";
	}

	@Override
	public String toString() {
		return "BeginParagraph";
	}
}
