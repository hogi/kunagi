package scrum.client;

import ilarkesto.gwt.client.Gwt;
import scrum.client.common.AScrumGwtEntity;

import com.google.gwt.user.client.ui.HTML;

public class ScrumGwt extends Gwt {

	public static HTML createPdfLink(String text, String pdfId, AScrumGwtEntity entity) {
		return createPdfLink(text, pdfId, "entityId", entity.getId());
	}

	public static HTML createPdfLink(String text, String pdfId, String parameterKey, String parameterValue) {
		return createPdfLink(text, pdfId, parameterKey + "=" + parameterValue);
	}

	public static HTML createPdfLink(String text, String pdfId, String parameters) {
		return createServletDownloadLink("pdf.pdf?pdfId=" + pdfId + "&" + parameters, text);
	}

}
