package scrum.client.collaboration;

import ilarkesto.core.scope.Scope;
import scrum.client.admin.User;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ChatWidget extends AScrumWidget {

	private Chat chat;

	private HTML output;
	private ScrollPanel outputScroller;
	private TextBox input;

	@Override
	protected Widget onInitialization() {
		chat = Scope.get().getComponent(Chat.class);

		output = new HTML();
		output.setStyleName("ChatWidget-output");

		outputScroller = new ScrollPanel(output);
		outputScroller.setStyleName("ChatWidget-outputScroller");
		outputScroller.setHeight("100px");

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
		for (ChatMessage m : chat.getChatMessages()) {
			appendMessage(m, sb);
		}
		output.setHTML(sb.toString());
		outputScroller.scrollToBottom();
	}

	private void postMessage() {
		String text = input.getText();
		if (text.trim().length() == 0) return;
		chat.postMessage(text);
		input.setText("");
		input.setFocus(true);
	}

	private void appendMessage(ChatMessage m, StringBuilder sb) {
		User author = m.getAuthor();
		String authorLabel = author != null ? author.getName() : "System";
		String authorClass = author != null ? (author.equals(getCurrentUser()) ? "author-me" : "author")
				: "author-system";
		String authorStyle = author != null ? "color: " + author.getProjectConfig().getColor() + ";" : "";
		String text = m.getText();
		sb.append("<p class='").append(authorClass).append("' style='").append(authorStyle).append(
			" margin-bottom: 2px;'>").append(authorLabel).append(", ").append(
			m.getDateAndTime().getTime().toString(false)).append("</p>");
		sb.append("<div class='ChatWidget-message'>");
		sb.append(Wiki.toHtml(text));
		sb.append("</div>");
	}
}
