package scrum.client.workspace;

import ilarkesto.gwt.client.Gwt;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PagePanel extends Composite implements HasWidgets {

	private FlowPanel content;

	public PagePanel() {
		content = new FlowPanel();
		initWidget(Gwt.createDiv("PagePanel", Gwt.createDiv("PagePanel-content", content)));
	}

	public void addHeader(String text) {
		addHeader(new Label(text));
	}

	public void addHeader(Widget widget) {
		add("header", widget);
	}

	public void addSection(String text) {
		addSection(new Label(text));
	}

	public void addSection(Widget widget) {
		add("section", widget);
	}

	public void add(String styleSuffix, Widget widget) {
		add(Gwt.createDiv("PagePanel-" + styleSuffix, widget));
	}

	public void add(Widget w) {
		content.add(w);
	}

	public boolean remove(Widget w) {
		return content.remove(w);
	}

	public void clear() {
		content.clear();
	}

	public Iterator<Widget> iterator() {
		return content.iterator();
	}

}
