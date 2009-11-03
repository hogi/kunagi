package scrum.client.wiki;

public interface WikiPlugin {

	boolean processWord(String word, WikiParser parser, WikiModel model);

}
