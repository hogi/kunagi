package scrum.server.project;

import ilarkesto.base.Str;
import ilarkesto.base.time.Date;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.core.logging.Log;
import ilarkesto.io.IO;
import ilarkesto.velocity.ContextBuilder;
import ilarkesto.velocity.Velocity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import scrum.client.wiki.HtmlContext;
import scrum.client.wiki.WikiModel;
import scrum.client.wiki.WikiParser;
import scrum.server.collaboration.Wikipage;
import scrum.server.common.BurndownChart;
import scrum.server.issues.Issue;
import scrum.server.pr.BlogEntry;
import scrum.server.sprint.Sprint;

public class HomepageUpdater {

	private static Log log = Log.get(HomepageUpdater.class);

	private Project project;
	private MyHtmlContext htmlContext;
	private File templateDir;
	private File outputDir;
	private Velocity velocity;
	private Properties properties;

	private HomepageUpdater(Project project, String templatePath, String outputPath) {
		super();
		assert project != null;
		this.project = project;
		this.templateDir = new File(templatePath);
		this.outputDir = new File(outputPath);
		htmlContext = new MyHtmlContext(project);
		velocity = new Velocity(templateDir);

		loadProperties();
	}

	public static void updateHomepage(String templatePath, String outputPath, Project project) {
		HomepageUpdater updater = new HomepageUpdater(project, templatePath, outputPath);
		synchronized (project) {
			updater.processDefaultTemplates();
			updater.processIssueTemplates();
			updater.processStoryTemplates();
			updater.createSprintBurndownChart(700, 200);
		}
	}

	private void processDefaultTemplates() {
		ContextBuilder context = new ContextBuilder();
		fillProject(context.putSubContext("project"));
		fillWiki(context.putSubContext("wiki"));
		fillBlog(context.putSubContext("blog"));
		fillSprintBacklog(context.putSubContext("sprintBacklog"));
		fillProductBacklog(context.putSubContext("productBacklog"));
		fillBugs(context);
		fillIdeas(context);

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
		List<Issue> issues = new ArrayList<Issue>(project.getOpenBugs());
		issues.addAll(project.getOpenIdeas());
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
	}

	private void createSprintBurndownChart(int width, int height) {
		byte[] imageData = BurndownChart.createBurndownChartAsByteArray(project.getCurrentSprint(), width, height);
		IO.copyDataToFile(imageData,
			new File(outputDir.getPath() + "/sprint-burndown-" + width + "x" + height + ".png"));
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

	private void fillBugs(ContextBuilder context) {
		List<Issue> issues = new ArrayList<Issue>(project.getOpenBugs());
		for (Issue issue : issues) {
			fillIssue(context.addSubContext("bugs"), issue);
		}
	}

	private void fillIdeas(ContextBuilder context) {
		List<Issue> issues = new ArrayList<Issue>(project.getOpenIdeas());
		for (Issue issue : issues) {
			fillIssue(context.addSubContext("ideas"), issue);
		}
	}

	private void fillIssue(ContextBuilder context, Issue issue) {
		context.put("reference", issue.getReference());
		context.put("label", issue.getLabel());
		context.put("description", wiki2html(issue.getDescription()));
		context.put("statement", wiki2html(issue.getStatement()));
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
		context.put("text", wiki2html(entry.getText()));
		context.put("plainText", wiki2text(entry.getText()));
		DateAndTime date = entry.getDateAndTime();
		context.put("date", date.toString(Date.FORMAT_LONGMONTH_DAY_YEAR));
		context.put("rssDate", date.toString(DateAndTime.FORMAT_RFC822));
	}

	private void fillSprintBacklog(ContextBuilder context) {
		Sprint sprint = project.getCurrentSprint();
		List<Requirement> requirements = new ArrayList<Requirement>(sprint.getRequirements());
		context.put("label", sprint.getLabel());
		context.put("goal", wiki2html(sprint.getGoal()));
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
		context.put("description", wiki2html(requirement.getDescription()));
	}

	private void fillWiki(ContextBuilder context) {
		for (Wikipage page : project.getWikipages()) {
			context.put(page.getName(), wiki2html(page.getText()));
		}
	}

	private void fillProject(ContextBuilder context) {
		context.put("label", project.getLabel());
		context.put("shortDescription", project.getShortDescription());
		context.put("description", wiki2html(project.getDescription()));
		context.put("longDescription", wiki2html(project.getLongDescription()));
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

	public String wiki2html(String wikitext) {
		if (Str.isBlank(wikitext)) return "";
		WikiParser wikiParser = new WikiParser(wikitext);
		WikiModel model = wikiParser.parse();
		return model.toHtml(htmlContext);
	}

	public static String wiki2text(String wikitext) {
		if (Str.isBlank(wikitext)) return "";
		return wikitext;
	}

	private void loadProperties() {
		File file = new File(outputDir.getPath() + "/kunagi.properties");
		if (!file.exists()) file = new File(templateDir.getPath() + "/kunagi.properties");
		if (!file.exists()) {
			properties = new Properties();
			return;
		}
		properties = IO.loadProperties(file, IO.UTF_8);
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
