package scrum.server.admin;

import ilarkesto.core.base.Str;
import ilarkesto.core.logging.Log;

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
		if (Str.isBlanc(email) || !user.isEmail(email)) {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		user.setEmailVerified(true);
		webApplication.getTransactionService().commit();
		log.info("Email verified:", user);
		resp.sendRedirect(webApplication.getBaseUrl());
	}

	@Override
	protected void onInit(ServletConfig config) {
		super.onInit(config);
		webApplication = ScrumWebApplication.get();
	}

}
