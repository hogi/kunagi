package scrum.client.project;

import java.util.ArrayList;
import java.util.List;

public class EstimationBarFactory {

	public void createEstimationBars(List<Requirement> requirements, int velocity) {
		assert velocity > 0;
		int sprintOffset = 0;
		int remainingWorkInSprint = velocity;
		List<Integer> workPerSprint;
		for (Requirement requirement : requirements) {
			workPerSprint = new ArrayList<Integer>();
			int work = requirement.getEstimatedWork() == null ? 0 : requirement.getEstimatedWork();
			while (work > remainingWorkInSprint) {
				workPerSprint.add(remainingWorkInSprint);
				work -= remainingWorkInSprint;
				sprintOffset++;
				remainingWorkInSprint = velocity;
			}
			if (work > 0) {
				workPerSprint.add(work);
				remainingWorkInSprint -= work;
			}
			if (remainingWorkInSprint == 0) {
				sprintOffset++;
				remainingWorkInSprint = velocity;
			}
			requirement
					.setEstimationBar(new EstimationBar(sprintOffset, requirement.getEstimatedWork(), workPerSprint));
		}
	}

}
