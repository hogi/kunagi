package scrum.server.project;

import ilarkesto.base.Str;
import ilarkesto.base.time.Date;
import ilarkesto.core.logging.Log;
import ilarkesto.velocity.Velocity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scrum.client.wiki.HtmlContext;
import scrum.client.wiki.WikiModel;
import scrum.client.wiki.WikiParser;
import scrum.server.collaboration.Wikipage;

public class HomepageUpdater {

	private static Log log = Log.get(HomepageUpdater.class);

	private Project project;
	private Map<String, Object> context;
	private MyHtmlContext htmlContext;

	private HomepageUpdater(Project project) {
		super();
		assert project != null;
		this.project = project;
		htmlContext = new MyHtmlContext(project);
	}

	private void updateHomepage(String templatePath, String outputPath) {
		context = new HashMap<String, Object>();

		context.put("project", createProjectMap());
		context.put("wiki", createWikiMap());
		context.put("blogEntries", createBlogEntries());

		updateHomepage(templatePath, outputPath, context);
	}

	private List<Map<String, String>> createBlogEntries() {
		List<Map<String, String>> entryList = new ArrayList<Map<String, String>>();
		// TODO loop blog entries. don't forget to sort.
		for (int i = 0; i < 5; i++) {
			Map<String, String> entryMap = new HashMap<String, String>();
			entryMap.put("date", Date.beforeDays(i).toString(Date.FORMAT_SHORTWEEKDAY_DAY_MONTH_YEAR));
			entryMap.put("title", Str.generateRandomSentence(4, 6));
			entryMap.put("text", "<p>" + Str.generateRandomParagraph() + "</p>");
			entryList.add(entryMap);
		}
		return entryList;
	}

	private Map<String, String> createWikiMap() {
		Map<String, String> map = new HashMap<String, String>();
		for (Wikipage page : project.getWikipages()) {
			map.put(page.getName(), wiki2html(page.getText(), htmlContext));
		}
		return map;
	}

	private Map<String, Object> createProjectMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shortDescription", wiki2html(project.getShortDescription(), htmlContext));
		map.put("description", wiki2html(project.getShortDescription(), htmlContext));
		map.put("longDescription", wiki2html(project.getShortDescription(), htmlContext));
		return map;
	}

	public static void updateHomepage(String templatePath, String outputPath, Project project) {
		HomepageUpdater updater = new HomepageUpdater(project);
		updater.updateHomepage(templatePath, outputPath);
	}

	public static void updateHomepage(String templatePath, String outputPath, Map<String, Object> context) {
		Velocity.processDir(new java.io.File(templatePath), new java.io.File(outputPath), context);
	}

	public static void updateHomepage(Project project) {
		File homepageDir = project.getHomepageDirFile();
		if (homepageDir == null || !homepageDir.exists()) {
			log.warn("Updating project homepage failed. Homepage directory does not exist:", homepageDir);
			return;
		}

		File velocityDir = new File(homepageDir.getPath() + "/velocity");
		if (velocityDir.exists()) updateHomepage(velocityDir.getPath(), homepageDir.getPath(), project);

	}

	public static String wiki2html(String wikitext, HtmlContext context) {
		if (Str.isBlanc(wikitext)) return "";
		WikiParser wikiParser = new WikiParser(wikitext);
		WikiModel model = wikiParser.parse();
		return model.toHtml(context);
	}

	private static class MyHtmlContext implements HtmlContext {

		private Project project;

		public MyHtmlContext(Project project) {
			super();
			this.project = project;
		}

		@Override
		public String getDownloadUrlByReference(String reference) {
			return reference;
		}

		@Override
		public String getEntityLabelByReference(String reference) {
			return reference;
		}

	}
}
