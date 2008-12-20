package scrum.client.common.editable;

import scrum.client.common.StyleSheet;
import scrum.client.gwtsamples.content.text.RichTextToolbar;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for a editable long text widget.
 * 
 * In view mode this widget displays a label for the value. In edit mode a <code>TextArea</code> is used as
 * editor to allow editing the value.
 */
public abstract class AEditableTextareaWidget extends AEditableWidget {

	private Label viewer;

	private VerticalPanel editorPanel;
	private RichTextArea editor;
	private RichTextToolbar editorToolbar;
	private boolean showToolbar;

	/**
	 * Provide the value for view mode and edit mode.
	 */
	protected abstract String getText();

	/**
	 * Set the value inputed by the user.
	 */
	protected abstract void setText(String text);

	public AEditableTextareaWidget() {
		this(false);
	}

	public AEditableTextareaWidget(boolean showToolbar) {
		this.showToolbar = showToolbar;

		viewer = new Label();
		viewer.addClickListener(new ViewerClickListener());
		viewer.setStyleName(StyleSheet.VIEWER);
		rebuild();
	}

	@Override
	protected Widget getEditor() {
		if (editorPanel == null) {
			String text = getText();
			editor = new RichTextArea();
			editor.ensureDebugId("richtext-id");

			editor.setStyleName(StyleSheet.TEXTAREA_EDITOR);
			editor.setHTML(text);
			editor.addKeyboardListener(new EditorKeyboardListener());
			editorToolbar = new RichTextToolbar(editor);

			Button applyButton = new Button("Apply");
			applyButton.addClickListener(new ApplyListener());
			Button cancelButton = new Button("Cancel");
			cancelButton.addClickListener(new CancelListener());

			HorizontalPanel toolbar = new HorizontalPanel();
			toolbar.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
			toolbar.setSpacing(2);
			toolbar.add(applyButton);
			toolbar.add(cancelButton);

			editorPanel = new VerticalPanel();
			editorPanel.setStyleName(StyleSheet.A_EDITABLE_TEXTAREA_WIDGET_EDITOR_PANEL);
			VerticalPanel vpanel = new VerticalPanel();
			if (showToolbar) {
				vpanel.add(editorToolbar);
			}
			vpanel.add(editor);
			editorPanel.add(vpanel);
			editorPanel.add(toolbar);
		}
		return editorPanel;
	}

	@Override
	protected Widget getViewer() {

		String text = getText();

		if (text != null && text.length() > 45) {
			text = text.substring(0, 45);
			text += "...";
		}
		viewer.setText(text);
		return viewer;
	}

	private class ViewerClickListener implements ClickListener {

		public void onClick(Widget sender) {
			setEditMode(true);
		}

	}

	private class ApplyListener implements ClickListener {

		public void onClick(Widget sender) {
			setText(editor.getText());
			setEditMode(false);

		}
	}

	private class CancelListener implements ClickListener {

		public void onClick(Widget sender) {
			setEditMode(false);
		}
	}

	private class EditorKeyboardListener extends KeyboardListenerAdapter {

		@Override
		public void onKeyPress(Widget sender, char keyCode, int modifiers) {
			if (keyCode == KeyboardListener.KEY_ESCAPE) {
				setEditMode(false);
			}
		}

	}
}
