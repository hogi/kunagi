package scrum.server.issues;

import ilarkesto.base.Str;
import ilarkesto.core.logging.Log;
import ilarkesto.io.IO;
import ilarkesto.persistence.TransactionService;
import ilarkesto.webapp.Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.common.AHttpServlet;
import scrum.server.journal.ProjectEventDao;
import scrum.server.project.Project;
import scrum.server.project.ProjectDao;

public class IssueServlet extends AHttpServlet {

	private static final long serialVersionUID = 1;

	private static Log log = Log.get(IssueServlet.class);

	private IssueDao issueDao;
	private ProjectDao projectDao;
	private ProjectEventDao projectEventDao;
	private TransactionService transactionService;

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		req.setCharacterEncoding(IO.UTF_8);

		String projectId = req.getParameter("projectId");
		String text = req.getParameter("text");
		String name = req.getParameter("name");
		if (Str.isBlanc(name)) name = null;
		String email = req.getParameter("email");
		if (Str.isBlanc(email)) email = null;

		log.info("Message from the internets");
		log.info("    projectId: " + projectId);
		log.info("    name: " + name);
		log.info("    email: " + email);
		log.info("    text: " + text);
		log.info("  Request-Data:");
		log.info(Servlet.toString(req, "        "));

		String message;
		try {
			message = submitIssue(projectId, text, name, email);
		} catch (Throwable ex) {
			log.error("Submitting issue failed.", "\n" + Servlet.toString(req, "  "), ex);
			message = "<h2>Failure</h2><p>Submitting your feedback failed: <strong>" + Str.getRootCauseMessage(ex)
					+ "</strong></p><p>We are sorry, please try again later.</p>";
		}

		String returnUrl = req.getParameter("returnUrl");
		if (returnUrl == null) returnUrl = "http://kunagi.org";
		returnUrl += "?message=" + Str.encodeUrlParameter(message);

		resp.sendRedirect(returnUrl);
	}

	private String submitIssue(String projectId, String text, String name, String email) {
		if (projectId == null) throw new RuntimeException("projectId == null");
		Project project = projectDao.getById(projectId);
		Issue issue = issueDao.postIssue(project, "Message from the Internets", text, name, email);
		projectEventDao.postEvent(project, issue.getIssuer() + " submitted " + issue.getReferenceAndLabel());
		transactionService.commit();
		return "<h2>Feedback submitted</h2>" + "<p>Thank you for your feedback!</p>"
				+ "<p>In case you submitted a bug or feature request, it is now known as <code>" + issue.getReference()
				+ "</code> and will be reviewed by our Product Owner shortly.</p><p>Thank you!</p>";
	}

	@Override
	protected void onInit(ServletConfig config) {
		ScrumWebApplication app = ScrumWebApplication.get(config);
		issueDao = app.getIssueDao();
		projectDao = app.getProjectDao();
		transactionService = app.getTransactionService();
		projectEventDao = app.getProjectEventDao();
	}

}
