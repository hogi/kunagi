// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.core;

public class RequestEntityByReferenceServiceCall extends scrum.client.core.AServiceCall {

    private String reference;

    public  RequestEntityByReferenceServiceCall(String reference) {
        this.reference = reference;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().requestEntityByReference(serviceCaller.getConversationNumber(), reference, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "RequestEntityByReference";
    }

}

