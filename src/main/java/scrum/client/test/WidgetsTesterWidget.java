package scrum.client.test;

import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.ImageAnchor;
import ilarkesto.gwt.client.MultiSelectionWidget;
import ilarkesto.gwt.client.NavigatorWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.common.FieldsWidget;
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

		testFields();
		testMultiSelection();
		testNavigator();
		testToolbars();
		// testButtons();
		// testImageAnchor();

		return panel;
	}

	private String fieldsText = "test";

	private void testFields() {
		FieldsWidget fields = new FieldsWidget();
		fields.add("ATextViewEditWidget", new ATextViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(fieldsText);
			}

			@Override
			protected void onEditorUpdate() {
				setEditorText(fieldsText);
			}

			@Override
			protected void onEditorSubmit() {
				fieldsText = getEditorText();
			}
		});
		fields.update();
		addTest("FieldsWidget", fields);
	}

	private void testMultiSelection() {
		MultiSelectionWidget<String> ms = new MultiSelectionWidget<String>();
		ms.add("Item 1");
		ms.add("Item 2");
		ms.add("Item 3");
		ms.update();
		addTest("MultiSelectionWidget", ms);
	}

	private void testNavigator() {
		NavigatorWidget navigator = new NavigatorWidget();
		navigator.addItem(Img.bundle.test16(), "Item 1", "1", null);
		navigator.addItem(Img.bundle.test16(), "Item 2", "2", null);
		navigator.addItem(Img.bundle.test16(), "Item 3", "3", null);
		addTest("NavigatorWidget", navigator);
	}

	private void testToolbars() {
		ToolbarWidget vertical = new ToolbarWidget();
		vertical.add(new ButtonWidget(Img.bundle.test16().createImage(), "icon and text"));
		vertical.add(new ButtonWidget("text only"));
		vertical.add(new ButtonWidget(Img.bundle.test16().createImage(), null));
		addTest("ToolbarWidget:vertical", vertical);

		ToolbarWidget horizontal = new ToolbarWidget(true);
		horizontal.add(new ButtonWidget(Img.bundle.test16().createImage(), "icon and text"));
		horizontal.add(new ButtonWidget("text only"));
		horizontal.add(new ButtonWidget(Img.bundle.test16().createImage(), null));
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

	private void testImageAnchor() {
		ImageAnchor a = new ImageAnchor(Img.bundle.test16().createImage(), "click");
		addTest("ImageAnchor", a);
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
