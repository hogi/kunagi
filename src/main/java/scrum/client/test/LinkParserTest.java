package scrum.client.test;

import ilarkesto.gwt.client.Gwt;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;

public class LinkParserTest {

	public static void main(String[] args) {
		String s = "Hallo Welt! Einmal den Task t21 ansehen. Und beachte Impediment i33.";
		System.out.println(s);
		System.out.println(parseEntityLinks(s));
	}

	public static final String[] PREFIXES = new String[] { "t", "i" };

	/**
	 * Ermittelt aus einem String Entity-IDs (t21, i33,..) und macht daraus (momentan Dummy-)Links.
	 */
	public static String parseEntityLinks(String s) {
		if (Gwt.isEmpty(s)) { return s; }

		for (String pre : PREFIXES) {

			int j = -1;
			while ((j = s.indexOf(pre, j)) > -1) {

				int id = -1;
				int k = 1;
				while (j + k < s.length()) {

					String sid = s.substring(j + pre.length(), j + k + pre.length());
					try {
						id = Integer.parseInt(sid);
					} catch (NumberFormatException e) {
						if (id > -1) {

							// String testUseAnchorInstead = new String("<" + pre + id + ">");
							// s = s.substring(0, j) + testUseAnchorInstead + s.substring(j + k + pre.length()
							// - 1);
							// j = j + testUseAnchorInstead.length();

							// Anchor >>>
							Anchor anchor = new Anchor(pre + id);
							anchor.addClickHandler(new EntityIdClickHandler());

							System.out.println(anchor.getHref());
							System.out.println(anchor.getHTML());
							System.out.println(anchor.getElement().getInnerHTML());

							// TODO
							String link = "<a href=\"\">" + anchor.getHTML() + "</a>";
							s = s.substring(0, j) + link + s.substring(j + k + pre.length() - 1);
							j = j + link.length();
						} else {
							j++;
						}
						break;
					}

					k++;
				}

			}

		}

		return s;
	}

}

class EntityIdClickHandler implements ClickHandler {

	public void onClick(ClickEvent event) {
		System.out.println(event.getSource());
	}

}