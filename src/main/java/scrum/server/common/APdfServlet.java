package scrum.server.common;

import ilarkesto.pdf.APdfBuilder;
import ilarkesto.pdf.itext.PdfBuilder;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.server.WebSession;

public abstract class APdfServlet extends AHttpServlet {

	protected abstract void buildPdf(APdfBuilder pdf, HttpServletRequest req, WebSession session);

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		resp.setContentType("application/pdf");
		PdfBuilder pdf = new PdfBuilder(resp.getOutputStream());
		buildPdf(pdf, req, session);
		pdf.close();
	}

}
