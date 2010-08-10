package scrum.client.admin;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.Widget;

public class SystemConfigWidget extends AScrumWidget {

	@Override
	protected Widget onInitialization() {
		SystemConfig config = getDao().getSystemConfig();

		PagePanel page = new PagePanel();

		page.addHeader("Messages");
		TableBuilder tbMessages = ScrumGwt.createFieldTable();
		tbMessages.addFieldRow("Login Page", config.getLoginPageMessageModel());
		tbMessages.addFieldRow("Register Page", config.getRegisterPageMessageModel());
		tbMessages.addFieldRow("About Page", config.getAboutPageMessageModel());
		page.addSection(tbMessages.createTable());

		page.addHeader("Installation");
		TableBuilder tbKunagi = ScrumGwt.createFieldTable();
		tbKunagi.addFieldRow("Public URL", config.getUrlModel());
		page.addSection(tbKunagi.createTable());

		page.addHeader("Email", new ButtonWidget(new SendTestEmailAction()));
		TableBuilder tbEmail = ScrumGwt.createFieldTable();
		tbEmail.addFieldRow("SMTP Server", config.getSmtpServerModel());
		tbEmail.addFieldRow("SMTP User", config.getSmtpUserModel());
		tbEmail.addFieldRow("SMTP Password", config.getSmtpPasswordModel());
		tbEmail.addFieldRow("Sender email", config.getSmtpFromModel());
		tbKunagi.addFieldRow("Administrator email", config.getAdminEmailModel());
		page.addSection(tbEmail.createTable());

		page.addHeader("Google Integration");
		TableBuilder tbGoogle = ScrumGwt.createFieldTable();
		tbGoogle.addFieldRow("Analytics Id", config.getGoogleAnalyticsIdModel());
		page.addSection(tbGoogle.createTable());

		return page;
	}

}
