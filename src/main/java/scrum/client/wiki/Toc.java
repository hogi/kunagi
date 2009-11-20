package scrum.client.wiki;

import java.util.ArrayList;
import java.util.List;

public class Toc extends AWikiElement {

	private WikiModel model;

	public Toc(WikiModel model) {
		super();
		this.model = model;
	}

	@Override
	String toHtml() {
		List<Header> headers = new ArrayList<Header>();
		for (AWikiElement element : model.getElements()) {
			appendHeaders(headers, element);
		}
		if (headers.isEmpty()) return "";

		StringBuilder sb = new StringBuilder();
		sb.append("<ul class=\"toc\">");
		int currentDepth = 1;
		for (Header h : headers) {

			int depth = h.getDepth();
			int diff = depth - currentDepth;
			for (int i = 0; i < diff; i++) {
				sb.append("<ul>");
			}
			for (int i = diff; i < 0; i++) {
				sb.append("</ul>");
			}
			currentDepth = depth;

			sb.append("<li>");
			sb.append(escapeHtml(h.getText()));
			sb.append("</li>");
		}
		while (currentDepth > 1) {
			sb.append("</ul>");
			currentDepth--;
		}
		sb.append("</ul>");
		return sb.toString();
	}

	private void appendHeaders(List<Header> headers, AWikiElement element) {
		if (element instanceof Paragraph) {
			Paragraph p = (Paragraph) element;
			for (AWikiElement e : p.getElements()) {
				appendHeaders(headers, e);
			}
			return;
		}
		if (element instanceof Header) headers.add((Header) element);
	}

	@Override
	public String toString() {
		return "Toc";
	}

}
