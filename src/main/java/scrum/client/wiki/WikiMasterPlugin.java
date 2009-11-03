package scrum.client.wiki;

import java.util.ArrayList;
import java.util.List;

public class WikiMasterPlugin implements WikiPlugin {

	private List<WikiPlugin> plugins = new ArrayList<WikiPlugin>();

	public void addPlugin(WikiPlugin plugin) {
		plugins.add(plugin);
	}

	public boolean processWord(String word, WikiParser parser, WikiModel model) {
		for (WikiPlugin plugin : plugins) {
			boolean processed = plugin.processWord(word, parser, model);
			if (processed) return true;
		}
		return false;
	}

}
