package scrum.client.collaboration;

import ilarkesto.gwt.client.BetterTextArea;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.Initializer;
import ilarkesto.gwt.client.RichtextFormater;
import ilarkesto.gwt.client.ToolbarWidget;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import scrum.client.files.File;
import scrum.client.files.Uploader;
import scrum.client.img.Img;
import scrum.client.wiki.ScrumHtmlContext;
import scrum.client.wiki.WikiModel;
import scrum.client.wiki.WikiParser;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.Widget;

public class Wiki extends GWiki implements RichtextFormater {

	@Override
	public void initialize() {
		Gwt.setDefaultRichtextFormater(this);
		Gwt.setRichtextEditorEditInitializer(new RichtextEditorEditInitializer());
		Gwt.setDefaultRichtextSyntaxInfo(WikiParser.SYNTAX_INFO_HTML);
	}

	public String getTemplate(String name) {
		Wikipage page = project.getWikipage("template:" + name);
		return page == null ? null : page.getText();
	}

	public String richtextToHtml(String text) {
		return toHtml(text);
	}

	public static String toHtml(String wiki) {
		if (wiki == null) return null;
		if (wiki.trim().length() == 0) return "";
		WikiParser parser = new WikiParser(wiki);
		WikiModel model = parser.parse();
		return model.toHtml(new ScrumHtmlContext());
	}

	public SuggestOracle createPagesSuggestOracle() {
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		for (Wikipage page : project.getWikipages()) {
			oracle.add(page.getName());
		}
		return oracle;
	}

	class RichtextEditorEditInitializer implements Initializer<RichtextEditorWidget> {

		public void initialize(final RichtextEditorWidget editor) {
			final ToolbarWidget toolbar = editor.getEditorToolbar();

			toolbar.insert(createToolbarButton(Img.bundle.upload().createImage(), "Upload file", new ClickHandler() {

				public void onClick(ClickEvent event) {
					BetterTextArea textArea = editor.getEditor();
					int topPosition = textArea.getAbsoluteTop() + 20;
					ReferenceInserter refInserter = new ReferenceInserter(textArea);
					uploader.showUploadDialog(topPosition, refInserter);
				}
			}), 0);

			toolbar.insert(createToolbarButton(Img.bundle.formatTextCode().createImage(), "Code", new ClickHandler() {

				public void onClick(ClickEvent event) {
					BetterTextArea textArea = editor.getEditor();
					textArea.wrapSelection("<code>", "</code>");
					textArea.setFocus(true);
				}
			}), 0);

			toolbar.insert(createToolbarButton(Img.bundle.image().createImage(), "Image", new ClickHandler() {

				public void onClick(ClickEvent event) {
					BetterTextArea textArea = editor.getEditor();
					String selected = textArea.getSelectedText();
					if (selected != null && (selected.startsWith("http") || selected.startsWith("www."))) {
						textArea.wrapSelection("[Image:", "|thumb]");
					} else {
						textArea.wrapSelection("[Image:http://", "|thumb]");
					}
					textArea.setFocus(true);
				}
			}), 0);

			toolbar.insert(createToolbarButton(Img.bundle.table().createImage(), "Table", new ClickHandler() {

				public void onClick(ClickEvent event) {
					BetterTextArea textArea = editor.getEditor();
					textArea.wrapSelection("\n{|\n! Header 1\n! Header 2\n|-\n| ", "\n| \n|}\n");
					textArea.setFocus(true);
				}
			}), 0);

			toolbar.insert(createToolbarButton(Img.bundle.hyperlink().createImage(), "Hyperlink", new ClickHandler() {

				public void onClick(ClickEvent event) {
					BetterTextArea textArea = editor.getEditor();
					String selected = textArea.getSelectedText();
					if (selected != null && (selected.startsWith("http") || selected.startsWith("www."))) {
						textArea.wrapSelection("[", "]");
					} else {
						textArea.wrapSelection("[http:// ", "]");
					}
					textArea.setFocus(true);
				}
			}), 0);

			toolbar.insert(createToolbarButton(Img.bundle.enumlist().createImage(), "Numbered list",
				new ClickHandler() {

					public void onClick(ClickEvent event) {
						BetterTextArea textArea = editor.getEditor();
						textArea.wrapSelection("\n# ", "\n# ");
						textArea.setFocus(true);
					}
				}), 0);

			toolbar.insert(createToolbarButton(Img.bundle.itemlist().createImage(), "Bulleted list",
				new ClickHandler() {

					public void onClick(ClickEvent event) {
						BetterTextArea textArea = editor.getEditor();
						textArea.wrapSelection("\n* ", "\n* ");
						textArea.setFocus(true);
					}
				}), 0);

			toolbar.insert(createToolbarButton(Img.bundle.formatTextBold().createImage(), "Bold", new ClickHandler() {

				public void onClick(ClickEvent event) {
					BetterTextArea textArea = editor.getEditor();
					textArea.wrapSelection("'''", "'''");
					textArea.setFocus(true);
				}
			}), 0);

			toolbar.insert(createToolbarButton(Img.bundle.formatTextItalic().createImage(), "Italic",
				new ClickHandler() {

					public void onClick(ClickEvent event) {
						BetterTextArea textArea = editor.getEditor();
						textArea.wrapSelection("''", "''");
						textArea.setFocus(true);
					}
				}), 0);

		}
	}

	protected final Widget createToolbarButton(Image icon, String tooltip, ClickHandler clickHandler) {
		PushButton button = new PushButton(icon, clickHandler);
		button.setTitle(tooltip);
		return button;
	}

	private class ReferenceInserter implements Uploader.UploadedFileHandler {

		private BetterTextArea textArea;

		public ReferenceInserter(BetterTextArea textArea) {
			super();
			this.textArea = textArea;
		}

		public void onFileUploaded(File file) {
			textArea.wrapSelection(file.getReference() + " ", "");
			textArea.setFocus(true);
		}

	}

}
