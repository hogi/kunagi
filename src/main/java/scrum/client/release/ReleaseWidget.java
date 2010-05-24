package scrum.client.release;

import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.journal.ChangeHistoryWidget;
import scrum.client.sprint.Sprint;

import com.google.gwt.user.client.ui.Widget;

public class ReleaseWidget extends AScrumWidget {

	private Release release;

	public ReleaseWidget(Release release) {
		super();
		this.release = release;
	}

	@Override
	protected Widget onInitialization() {
		getApp().callRequestReleaseIssues(release.getId());

		TableBuilder tb = ScrumGwt.createFieldTable();

		tb.addFieldRow("Label", release.getLabelModel());
		tb.addFieldRow("Release date", release.getReleaseDateModel());
		tb.addFieldRow("Description", release.getNoteModel());
		tb.addFieldRow("Release notes", release.getReleaseNotesModel());
		if (release.isMajor()) {
			tb.addFieldRow("Sprints", new AMultiSelectionViewEditWidget<Sprint>() {

				@Override
				protected void onViewerUpdate() {
					setViewerItems(release.getSprints());
				}

				@Override
				protected void onEditorUpdate() {
					setEditorItems(release.getProject().getSprints());
					setEditorSelectedItems(release.getSprints());
				}

				@Override
				protected void onEditorSubmit() {
					release.setSprints(getEditorSelectedItems());
				}

				@Override
				public boolean isEditable() {
					return release.getProject().isProductOwner(getCurrentUser());
				}
			});

			tb.addFieldRow("Sprints", ScrumGwt.createToHtmlItemsWidget(release.getSprints()));
			tb.addFieldRow("Requirements", ScrumGwt.createToHtmlItemsWidget(release.getRequirements()));
		}
		tb.addFieldRow("Affected by issues", ScrumGwt.createToHtmlItemsWidget(release.getAffectedByIssues()));
		tb.addFieldRow("Fixed issues", ScrumGwt.createToHtmlItemsWidget(release.getFixedIssues()));
		tb.addFieldRow("Planned issues", ScrumGwt.createToHtmlItemsWidget(release.getPlannedIssues()));
		tb.addRow(new ChangeHistoryWidget(release), 2);

		return TableBuilder.row(20, tb.createTable(), ScrumGwt.createEmoticonsAndComments(release));
	}
}
