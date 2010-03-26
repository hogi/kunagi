package scrum.client;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.collaboration.CommentsWidget;
import scrum.client.collaboration.EmoticonSelectorWidget;
import scrum.client.common.AScrumGwtEntity;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ScrumGwt extends Gwt {

	public static Widget createEmoticonsAndComments(AScrumGwtEntity entity) {
		TableBuilder tb = ScrumGwt.createFieldTable();
		tb.addFieldRow("My emoticon", new EmoticonSelectorWidget(entity));
		tb.addRow(new CommentsWidget(entity), 2);
		return tb.createTable();
	}

	public static Widget createActionsPanel(Widget... widgets) {
		FlowPanel panel = new FlowPanel();
		panel.setStyleName("ActionsPanel");
		for (Widget widget : widgets) {
			panel.add(widget);
		}
		return panel;
	}

	public static TableBuilder createFieldTable() {
		TableBuilder tb = new TableBuilder();
		tb.setCellPadding(2);
		tb.setColumnWidths("100px");
		return tb;
	}

	public static HTML createPdfLink(String text, String pdfId, AScrumGwtEntity entity) {
		return createPdfLink(text, pdfId, "entityId", entity.getId());
	}

	public static HTML createPdfLink(String text, String pdfId, String parameterKey, String parameterValue,
			String parameter2Key, String parameter2Value) {
		return createPdfLink(text, pdfId, parameterKey + "=" + parameterValue + "&" + parameter2Key + "="
				+ parameter2Value);
	}

	public static HTML createPdfLink(String text, String pdfId, String parameterKey, String parameterValue) {
		return createPdfLink(text, pdfId, parameterKey + "=" + parameterValue);
	}

	public static HTML createPdfLink(String text, String pdfId, String parameters) {
		return createServletDownloadLink("pdf.pdf?pdfId=" + pdfId + "&" + parameters, text);
	}

}
