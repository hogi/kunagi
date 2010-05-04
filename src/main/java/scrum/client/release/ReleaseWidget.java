package scrum.client.release;

import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.journal.ChangeHistoryWidget;

import com.google.gwt.user.client.ui.Widget;

public class ReleaseWidget extends AScrumWidget {

	private Release release;

	public ReleaseWidget(Release risk) {
		super();
		this.release = risk;
	}

	@Override
	protected Widget onInitialization() {
		getApp().callRequestReleaseIssues(release.getId());

		TableBuilder tb = ScrumGwt.createFieldTable();

		tb.addFieldRow("Label", release.getLabelModel());
		tb.addFieldRow("Release date", release.getReleaseDateModel());
		tb.addFieldRow("Description", release.getNoteModel());
		tb.addFieldRow("Release notes", release.getReleaseNotesModel());
		tb.addFieldRow("Affected by issues", ScrumGwt.createToHtmlItemsWidget(release.getAffectedByIssues()));
		tb.addFieldRow("Fixed issues", ScrumGwt.createToHtmlItemsWidget(release.getFixedIssues()));
		tb.addFieldRow("Planned issues", ScrumGwt.createToHtmlItemsWidget(release.getPlannedIssues()));
		tb.addRow(new ChangeHistoryWidget(release), 2);

		return TableBuilder.row(20, tb.createTable(), ScrumGwt.createEmoticonsAndComments(release));
	}
}
