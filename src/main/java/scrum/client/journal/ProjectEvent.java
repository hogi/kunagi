package scrum.client.journal;

import java.util.Comparator;
import java.util.Map;

public class ProjectEvent extends GProjectEvent {

	public ProjectEvent(Map data) {
		super(data);
	}

	@Override
	public boolean isEditable() {
		return false;
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
