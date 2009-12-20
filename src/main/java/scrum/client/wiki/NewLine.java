package scrum.client.wiki;

public class NewLine extends AWikiElement {

	@Override
	String toHtml(HtmlContext context) {
		return "<br>";
	}

	@Override
	public String toString() {
		return "NewLine";
	}
}
