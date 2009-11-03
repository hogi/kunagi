package scrum.client.wiki;

public class Wiki {

	public static String toHtml(String wiki) {
		WikiParser parser = new WikiParser(wiki);
		parser.addPlugin(new EntityReferencePlugin());
		WikiModel model = parser.parse();
		return model.toHtml();
	}

}
