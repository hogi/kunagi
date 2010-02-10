package scrum.client.collaboration;

import ilarkesto.gwt.client.HyperlinkWidget;

import java.util.Map;

import scrum.client.common.ShowEntityAction;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.Widget;

public class Wikipage extends GWikipage implements ForumSupport {

	public Wikipage(Project project, String name) {
		setProject(project);
		setName(name);
		setText("New Wiki Page...");
	}

	public Wikipage(Map data) {
		super(data);
	}

	@Override
	public String toHtml() {
		return cm.getWiki().richtextToHtml("[[" + getName() + "]]");
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getLabel() {
		return getName();
	}

	public String getReference() {
		return "wki";
	}

	public Widget createForumItemWidget() {
		return new HyperlinkWidget(new ShowEntityAction(this, getLabel()));
	}

}
