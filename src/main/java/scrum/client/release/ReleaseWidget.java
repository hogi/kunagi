package scrum.client.release;

import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.AOutputViewEditWidget;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumWidget;
import scrum.client.issues.RequestReleaseIssuesServiceCall;
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
		new RequestReleaseIssuesServiceCall(release.getId()).execute();

		TableBuilder tb = ScrumGwt.createFieldTable();

		tb.addFieldRow("Label", release.getLabelModel());
		tb.addFieldRow("Release date", release.getReleaseDateModel());
		tb.addFieldRow("Description", release.getNoteModel());
		tb.addFieldRow("Release notes", release.getReleaseNotesModel());
		tb.addFieldRow("SCM Tag", release.getScmTagModel());
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
					return release.getProject().isProductOwnerOrScrumMaster(getCurrentUser());
				}

				@Override
				public String getTooltip() {
					return getLocalizer().fields().releaseSprints();
				}
			});

			tb.addFieldRow("Requirements", new AOutputViewEditWidget() {

				@Override
				protected void onViewerUpdate() {
					setViewer(ScrumGwt.createToHtmlItemsWidget(release.getRequirements()));
				}

				@Override
				public String getTooltip() {
					return getLocalizer().fields().releaseRequirements();
				}
			});
		}
		tb.addFieldRow("Affected by issues", new AOutputViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewer(ScrumGwt.createToHtmlItemsWidget(release.getAffectedByIssues()));
			}

			@Override
			public String getTooltip() {
				return getLocalizer().fields().releaseAffectedByIssues();
			}
		});
		tb.addFieldRow("Fixed issues", new AOutputViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewer(ScrumGwt.createToHtmlItemsWidget(release.getFixedIssues()));
			}

			@Override
			public String getTooltip() {
				return getLocalizer().fields().releaseFixedIssues();
			}
		});
		tb.addFieldRow("Planned issues", new AOutputViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewer(ScrumGwt.createToHtmlItemsWidget(release.getPlannedIssues()));
			}

			@Override
			public String getTooltip() {
				return getLocalizer().fields().releasePlannedIssues();
			}
		});
		tb.addRow(new ChangeHistoryWidget(release), 2);

		return TableBuilder.row(20, tb.createTable(), ScrumGwt.createEmoticonsAndComments(release));
	}
}
