package scrum.client.project;

import ilarkesto.gwt.client.Gwt;

import java.util.List;

public class EstimationBar {

	private int sprintOffset;
	private List<Integer> workPerSprint;

	public EstimationBar(int sprintOffset, List<Integer> workPerSprint) {
		super();
		this.sprintOffset = sprintOffset;
		this.workPerSprint = workPerSprint;
	}

	public int getSprintOffset() {
		return sprintOffset;
	}

	public int getEndSprintOffset() {
		return sprintOffset + workPerSprint.size() - 1;
	}

	public List<Integer> getWorkPerSprint() {
		return workPerSprint;
	}

	public boolean isCompetedOnSameSprint(EstimationBar previous) {
		if (workPerSprint.isEmpty()) return true;
		if (workPerSprint.size() == 1 && workPerSprint.get(0) == 0) return true;
		return getEndSprintOffset() == previous.getEndSprintOffset();
	}

	@Override
	public String toString() {
		return "EstimationBar(" + sprintOffset + ", " + Gwt.toString(workPerSprint) + ")";
	}

}
