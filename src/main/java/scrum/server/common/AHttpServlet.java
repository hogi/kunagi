package scrum.server.common;

import ilarkesto.logging.Logger;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AHttpServlet extends HttpServlet {

	private static final Logger LOG = Logger.get(AHttpServlet.class);

	protected abstract void onRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException;

	protected void onInit(ServletConfig config) {}

	@Override
	protected final void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			onRequest(req, resp);
		} catch (Throwable ex) {
			LOG.fatal("GET failed:", getClass().getName(), "->", ex);
		}
	}

	@Override
	protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			onRequest(req, resp);
		} catch (Throwable ex) {
			LOG.fatal("POST failed:", getClass().getName(), "->", ex);
		}
	}

	@Override
	public final void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			onInit(config);
		} catch (Throwable ex) {
			LOG.fatal("Init failed:", getClass().getName(), "->", ex);
		}
	}

	@Override
	public final void init() throws ServletException {
		super.init();
	}

}
