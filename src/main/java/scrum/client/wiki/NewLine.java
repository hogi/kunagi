package scrum.client.wiki;

public class NewLine extends AWikiElement {

	@Override
	String toHtml() {
		return "<br>";
	}

	@Override
	public String toString() {
		return "NewLine";
	}
}
