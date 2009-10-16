package scrum.server.css;

import ilarkesto.io.DynamicClassLoader;
import ilarkesto.logging.Logger;
import ilarkesto.ui.web.CssRenderer;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.server.ScrumWebApplication;

public class CssServlet extends HttpServlet {

	private static final Logger LOG = Logger.get(CssServlet.class);

	private ScreenCssBuilder screenCssBuilder = new ScreenCssBuilder();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/css");
		CssRenderer css = new CssRenderer(resp.getWriter());
		getCssBuilder().buildCss(css);
		css.flush();
	}

	private CssBuilder getCssBuilder() {
		if (ScrumWebApplication.get().isDevelopmentMode()) {
			ClassLoader loader = new DynamicClassLoader(getClass().getClassLoader(), ScreenCssBuilder.class.getName());
			Class<? extends CssBuilder> type;
			try {
				type = (Class<? extends CssBuilder>) loader.loadClass(ScreenCssBuilder.class.getName());
				return type.newInstance();
			} catch (Throwable ex) {
				LOG.fatal(ex);
				throw new RuntimeException(ex);
			}
		} else {
			return screenCssBuilder;
		}
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		ScrumWebApplication.get(servletConfig);
	}

}
