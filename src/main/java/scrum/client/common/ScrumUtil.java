package scrum.client.common;

import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.Label;

public class ScrumUtil {

	public static void addFiller(CellPanel panel) {
		Label filler = new Label("");
		panel.add(filler);
		panel.setCellWidth(filler, "99%");
		panel.setCellHeight(filler, "99%");
	}

}
