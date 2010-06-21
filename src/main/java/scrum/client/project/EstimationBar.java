package scrum.client.project;

import ilarkesto.gwt.client.Gwt;

import java.util.List;

public class EstimationBar {

	private int sprintOffset;
	private List<Float> workPerSprint;

	public EstimationBar(int sprintOffset, List<Float> workPerSprint) {
		super();
		this.sprintOffset = sprintOffset;
		this.workPerSprint = workPerSprint;
	}

	public int getSprintOffset() {
		return sprintOffset;
	}

	public int getEndSprintOffset() {
		int offset = workPerSprint.isEmpty() ? 0 : workPerSprint.size() - 1;
		return sprintOffset + offset;
	}

	public List<Float> getWorkPerSprint() {
		return workPerSprint;
	}

	public boolean isCompetedOnSameSprint(EstimationBar previous) {
		return getEndSprintOffset() == previous.getEndSprintOffset();
	}

	@Override
	public String toString() {
		return "EstimationBar(" + sprintOffset + ", " + Gwt.toString(workPerSprint) + ")";
	}

}
