package scrum.client.common;

import java.util.Collection;

import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ScrumUtil {

	public static final Widget createEmptyDiv(String styleName) {
		SimplePanel div = new SimplePanel();
		div.setStyleName(styleName);
		return div;
	}

	public static void addFiller(CellPanel panel) {
		Label filler = new Label("");
		panel.add(filler);
		// panel.setCellWidth(filler, "99%");
		// panel.setCellHeight(filler, "99%");
	}

	public static String toCommataSeperatedString(Collection c) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (Object o : c) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			sb.append(o);
		}
		return sb.toString();
	}

}
