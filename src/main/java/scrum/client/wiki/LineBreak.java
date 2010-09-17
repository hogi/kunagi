package scrum.client.wiki;

public class LineBreak extends AWikiElement {

	public static final LineBreak INSTANCE = new LineBreak();

	@Override
	String toHtml(HtmlContext context) {
		return "<br>";
	}

	@Override
	public String toString() {
		return "LineBreak";
	}
}
