package scrum.client.workspace;

import ilarkesto.core.base.Str;
import scrum.client.common.AScrumWidget;

import com.google.gwt.user.client.ui.Widget;

public class Page {

	private String name;
	private AScrumWidget widget;
	private String label;
	private String groupKey;

	public Page(String name, AScrumWidget widget, String label, String groupKey) {
		super();
		this.name = name;
		this.widget = widget;
		this.label = label;
		this.groupKey = groupKey;
	}

	public Page(AScrumWidget widget, String label, String groupKey) {
		this(getPageName(widget), widget, label, groupKey);
	}

	public String getLabel() {
		return label;
	}

	public String getName() {
		return name;
	}

	public AScrumWidget getWidget() {
		return widget;
	}

	public String getGroupKey() {
		return groupKey;
	}

	@Override
	public String toString() {
		return name;
	}

	public static String getPageName(Widget page) {
		String name = Str.getSimpleName(page.getClass());
		return name.substring(0, name.length() - 6);
	}

}
