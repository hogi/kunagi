package scrum.client.admin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

public class LogoutAction extends GLogoutAction {

	@Override
	public String getLabel() {
		return "Logout";
	}

	@Override
	public boolean isExecutable() {
		return getAuth().isUserLoggedIn();
	}

	@Override
	protected void onExecute() {
		getAuth().logout();
		String url = GWT.getHostPageBaseURL();
		if (!GWT.isScript()) url += "index.html?gwt.codesvr=localhost:9997";
		Window.Location.replace(url);
	}

}