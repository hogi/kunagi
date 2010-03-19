package scrum.client.journal;

import ilarkesto.gwt.client.DateAndTime;

import java.util.Comparator;
import java.util.Map;

import scrum.client.collaboration.Wiki;
import scrum.client.project.Project;

public class ProjectEvent extends GProjectEvent {

	public ProjectEvent(Map data) {
		super(data);
	}

	public ProjectEvent(Project project, String label) {
		setDateAndTime(DateAndTime.now());
		setProject(project);
		setLabel(label);
	}

	@Override
	public boolean isEditable() {
		return false;
	}

	@Override
	public String toHtml() {
		return Wiki.toHtml(getLabel());
	}

	@Override
	public String toString() {
		return getLabel();
	}

	public static final Comparator<ProjectEvent> DATE_AND_TIME_COMPARATOR = new Comparator<ProjectEvent>() {

		public int compare(ProjectEvent a, ProjectEvent b) {
			return b.getDateAndTime().compareTo(a.getDateAndTime());
		}
	};

}
