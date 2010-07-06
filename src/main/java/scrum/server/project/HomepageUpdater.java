package scrum.server.project;

import ilarkesto.base.Str;
import ilarkesto.base.time.Date;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.base.time.Time;
import ilarkesto.core.logging.Log;
import ilarkesto.velocity.Velocity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		context.put("sprintBacklog", createSprintBacklog());
		context.put("productBacklog", createProductBacklog());

		updateHomepage(templatePath, outputPath, context);
	}

	private List<Map<String, String>> createBlogEntries() {
		List<Map<String, String>> entryList = new ArrayList<Map<String, String>>();
		// TODO loop blog entries. don't forget to sort.
		for (int i = 0; i < 5; i++) {
			String reference = "blg" + i;
			String title = Str.generateRandomSentence(4, 6);
			String text = Str.generateRandomParagraph();
			DateAndTime date = new DateAndTime(Date.beforeDays(i), Time.now());

			Map<String, String> entryMap = new HashMap<String, String>();
			entryMap.put("reference", reference);
			entryMap.put("title", title);
			entryMap.put("text", "<p>" + wiki2html(text, htmlContext) + "</p>");
			entryMap.put("plainText", text);
			entryMap.put("date", date.toString(DateAndTime.FORMAT_WEEKDAY_LONGMONTH_DAY_YEAR_HOUR_MINUTE));
			entryMap.put("rssDate", date.toString(DateAndTime.FORMAT_RFC822));
			entryList.add(entryMap);
		}
		return entryList;
	}

	private Map<String, ?> createSprintBacklog() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, String>> storysList = new ArrayList<Map<String, String>>();
		map.put("stories", storysList);
		Set<Requirement> requirements = project.getCurrentSprint().getRequirements();
		for (Requirement requirement : requirements) {
			Map<String, String> storyMap = createStoryMap(requirement);
			storysList.add(storyMap);
		}
		return map;
	}

	private Map<String, ?> createProductBacklog() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, String>> storysList = new ArrayList<Map<String, String>>();
		map.put("stories", storysList);
		Set<Requirement> requirements = project.getRequirements();
		for (Requirement requirement : requirements) {
			if (requirement.isClosed() || requirement.isInCurrentSprint()) continue;
			Map<String, String> storyMap = createStoryMap(requirement);
			storysList.add(storyMap);
		}
		return map;
	}

	private Map<String, String> createStoryMap(Requirement requirement) {
		Map<String, String> storyMap = new HashMap<String, String>();
		storyMap.put("reference", requirement.getReference());
		storyMap.put("label", requirement.getLabel());
		return storyMap;
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
		map.put("label", project.getLabel());
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
