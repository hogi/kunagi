package scrum.client.project;

import java.util.List;

public class EstimationBar {

	private int sprintOffset;
	private int totalWork;
	private List<Integer> workPerSprint;

	public EstimationBar(int sprintOffset, int totalWork, List<Integer> workPerSprint) {
		super();
		this.sprintOffset = sprintOffset;
		this.totalWork = totalWork;
		this.workPerSprint = workPerSprint;
	}

	public int getSprintOffset() {
		return sprintOffset;
	}

	public int getTotalWork() {
		return totalWork;
	}

	public List<Integer> getWorkPerSprint() {
		return workPerSprint;
	}

}
