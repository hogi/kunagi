package scrum.client.collaboration;

import ilarkesto.core.scope.Scope;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.DropdownMenuButtonWidget;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.TableBuilder;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import scrum.client.ScrumGwt;
import scrum.client.common.AScrumAction;
import scrum.client.common.AScrumWidget;
import scrum.client.journal.ActivateChangeHistoryAction;
import scrum.client.journal.ChangeHistoryWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

public class WikiWidget extends AScrumWidget {

	private static final String START_PAGE_NAME = "Start";
	private String pageName;
	private Wikipage wikipage;
	private Wiki wiki;

	private FlowPanel panel;
	private RichtextEditorWidget editor;

	@Override
	protected Widget onInitialization() {
		wiki = Scope.get().getComponent(Wiki.class);

		panel = new FlowPanel();
		panel.setStyleName("WikiWidget");
		return panel;
	}

	@Override
	protected void onUpdate() {
		if (editor != null && editor.isEditMode()) return;

		if (pageName == null || pageName.trim().length() == 0) pageName = START_PAGE_NAME;

		wikipage = getCurrentProject().getWikipage(pageName);

		createPageSelector();

		PagePanel page = new PagePanel();

		if (wikipage == null) {
			page.addHeader(pageName, createPageSelector());
			page.addSection("Page does not exist.");
			page.addSection(new ButtonWidget(new CreateWikipageAction(pageName)));
		} else {
			page.addHeader(wikipage.getName(), createPageSelector());
			editor = new RichtextEditorWidget(wikipage.getTextModel());
			editor.setEditorHeight(600);

			FlowPanel right = new FlowPanel();
			right.add(new CommentsWidget(wikipage));

			page.addSection(TableBuilder.row(20, editor, right));

			page.addHeader("Page properties", createActionsDropdown());

			FlowPanel left = new FlowPanel();
			left.add(Gwt.createSpacer(1, 5));
			left.add(TableBuilder.row(5, new Label("My emoticon"), wikipage.createCurrentUserEmotionEditor(),
				new EmoticonsWidget(wikipage)));
			left.add(Gwt.createSpacer(1, 10));
			left.add(ScrumGwt.createPdfLink("Downlad as PDF", "wikipage", wikipage));
			left.add(Gwt.createSpacer(1, 10));
			left.add(new ChangeHistoryWidget(wikipage));
			page.addSection(left);
		}

		panel.clear();
		panel.add(page);
		Gwt.update(panel);
	}

	private Widget createActionsDropdown() {
		DropdownMenuButtonWidget dropdown = new DropdownMenuButtonWidget();
		dropdown.addAction(new ActivateChangeHistoryAction(wikipage));
		dropdown.addAction(new DeleteWikipageAction(wikipage));
		return dropdown;
	}

	private Widget createPageSelector() {
		SuggestBox pageNameBox = new SuggestBox(wiki.createPagesSuggestOracle());
		pageNameBox.setAutoSelectEnabled(false);
		pageNameBox.setTitle("Enter name of wiki page");
		pageNameBox.addSelectionHandler(new PageNameHandler());
		pageNameBox.addKeyPressHandler(new PageNameHandler());
		pageNameBox.setText(pageName);

		DropdownMenuButtonWidget dropdown = new DropdownMenuButtonWidget();
		for (Wikipage page : getCurrentProject().getWikipages()) {
			dropdown.addAction(new ShowPageAction("", page.getName()));
		}

		return TableBuilder.row(5, new ButtonWidget(new ShowPageAction("Go to ", START_PAGE_NAME)), pageNameBox,
			dropdown);
	}

	public void showPage(String name) {
		this.pageName = name;
		update();
	}

	class ShowPageAction extends AScrumAction {

		private String prefix;
		private String pageName;

		public ShowPageAction(String prefix, String pageName) {
			super();
			this.prefix = prefix;
			this.pageName = pageName;
		}

		@Override
		public String getLabel() {
			return prefix + pageName;
		}

		@Override
		public String getTooltip() {
			return "Goto wiki page " + pageName;
		}

		@Override
		protected void onExecute() {
			showPage(pageName);
		}
	}

	class PageNameHandler implements KeyPressHandler, SelectionHandler<Suggestion> {

		public void onKeyPress(KeyPressEvent event) {
			SuggestBox pageNameBox = (SuggestBox) event.getSource();
			if (pageNameBox.isSuggestionListShowing()) return;
			if (event.getCharCode() == KeyCodes.KEY_ENTER) {
				event.stopPropagation();
				showPage(pageNameBox.getText());
			}
		}

		public void onSelection(SelectionEvent<Suggestion> event) {
			showPage(event.getSelectedItem().getReplacementString());
		}

	}
}
