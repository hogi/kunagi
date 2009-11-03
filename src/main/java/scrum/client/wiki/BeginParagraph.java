package scrum.client.wiki;

public class BeginParagraph extends AWikiElement {

	@Override
	String toHtml() {
		return "<p>";
	}

	@Override
	public String toString() {
		return "BeginParagraph";
	}
}
