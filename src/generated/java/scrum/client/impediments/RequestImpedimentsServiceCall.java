// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.impediments;

public class RequestImpedimentsServiceCall extends scrum.client.core.AServiceCall {

    public  RequestImpedimentsServiceCall() {
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().requestImpediments(serviceCaller.getConversationNumber(), new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "RequestImpediments";
    }

}

