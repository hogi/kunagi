package scrum.server.css;

import ilarkesto.ui.web.CssRenderer;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ScreenCssBuilder implements CssBuilder {

	// http://www.colorcombos.com/color-scheme-203.html

	String fontFamily = "Arial Unicode MS, Arial, sans-serif";
	int fontSize = 12;
	int lineHeight = 14;

	int fontSizeSmall = 9;
	int lineHeightSmall = 9;

	int fontSizeTitle = 14;
	int lineHeightTitle = 18;

	String cBackground = "#F2F5FE";

	String cHeaderBackground = "#667B99";
	String cHeaderLink = "#D6E4E1";

	String cLink = "#2956B2";
	String cErrorBackground = "#FEE";
	String cError = "darkred";
	String cHeaderText = "white";

	String cNavigatorSeparator = "#D9DEE6";
	String cNavigatorLink = "#465B79";
	String cNavigatorSelectedItemBackground = "#CCD5E6";
	String cNavigatorHoverItemBackground = "#E9EEF6";

	String cBlockHeaderBackground = cNavigatorHoverItemBackground;
	String cBlockHeaderHoverBackground = cBackground;
	String cBlockHeaderDragHandleBackground = "#FBFBFF";

	String cToolbarBackground = cNavigatorHoverItemBackground;

	String cPagePanelHeaderBackground = "#FFF7F0";
	String cPagePanelHeader = "#FF6637";
	String cPagePanelBorder = cNavigatorSeparator;

	String cChatBackground = "white";
	String cChatBorder = cPagePanelBorder;

	String cTrashBackground = cNavigatorHoverItemBackground;
	String cTrashBorder = cPagePanelBorder;

	String cFieldBackground = "#FAFDFF";

	String cWaitBackground = cFieldBackground;
	String cWait = cLink;

	String cCommentDate = "darkgray";

	String cButtonText = "#444";
	String cButtonTextHover = "#500";
	String cButtonTextDisabled = "lightgray";
	String cButtonBorder = "#666";
	String cButtonBorderHover = "#866";
	String cButtonBorderDisabled = cButtonTextDisabled;

	public void buildCss(CssRenderer css) {
		css.html().height100().padding(0).margin(0);
		css.body().height100().padding(0).margin(0).background(cBackground).fontFamily(fontFamily).fontSize(fontSize)
				.lineHeight(lineHeight);
		css.table().borderCollapseCollapse();
		css.td().verticalAlignTop().fontFamily(fontFamily).fontSize(fontSize).lineHeight(lineHeight);
		css.a().cursorPointer();

		css.style(".gwt-Hyperlink a").color(cLink);
		css.style(".gwt-Button").fontFamily(fontFamily).fontSize(fontSize).fontWeightBold().color(cButtonText).padding(
			2).margin(0).whiteSpaceNowrap().border(1, "solid", cButtonBorder);
		css.style(".gwt-Button:hover").color(cButtonTextHover).border(1, "solid", cButtonBorderHover);
		css.style(".gwt-Button[disabled], .gwt-Button[disabled]:hover").color(cButtonTextDisabled).border(1, "solid",
			cButtonBorderDisabled);

		css.style(".BugMarker").borderSolid(1, cError).background(cErrorBackground).color(cError);

		css.style(".Workspace-header").background(cHeaderBackground);

		css.style(".HeaderWidget-title").color(cHeaderText).fontSize(12).fontWeightBold().paddingLeft(5).paddingTop(3);
		css.style(".HeaderWidget-user").color(cHeaderText);
		css.style(".HeaderWidget .ToolbarWidget").background("none");
		css.style(".HeaderWidget .gwt-Hyperlink a").color(cHeaderLink);

		systemMessage(css);
		blockList(css);
		comments(css);
		navigator(css);
		chat(css);
		pagePanel(css);

		css.style(".TrashWidget").background(cTrashBackground).border(1, "solid", cTrashBorder);

		css.style(".Workspace-body-west .PagePanel").padding(0);
		css.style(".Workspace-body-west .PagePanel-header").background(cBackground).color(cHeaderText);
		css.style(".Workspace-body-west .PagePanel-content").background(cBackground).border("0");

		css.style(".ToolbarWidget").background(cToolbarBackground);

		css.style(".WhiteboardWidget-columnLabel").background(cPagePanelHeaderBackground).color(cPagePanelHeader);

		css.style(".fieldLabel").color(cHeaderBackground);
		css.style(".AFieldValueWidget").background("white").border(1, "dotted", "white");
		css.style(".FieldsWidget-fieldLabel").color(cHeaderBackground);

		css.style(".AViewEditWidget-viewer").background(cFieldBackground).border(1, "dotted",
			cNavigatorSelectedItemBackground);

		css.style(".dnd-drop-allowed").background(cHeaderBackground);

		css.style(".WidgetsTesterWidget .test-content").background("white").color("black").border(1, "solid", "#AAA");

		css.style(".highlighted .ABlockWidget-title").border(1, "solid", cError);

		css.style(".WaitWidget").background(cWaitBackground).borderTop(1, "solid", cPagePanelBorder).borderBottom(1,
			"solid", cPagePanelBorder);
		css.style(".LoginWidget-errorMessage").background(cErrorBackground).color(cError).border(1, "solid", cError);

		css.style(".AIntegerViewEditWidget .gwt-Button").padding(0, 3, 0, 3);
	}

	private void systemMessage(CssRenderer css) {
		css.style(".SystemMessageWidget-box").background(cErrorBackground).color(cError).border(1, "solid", cError)
				.padding(10).margin("10px 10px 0px 10px");
		css.style(".SystemMessageWidget-box-title").fontWeightBold().marginBottom(5);
		css.style(".SystemMessageWidget-box-time").fontStyleItalic().marginTop(5).textAlignRight();
	}

	private void chat(CssRenderer css) {
		css.style(".ChatWidget-outputScroller").background(cChatBackground).border(1, "solid", cChatBorder).padding(5);
		css.style(".ChatWidget-output .author").color("green");
		css.style(".ChatWidget-output .author-system").color("red");
		css.style(".ChatWidget-output .author-me").color("gray");
	}

	private void navigator(CssRenderer css) {
		css.style(".NavigatorWidget-head").borderBottom(1, "solid", cNavigatorSeparator);
		css.style(".NavigatorWidget .item a").borderBottom(1, "solid", cNavigatorSeparator).color(cNavigatorLink);
		css.style(".NavigatorWidget .item a:hover").background(cNavigatorHoverItemBackground);
		css.style(".NavigatorWidget .selected .item a").background(cNavigatorSelectedItemBackground);
	}

	private void comments(CssRenderer css) {
		css.style(".CommentsWidget");
		css.style(".CommentWidget").margin(5, 0, 10, 0).borderTop(1, "solid", cBlockHeaderBackground);
		css.style(".CommentWidget-header").margin(4, 0, 2, 0);
		css.style(".CommentWidget-header-author").floatLeft().marginRight(5);
		css.style(".CommentWidget-header-date").color(cCommentDate);
		css.style(".CommentWidget-editor");
	}

	private void blockList(CssRenderer css) {
		css.style(".ABlockWidget-extended").border(2, "solid", cHeaderBackground).padding(3);
		css.style(".ABlockWidget-body").padding(10).border(1, "solid", cBlockHeaderBackground);

		css.style(".BlockHeaderWidget").background(cBlockHeaderBackground);
		css.style(".BlockHeaderWidget:hover").background(cBlockHeaderHoverBackground);
		css.style(".BlockHeaderWidget-dragHandle").margin(2).padding(2).fontSize(fontSize - 1).lineHeight(
			lineHeight - 2).textAlignCenter().cursorMove().background(cBlockHeaderDragHandleBackground).border(1,
			"solid", cNavigatorSeparator).borderRadius(5);
		css.style(".BlockHeaderWidget-center").padding(2).cursorPointer().displayBlock();
		css.style(".BlockHeaderWidget-cell").padding(2);
		css.style(".BlockHeaderWidget-cell .gwt-MenuItem").fontSize(fontSizeSmall).padding(2, 3, 2, 3)
				.whiteSpaceNowrap();
		css.style(".BlockHeaderWidget-prefixLabel").cursorPointer();
		css.style(".BlockHeaderWidget-cell .gwt-Button").fontSize(fontSizeSmall).padding(2, 3, 2, 3).margin(0);

		css.style(".BlockDndMarkerWidget").background("none");
		css.style(".BlockDndMarkerWidget-active").background(cError);

		css.style(".UsersOnBlockWidget").textAlignRight();
	}

	private void pagePanel(CssRenderer css) {
		css.style(".PagePanel").padding(10);
		css.style(".PagePanel-content").background("white").border(1, "solid", cPagePanelBorder);
		css.style(".PagePanel-header").padding(6, 10, 6, 10).fontSize(fontSizeTitle).lineHeight(lineHeightTitle)
				.background(cPagePanelHeaderBackground).color(cPagePanelHeader);
		css.style(".PagePanel-header .gwt-Button").fontSize(fontSizeSmall);
		css.style(".PagePanel-section").margin(0, 10, 0, 10);
		css.style(".PagePanel-spacer").height(10);
	}

	@Override
	public String toString() {
		StringWriter out = new StringWriter();
		CssRenderer renderer = new CssRenderer(new PrintWriter(out));
		buildCss(renderer);
		renderer.flush();
		return out.toString();
	}
}
