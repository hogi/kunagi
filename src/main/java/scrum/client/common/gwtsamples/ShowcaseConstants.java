/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package scrum.client.common.gwtsamples;

import scrum.client.common.gwtsamples.content.i18n.CwConstantsExample;
import scrum.client.common.gwtsamples.content.i18n.CwConstantsWithLookupExample;
import scrum.client.common.gwtsamples.content.i18n.CwDateTimeFormat;
import scrum.client.common.gwtsamples.content.i18n.CwDictionaryExample;
import scrum.client.common.gwtsamples.content.i18n.CwMessagesExample;
import scrum.client.common.gwtsamples.content.i18n.CwNumberFormat;
import scrum.client.common.gwtsamples.content.lists.CwListBox;
import scrum.client.common.gwtsamples.content.lists.CwMenuBar;
import scrum.client.common.gwtsamples.content.lists.CwStackPanel;
import scrum.client.common.gwtsamples.content.lists.CwSuggestBox;
import scrum.client.common.gwtsamples.content.lists.CwTree;
import scrum.client.common.gwtsamples.content.other.CwAnimation;
import scrum.client.common.gwtsamples.content.other.CwCookies;
import scrum.client.common.gwtsamples.content.other.CwFrame;
import scrum.client.common.gwtsamples.content.panels.CwAbsolutePanel;
import scrum.client.common.gwtsamples.content.panels.CwDecoratorPanel;
import scrum.client.common.gwtsamples.content.panels.CwDisclosurePanel;
import scrum.client.common.gwtsamples.content.panels.CwDockPanel;
import scrum.client.common.gwtsamples.content.panels.CwFlowPanel;
import scrum.client.common.gwtsamples.content.panels.CwHorizontalPanel;
import scrum.client.common.gwtsamples.content.panels.CwHorizontalSplitPanel;
import scrum.client.common.gwtsamples.content.panels.CwTabPanel;
import scrum.client.common.gwtsamples.content.panels.CwVerticalPanel;
import scrum.client.common.gwtsamples.content.panels.CwVerticalSplitPanel;
import scrum.client.common.gwtsamples.content.popups.CwBasicPopup;
import scrum.client.common.gwtsamples.content.popups.CwDialogBox;
import scrum.client.common.gwtsamples.content.tables.CwFlexTable;
import scrum.client.common.gwtsamples.content.tables.CwGrid;
import scrum.client.common.gwtsamples.content.text.CwBasicText;
import scrum.client.common.gwtsamples.content.text.CwRichText;
import scrum.client.common.gwtsamples.content.widgets.CwBasicButton;
import scrum.client.common.gwtsamples.content.widgets.CwCheckBox;
import scrum.client.common.gwtsamples.content.widgets.CwCustomButton;
import scrum.client.common.gwtsamples.content.widgets.CwFileUpload;
import scrum.client.common.gwtsamples.content.widgets.CwHyperlink;
import scrum.client.common.gwtsamples.content.widgets.CwRadioButton;

import com.google.gwt.i18n.client.Constants;

/**
 * Constants used throughout the showcase.
 */
public interface ShowcaseConstants extends Constants,
    ContentWidget.CwConstants, CwCheckBox.CwConstants,
    CwRadioButton.CwConstants, CwBasicButton.CwConstants,
    CwCustomButton.CwConstants, CwListBox.CwConstants,
    CwSuggestBox.CwConstants, CwTree.CwConstants, CwMenuBar.CwConstants,
    CwFlowPanel.CwConstants, CwDisclosurePanel.CwConstants,
    CwTabPanel.CwConstants, CwDockPanel.CwConstants,
    CwHorizontalPanel.CwConstants, CwHorizontalSplitPanel.CwConstants,
    CwVerticalPanel.CwConstants, CwVerticalSplitPanel.CwConstants,
    CwBasicPopup.CwConstants, CwDialogBox.CwConstants, CwGrid.CwConstants,
    CwFlexTable.CwConstants, CwBasicText.CwConstants, CwRichText.CwConstants,
    CwFileUpload.CwConstants, CwAbsolutePanel.CwConstants,
    CwHyperlink.CwConstants, CwFrame.CwConstants, CwStackPanel.CwConstants,
    CwCookies.CwConstants, CwNumberFormat.CwConstants,
    CwDateTimeFormat.CwConstants, CwMessagesExample.CwConstants,
    CwConstantsExample.CwConstants, CwConstantsWithLookupExample.CwConstants,
    CwDictionaryExample.CwConstants, CwDecoratorPanel.CwConstants,
    CwAnimation.CwConstants {

  /**
   * The path to source code for examples, raw files, and style definitions.
   */
  String DST_SOURCE = "gwtShowcaseSource/";

  /**
   * The destination folder for parsed source code from Showcase examples.
   */
  String DST_SOURCE_EXAMPLE = DST_SOURCE + "java/";

  /**
   * The destination folder for raw files that are included in entirety.
   */
  String DST_SOURCE_RAW = DST_SOURCE + "raw/";

  /**
   * The destination folder for parsed CSS styles used in Showcase examples.
   */
  String DST_SOURCE_STYLE = DST_SOURCE + "css/";

  /**
   * Link to GWT homepage.
   */
  String GWT_HOMEPAGE = "http://code.google.com/webtoolkit/";

  /**
   * Link to GWT examples page.
   */
  String GWT_EXAMPLES = GWT_HOMEPAGE + "examples/";

  /**
   * The available style themes that the user can select.
   */
  String[] STYLE_THEMES = {"standard", "chrome", "dark"};

  String categoryI18N();

  String categoryLists();

  String categoryOther();

  String categoryPanels();

  String categoryPopups();

  String categoryTables();

  String categoryTextInput();

  String categoryWidgets();

  /**
   * @return text for the link to more examples
   */
  String mainLinkExamples();

  /**
   * @return text for the link to the GWT homepage
   */
  String mainLinkHomepage();

  /**
   * @return the title of the main menu
   */
  String mainMenuTitle();

  /**
   * @return the sub title of the application
   */
  String mainSubTitle();

  /**
   * @return the title of the application
   */
  String mainTitle();
}
