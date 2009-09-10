package scrum.client.collaboration;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.admin.User;
import scrum.client.workspace.SidebarWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ChatWidget extends AWidget {

	private HTML output;
	private ScrollPanel outputScroller;
	private TextBox input;

	@Override
	protected Widget onInitialization() {
		output = new HTML();
		output.setStyleName("ChatWidget-output");

		outputScroller = new ScrollPanel(output);
		outputScroller.setStyleName("ChatWidget-outputScroller");
		outputScroller.setHeight("200px");

		input = new TextBox();
		input.setStyleName("ChatWidget-input");
		input.addKeyboardListener(new KeyboardListenerAdapter() {

			@Override
			public void onKeyPress(Widget sender, char keyCode, int modifiers) {
				super.onKeyPress(sender, keyCode, modifiers);
				if (keyCode == KeyboardListener.KEY_ENTER) postMessage();
			}

		});

		FlowPanel panel = new FlowPanel();
		panel.setStyleName("ChatWidget");
		panel.add(outputScroller);
		panel.add(input);
		return panel;
	}

	@Override
	protected void onUpdate() {
		StringBuilder sb = new StringBuilder();
		for (ChatMessage m : ScrumGwtApplication.get().getChatMessages()) {
			appendMessage(m, sb);
		}
		output.setHTML(sb.toString());
		outputScroller.scrollToBottom();
		input.setFocus(true);
	}

	private void postMessage() {
		String text = input.getText();
		text = text.trim();
		if (text.length() == 0) return;
		ScrumGwtApplication.get().postMessage(text);
		input.setText("");
	}

	private void appendMessage(ChatMessage m, StringBuilder sb) {
		User author = m.getAuthor();
		String authorLabel = author != null ? author.getName() : "System";
		String authorStyle = author != null ? (author.equals(ScrumGwtApplication.get().getUser()) ? "author-me"
				: "author") : "author-system";
		String text = m.getText();
		String html = ScrumGwtApplication.get().textToHtml(text);
		sb.append("<p class='message'>");
		sb.append("<span class='").append(authorStyle).append("'>").append(authorLabel).append(":&nbsp;</span>");
		sb.append("<span class='text'>").append(html).append("</span>");
		sb.append("</p>");
	}

	public static ChatWidget get() {
		return SidebarWidget.get().getChat();
	}

}
