package scrum.client.admin;

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

		page.addHeader("Kunagi installation");
		TableBuilder tbKunagi = ScrumGwt.createFieldTable();
		tbKunagi.addFieldRow("Public URL", config.getUrlModel());
		tbKunagi.addFieldRow("Administrator email", config.getAdminEmailModel());
		page.addSection(tbKunagi.createTable());

		page.addHeader("Email Server");
		TableBuilder tbEmail = ScrumGwt.createFieldTable();
		tbEmail.addFieldRow("SMTP Server", config.getSmtpServerModel());
		tbEmail.addFieldRow("SMTP User", config.getSmtpUserModel());
		tbEmail.addFieldRow("SMTP Password", config.getSmtpPasswordModel());
		tbEmail.addFieldRow("Sender email", config.getSmtpFromModel());
		page.addSection(tbEmail.createTable());

		page.addHeader("Google Integration");
		TableBuilder tbGoogle = ScrumGwt.createFieldTable();
		tbGoogle.addFieldRow("Analytics Id", config.getGoogleAnalyticsIdModel());
		page.addSection(tbGoogle.createTable());

		return page;
	}

}
