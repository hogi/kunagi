package scrum.server.admin;

import ilarkesto.base.Crypt;
import ilarkesto.base.Utl;
import ilarkesto.core.logging.Log;
import ilarkesto.email.Eml;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import scrum.server.ScrumWebApplication;

public class User extends GUser {

	private static Log log = Log.get(User.class);

	// --- dependencies ---

	private static ScrumWebApplication webApplication;

	public static void setWebApplication(ScrumWebApplication webApplication) {
		User.webApplication = webApplication;
	}

	// --- ---

	private String password;

	public void triggerEmailVerification() {
		String urlBase = webApplication.getBaseUrl();
		SystemConfig config = webApplication.getSystemConfig();
		String smtpServer = config.getSmtpServer();
		if (smtpServer == null) {
			log.warn("SMTP server not set in System Configuration");
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("You have created a Kunagi account on ").append(urlBase).append("\n");
			sb.append("\n");
			sb.append("Please visit the following link, to confirm your email: ").append(urlBase).append(
				"/confirmEmail?user=").append(getId()).append("&email=").append(getEmail()).append("\n");

			Session session = Eml.createSmtpSession(smtpServer, config.getSmtpUser(), config.getSmtpPassword());
			MimeMessage message = Eml.createTextMessage(session, "Kunagi email verification: " + getEmail(), sb
					.toString(), config.getSmtpFrom(), getEmail());
			Eml.sendSmtpMessage(session, message);
		}
	}

	@Override
	public String getRealName() {
		return getName();
	}

	@Override
	public boolean matchesPassword(String password) {
		return Crypt.cryptWebPassword(password).equals(this.password);
	}

	@Override
	public void setPassword(String value) {
		this.password = Crypt.cryptWebPassword(value);
		fireModified();
	}

	@Override
	public String getAutoLoginString() {
		return null;
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (!isColorSet()) setColor(getDefaultColor());
	}

	public boolean isVisibleFor(User user) {
		return true;
	}

	@Override
	public String toString() {
		return getName();
	}

	public static String getDefaultColor() {
		return Utl.randomElement(getDefaultColors());
	}

	public static List<String> getDefaultColors() {
		List<String> colors = new ArrayList<String>();
		colors.add("black");
		colors.add("darkred");
		colors.add("darkgreen");
		colors.add("darkblue");
		colors.add("darkorange");
		colors.add("darkorchid");
		colors.add("darkslateblue");
		colors.add("darkgray");
		colors.add("orange");
		colors.add("green");
		return colors;
	}

}
