package scrum.client.test;

import ilarkesto.gwt.client.ATextViewEditWidget;
import ilarkesto.gwt.client.AWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.ImageAnchor;
import ilarkesto.gwt.client.MultiSelectionWidget;
import ilarkesto.gwt.client.NavigatorWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import scrum.client.common.AExtensibleBlockWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.img.Img;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class WidgetsTesterWidget extends AWidget {

	private FlowPanel panel;

	@Override
	protected Widget onInitialization() {
		panel = new FlowPanel();
		panel.setStyleName("WidgetsTesterWidget");

		testBlockList();
		testFields();
		testMultiSelection();
		testNavigator();
		testToolbars();
		// testButtons();
		// testImageAnchor();

		return panel;
	}

	private void testBlockList() {
		final BlockListWidget<String> list = new BlockListWidget<String>(TestBlock.FACTORY);
		list.addBlock("Element A");
		list.addBlock("Element B");
		list.addBlock("Element C");
		list.addBlock("Element D");
		list.addBlock("Element E");
		list.update();
		addTest("test", new Button("click", new ClickHandler() {

			public void onClick(ClickEvent event) {
				// list.addBlock("new " + System.currentTimeMillis());
				// list.removeRow(5);
				list.move(list.getBlock(4), 3);
			}
		}));
		addTest("BlockList", list);
	}

	private static class TestBlock extends AExtensibleBlockWidget<String> {

		private String text = "";

		@Override
		protected String getObject() {
			return text;
		}

		@Override
		protected void onCollapsedInitialization() {}

		@Override
		protected void onExtendedInitialization() {}

		@Override
		protected void onHeadUpdate() {
			setBlockTitle(text);
			setIcon(Img.bundle.project16());
			createToolbar();
		}

		@Override
		protected void onContentUpdate() {
			HTML content = new HTML(
					"<h3>"
							+ text
							+ "</h3><p>Das ist der Content. Das ist der Content. Das ist der Content. Das ist der Content. </p>");
			setContent(content);
			setIcon(Img.bundle.project16());
		}

		private void createToolbar() {
			addToolbarCommand("Action 1", new Command() {

				public void execute() {}
			});
			addToolbarCommand("Action 2", new Command() {

				public void execute() {}
			});
			addMenuCommand("Action 3", new Command() {

				public void execute() {}
			});
			addMenuCommand("Action 4", new Command() {

				public void execute() {}
			});
			addMenuCommand("Action 5", new Command() {

				public void execute() {}
			});
		}

		@Override
		protected void setObject(String object) {
			text = object;
		}

		@Override
		public String toString() {
			return text;
		}

		public static BlockWidgetFactory<String> FACTORY = new BlockWidgetFactory<String>() {

			public TestBlock createBlock() {
				return new TestBlock();
			}
		};

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
