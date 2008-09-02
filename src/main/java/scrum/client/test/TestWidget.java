package scrum.client.test;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Label;

public class TestWidget extends Composite {

	public TestWidget() {
		Label left = new Label("left");
		Label right = new Label("right");

		HorizontalSplitPanel split = new HorizontalSplitPanel();
		split.setLeftWidget(left);
		split.setRightWidget(right);
		split.setSplitPosition("50px");

		initWidget(split);
	}
}
