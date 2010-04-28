package scrum.client.release;

import ilarkesto.gwt.client.HyperlinkWidget;

import java.util.Map;

import scrum.client.ScrumGwt;
import scrum.client.collaboration.ForumSupport;
import scrum.client.common.ReferenceSupport;
import scrum.client.common.ShowEntityAction;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.Widget;

public class Release extends GRelease implements ReferenceSupport, ForumSupport {

	public static final String REFERENCE_PREFIX = "rel";

	public Release(Project project) {
		setLabel("Next Release");
		setProject(project);
	}

	public Release(Map data) {
		super(data);
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	public Widget createForumItemWidget() {
		return new HyperlinkWidget(new ShowEntityAction(this, getLabel()));
	}

	@Override
	public String toHtml() {
		return ScrumGwt.toHtml(getReference(), getLabel());
	}

	@Override
	public String toString() {
		return super.getLabel();
	}

}
