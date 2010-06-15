// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.issues;

public class RequestClosedIssuesServiceCall extends scrum.client.core.AServiceCall {

    public  RequestClosedIssuesServiceCall() {
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().requestClosedIssues(serviceCaller.getConversationNumber(), new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "RequestClosedIssues";
    }

}

