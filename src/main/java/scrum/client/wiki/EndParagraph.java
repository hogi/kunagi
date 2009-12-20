package scrum.client.wiki;

public class EndParagraph extends AWikiElement {

	@Override
	String toHtml(HtmlContext context) {
		return "</p>";
	}

	@Override
	public String toString() {
		return "EndParagraph";
	}
}
