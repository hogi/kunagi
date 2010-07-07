package scrum.client.pr;

import ilarkesto.core.base.Utl;

import java.util.Comparator;
import java.util.Map;

import scrum.client.ScrumGwt;
import scrum.client.project.Project;

public class BlogEntry extends GBlogEntry {

	public static final String REFERENCE_PREFIX = "blg";

	public BlogEntry(Map data) {
		super(data);
	}

	public BlogEntry(Project project) {
		setProject(project);
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	@Override
	public String toHtml() {
		return ScrumGwt.toHtml(getReference(), getTitle());
	}

	@Override
	public String toString() {
		return getReference() + " " + getTitle();
	}

	public static final Comparator<BlogEntry> DATE_COMPARATOR = new Comparator<BlogEntry>() {

		public int compare(BlogEntry a, BlogEntry b) {
			return Utl.compare(b.getDateAndTime(), a.getDateAndTime());
		}
	};
}