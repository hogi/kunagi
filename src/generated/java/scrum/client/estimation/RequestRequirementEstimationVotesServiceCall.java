// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.estimation;

public class RequestRequirementEstimationVotesServiceCall extends scrum.client.core.AServiceCall {

    private String requirementId;

    public  RequestRequirementEstimationVotesServiceCall(String requirementId) {
        this.requirementId = requirementId;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().requestRequirementEstimationVotes(serviceCaller.getConversationNumber(), requirementId, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "RequestRequirementEstimationVotes";
    }

}

