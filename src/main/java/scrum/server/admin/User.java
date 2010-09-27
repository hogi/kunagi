package scrum.server.admin;

import ilarkesto.base.Crypt;
import ilarkesto.base.Str;
import ilarkesto.base.Utl;
import ilarkesto.core.logging.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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

	public String getLabel() {
		if (isEmailSet()) return getName() + " (" + getEmail() + ")";
		return getName();
	}

	public Set<Project> getProjects() {
		return projectDao.getProjectsByParticipant(this);
	}

	private String password;

	public void triggerEmailVerification() {
		if (!isEmailSet()) {
			log.info("User has no email. Skipping email verification:", this);
			return;
		}

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
		if (!isEmailSet()) {
			log.info("User has no email. Skipping new password request:", this);
			return;
		}

		String newPassword = Str.generatePassword(8);

		StringBuilder sb = new StringBuilder();
		sb.append("You requested a new password for your Kunagi account on ").append(webApplication.getBaseUrl())
				.append("\n");
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

		String newPassword = Str.generatePassword(8);
		setPassword(newPassword);
		log.info("Password changed for", this);

		StringBuilder sb = new StringBuilder();
		sb.append("An admin created a new password for your Kunagi account on ").append(urlBase).append("\n");
		sb.append("\n");
		sb.append("Email: ").append(getEmail()).append("\n");
		sb.append("Password: ").append(newPassword).append("\n");
		sb.append("\n");
		sb.append("You sould change this password, since somebody else could read this email.");
		webApplication.sendEmail(null, getEmail(), "Kunagi password", sb.toString());
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
		return getLoginToken();
	}

	public void createLoginToken() {
		setLoginToken(UUID.randomUUID().toString());
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		if (!isColorSet()) setColor(getDefaultColor());
		if (!isLoginTokenSet()) createLoginToken();
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
