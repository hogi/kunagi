package scrum.server.journal;

import ilarkesto.logging.Logger;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.common.AHttpServlet;

public class JournalRssServlet extends AHttpServlet {

	private static final Logger LOG = Logger.get(JournalRssServlet.class);
	private static final long serialVersionUID = 1;

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		resp.setContentType("application/rss+xml");
	}

	@Override
	protected void onInit(ServletConfig config) {
		ScrumWebApplication.get(config);
	}

}
