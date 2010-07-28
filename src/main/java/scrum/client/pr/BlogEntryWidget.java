package scrum.client.pr;

import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.ScrumGwt;
import scrum.client.admin.User;
import scrum.client.common.AScrumWidget;
import scrum.client.journal.ChangeHistoryWidget;

import com.google.gwt.user.client.ui.Widget;

public class BlogEntryWidget extends AScrumWidget {

	private BlogEntry blogEntry;

	public BlogEntryWidget(BlogEntry blogEntry) {
		super();
		this.blogEntry = blogEntry;
	}

	@Override
	protected Widget onInitialization() {
		TableBuilder tb = ScrumGwt.createFieldTable();

		tb.addFieldRow("Title", blogEntry.getTitleModel());
		tb.addFieldRow("Text", blogEntry.getTextModel());
		tb.addFieldRow("Date", blogEntry.getDateAndTimeModel());
		tb.addFieldRow("Authors", new AMultiSelectionViewEditWidget<User>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItems(blogEntry.getAuthors());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(blogEntry.getProject().getParticipants());
				setEditorSelectedItems(blogEntry.getAuthors());
			}

			@Override
			protected void onEditorSubmit() {
				blogEntry.setAuthors(getEditorSelectedItems());
			}

			@Override
			public boolean isEditable() {
				return true;
			}
		});
		tb.addRow(new ChangeHistoryWidget(blogEntry), 2);

		return TableBuilder.row(20, tb.createTable(), ScrumGwt.createEmoticonsAndComments(blogEntry));
	}
}
