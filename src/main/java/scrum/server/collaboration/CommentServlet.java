package scrum.server.collaboration;

import ilarkesto.base.Str;
import ilarkesto.core.logging.Log;
import ilarkesto.io.IO;
import ilarkesto.persistence.AEntity;
import ilarkesto.persistence.DaoService;
import ilarkesto.persistence.TransactionService;
import ilarkesto.webapp.Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.client.common.LabelSupport;
import scrum.client.common.ReferenceSupport;
import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.common.AHttpServlet;
import scrum.server.journal.ProjectEventDao;
import scrum.server.project.Project;
import scrum.server.project.ProjectDao;

public class CommentServlet extends AHttpServlet {

	private static final long serialVersionUID = 1;

	private static Log log = Log.get(CommentServlet.class);

	private transient DaoService daoService;
	private transient CommentDao commentDao;
	private transient ProjectDao projectDao;
	private transient ProjectEventDao projectEventDao;
	private transient TransactionService transactionService;

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		req.setCharacterEncoding(IO.UTF_8);

		String projectId = req.getParameter("projectId");
		String entityId = req.getParameter("entityId");
		String text = req.getParameter("text");
		String name = Str.cutRight(req.getParameter("name"), 33);
		if (Str.isBlank(name)) name = null;
		String email = Str.cutRight(req.getParameter("email"), 33);
		if (Str.isBlank(email)) email = null;

		log.info("Comment from the internets");
		log.info("    projectId: " + projectId);
		log.info("    entityId: " + entityId);
		log.info("    name: " + name);
		log.info("    email: " + email);
		log.info("    text: " + text);
		log.info("  Request-Data:");
		log.info(Servlet.toString(req, "        "));

		String message;
		try {
			message = postComment(projectId, entityId, text, name, email);
		} catch (Throwable ex) {
			log.error("Posting comment failed.", "\n" + Servlet.toString(req, "  "), ex);
			message = "<h2>Failure</h2><p>Posting your comment failed: <strong>" + Str.getRootCauseMessage(ex)
					+ "</strong></p><p>We are sorry, please try again later.</p>";
		}

		String returnUrl = req.getParameter("returnUrl");
		if (returnUrl == null) returnUrl = "http://kunagi.org/message.html?#{message}";
		returnUrl = returnUrl.replace("{message}", Str.encodeUrlParameter(message));

		resp.sendRedirect(returnUrl);
	}

	private String postComment(String projectId, String entityId, String text, String name, String email) {
		if (projectId == null) throw new RuntimeException("projectId == null");
		Project project = projectDao.getById(projectId);
		AEntity entity = daoService.getById(entityId);
		Comment comment = commentDao.postComment(entity, text, name, email, true);
		project.updateHomepage(entity, true);
		String reference = ((ReferenceSupport) entity).getReference();
		String label = ((LabelSupport) entity).getLabel();
		projectEventDao.postEvent(project, comment.getAuthorName() + " posted comment on " + reference + " " + label);
		transactionService.commit();

		return "<h2>Comment posted</h2><p>Thank you for your comment!</p><p>Back to <a href=\"" + reference
				+ ".html\">" + label + ".</p>";
	}

	@Override
	protected void onInit(ServletConfig config) {
		ScrumWebApplication app = ScrumWebApplication.get(config);
		daoService = app.getDaoService();
		commentDao = app.getCommentDao();
		projectDao = app.getProjectDao();
		transactionService = app.getTransactionService();
		projectEventDao = app.getProjectEventDao();
	}

}