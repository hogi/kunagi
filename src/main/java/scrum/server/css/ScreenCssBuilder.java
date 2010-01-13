package scrum.server.css;

import ilarkesto.ui.web.CssRenderer;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ScreenCssBuilder implements CssBuilder {

	// http://www.colorcombos.com/color-scheme-203.html

	String fontFamily = "Arial Unicode MS, Arial, sans-serif";
	int fontSize = 12;
	int lineHeight = 16;

	int fontSizeSmall = 9;
	int lineHeightSmall = 9;

	int fontSizeTitle = 14;
	int lineHeightTitle = 18;

	String cBackground = "#F2F5FE";

	String cHeaderBackground = "#667B99";
	String cHeaderLink = "#D6E4E1";

	String cEstimationBar0 = cHeaderBackground;
	String cEstimationBar1 = "#669976";

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
	String cBlockHeaderCellSecondary = "gray";

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

	String cButtonText = "#444";
	String cButtonTextHover = "#500";
	String cButtonTextDisabled = "lightgray";
	String cButtonBorder = "#666";
	String cButtonBorderHover = "#866";
	String cButtonBorderDisabled = cButtonTextDisabled;

	String cCommentsBackground = "#F8FFF8";
	String cCommentsBorder = "#EAFFEA";
	String cCommentDate = "darkgray";

	String cPlanningPokerTableLines = "#EEE";

	public void buildCss(CssRenderer css) {
		html(css);
		gwt(css);
		ilarkesto(css);
		systemMessage(css);
		blockList(css);
		comments(css);
		workspace(css);
		navigator(css);
		chat(css);
		pagePanel(css);
		whiteboard(css);
		dashboard(css);
		calendar(css);
		planningPoker(css);

		css.style(".TrashWidget").background(cTrashBackground).border(1, "solid", cTrashBorder).padding(5);

		css.style(".ToolbarWidget").background(cToolbarBackground).padding(3, 0, 3, 3);
		css.style(".ToolbarWidget .FloatingFlowPanel-element-left").marginRight(3);
		css.style(".ToolbarWidget .FloatingFlowPanel-element-right").marginLeft(3);

		css.style(".fieldLabel").color(cHeaderBackground).lineHeight(22).whiteSpaceNowrap();
		css.style(".AFieldValueWidget").background("white").border(1, "dotted", "white").minWidth(16).minHeight(16)
				.displayBlock().padding(3);
		css.style(".FieldsWidget-fieldLabel").color(cHeaderBackground).lineHeight(22).whiteSpaceNowrap();

		css.style(".AViewEditWidget-viewer").background(cFieldBackground).border(1, "dotted",
			cNavigatorSelectedItemBackground);
		css.style(".AViewEditWidget-error").color(cError).background(cErrorBackground).border(1, "solid", cError)
				.margin(2).padding(2);

		css.style(".dnd-drop-allowed").background(cHeaderBackground);

		css.style(".WidgetsTesterWidget .test-content").background("white").color("black").border(1, "solid", "#AAA");

		css.style(".highlighted .ABlockWidget-title").border(1, "solid", cError);

		css.style(".WaitWidget").background(cWaitBackground).borderTop(1, "solid", cPagePanelBorder).borderBottom(1,
			"solid", cPagePanelBorder).margin(200, 0, 200, 0).fontSize(fontSize + 2);
		css.style(".LoginWidget-errorMessage").background(cErrorBackground).color(cError).border(1, "solid", cError)
				.fontSize(fontSize + 2).padding(5);

		css.style(".PunishmentsWidget-tableHeader").padding(10).fontSize(fontSizeTitle).lineHeight(lineHeightTitle);

		css.style(".AViewEditWidget-viewer").cursorPointer().minWidth(16).minHeight(16).displayBlock().padding(3);
		css.style(".ARichtextViewEditWidget-editor").height(100).width(96, "%");
		css.style(".AEditableTextareaWidget-editorPanel").width100();
		css.style(".Integer-editor").width(10, "%");

		css.style(".AIntegerViewEditWidget .gwt-Button").padding(0, 3, 0, 3).fontSize(fontSizeSmall);

		css.style(".TaskRemainingWorkWidget").marginLeft(3);
		css.style(".TaskRemainingWorkWidget .gwt-Button").fontSize(fontSizeSmall);

		css.style(".RequirementEstimatedWorkWidget").marginLeft(3);
		css.style(".RequirementEstimatedWorkWidget .gwt-Button").fontSize(fontSizeSmall);

		css.style(".EstimationBarWidget").marginTop(7).border(1, "solid", cNavigatorSelectedItemBackground);
		css.style(".EstimationBarWidget-bar0").background(cEstimationBar0).lineHeight(1).fontSize(1);
		css.style(".EstimationBarWidget-bar1").background(cEstimationBar1).lineHeight(1).fontSize(1);

		css.style(".SprintBorderIndicatorWidget").background(cPagePanelHeaderBackground).color(cPagePanelHeader)
				.border(1, "solid", cPagePanelBorder).textAlignCenter().borderRadius(10).fontSize(fontSizeSmall)
				.margin(3, 100, 3, 100);

		css.style("ul.toc");// .displayInline().floatRight();
	}

	private void planningPoker(CssRenderer css) {
		css.style(".PlanningPokerWidget-table-border").background("#333").border(2, "solid", "#2A2A2A").padding(12)
				.borderRadius(60).marginBottom(10);
		css.style(".PlanningPokerWidget-table").border(2, "solid", "#2A2A2A").borderRadius(45).background("#5A5")
				.padding(40);
		css.style(".PlanningPokerWidget-table-branding").color(cPlanningPokerTableLines).fontFamily("Times New Roman")
				.fontWeightBold().fontSize(30).textAlignCenter().marginBottom(30);

		int cardWidth = 40;
		int cardHeight = 60;
		css.style(".PlanningPokerCardSlotWidget-slot").width(cardWidth - 8).height(cardHeight - 8).border(5, "solid",
			cPlanningPokerTableLines).borderRadius(5);
		css.style(".PlanningPokerCardSlotWidget-name").color(cPlanningPokerTableLines).fontSize(fontSizeSmall)
				.textAlignCenter();

		css.style(".PlanningPokerCardWidget").borderRadius(5).width(cardWidth).height(cardHeight).background("#FFF")
				.border(1, "solid", "#333");
		css.style(".PlanningPokerCardWidget-clickable").cursorPointer();
		css.style(".PlanningPokerCardWidget-text").fontSize(23).lineHeight(58).fontFamily("Times New Roman")
				.textAlignCenter();

	}

	private void calendar(CssRenderer css) {
		css.style(".DateSelectorWidget").background("white").textAlignCenter().border(1, "solid", "white");
		css.style(".DateSelectorWidget-weekday").background("white").textAlignCenter().color(cBlockHeaderCellSecondary)
				.border(1, "solid", "white");
		css.style(".DateSelectorWidget-weeknumber").background("white").color(cBlockHeaderCellSecondary).width(20)
				.border(1, "solid", "white");
		css.style(".DateSelectorWidget-spacer").background("white").textAlignCenter().height(20).border(1, "solid",
			"white");
		css.style(".DateSelectorWidget-selected").background(cNavigatorHoverItemBackground).textAlignCenter().border(1,
			"solid", cNavigatorHoverItemBackground);
		css.style(".DateSelectorWidget-visible").background(cBackground).textAlignCenter().border(1, "solid",
			cBackground);
		css.style(".DateSelectorWidget-today").border(1, "solid", cNavigatorSelectedItemBackground);
		css.style(".DateSelectorWidget-events").color("red").fontSize(fontSizeSmall).lineHeight(lineHeightSmall)
				.textAlignCenter();
		css.style(".DayListWidget-date").color(cBlockHeaderCellSecondary);
		css.style(".DayListWidget-date-info").padding(2);
		css.style(".DayListWidget-week").color(cBlockHeaderCellSecondary);
		css.style(".DayListWidget-month").color(cBlockHeaderCellSecondary);
	}

	private void dashboard(CssRenderer css) {
		css
				.style(
					".TeamTasksWidget, .UpcomingTasksWidget, .OpenImpedimentsWidget, .HighestRisksWidget, .LatestEventsWidget td")
				.lineHeight(lineHeight + 4);
		css.style(".LatestEventsWidget td").borderTop(1, "solid", cBlockHeaderBackground).padding(3);
		css.style(".LatestEventsWidget table").borderBottom(1, "solid", cBlockHeaderBackground);
		css.style(".LatestEventsWidget-time").whiteSpaceNowrap().color(cCommentDate);
	}

	private void whiteboard(CssRenderer css) {
		css.style(".WhiteboardWidget-columnLabel").padding(10).fontSize(14);
		css.style(".WhiteboardWidget-open").padding(5);
		css.style(".WhiteboardWidget-owned").padding(5);
		css.style(".WhiteboardWidget-done").padding(5);
		css.style(".WhiteboardWidget-columnLabel").background(cPagePanelHeaderBackground).color(cPagePanelHeader);
		css.style(".WhiteboardWidget-requirement-list").padding(0).minHeight(5, "px");
	}

	private void ilarkesto(CssRenderer css) {
		css.style(".AWidget-height100").height100();
		css.style(".ImageAnchor img").floatLeft().marginRight(3);
		css.style(".ImageAnchor .text").displayInline();

		css.style(".FloatingFlowPanel-element-left").floatLeft();
		css.style(".FloatingFlowPanel-element-right").floatRight();
		css.style(".floatClear").clearBoth();

		css.style(".BugMarker").borderSolid(1, cError).background(cErrorBackground).color(cError).displayBlock()
				.margin(3).padding(3).fontWeightBold().fontSize(fontSizeSmall);

		css.style(".Tooltip").background(cBackground).padding(10).border(1, "solid", cPagePanelBorder);
	}

	private void gwt(CssRenderer css) {
		css.style(".gwt-Hyperlink a").whiteSpaceNowrap();
		css.style(".gwt-Button").fontFamily(fontFamily).fontSize(fontSize).fontWeightBold().color(cButtonText).padding(
			2).margin(0).whiteSpaceNowrap().border(1, "solid", cButtonBorder);
		css.style(".gwt-Button:hover").color(cButtonTextHover).border(1, "solid", cButtonBorderHover);
		css.style(".gwt-Button[disabled], .gwt-Button[disabled]:hover").color(cButtonTextDisabled).border(1, "solid",
			cButtonBorderDisabled);
		css.style(".MenuItem-disabled").color(cButtonTextDisabled);
	}

	private void html(CssRenderer css) {
		css.html().height100().padding(0).margin(0);
		css.body().height100().padding(0).margin(0).background(cBackground).fontFamily(fontFamily).fontSize(fontSize)
				.lineHeight(lineHeight);
		css.table().borderCollapseCollapse();
		css.td().verticalAlignTop().fontFamily(fontFamily).fontSize(fontSize).lineHeight(lineHeight);
		css.a().cursorPointer().color(cLink).textDecorationUnderline();
		css.p().margin(0, 0, 10, 0);
		css.h1().fontSize(fontSize + 4).lineHeight(lineHeight + 4).fontWeightBold().margin(5, 0, 5, 0);
		css.h2().fontSize(fontSize + 2).lineHeight(lineHeight + 2).fontWeightBold().margin(5, 0, 5, 0);
		css.h3().fontSize(fontSize + 1).lineHeight(lineHeight + 1).fontWeightBold().margin(0, 0, 5, 0);
		css.h4().fontSize(fontSize).lineHeight(lineHeight).fontWeightBold().margin(0, 0, 5, 0);
		css.pre().margin(0, 0, 10, 0).color(cHeaderBackground).fontSize(fontSize).lineHeight(lineHeight);
		css.code().color(cHeaderBackground).fontSize(fontSize).lineHeight(lineHeight);
		css.ul().margin(0, 0, 10, 0).padding(0, 0, 0, 20);
		css.ol().margin(0, 0, 10, 0).padding(0, 0, 0, 20);
		css.img().border("0");
	}

	private void workspace(CssRenderer css) {
		css.style(".Workspace").height100();
		css.style(".Workspace-header").height(25).background(cHeaderBackground);
		css.style(".Workspace-body").height100();
		css.style(".Workspace-body-west").floatLeft().width(200).height100();
		css.style(".Workspace-body-center").height100();
		css.style(".Workspace-body-center-content").padding(10);
		css.style(".Workspace-body-west .PagePanel").padding(0);
		css.style(".Workspace-body-west .PagePanel-header").background(cBackground).color(cHeaderText);
		css.style(".Workspace-body-west .PagePanel-content").background(cBackground).border("0");

		css.style(".HeaderWidget-title").color(cHeaderText).fontSize(12).fontWeightBold().paddingLeft(5).paddingTop(3);
		css.style(".HeaderWidget-user").color(cHeaderText).fontSize(12).textAlignCenter().marginTop(3).marginRight(5);
		css.style(".HeaderWidget .ToolbarWidget").background("none").margin(0).textAlignRight();
		css.style(".HeaderWidget .ToolbarWidget .FloatingFlowPanel-element").floatRight();
		css.style(".HeaderWidget .gwt-Hyperlink a").color(cHeaderLink);
		css.style(".HeaderWidget .UndoButtonWidget .gwt-MenuItem").fontSize(fontSizeSmall).padding(0, 3, 0, 3);

		css.style(".SearchInputWidget input").fontSize(fontSizeSmall).lineHeight(lineHeightSmall).margin(0, 10, 0, 10)
				.padding(1);
	}

	private void systemMessage(CssRenderer css) {
		css.style(".SystemMessageWidget-box").background(cErrorBackground).color(cError).border(1, "solid", cError)
				.padding(10).margin("10px 10px 0px 10px");
		css.style(".SystemMessageWidget-box-title").fontWeightBold().marginBottom(5);
		css.style(".SystemMessageWidget-box-time").fontStyleItalic().marginTop(5).textAlignRight();
	}

	private void chat(CssRenderer css) {
		css.style(".ChatWidget-outputScroller").background(cChatBackground).border(1, "solid", cChatBorder).padding(5);
		css.style(".ChatWidget-output .author").color("green").fontStyleItalic();
		css.style(".ChatWidget-output .author-system").color("red").fontStyleItalic();
		css.style(".ChatWidget-output .author-me").color("gray").fontStyleItalic();
		css.style(".ChatWidget-output a").textDecorationUnderline();
		css.style(".ChatWidget-message").margin(0, 0, 0, 5);
		css.style(".ChatWidget-input").marginTop(5).width(97, "%");
	}

	private void navigator(CssRenderer css) {
		css.style(".NavigatorWidget-head").borderBottom(1, "solid", cNavigatorSeparator);
		css.style(".NavigatorWidget .item a").borderBottom(1, "solid", cNavigatorSeparator).color(cNavigatorLink)
				.displayBlock().padding(5, 3, 5, 3).textDecorationNone();
		css.style(".NavigatorWidget .item a:hover").background(cNavigatorHoverItemBackground);
		css.style(".NavigatorWidget .selected .item a").background(cNavigatorSelectedItemBackground);
	}

	private void comments(CssRenderer css) {
		css.style(".CommentsWidget").background(cCommentsBackground).border(1, "solid", cCommentsBorder).padding(7)
				.borderRadius(10);
		css.style(".CommentWidget").margin(15, 0, 10, 0).borderTop(1, "solid", cBlockHeaderBackground);
		css.style(".CommentWidget-header").margin(4, 0, 2, 0);
		css.style(".CommentWidget-header-author").floatLeft().marginRight(5);
		css.style(".CommentWidget-header-date").color(cCommentDate);
		css.style(".CommentWidget-editor");
	}

	private void blockList(CssRenderer css) {
		css.style(".ABlockWidget-extended").border(2, "solid", cHeaderBackground).padding(3);
		css.style(".ABlockWidget-body").padding(10).border(1, "solid", cBlockHeaderBackground);

		css.style(".BlockHeaderWidget").background(cBlockHeaderBackground).minHeight(50, "px");
		css.style(".BlockHeaderWidget:hover").background(cBlockHeaderHoverBackground);
		css.style(".BlockHeaderWidget-dragHandle").margin(2).padding(2).fontSize(fontSize - 1).lineHeight(
			lineHeight - 2).textAlignCenter().cursorMove().background(cBlockHeaderDragHandleBackground).border(1,
			"solid", cNavigatorSeparator).borderRadius(5);
		css.style(".BlockHeaderWidget-center").padding(2).cursorPointer();
		css.style(".BlockHeaderWidget-center-text").fontWeightBold().color(cHeaderBackground);
		css.style(".BlockHeaderWidget-centerSuffix").marginLeft(5).color(cBlockHeaderCellSecondary).fontSize(
			fontSizeSmall);
		css.style(".BlockHeaderWidget-cell").padding(2);
		css.style(".BlockHeaderWidget-cell-secondary").color(cBlockHeaderCellSecondary);
		css.style(".BlockHeaderWidget-cell .gwt-MenuItem").fontSize(fontSizeSmall).padding(0, 3, 0, 3)
				.whiteSpaceNowrap();
		css.style(".BlockHeaderWidget-prefixLabel"); // .cursorPointer();
		css.style(".BlockHeaderWidget-cell .gwt-Button").fontSize(fontSizeSmall).padding(2, 3, 2, 3).margin(0);

		css.style(".BlockDndMarkerWidget").background("none");
		css.style(".BlockDndMarkerWidget-active").background(cError);

		css.style(".UsersOnBlockWidget").textAlignRight();

		css.style(".UserStatusWidget").textDecorationLineThrough().fontStyleItalic();
		css.style(".UserStatusWidget-online").textDecorationNone().fontStyleNormal();

	}

	private void pagePanel(CssRenderer css) {
		css.style(".PagePanel");// .padding(10);
		css.style(".PagePanel-content").background("white").border(1, "solid", cPagePanelBorder);
		css.style(".PagePanel-header").padding(6, 10, 6, 10).fontSize(fontSizeTitle).lineHeight(lineHeightTitle)
				.background(cPagePanelHeaderBackground).color(cPagePanelHeader);
		css.style(".PagePanel-header .gwt-Button").fontSize(fontSizeSmall);
		css.style(".PagePanel-header input").fontSize(fontSizeSmall);
		css.style(".PagePanel-header .HyperlinkWidget").fontSize(fontSizeSmall);
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
