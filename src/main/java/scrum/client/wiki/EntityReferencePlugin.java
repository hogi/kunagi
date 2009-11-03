package scrum.client.wiki;

public class EntityReferencePlugin implements WikiPlugin {

	public boolean processWord(String word, WikiParser parser, WikiModel model) {
		int len = word.length();
		if (len < 4) return false;
		String prefix = word.substring(0, 3);
		if (prefix.equals("tsk") || prefix.equals("req") || prefix.equals("iss")) {
			String suffix = word.substring(3);
			try {
				Integer.parseInt(suffix);
			} catch (NumberFormatException ex) {
				return false;
			}
			parser.clearWord();
			parser.flushText();
			model.add(new EntityReference(word));
			return true;
		}
		return false;
	}

}
