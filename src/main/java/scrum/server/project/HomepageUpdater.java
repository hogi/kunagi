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
import scrum.server.issues.Issue;
import scrum.server.pr.BlogEntry;

public class HomepageUpdater {

	private static Log log = Log.get(HomepageUpdater.class);

	private Project project;
	private MyHtmlContext htmlContext;
	private File templateDir;
	private File outputDir;
	private Velocity velocity;

	private HomepageUpdater(Project project, String templatePath, String outputPath) {
		super();
		assert project != null;
		this.project = project;
		this.templateDir = new File(templatePath);
		this.outputDir = new File(outputPath);
		htmlContext = new MyHtmlContext(project);
		velocity = new Velocity(templateDir);
	}

	private void processDefaultTemplates() {
		ContextBuilder context = new ContextBuilder();
		fillProject(context.putSubContext("project"));
		fillWiki(context.putSubContext("wiki"));
		fillBlog(context.putSubContext("blog"));
		fillSprintBacklog(context.putSubContext("sprintBacklog"));
		fillProductBacklog(context.putSubContext("productBacklog"));
		fillIssues(context);

		File[] templateFiles = templateDir.listFiles();
		if (templateFiles == null) return;
		for (File templateFile : templateFiles) {
			String templateName = templateFile.getName();
			if (!templateName.endsWith(".vm")) continue;
			if (templateName.equals(Velocity.LIB_TEMPLATE_NAME)) continue;
			if (templateName.startsWith("iss.")) continue;
			String outputFileName = Str.removeSuffix(templateName, ".vm");
			velocity.processTemplate(templateName, new File(outputDir.getPath() + "/" + outputFileName), context);
		}
	}

	private void processIssueTemplates() {
		List<Issue> issues = new ArrayList<Issue>(project.getUrgentAndOpenIssues());
		for (Issue issue : issues) {
			ContextBuilder context = new ContextBuilder();
			fillIssue(context.putSubContext("issue"), issue);
			String reference = issue.getReference();
			processEntityTemplate(context, reference);
		}
	}

	private void processStoryTemplates() {
		List<Requirement> requirements = new ArrayList<Requirement>(project.getRequirements());
		for (Requirement requirement : requirements) {
			if (requirement.isClosed()) continue;
			ContextBuilder context = new ContextBuilder();
			fillStory(context.putSubContext("story"), requirement);
			processEntityTemplate(context, requirement.getReference());
		}

		List<Issue> issues = new ArrayList<Issue>(project.getUrgentAndOpenIssues());
		for (Issue issue : issues) {
			ContextBuilder context = new ContextBuilder();
			fillIssue(context.putSubContext("issue"), issue);
			processEntityTemplate(context, issue.getReference());
		}
	}

	private void processEntityTemplate(ContextBuilder context, String reference) {
		String prefix = reference.substring(0, 3);
		File[] templateFiles = templateDir.listFiles();
		if (templateFiles == null) return;
		for (File templateFile : templateFiles) {
			String templateName = templateFile.getName();
			if (!templateName.endsWith(".vm")) continue;
			if (!templateName.startsWith(prefix + ".")) continue;
			String outputFileName = Str.removeSuffix(templateName, ".vm");
			outputFileName = Str.removePrefix(outputFileName, prefix + ".");
			outputFileName = reference + "." + outputFileName;
			velocity.processTemplate(templateName, new File(outputDir.getPath() + "/" + outputFileName), context);
		}
	}

	private void fillIssues(ContextBuilder context) {
		List<Issue> issues = new ArrayList<Issue>(project.getUrgentAndOpenIssues());
		for (Issue issue : issues) {
			fillIssue(context.addSubContext("bugs"), issue);
		}
	}

	private void fillIssue(ContextBuilder context, Issue issue) {
		context.put("reference", issue.getReference());
		context.put("label", issue.getLabel());
		context.put("description", wiki2html(issue.getDescription(), htmlContext));
		context.put("statement", wiki2html(issue.getStatement(), htmlContext));
	}

	private void fillBlog(ContextBuilder context) {
		List<BlogEntry> entries = new ArrayList<BlogEntry>(project.getBlogEntrys());
		Collections.sort(entries);
		for (BlogEntry entry : entries) {
			if (!entry.isPublished()) continue;
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
		context.put("description", wiki2html(requirement.getDescription(), htmlContext));
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
		HomepageUpdater updater = new HomepageUpdater(project, templatePath, outputPath);
		synchronized (project) {
			updater.processDefaultTemplates();
			updater.processIssueTemplates();
			updater.processStoryTemplates();
		}
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
