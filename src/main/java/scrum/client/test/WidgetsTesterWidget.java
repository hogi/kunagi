package scrum.client.test;

import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.NavigatorWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.img.Img;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class WidgetsTesterWidget extends AWidget {

	private FlowPanel panel;

	@Override
	protected Widget onInitialization() {
		panel = new FlowPanel();
		panel.setStyleName("WidgetsTesterWidget");

		testNavigator();
		testToolbars();
		testButtons();

		return panel;
	}

	private void testNavigator() {
		NavigatorWidget navigator = new NavigatorWidget();
		navigator.addItem(Img.bundle.test16(), "Item 1", null);
		navigator.addItem(Img.bundle.test16(), "Item 2", null);
		navigator.addItem(Img.bundle.test16(), "Item 3", null);
		addTest("NavigatorWidget", navigator);
	}

	private void testToolbars() {
		ToolbarWidget vertical = new ToolbarWidget();
		vertical.add(new ButtonWidget("Button 1"));
		vertical.add(new ButtonWidget("Button 2"));
		vertical.add(new ButtonWidget("Button 3"));
		addTest("ToolbarWidget:vertical", vertical);

		ToolbarWidget horizontal = new ToolbarWidget(true);
		horizontal.add(new ButtonWidget("Button 1"));
		horizontal.add(new ButtonWidget("Button 2"));
		horizontal.add(new ButtonWidget("Button 3"));
		addTest("ToolbarWidget:horizontal", horizontal);
	}

	private void testButtons() {
		addTest("ButtonWidget:text-only", new ButtonWidget("text only"));
		addTest("ButtonWidget:icon-only", new ButtonWidget(Img.bundle.test16().createImage(), null));
		addTest("ButtonWidget:icon-text", new ButtonWidget(Img.bundle.test16().createImage(), "icon and text"));

		FlowPanel multipleButtons = new FlowPanel();
		multipleButtons.add(new ButtonWidget("Button 1").update());
		multipleButtons.add(new ButtonWidget("Button 2").update());
		multipleButtons.add(new ButtonWidget("Button 3").update());
		addTest("multiple ButtonWidgets", multipleButtons);
	}

	@Override
	protected void onUpdate() {

	}

	private void addTest(String title, Widget content) {
		if (content instanceof AWidget) ((AWidget) content).update();

		SimplePanel sectionContent = new SimplePanel();
		sectionContent.setStyleName("test-content");
		sectionContent.setWidget(content);

		FlowPanel section = new FlowPanel();
		section.setStyleName("test-section");
		section.add(new Label(title));
		section.add(sectionContent);
		panel.add(section);
	}

}
