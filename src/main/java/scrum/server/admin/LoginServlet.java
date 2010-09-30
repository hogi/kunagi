package scrum.server.admin;

import ilarkesto.auth.OpenId;
import ilarkesto.base.Str;
import ilarkesto.base.Utl;
import ilarkesto.base.time.DateAndTime;
import ilarkesto.core.logging.Log;
import ilarkesto.io.IO;
import ilarkesto.ui.web.HtmlRenderer;
import ilarkesto.webapp.Servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import scrum.client.ApplicationInfo;
import scrum.client.ScrumGwtApplication;
import scrum.server.ScrumConfig;
import scrum.server.ScrumWebApplication;
import scrum.server.WebSession;
import scrum.server.common.AHttpServlet;

public class LoginServlet extends AHttpServlet {

	private static final int LOGIN_TOKEN_COOKIE_MAXAGE = 259200; // 3days
	private static final long serialVersionUID = 1;

	private static Log log = Log.get(LoginServlet.class);

	private static ScrumWebApplication webApplication;
	private ApplicationInfo applicationInfo;
	private ScrumConfig config;
	private UserDao userDao;
	private SystemConfig systemConfig;

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp, WebSession session) throws IOException {
		String historyToken = req.getParameter("historyToken");

		if (session.getUser() != null) {
			resp.sendRedirect(getStartPage(historyToken));
			return;
		}

		String loginToken = Servlet.getCookieValue(req, ScrumGwtApplication.LOGIN_TOKEN_COOKIE);
		if (!Str.isBlank(loginToken)) {
			User user = userDao.getUserByLoginToken(loginToken);
			if (user != null) {
				user.setLastLoginDateAndTime(DateAndTime.now());
				session.setUser(user);
				Servlet.setCookie(resp, ScrumGwtApplication.LOGIN_TOKEN_COOKIE, user.getLoginToken(),
					LOGIN_TOKEN_COOKIE_MAXAGE);
				resp.sendRedirect(getStartPage(historyToken));
				return;
			}
		}

		if (OpenId.isOpenIdCallback(req)) {
			loginOpenId(resp, session, req);
			return;
		}

		if (req.getParameter("createAccount") != null) {
			createAccount(req.getParameter("username"), req.getParameter("email"), req.getParameter("password"),
				historyToken, resp, session);
			return;
		}

		if (req.getParameter("passwordRequest") != null) {
			passwordRequest(req.getParameter("email"), historyToken, resp, session);
			return;
		}

		String openId = req.getParameter("openid");
		if (openId != null) {
			redirectOpenId(openId, req.getParameter("keepmeloggedin") != null, historyToken, resp, session, req);
			return;
		}

		String username = req.getParameter("username");
		if (username != null) {
			login(username, req.getParameter("password"), req.getParameter("keepmeloggedin") != null, historyToken,
				resp, session);
			return;
		}

		renderLoginPage(resp, null, null, historyToken, null, req.getParameter("showPasswordRequest") != null,
			req.getParameter("showCreateAccount") != null);
	}

	private void passwordRequest(String login, String historyToken, HttpServletResponse resp, WebSession session)
			throws UnsupportedEncodingException, IOException {
		if (login == null || Str.isBlank(login)) {
			renderLoginPage(resp, login, null, historyToken, "E-Mail required.", true, false);
			return;
		}

		login = login.toLowerCase();
		User user = null;
		if (login.contains("@")) {
			user = userDao.getUserByEmail(login);
		}
		if (user == null) {
			user = userDao.getUserByName(login);
		}

		if (user == null) {
			renderLoginPage(resp, login, login, historyToken, "User '" + login + "' does not exist.", true, false);
			return;
		}

		if (user.isAdmin()) {
			renderLoginPage(resp, login, login, historyToken, "Admins can not request new passwords.", true, false);
			return;
		}

		if (!user.isEmailVerified()) {
			renderLoginPage(resp, login, login, historyToken, "User '" + login
					+ "' has no verified email. Please contact the admin: " + systemConfig.getAdminEmail(), true, false);
			return;
		}

		user.triggerNewPasswordRequest();
		renderLoginPage(resp, login, login, historyToken, "New password has been sent to " + login, false, false);
	}

	private void createAccount(String username, String email, String password, String historyToken,
			HttpServletResponse resp, WebSession session) throws UnsupportedEncodingException, IOException {

		if (Str.isBlank(username)) username = null;
		if (Str.isBlank(email)) email = null;
		if (Str.isBlank(password)) password = null;

		if (username == null) {
			renderLoginPage(resp, username, email, historyToken, "Creating account failed. Username required.", false,
				true);
			return;
		}
		if (systemConfig.isUserEmailMandatory() && email == null) {
			renderLoginPage(resp, username, email, historyToken, "Creating account failed. E-Mail required.", false,
				true);
			return;
		}
		if (password == null) {
			renderLoginPage(resp, username, email, historyToken, "Creating account failed. Password required.", false,
				true);
			return;
		}

		if (Str.containsNonLetterOrDigit(username)) {
			renderLoginPage(resp, username, email, historyToken, "Creating account failed. Name '" + username
					+ "' contains an illegal character. Only letters and digits allowed.", false, true);
			return;
		}

		if (email != null && !Str.isEmail(email)) {
			renderLoginPage(resp, username, email, historyToken, "Creating account failed. Illegal email address.",
				false, true);
			return;
		}

		if (userDao.getUserByName(username) != null) {
			renderLoginPage(resp, username, email, historyToken, "Creating account failed. Name '" + username
					+ "' is already used.", false, true);
			log.warn("Registration failed. User name already exists:", username);
			return;
		}

		if (email != null && userDao.getUserByEmail(email) != null) {
			renderLoginPage(resp, username, email, historyToken, "Creating account failed. Email '" + email
					+ "' is already used.", false, true);
			log.warn("Registration failed. User email already exists:", email);
			return;
		}

		User user = userDao.postUser(email, username, password);
		user.setLastLoginDateAndTime(DateAndTime.now());
		user.triggerEmailVerification();
		webApplication.triggerRegisterNotification(user);

		session.setUser(user);
		resp.sendRedirect(getStartPage(historyToken));
	}

	private String getStartPage(String historyToken) {
		String url = webApplication.isDevelopmentMode() ? "index.html?gwt.codesvr=127.0.0.1:9997" : "";
		if (historyToken != null) url += "#" + historyToken;
		url = webApplication.createUrl(url);
		return url;
	}

	private void loginOpenId(HttpServletResponse resp, WebSession session, HttpServletRequest request)
			throws UnsupportedEncodingException, IOException {
		HttpSession httpSession = request.getSession();
		String historyToken = (String) httpSession.getAttribute("openidHistoryToken");
		boolean keepmeloggedin = httpSession.getAttribute("openidKeepmeloggedin") != null;

		String openId;
		try {
			openId = OpenId.getIdentifierFromCallbackWithoutSuffix(request);
		} catch (RuntimeException ex) {
			log.error("OpenID authentication failed.", ex);
			renderLoginPage(resp, null, null, historyToken,
				"OpenID authentication failed: " + Str.format(Utl.getRootCause(ex)), false, false);
			return;
		}
		if (openId == null) {
			renderLoginPage(resp, null, null, historyToken, "OpenID authentication failed.", false, false);
			return;
		}

		log.info("User authenticated by OpenID:", openId);

		User user = userDao.getUserByOpenId(openId);

		if (user == null) {
			if (webApplication.getSystemConfig().isRegistrationDisabled()) {
				renderLoginPage(resp, null, null, historyToken, "There is no user with the OpenID " + openId
						+ " and creating new users is disabled.", false, false);
				return;
			}

			if (userDao.getUserByOpenId(openId) != null) {
				renderLoginPage(resp, null, null, historyToken, "Creating account failed. OpenID '" + openId
						+ "' is already used.", false, true);
				log.warn("Registration failed. OpenID already exists:", openId);
				return;
			}

			user = userDao.postUserWithOpenId(openId);
			webApplication.triggerRegisterNotification(user);
		}

		if (user.isDisabled()) {
			renderLoginPage(resp, null, null, historyToken, "User is disabled.", false, false);
			return;
		}

		user.setLastLoginDateAndTime(DateAndTime.now());
		session.setUser(user);

		if (keepmeloggedin)
			Servlet.setCookie(resp, ScrumGwtApplication.LOGIN_TOKEN_COOKIE, user.getLoginToken(),
				LOGIN_TOKEN_COOKIE_MAXAGE);

		resp.sendRedirect(getStartPage(historyToken));
	}

	private void redirectOpenId(String openId, boolean keepmeloggedin, String historyToken, HttpServletResponse resp,
			WebSession session, HttpServletRequest request) throws UnsupportedEncodingException, IOException {
		HttpSession httpSession = request.getSession();
		if (Str.isBlank(openId)) openId = null;

		if (openId == null) {
			renderLoginPage(resp, null, null, historyToken, "Login failed. OpenID required.", false, true);
			return;
		}

		String url;
		try {
			url = OpenId.createAuthenticationRequestUrl(openId, webApplication.createUrl("login.html"), httpSession);
		} catch (RuntimeException ex) {
			log.error("OpenID authentication failed.", ex);
			renderLoginPage(resp, null, null, historyToken,
				"OpenID authentication failed: " + Str.format(Utl.getRootCause(ex)), false, false);
			return;
		}

		httpSession.setAttribute("openidHistoryToken", historyToken);
		httpSession.setAttribute("openidKeepmeloggedin", keepmeloggedin ? "true" : null);

		resp.sendRedirect(url);
	}

	private void login(String username, String password, boolean keepmeloggedin, String historyToken,
			HttpServletResponse resp, WebSession session) throws UnsupportedEncodingException, IOException {
		username = username.toLowerCase();
		User user = null;
		if (username.contains("@")) {
			user = userDao.getUserByEmail(username);
		}
		if (user == null) {
			user = userDao.getUserByName(username);
		}

		if (user == null || user.matchesPassword(password) == false) {
			renderLoginPage(resp, username, null, historyToken, "Login failed.", false, false);
			return;
		}

		if (user.isDisabled()) {
			renderLoginPage(resp, username, null, historyToken, "User is disabled.", false, false);
			return;
		}

		user.setLastLoginDateAndTime(DateAndTime.now());
		session.setUser(user);

		if (keepmeloggedin)
			Servlet.setCookie(resp, ScrumGwtApplication.LOGIN_TOKEN_COOKIE, user.getLoginToken(),
				LOGIN_TOKEN_COOKIE_MAXAGE);

		resp.sendRedirect(getStartPage(historyToken));
	}

	private void renderLoginPage(HttpServletResponse resp, String username, String email, String historyToken,
			String message, boolean passwordRequest, boolean createAccount) throws UnsupportedEncodingException,
			IOException {
		String charset = IO.UTF_8;
		resp.setContentType("text/html");

		HtmlRenderer html = new HtmlRenderer(resp.getOutputStream(), charset);
		html.startHTMLstandard();

		html.startHEAD(applicationInfo.getName() + " Login", "EN");
		html.META("X-UA-Compatible", "chrome=1");
		html.LINKfavicon();
		html.LINKcss("scrum.ScrumGwtApplication/screen.css");
		html.endHEAD();

		html.startBODY();
		html.startDIV("loginPage");
		html.startDIV("panel");
		String logoUrl = webApplication.getSystemConfig().getLoginPageLogoUrl();
		if (Str.isBlank(logoUrl)) logoUrl = "kunagi.png";
		html.IMG(logoUrl, "Kunagi", null, null, null);
		html.DIV("separator", null);
		if (message != null) renderMessage(html, message);
		if (!createAccount && !passwordRequest) renderLogin(html, username, historyToken);
		if (passwordRequest) renderPasswordRequest(html, username, historyToken);
		if (createAccount) renderCreateAccount(html, username, email, historyToken);
		html.DIV("separator", null);
		html.startDIV("kunagiLink");
		html.text("Kunagi " + webApplication.getReleaseLabel() + " | ");
		html.A("http://kunagi.org", "kunagi.org");
		html.endDIV();
		html.endDIV();

		html.endDIV();
		html.comment(applicationInfo.toString());

		html.SCRIPTjavascript(null, "document.getElementById('username').focus();");

		String analyticsId = config.getGoogleAnalyticsId();
		if (analyticsId != null) html.googleAnalytics(analyticsId);
		html.endBODY();
		html.endHTML();
		html.flush();
	}

	private void renderLogin(HtmlRenderer html, String username, String historyToken) {
		html.H2("Login with OpenID");
		renderOpenIdLoginForm(html, historyToken);

		html.DIV("separator", null);
		html.H2("Login with Password");
		renderRetroLoginForm(html, username, historyToken);
		html.BR();
		html.A("login.html?showPasswordRequest=true", "Forgot your password?");
		if (!systemConfig.isRegistrationDisabled()) {
			html.nbsp();
			html.nbsp();
			html.A("login.html?showCreateAccount=true", "Create new account");
		}

		if (webApplication.isAdminPasswordDefault()) {
			html.DIV("separator", null);
			html.startDIV("configMessage");
			html.html("<h2>Warning!</h2>The administrator user <code>admin</code> has the default password <code>"
					+ scrum.client.admin.User.INITIAL_PASSWORD + "</code>. Please change it.");
			html.endDIV();
		}

		if (systemConfig.isLoginPageMessageSet()) {
			html.DIV("separator", null);
			html.startDIV("configMessage");
			html.html(systemConfig.getLoginPageMessage());
			html.endDIV();
		}
	}

	public void renderRetroLoginForm(HtmlRenderer html, String username, String historyToken) {
		html.startFORM(null, "loginForm", false);
		html.INPUThidden("historyToken", historyToken);
		html.startTABLE().setAlignCenter();

		html.startTR();
		html.startTD();
		html.LABEL("username", "Username / E-Mail");
		html.endTD();
		html.startTD();
		html.LABEL("password", "Password");
		html.endTD();
		html.endTR();

		html.startTR();
		html.startTD();
		html.INPUTtext("username", "username", username, 80);
		html.endTD();
		html.startTD();
		html.INPUTpassword("password", "password", 80, "");
		html.endTD();
		html.endTR();

		html.startTR();
		html.startTD();
		html.INPUTcheckbox("keepmeloggedin", "keepmeloggedin", false);
		html.LABEL("keepmeloggedin", "Keep me logged in");
		html.endTD();
		html.startTD().setAlignRight();
		html.INPUTsubmit("login", "Login", null, 's');
		html.endTD();
		html.endTR();

		html.endTABLE();
		html.endFORM();
	}

	public void renderOpenIdLoginForm(HtmlRenderer html, String historyToken) {
		renderOpenIdLink(OpenId.MYOPENID, "MyOpenID", historyToken, html);
		renderOpenIdLink(OpenId.GOOGLE, "Google", historyToken, html);
		renderOpenIdLink(OpenId.YAHOO, "Yahoo!", historyToken, html);
		renderOpenIdLink(OpenId.LAUNCHPAD, "Launchpad", historyToken, html);
		renderOpenIdLink(OpenId.AOL, "AOL", historyToken, html);
		renderOpenIdLink(OpenId.VERISIGN, "Verisign", historyToken, html);
		renderOpenIdLink(OpenId.WORDPRESS, "WordPress", historyToken, html);
		renderOpenIdLink(OpenId.FLICKR, "Flickr", historyToken, html);
		// renderOpenIdLink(OpenId.BLOGSPOT, "Blogger", historyToken, html);
		renderOpenIdLink(OpenId.MYVIDOOP, "Vidoop", historyToken, html);
		html.DIVclear();
		html.BR();

		html.startFORM(null, "openIdForm", false);
		html.INPUThidden("historyToken", historyToken);
		html.startTABLE().setAlignCenter();

		html.startTR();
		html.startTD();
		html.LABEL("openid", "Custom OpenID");
		html.endTD();
		html.TD("");
		html.endTR();

		html.startTR();
		html.startTD(null, 2);
		html.INPUTtext("openid", "openid", null, 80);
		html.endTD();
		html.endTR();

		html.startTR();
		html.startTD();
		html.INPUTcheckbox("keepmeloggedinOpenId", "keepmeloggedin", false);
		html.LABEL("keepmeloggedinOpenId", "Keep me logged in");
		html.endTD();
		html.startTD().setAlignRight();
		html.INPUTsubmit("login", "Login", null, 's');
		html.endTD();
		html.endTR();

		html.endTABLE();
		html.endFORM();
	}

	private void renderOpenIdLink(String openId, String label, String historyToken, HtmlRenderer html) {
		StringBuilder sb = new StringBuilder();
		sb.append("login.html?openid=").append(Str.encodeUrlParameter(openId));
		sb.append("&login=Login");
		if (historyToken != null) sb.append("&historyToken=").append(Str.encodeUrlParameter(historyToken));
		html.startA("openid", sb.toString());
		html.startDIV("button");
		html.text(label);
		html.endDIV();
		html.endA();
	}

	private void renderPasswordRequest(HtmlRenderer html, String username, String historyToken) {
		html.H2("Request new password");
		html.startFORM(null, "passwordRequestForm", false);
		html.INPUThidden("historyToken", historyToken);
		html.startTABLE().setAlignCenter();

		html.startTR();
		html.startTD();
		html.LABEL("email", "E-Mail");
		html.endTD();
		html.TD(" ");
		html.endTR();

		html.startTR();
		html.startTD();
		html.INPUTtext("email", "email", username, 80);
		html.endTD();
		html.startTD();
		html.INPUTsubmit("passwordRequest", "Request password", null, 's');
		html.endTD();
		html.endTR();

		html.endTABLE();
		html.endFORM();

		html.BR();
		html.A("login.html", "Back to Login");
	}

	private void renderCreateAccount(HtmlRenderer html, String username, String email, String historyToken) {
		html.H2("Create account");
		html.startDIV("createAccount");
		html.startFORM(null, "loginForm", false);
		html.INPUThidden("historyToken", historyToken);
		html.startTABLE().setAlignCenter();

		html.startTR();
		html.startTD();
		html.LABEL("username", "Username");
		html.endTD();
		html.startTD();
		html.INPUTtext("username", "username", username, 80);
		html.endTD();
		html.endTR();

		html.startTR();
		html.startTD();
		if (!webApplication.getSystemConfig().isUserEmailMandatory()) html.startDIV("optionalLabel");
		html.LABEL("email", "E-Mail");
		if (!webApplication.getSystemConfig().isUserEmailMandatory()) html.endDIV();
		html.endTD();
		html.startTD();
		html.INPUTtext("email", "email", email, 80);
		html.endTD();
		html.endTR();

		html.startTR();
		html.startTD();
		html.LABEL("password", "Password");
		html.endTD();
		html.startTD();
		html.INPUTpassword("password", "password", 80, "");
		html.endTD();
		html.endTR();

		html.startTR();
		html.TD("");
		html.startTD();
		html.INPUTsubmit("createAccount", "Create account", null, 's');
		html.endTD();
		html.endTR();

		html.endTABLE();
		html.endFORM();
		html.endDIV();

		html.BR();
		html.A("login.html", "Back to Login");

		if (systemConfig.isRegisterPageMessageSet()) {
			html.DIV("separator", null);
			html.startDIV("configMessage");
			html.html(systemConfig.getRegisterPageMessage());
			html.endDIV();
		}
	}

	private void renderMessage(HtmlRenderer html, String message) {
		html.startDIV("message");
		html.text(message);
		html.endDIV();
		html.DIV("separator", null);
	}

	@Override
	protected void onInit(ServletConfig servletConfig) {
		super.onInit(servletConfig);
		webApplication = ScrumWebApplication.get();
		userDao = webApplication.getUserDao();
		applicationInfo = webApplication.getApplicationInfo();
		config = webApplication.getConfig();
		systemConfig = webApplication.getSystemConfig();
	}

}
