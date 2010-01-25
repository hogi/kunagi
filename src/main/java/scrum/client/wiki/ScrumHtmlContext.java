package scrum.client.wiki;

import com.google.gwt.core.client.GWT;

public class ScrumHtmlContext implements HtmlContext {

	public String getDownloadUrlByReference(String reference) {
		return GWT.getModuleBaseURL() + "fileDownload?reference=" + reference;
	}
}
