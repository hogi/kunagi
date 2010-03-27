package scrum.client.impediments;

import ilarkesto.gwt.client.Date;
import ilarkesto.gwt.client.HyperlinkWidget;

import java.util.Comparator;
import java.util.Map;

import scrum.client.ScrumGwt;
import scrum.client.collaboration.ForumSupport;
import scrum.client.common.ReferenceSupport;
import scrum.client.common.ShowEntityAction;
import scrum.client.project.Project;

import com.google.gwt.user.client.ui.Widget;

public class Impediment extends GImpediment implements ReferenceSupport, ForumSupport {

	public static final String REFERENCE_PREFIX = "imp";

	public Impediment(Project project) {
		setDate(Date.today());
		setProject(project);
	}

	public Impediment(Map data) {
		super(data);
	}

	public boolean isOpen() {
		return !isClosed();
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	@Override
	public String toHtml() {
		return ScrumGwt.toHtml(getReference(), getLabel());
	}

	@Override
	public String toString() {
		return getReference() + " " + getLabel();
	}

	public Widget createForumItemWidget() {
		return new HyperlinkWidget(new ShowEntityAction(this, getLabel()));
	}

	public static final Comparator<Impediment> DATE_COMPARATOR = new Comparator<Impediment>() {

		public int compare(Impediment a, Impediment b) {
			return a.getDate().compareTo(b.getDate());
		}
	};

	public static final Comparator<Impediment> REVERSE_DATE_COMPARATOR = new Comparator<Impediment>() {

		public int compare(Impediment a, Impediment b) {
			return DATE_COMPARATOR.compare(b, a);
		}
	};
}
