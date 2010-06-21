package scrum.client.project;

import java.util.ArrayList;
import java.util.List;

public class EstimationBarFactory {

	public static void createEstimationBars(List<Requirement> requirements, Integer velocity) {
		int sprintOffset = 0;
		float remainingWorkInSprint = velocity == null ? 0 : velocity;
		List<Float> workPerSprint;
		boolean stop = false;
		for (Requirement requirement : requirements) {
			workPerSprint = new ArrayList<Float>();

			Float estimatedWork = requirement.getEstimatedWork();
			if (estimatedWork == null && !stop) {
				stop = true;
				sprintOffset++;
			}

			if (velocity == null || velocity == 0 || estimatedWork == null) {
				if (estimatedWork != null) workPerSprint.add(estimatedWork);
				requirement.setEstimationBar(new EstimationBar(sprintOffset, workPerSprint));
				continue;
			}
			float work = estimatedWork;
			while (work > remainingWorkInSprint) {
				workPerSprint.add(remainingWorkInSprint);
				work -= remainingWorkInSprint;
				if (!stop) sprintOffset++;
				remainingWorkInSprint = velocity;
			}
			if (work > 0) {
				workPerSprint.add(work);
				remainingWorkInSprint -= work;
			}
			requirement.setEstimationBar(new EstimationBar(sprintOffset - workPerSprint.size() + 1, workPerSprint));
			if (remainingWorkInSprint == 0) {
				if (!stop) sprintOffset++;
				remainingWorkInSprint = velocity;
			}
		}

	}
}
