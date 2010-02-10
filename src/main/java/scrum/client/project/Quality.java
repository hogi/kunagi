package scrum.client.project;

import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.HyperlinkWidget;

import java.util.Map;

import scrum.client.ScrumJs;
import scrum.client.collaboration.ForumSupport;
import scrum.client.common.ReferenceSupport;
import scrum.client.common.ShowEntityAction;
import scrum.client.issues.Issue;

import com.google.gwt.user.client.ui.Widget;

public class Quality extends GQuality implements ReferenceSupport, ForumSupport {

	public static final String REFERENCE_PREFIX = "qlt";

	public Quality(Project project) {
		setProject(project);
	}

	public Quality(Issue issue) {
		setProject(issue.getProject());
		setLabel(issue.getLabel());
		setDescription(issue.getDescription());
	}

	public Quality(Map data) {
		super(data);
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	@Override
	public String toHtml() {
		return ScrumJs.createShowEntityByReferenceLink(getReference()) + " " + Gwt.escapeHtml(getLabel());
	}

	@Override
	public String toString() {
		return getReference() + " " + getLabel();
	}

	@Override
	public boolean isEditable() {
		if (!cm.getProjectContext().getProject().isProductOwner(cm.getAuth().getUser())) return false;
		return true;
	}

	public Widget createForumItemWidget() {
		return new HyperlinkWidget(new ShowEntityAction(this, getLabel()));
	}

}
