package scrum.client.project;

import java.util.ArrayList;
import java.util.List;

public class EstimationBarFactory {

	public static void createEstimationBars(List<Requirement> requirements, Integer velocity) {
		int sprintOffset = 0;
		int remainingWorkInSprint = velocity == null ? 0 : velocity;
		List<Integer> workPerSprint;
		for (Requirement requirement : requirements) {
			workPerSprint = new ArrayList<Integer>();
			if (velocity == null || requirement.getEstimatedWork() == null) {
				requirement.setEstimationBar(new EstimationBar(sprintOffset, workPerSprint));
				continue;
			}
			int work = requirement.getEstimatedWork();
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
			requirement.setEstimationBar(new EstimationBar(sprintOffset, workPerSprint));
		}
	}
}
