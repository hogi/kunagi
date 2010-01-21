package scrum.client.collaboration;

import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.GwtLogger;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

public class WikiWidget extends AScrumWidget {

	private String pageName;
	private Wikipage wikipage;

	private FlowPanel panel;
	private SuggestBox pageNameBox;
	private RichtextEditorWidget editor;

	@Override
	protected Widget onInitialization() {

		panel = new FlowPanel();
		panel.setStyleName("WikiWidget");
		return panel;
	}

	@Override
	protected void onUpdate() {
		if (editor != null && editor.isEditMode()) return;

		if (pageName == null || pageName.trim().length() == 0) pageName = "Start";

		wikipage = getCurrentProject().getWikipage(pageName);

		pageNameBox = new SuggestBox(cm.getWiki().createPagesSuggestOracle());
		pageNameBox.setAutoSelectEnabled(false);
		pageNameBox.setTitle("Enter name of wiki page");
		pageNameBox.addSelectionHandler(new PageNameHandler());
		pageNameBox.addKeyPressHandler(new PageNameHandler());
		pageNameBox.setText(pageName);

		PagePanel page = new PagePanel();

		if (wikipage == null) {
			page.addHeader("Wiki: " + pageName, pageNameBox, new ButtonWidget(new CreateWikipageAction(pageName)));
			page.addSection("Page does not exist.");
		} else {
			page.addHeader("Wiki: " + wikipage.getName(), pageNameBox, new ButtonWidget(new DeleteWikipageAction(
					wikipage)));
			editor = new RichtextEditorWidget(wikipage.getTextModel());
			editor.setEditorHeight(500);

			FlowPanel right = new FlowPanel();
			right.add(Gwt.createServletDownloadLink("wikipage.pdf?wikipageId=" + wikipage.getId(), "Downlad as PDF"));
			right.add(Gwt.createSpacer(1, 10));
			right.add(new CommentsWidget(wikipage));

			page.addSection(TableBuilder.row(20, editor, right));
		}

		panel.clear();
		panel.add(page);
		Gwt.update(panel);
	}

	public void showPage(String name) {
		this.pageName = name;
		update();
	}

	class ShowPageAction extends AScrumAction {

		private Wikipage page;

		public ShowPageAction(Wikipage page) {
			super();
			this.page = page;
		}

		@Override
		public String getLabel() {
			return page.getName();
		}

		@Override
		protected void onExecute() {
			showPage(page.getName());
		}
	}

	class PageNameHandler implements KeyPressHandler, SelectionHandler<Suggestion> {

		public void onKeyPress(KeyPressEvent event) {
			if (pageNameBox.isSuggestionListShowing()) return;
			if (event.getCharCode() == KeyCodes.KEY_ENTER) {
				GwtLogger.DEBUG("-------------------- ENTER ----");
				event.stopPropagation();
				showPage(pageNameBox.getText());
			}
		}

		public void onSelection(SelectionEvent<Suggestion> event) {
			showPage(event.getSelectedItem().getReplacementString());
		}

	}
}
