package scrum.client.collaboration;

import java.util.Comparator;
import java.util.Map;

import scrum.client.ScrumJs;
import scrum.client.project.Project;

public class Subject extends GSubject {

	public static final String REFERENCE_PREFIX = "sbj";

	public Subject(Project project) {
		setProject(project);
	}

	public Subject(Map data) {
		super(data);
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

	public static final Comparator<Subject> COMPARATOR = new Comparator<Subject>() {

		public int compare(Subject a, Subject b) {
			return a.toString().compareTo(b.toString());
		}
	};
}