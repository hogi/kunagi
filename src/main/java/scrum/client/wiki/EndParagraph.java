package scrum.client.wiki;

public class EndParagraph extends AWikiElement {

	@Override
	String toHtml() {
		return "</p>";
	}

	@Override
	public String toString() {
		return "EndParagraph";
	}
}
