package scrum.client.impediments;

import ilarkesto.gwt.client.Date;

import java.util.Comparator;
import java.util.Map;

import scrum.client.ScrumJs;
import scrum.client.project.Project;

public class Impediment extends GImpediment {

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
		return ScrumJs.createShowEntityByReferenceLink(getReference()) + " " + getLabel();
	}

	@Override
	public String toString() {
		return getReference() + " " + getLabel();
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
