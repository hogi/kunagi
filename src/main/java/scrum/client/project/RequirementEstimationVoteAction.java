package scrum.client.project;

public class RequirementEstimationVoteAction extends GRequirementEstimationVoteAction {

	private Integer estimatedWork;

	public RequirementEstimationVoteAction(scrum.client.project.Requirement requirement, java.lang.Integer estimatedWork) {
		super(requirement, estimatedWork);
		this.estimatedWork = estimatedWork;
	}

	@Override
	public String getLabel() {
		return String.valueOf(estimatedWork.intValue());
	}

	@Override
	public String getDisabledLabel() {
		return String.valueOf(estimatedWork.intValue());
	}

	@Override
	protected void onExecute() {
		requirement.setVote(estimatedWork);
	}

	@Override
	public boolean isExecutable() {
		return requirement.isWorkEstimationVotingShowoff() == false;
	}

}