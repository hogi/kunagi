package scrum.client.wiki;

public class ListItem extends AWikiElement {

	private String text;

	public ListItem(String text) {
		super();
		this.text = text;
	}

	public void append(String s) {
		text += s;
	}

	@Override
	String toHtml() {
		return "<li>" + text + "</li>"; // TODO escaping
	}

	@Override
	public String toString() {
		return "ListItem(" + text + ")";
	}

}
