package scrum.client;

import gwtupload.client.IUploader;
import gwtupload.client.IUploadStatus.Status;
import ilarkesto.gwt.client.BetterTextArea;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.Initializer;
import ilarkesto.gwt.client.RichtextFormater;
import ilarkesto.gwt.client.ToolbarWidget;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import scrum.client.collaboration.Wikipage;
import scrum.client.common.AScrumComponent;
import scrum.client.files.File;
import scrum.client.files.UploadWidget;
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

public class Wiki extends AScrumComponent implements RichtextFormater, FileUploadedListener {

	private FileReferenceInserter fileReferenceInserter;

	@Override
	protected void onInitialization() {
		super.onInitialization();
		Gwt.setDefaultRichtextFormater(this);
		Gwt.setRichtextEditorEditInitializer(new RichtextEditorEditInitializer());
		Gwt.setDefaultRichtextSyntaxInfo(WikiParser.SYNTAX_INFO_HTML);
	}

	public void onFileUploaded(File file) {
		if (fileReferenceInserter == null) return;
		fileReferenceInserter.insertFile(file);
		fileReferenceInserter = null;
	}

	public String getTemplate(String name) {
		Wikipage page = getCurrentProject().getWikipage("template:" + name);
		return page == null ? null : page.getText();
	}

	public String richtextToHtml(String text) {
		if (Gwt.isEmpty(text)) return text;
		return toHtml(text);
	}

	public static String toHtml(String wiki) {
		WikiParser parser = new WikiParser(wiki);
		WikiModel model = parser.parse();
		return model.toHtml(new ScrumHtmlContext());
	}

	public SuggestOracle createPagesSuggestOracle() {
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		for (Wikipage page : getCurrentProject().getWikipages()) {
			oracle.add(page.getName());
		}
		return oracle;
	}

	class RichtextEditorEditInitializer implements Initializer<RichtextEditorWidget> {

		public void initialize(final RichtextEditorWidget editor) {
			final ToolbarWidget toolbar = editor.getEditorToolbar();

			toolbar.add(createToolbarButton(Img.bundle.formatTextItalic().createImage(), "Italic", new ClickHandler() {

				public void onClick(ClickEvent event) {
					BetterTextArea textArea = editor.getEditor();
					textArea.wrapSelection("''", "''");
					textArea.setFocus(true);
				}
			}));

			toolbar.add(createToolbarButton(Img.bundle.formatTextBold().createImage(), "Bold", new ClickHandler() {

				public void onClick(ClickEvent event) {
					BetterTextArea textArea = editor.getEditor();
					textArea.wrapSelection("'''", "'''");
					textArea.setFocus(true);
				}
			}));

			toolbar.add(createToolbarButton(Img.bundle.formatTextCode().createImage(), "Code", new ClickHandler() {

				public void onClick(ClickEvent event) {
					BetterTextArea textArea = editor.getEditor();
					textArea.wrapSelection("<code>", "</code>");
					textArea.setFocus(true);
				}
			}));

			toolbar.add(createToolbarButton(Img.bundle.upload().createImage(), "Upload file", new ClickHandler() {

				public void onClick(ClickEvent event) {
					final BetterTextArea textArea = editor.getEditor();
					UploadWidget.showDialog(textArea.getAbsoluteTop() + 20, new IUploader.OnFinishUploaderHandler() {

						public void onFinish(IUploader uploader) {
							if (uploader.getStatus() == Status.SUCCESS) {
								fileReferenceInserter = new FileReferenceInserter(textArea);
								cm.getApp().callPing();
							}
						}
					});
				}
			}));

		}
	}

	protected final Widget createToolbarButton(Image icon, String tooltip, ClickHandler clickHandler) {
		PushButton button = new PushButton(icon, clickHandler);
		button.setTitle(tooltip);
		return button;
	}

	private class FileReferenceInserter {

		private BetterTextArea textArea;

		public FileReferenceInserter(BetterTextArea textArea) {
			super();
			this.textArea = textArea;
		}

		public void insertFile(File file) {
			textArea.wrapSelection(file.getReference(), "");
			textArea.setFocus(true);
		}

	}

}
