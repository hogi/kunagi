package scrum.client.pr;

import java.util.Map;

import scrum.client.ScrumGwt;

public class BlogEntry extends GBlogEntry {

	public static final String REFERENCE_PREFIX = "blg";

	public BlogEntry(Map data) {
		super(data);
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
}