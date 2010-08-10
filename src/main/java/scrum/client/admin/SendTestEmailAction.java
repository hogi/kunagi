package scrum.client.admin;

import ilarkesto.core.base.Str;

public class SendTestEmailAction extends GSendTestEmailAction {

	@Override
	public String getLabel() {
		return "Send test email";
	}

	@Override
	public boolean isExecutable() {
		SystemConfig config = getDao().getSystemConfig();
		if (Str.isBlank(config.getSmtpFrom())) return false;
		if (Str.isBlank(config.getSmtpServer())) return false;
		if (Str.isBlank(config.getAdminEmail())) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		new SendTestEmailServiceCall().execute();
	}

}