package scrum.server.common;

import ilarkesto.io.IO;
import ilarkesto.ui.web.HtmlRenderer;
import ilarkesto.webapp.Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.client.ApplicationInfo;
import scrum.server.ScrumConfig;
import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;

public class StartServlet extends AHttpServlet {

	private static String webappUrl = "http://localhost:8080/kunagi/";
	private static ScrumWebApplication webApplication;

	private ScrumConfig config;
	private ApplicationInfo applicationInfo;

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		if (session.getUser() == null) {
			resp.sendRedirect("login.html");
			return;
		}

		String charset = IO.UTF_8;
		resp.setContentType("text/html");

		HtmlRenderer html = new HtmlRenderer(resp.getOutputStream(), charset);
		html.startHTMLstandard();

		html.startHEAD(applicationInfo.getName(), "EN");
		html.META("X-UA-Compatible", "chrome=1");
		// TODO html.LINKfavicon();
		html.SCRIPTjavascript("scrum.ScrumGwtApplication/scrum.ScrumGwtApplication.nocache.js", null);
		html.endHEAD();

		html.startBODY();
		html.comment(applicationInfo.toString());
		String analyticsId = config.getGoogleAnalyticsId();
		if (analyticsId != null) html.googleAnalytics(analyticsId);
		html.endBODY();

		html.endHTML();
		html.flush();
	}

	@Override
	protected void onInit(ServletConfig servletConfig) {
		super.onInit(servletConfig);

		webappUrl = Servlet.getWebappUrl(servletConfig, false);
		System.out.println("Initializing Kunagi (" + webappUrl + ")");

		webApplication = ScrumWebApplication.get(servletConfig);

		config = webApplication.getConfig();
		applicationInfo = webApplication.getApplicationInfo();
	}

	public static String getWebappUrl() {
		return webappUrl;
	}
}
