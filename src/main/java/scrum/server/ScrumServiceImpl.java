package scrum.server;

public class ScrumServiceImpl extends GScrumServiceImpl {

	@Override
	public void destroy() {
		ScrumWebApplication.get().shutdown();
	}

}
