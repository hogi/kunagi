package scrum.server.common;

import ilarkesto.io.IO;
import ilarkesto.ui.web.HtmlRenderer;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.client.ApplicationInfo;
import scrum.server.ScrumConfig;
import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;

public class StartServlet extends AHttpServlet {

	private ScrumConfig config;
	private ApplicationInfo applicationInfo;

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		String charset = IO.UTF_8;
		resp.setContentType("text/html");

		HtmlRenderer html = new HtmlRenderer(resp.getOutputStream(), charset);
		html.startHTML();

		html.startHEAD(applicationInfo.getName(), "EN");
		html.META("X-UA-Compatible", "chrome=1");
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

		ScrumWebApplication app = ScrumWebApplication.get();
		config = app.getConfig();
		applicationInfo = app.getApplicationInfo();
	}
}
