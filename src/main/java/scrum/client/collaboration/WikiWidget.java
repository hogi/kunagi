package scrum.client.collaboration;

import ilarkesto.gwt.client.ARichtextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.ScrumGwtApplication;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class WikiWidget extends AWidget {

	private String pageName;
	private Wikipage page;

	private FlowPanel panel;
	private TextBox pageNameBox;
	private FlowPanel navigPanel;
	private ToolbarWidget toolbar;
	private Label titleLabel;
	private WikipageEditor editor;
	private Label dummyLabel;

	@Override
	protected Widget onInitialization() {
		pageNameBox = new TextBox();
		pageNameBox.addKeyPressHandler(new PageNameHandler());
		navigPanel = new FlowPanel();
		navigPanel.setStyleName("WikiWidget-navig");
		navigPanel.add(pageNameBox);
		titleLabel = new Label();
		titleLabel.setStyleName("WikiWidget-title");
		toolbar = new ToolbarWidget();
		editor = new WikipageEditor();
		dummyLabel = new Label();
		dummyLabel.setStyleName("WikiWidget-dummy");

		panel = new FlowPanel();
		panel.setStyleName("WikiWidget");
		return panel;
	}

	@Override
	protected void onUpdate() {
		if (pageName == null || pageName.trim().length() == 0) pageName = "Start";

		page = ScrumGwtApplication.get().getProject().getWikipage(pageName);

		pageNameBox.setText(pageName);

		panel.clear();
		toolbar.clear();
		panel.add(navigPanel);
		panel.add(titleLabel);
		if (page == null) {
			titleLabel.setText(pageName);
			toolbar.addButton(new CreateWikipageAction(pageName));
			dummyLabel.setText("Page \"" + pageName + "\" does not exist.");
			panel.add(dummyLabel);
		} else {
			titleLabel.setText(page.getName());
			toolbar.addButton(new DeleteWikipageAction(page));
			editor.update();
			panel.add(editor);
		}
		panel.add(toolbar.update());

	}

	public void showPage(String name) {
		this.pageName = name;
		update();
	}

	class WikipageEditor extends ARichtextViewEditWidget {

		@Override
		protected void onViewerUpdate() {
			setViewerText(page.getText());
		}

		@Override
		protected void onEditorUpdate() {
			setEditorText(page.getText());
		}

		@Override
		protected void onEditorSubmit() {
			page.setText(getEditorText());
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
