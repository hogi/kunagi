package scrum.client.impediments;

import ilarkesto.gwt.client.Date;

import java.util.Comparator;
import java.util.Map;

import scrum.client.project.Project;

public class Impediment extends GImpediment {

	public static final String INIT_LABEL = "New Impediment";
	public static final String REFERENCE_PREFIX = "imp";

	public Impediment(Project project) {
		setLabel(INIT_LABEL);
		setDate(Date.today());
		setProject(project);
	}

	public Impediment(Map data) {
		super(data);
	}

	public String getReference() {
		return REFERENCE_PREFIX + getNumber();
	}

	@Override
	public String toString() {
		return getReference() + " " + getLabel();
	}

	public static Comparator<Impediment> DATE_COMPARATOR = new Comparator<Impediment>() {

		public int compare(Impediment a, Impediment b) {
			return a.getDate().compareTo(b.getDate());
		}
	};

	public static Comparator<Impediment> REVERSE_DATE_COMPARATOR = new Comparator<Impediment>() {

		public int compare(Impediment a, Impediment b) {
			return DATE_COMPARATOR.compare(b, a);
		}
	};
}
