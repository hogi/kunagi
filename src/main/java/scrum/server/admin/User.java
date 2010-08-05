package scrum.server.admin;

import ilarkesto.base.Crypt;
import ilarkesto.base.Str;
import ilarkesto.base.Utl;
import ilarkesto.core.logging.Log;
import ilarkesto.email.Eml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import scrum.server.ScrumWebApplication;
import scrum.server.project.Project;

public class User extends GUser {

	private static final int HOURS_FOR_EMAIL_VERIFICATION = 72;

	private static Log log = Log.get(User.class);

	// --- dependencies ---

	private static ScrumWebApplication webApplication;

	public static void setWebApplication(ScrumWebApplication webApplication) {
		User.webApplication = webApplication;
	}

	// --- ---

	public Set<Project> getProjects() {
		return projectDao.getProjectsByParticipant(this);
	}

	private String password;

	public void triggerEmailVerification() {
		String urlBase = webApplication.getBaseUrl();
		StringBuilder sb = new StringBuilder();
		sb.append("You have created a Kunagi account on ").append(urlBase).append("\n");
		sb.append("\n");
		sb.append("Please visit the following link, to confirm your email: ").append(urlBase)
				.append("confirmEmail?user=").append(getId()).append("&email=").append(getEmail()).append("\n");
		sb.append("\n");
		sb.append("Please confirm your email within " + HOURS_FOR_EMAIL_VERIFICATION
				+ " hours, otherwise your account will be deleted.\n");
		webApplication.sendEmail(null, getEmail(), "Kunagi email verification: " + getEmail(), sb.toString());
	}

	public void triggerNewPasswordRequest() {
		String urlBase = webApplication.getBaseUrl();

		String newPassword = Str.generatePassword(8);
		StringBuilder sb = new StringBuilder();
		sb.append("You requested a new password for your Kunagi account on ").append(urlBase).append("\n");
		sb.append("\n");
		sb.append("Email: ").append(getEmail()).append("\n");
		sb.append("Password: ").append(newPassword).append("\n");
		sb.append("\n");
		sb.append("You sould change this password, since somebody else could read this email.");

		webApplication.sendEmail(null, getEmail(), "Kunagi password", sb.toString());

		setPassword(newPassword);
		log.info("Password changed for", this);
	}

	public void triggerPasswordReset() {
		String urlBase = webApplication.getBaseUrl();
		SystemConfig config = webApplication.getSystemConfig();
		String smtpServer = config.getSmtpServer();
		if (smtpServer == null) {
			throw new RuntimeException("SMTP server not set in System Configuration");
		} else {
			String newPassword = Str.generatePassword(8);
			StringBuilder sb = new StringBuilder();
			sb.append("An admin created a new password for your Kunagi account on ").append(urlBase).append("\n");
			sb.append("\n");
			sb.append("Email: ").append(getEmail()).append("\n");
			sb.append("Password: ").append(newPassword).append("\n");
			sb.append("\n");
			sb.append("You sould change this password, since somebody else could read this email.");

			Session session = Eml.createSmtpSession(smtpServer, config.getSmtpUser(), config.getSmtpPassword());
			MimeMessage message = Eml.createTextMessage(session, "Kunagi password", sb.toString(),
				config.getSmtpFrom(), getEmail());
			Eml.sendSmtpMessage(session, message);

			setPassword(newPassword);
			log.info("Password changed for", this);
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
		fireModified("password=xxx");
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

	@Override
	public boolean isVisibleFor(User user) {
		return true;
	}

	@Override
	public boolean isEditableBy(User user) {
		return user == this || user.isAdmin();
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

	public static List<String> getNames(Collection<User> users) {
		List<String> names = new ArrayList<String>(users.size());
		for (User user : users) {
			names.add(user.getName());
		}
		return names;
	}

	public boolean isEmailVerificationOverdue() {
		if (!isRegistrationDateAndTimeSet()) return false;
		if (isEmailVerified()) return false;
		return getRegistrationDateAndTime().getPeriodToNow().abs().toHours() > HOURS_FOR_EMAIL_VERIFICATION;
	}

}
