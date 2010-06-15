// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.core;

public class RequestEntityServiceCall extends scrum.client.core.AServiceCall {

    private String entityId;

    public  RequestEntityServiceCall(String entityId) {
        this.entityId = entityId;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().requestEntity(serviceCaller.getConversationNumber(), entityId, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "RequestEntity";
    }

}

