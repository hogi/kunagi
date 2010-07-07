package scrum.client.pr;

import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.TableBuilder;
import scrum.client.Dao;
import scrum.client.ScrumGwt;
import scrum.client.admin.User;
import scrum.client.common.AScrumWidget;

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
				setViewerItems(blogEntry.getProject().getParticipants());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(Dao.get().getUsers());
				setEditorSelectedItems(blogEntry.getAuthors());
			}

			@Override
			protected void onEditorSubmit() {
				blogEntry.setAuthors(getEditorSelectedItems());
			}

			@Override
			public boolean isEditable() {
				// TODO change to only PO
				return false;
			}
		});

		return TableBuilder.row(20, tb.createTable(), ScrumGwt.createEmoticonsAndComments(blogEntry));
	}
}
