package scrum.server.issues;

import ilarkesto.base.Str;
import ilarkesto.core.logging.Log;
import ilarkesto.persistence.TransactionService;
import ilarkesto.webapp.Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.common.AHttpServlet;
import scrum.server.project.Project;
import scrum.server.project.ProjectDao;

public class IssueServlet extends AHttpServlet {

	private static final long serialVersionUID = 1;

	private static Log log = Log.get(IssueServlet.class);

	private IssueDao issueDao;
	private ProjectDao projectDao;
	private TransactionService transactionService;

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		String projectId = req.getParameter("projectId");
		String text = req.getParameter("text");
		String name = req.getParameter("name");
		String email = req.getParameter("email");

		String message;
		try {
			message = submitIssue(projectId, text, name, email);
		} catch (Throwable ex) {
			log.error("Submitting issue failed.", "\n" + Servlet.toString(req, "  "), ex);
			message = "Submitting issue failed: " + Str.getRootCauseMessage(ex);
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
		transactionService.commit();
		return "Your issue was submitted as " + issue.getReference() + ". Thank you!";
	}

	@Override
	protected void onInit(ServletConfig config) {
		ScrumWebApplication app = ScrumWebApplication.get(config);
		issueDao = app.getIssueDao();
		projectDao = app.getProjectDao();
		transactionService = app.getTransactionService();
	}

}
