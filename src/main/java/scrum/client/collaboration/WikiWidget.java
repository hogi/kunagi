package scrum.client.collaboration;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.common.AScrumWidget;
import scrum.client.workspace.PagePanel;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class WikiWidget extends AScrumWidget {

	private String pageName;
	private Wikipage wikipage;

	private FlowPanel panel;
	private TextBox pageNameBox;
	private FlowPanel navigPanel;
	private ToolbarWidget toolbar;
	private WikipageEditor editor;

	@Override
	protected Widget onInitialization() {
		pageNameBox = new TextBox();
		pageNameBox.addKeyPressHandler(new PageNameHandler());
		navigPanel = new FlowPanel();
		navigPanel.setStyleName("WikiWidget-navig");
		navigPanel.add(pageNameBox);
		toolbar = new ToolbarWidget();
		editor = new WikipageEditor();

		panel = new FlowPanel();
		panel.setStyleName("WikiWidget");
		return panel;
	}

	@Override
	protected void onUpdate() {
		if (pageName == null || pageName.trim().length() == 0) pageName = "Start";

		wikipage = getCurrentProject().getWikipage(pageName);

		pageNameBox.setText(pageName);

		panel.clear();
		toolbar.clear();

		PagePanel page = new PagePanel();
		panel.add(page);

		page.addSection(navigPanel);
		if (wikipage == null) {
			toolbar.addButton(new CreateWikipageAction(pageName));
			page.addSection("Page \"" + pageName + "\" does not exist.");
		} else {
			page.addHeader(wikipage.getName());
			toolbar.addButton(new DeleteWikipageAction(wikipage));
			editor.update();
			page.addSection(editor);
		}
		page.addSection(toolbar.update());
		if (wikipage != null) page.addSection(new CommentsWidget(wikipage).update());
	}

	public void showPage(String name) {
		this.pageName = name;
		update();
	}

	class WikipageEditor extends ARichtextViewEditWidget {

		@Override
		protected void onViewerUpdate() {
			setViewerText(wikipage.getText());
		}

		@Override
		protected void onEditorUpdate() {
			setEditorText(wikipage.getText());
		}

		@Override
		protected void onEditorSubmit() {
			wikipage.setText(getEditorText());
		}

	}

	class PageNameHandler implements KeyPressHandler {

		public void onKeyPress(KeyPressEvent event) {
			if (event.getCharCode() == KeyCodes.KEY_ENTER) {
				event.stopPropagation();
				showPage(pageNameBox.getText());
			}
		}

	}
}
