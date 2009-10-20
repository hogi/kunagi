package scrum.server.css;

import ilarkesto.ui.web.CssRenderer;

public class ScreenCssBuilder implements CssBuilder {

	// http://www.colorcombos.com/color-scheme-203.html

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

	String cBlockTitleBackground = cBackground;
	String cBlockTitleBackgroundGreen = "#CEFDC5";
	String cBlockTitleBackgroundRed = "#FDCEC5";
	String cBlockTitleBackgroundGrey = "#DEDEDE";

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

	public void buildCss(CssRenderer css) {
		css.body().background(cBackground).fontFamily("Helvetica");

		css.style(".gwt-Hyperlink a").color(cLink);
		css.style(".gwt-Button").fontFamily("Helvetica").fontSize(10).padding(2).margin(0).whiteSpaceNowrap();

		css.style(".BugMarker").borderSolid(1, cError).background(cErrorBackground).color(cError);

		css.style(".Workspace-header").background(cHeaderBackground);

		css.style(".HeaderWidget-title").color(cHeaderText).fontSize(12).fontWeightBold().paddingLeft(5).paddingTop(3);
		css.style(".HeaderWidget-user").color(cHeaderText);
		css.style(".HeaderWidget .ToolbarWidget").background("none");
		css.style(".HeaderWidget .gwt-Hyperlink a").color(cHeaderLink);

		css.style(".NavigatorWidget-head").borderBottom(1, "solid", cNavigatorSeparator);
		css.style(".NavigatorWidget .item a").borderBottom(1, "solid", cNavigatorSeparator).color(cNavigatorLink);
		css.style(".NavigatorWidget .item a:hover").background(cNavigatorHoverItemBackground);
		css.style(".NavigatorWidget .selected .item a").background(cNavigatorSelectedItemBackground);

		css.style(".TrashWidget").background(cTrashBackground).border(1, "solid", cTrashBorder);

		css.style(".ChatWidget-outputScroller").background(cChatBackground).border(1, "solid", cChatBorder).padding(5);
		css.style(".ChatWidget-output .author").color("green");
		css.style(".ChatWidget-output .author-system").color("red");
		css.style(".ChatWidget-output .author-me").color("gray");

		css.style(".CommentWidget-header-date").color("#333");

		css.style(".PagePanel-header").background(cPagePanelHeaderBackground).color(cPagePanelHeader);
		css.style(".PagePanel-content").background("white").border(1, "solid", cPagePanelBorder);

		css.style(".Workspace-body-west .PagePanel").padding(0);
		css.style(".Workspace-body-west .PagePanel-header").background(cBackground).color(cHeaderText);
		css.style(".Workspace-body-west .PagePanel-content").background(cBackground).border("0");

		css.style(".ToolbarWidget").background(cToolbarBackground);

		css.style(".BlockDndMarkerWidget-active").background(cHeaderBackground);

		css.style(".ABlockWidget-block").background("white");
		css.style(".ABlockWidget-block-selected").border("0");
		css.style(".ABlockWidget-title").background(cBlockTitleBackground);
		css.style(".ABlockWidget-content-expanded").border(1, "solid", cBlockTitleBackground);

		css.style(".RequirementBlock-closed .ABlockWidget-title").background(cBlockTitleBackgroundGreen);
		css.style(".RequirementBlock-inCurrentSprint .ABlockWidget-title").background(cBlockTitleBackgroundRed);
		css.style(".RequirementBlock-invalidForSprint .ABlockWidget-title").background(cBlockTitleBackgroundGrey);

		css.style(".TaskBlock-taskClosed .ABlockWidget-title").background(cBlockTitleBackgroundGreen);

		css.style(".RequirementInSprintBlock-done .ABlockWidget-title").background(cBlockTitleBackgroundGreen);
		css.style(".TaskInRequirementBlock-taskClosed .ABlockWidget-title").background(cBlockTitleBackgroundGreen);

		css.style(".ImpedimentBlock-open .ABlockWidget-title").background(cBlockTitleBackgroundRed);
		css.style(".ImpedimentBlock-closed .ABlockWidget-title").background(cBlockTitleBackgroundGrey);

		css.style(".WhiteboardWidget-columnLabel").background(cPagePanelHeaderBackground).color(cPagePanelHeader);
		css.style(".WhiteboardWidget-open .ABlockWidget-title").background(cBlockTitleBackgroundRed);
		css.style(".WhiteboardWidget-owned .ABlockWidget-title ").background(cBlockTitleBackgroundGrey);
		css.style(".WhiteboardWidget-done .ABlockWidget-title").background(cBlockTitleBackgroundGreen);

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

		css.style(".SystemMessageWidget-box").background(cErrorBackground).color(cError).border(1, "solid", cError)
				.padding(10).margin("10px 10px 0px 10px");
		css.style(".SystemMessageWidget-box-title").fontWeightBold().marginBottom(5);
		css.style(".SystemMessageWidget-box-time").fontStyleItalic().marginTop(5).textAlignRight();

		css.style(".CommentsWidget").marginTop(10);
		css.style(".CommentWidget").margin(5, 0, 15, 0).borderTop(1, "solid", cCommentDate);
		css.style(".CommentWidget-header").margin(7, 0, 4, 0);
		css.style(".CommentWidget-header-author").floatLeft().marginRight(5);
		css.style(".CommentWidget-header-date").color(cCommentDate).fontSize(11);
		css.style(".CommentWidget-editor");

		css.style(".AIntegerViewEditWidget .gwt-Button").padding(0, 3, 0, 3);
	}
}
