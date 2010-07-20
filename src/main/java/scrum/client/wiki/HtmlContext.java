package scrum.client.wiki;

public interface HtmlContext {

	String getDownloadUrlByReference(String reference);

	String getEntityLabelByReference(String reference);

	String getEntityReferenceHrefOrOnclickAParameter(String reference);

}
