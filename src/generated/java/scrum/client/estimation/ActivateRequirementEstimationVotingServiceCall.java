// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.estimation;

public class ActivateRequirementEstimationVotingServiceCall extends scrum.client.core.AServiceCall {

    private String requirementId;

    public  ActivateRequirementEstimationVotingServiceCall(String requirementId) {
        this.requirementId = requirementId;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().activateRequirementEstimationVoting(serviceCaller.getConversationNumber(), requirementId, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "ActivateRequirementEstimationVoting";
    }

}

