package scrum.server.sprint;

import scrum.server.ScrumWebApplication;
import scrum.server.common.Numbered;
import scrum.server.project.Project;

public class Task extends GTask implements Numbered {

	public String getReferenceAndLabel() {
		return getReference() + " (" + getLabel() + ")";
	}

	public String getReference() {
		return scrum.client.sprint.Task.REFERENCE_PREFIX + getNumber();
	}

	public boolean isProject(Project project) {
		return getRequirement().isProject(project);
	}

	public boolean isClosed() {
		return getRemainingWork() == 0;
	}

	public boolean isSprint(Sprint sprint) {
		return getRequirement().isSprint(sprint);
	}

	public void updateNumber() {
		if (getNumber() == 0) setNumber(getRequirement().getProject().generateTaskNumber());
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();
		updateNumber();

		// renaming: req -> sto
		if (ScrumWebApplication.REQ_RENAMING_DATE.isAfter(getLastModified())) {
			setDescription(ScrumWebApplication.convertReqToSto(getDescription()));
		}
	}

	@Override
	public String toString() {
		return getReferenceAndLabel();
	}

}
