package scrum.server.admin;

import ilarkesto.core.base.Str;
import ilarkesto.core.logging.Log;
import ilarkesto.io.IO;
import ilarkesto.ui.web.HtmlRenderer;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.common.AHttpServlet;

public class ConfirmEmailServlet extends AHttpServlet {

	private static Log log = Log.get(ConfirmEmailServlet.class);
	private static final long serialVersionUID = 1;

	private static ScrumWebApplication webApplication;

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		User user = getEntityByParameter(req, "user", User.class);
		if (user == null) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		String email = req.getParameter("email");
		if (Str.isBlank(email) || !user.isEmail(email)) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		user.setEmailVerified(true);
		webApplication.getTransactionService().commit();
		log.info("Email verified:", user);

		returnConfirmationPage(resp);
	}

	private void returnConfirmationPage(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		String url = webApplication.getBaseUrl();
		HtmlRenderer html = new HtmlRenderer(resp.getWriter(), IO.UTF_8);
		html.startHTML();
		html.startHEAD("Email confirmed", "en");
		html.METArefresh(3, url);
		html.endHEAD();
		html.startBODY();
		html.startDIV().setStyle("width: 200px", "border: 1px solid lightgray", "padding: 10px", "margin: 100px auto",
			"text-align: center", "font-family: sans-serif;");
		html.text("Email confirmed. ");
		html.A(url, "Continue...");
		html.endDIV();
		html.endBODY();
		html.endHTML();
		html.flush();
	}

	@Override
	protected void onInit(ServletConfig config) {
		super.onInit(config);
		webApplication = ScrumWebApplication.get();
	}

}
