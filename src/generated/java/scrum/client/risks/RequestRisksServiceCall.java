// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.risks;

public class RequestRisksServiceCall extends scrum.client.core.AServiceCall {

    public  RequestRisksServiceCall() {
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().requestRisks(serviceCaller.getConversationNumber(), new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "RequestRisks";
    }

}

