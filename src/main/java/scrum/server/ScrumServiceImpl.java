package scrum.server;

import ilarkesto.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class ScrumServiceImpl extends GScrumServiceImpl {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		Logger.setDebugEnabled(true);
		super.init(servletConfig);
	}

	@Override
	public void destroy() {
		ScrumWebApplication.get().shutdown();
		super.destroy();
	}

}
