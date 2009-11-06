package scrum.server.journal;


public class ProjectEvent extends GProjectEvent {

	@Override
	public String toString() {
		return getLabel();
	}

}