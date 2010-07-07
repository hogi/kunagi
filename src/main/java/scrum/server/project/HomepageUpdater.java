package scrum.server.project;

import ilarkesto.base.Str;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.core.logging.Log;
import ilarkesto.velocity.ContextBuilder;
import ilarkesto.velocity.Velocity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import scrum.client.wiki.HtmlContext;
import scrum.client.wiki.WikiModel;
import scrum.client.wiki.WikiParser;
import scrum.server.collaboration.Wikipage;
import scrum.server.pr.BlogEntry;

public class HomepageUpdater {

	private static Log log = Log.get(HomepageUpdater.class);

	private Project project;
	private MyHtmlContext htmlContext;
	private File outputDir;

	private HomepageUpdater(Project project, String outputPath) {
		super();
		assert project != null;
		this.project = project;
		this.outputDir = new File(outputPath);
		htmlContext = new MyHtmlContext(project);
	}

	private void processTemplates(String templatePath) {
		ContextBuilder context = new ContextBuilder();

		fillProject(context.putSubContext("project"));
		fillWiki(context.putSubContext("wiki"));
		fillBlog(context.putSubContext("blog"));
		fillSprintBacklog(context.putSubContext("sprintBacklog"));
		fillProductBacklog(context.putSubContext("productBacklog"));

		Velocity.processDir(new java.io.File(templatePath), outputDir, context);
	}

	private void fillBlog(ContextBuilder context) {
		List<BlogEntry> entries = new ArrayList<BlogEntry>(project.getBlogEntrys());
		Collections.sort(entries);
		for (BlogEntry entry : entries) {
			fillBlogEntry(context.addSubContext("entries"), entry);
		}
	}

	private void fillBlogEntry(ContextBuilder context, BlogEntry entry) {
		context.put("reference", entry.getReference());
		context.put("title", entry.getTitle());
		context.put("text", wiki2html(entry.getText(), htmlContext));
		context.put("plainText", wiki2text(entry.getText()));
		DateAndTime date = entry.getDateAndTime();
		context.put("date", date.toString(DateAndTime.FORMAT_WEEKDAY_LONGMONTH_DAY_YEAR_HOUR_MINUTE));
		context.put("rssDate", date.toString(DateAndTime.FORMAT_RFC822));
	}

	private void fillSprintBacklog(ContextBuilder context) {
		List<Requirement> requirements = new ArrayList<Requirement>(project.getCurrentSprint().getRequirements());
		Collections.sort(requirements, project.getRequirementsOrderComparator());
		for (Requirement requirement : requirements) {
			fillStory(context.addSubContext("stories"), requirement);
		}
	}

	private void fillProductBacklog(ContextBuilder context) {
		List<Requirement> requirements = new ArrayList<Requirement>(project.getRequirements());
		Collections.sort(requirements, project.getRequirementsOrderComparator());
		for (Requirement requirement : requirements) {
			if (requirement.isClosed() || requirement.isInCurrentSprint()) continue;
			fillStory(context.addSubContext("stories"), requirement);
		}
	}

	private void fillStory(ContextBuilder context, Requirement requirement) {
		context.put("reference", requirement.getReference());
		context.put("label", requirement.getLabel());
	}

	private void fillWiki(ContextBuilder context) {
		for (Wikipage page : project.getWikipages()) {
			context.put(page.getName(), wiki2html(page.getText(), htmlContext));
		}
	}

	private void fillProject(ContextBuilder context) {
		context.put("label", project.getLabel());
		context.put("shortDescription", wiki2html(project.getShortDescription(), htmlContext));
		context.put("description", wiki2html(project.getShortDescription(), htmlContext));
		context.put("longDescription", wiki2html(project.getShortDescription(), htmlContext));
	}

	public static void updateHomepage(String templatePath, String outputPath, Project project) {
		HomepageUpdater updater = new HomepageUpdater(project, outputPath);
		updater.processTemplates(templatePath);
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

	public static String wiki2text(String wikitext) {
		if (Str.isBlanc(wikitext)) return "";
		return wikitext;
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
