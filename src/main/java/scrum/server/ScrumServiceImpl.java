package scrum.server;

import ilarkesto.logging.Logger;

import javax.servlet.ServletException;

public class ScrumServiceImpl extends GScrumServiceImpl {

	@Override
	public void init() throws ServletException {
		super.init();
		Logger.setDebugEnabled(true);
	}

	@Override
	public void destroy() {
		ScrumWebApplication.get().shutdown();
		super.destroy();
	}

}
