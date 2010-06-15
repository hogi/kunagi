// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.communication;

public class StartConversationServiceCall extends scrum.client.core.AServiceCall {

    public  StartConversationServiceCall() {
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().startConversation(serviceCaller.getConversationNumber(), new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "StartConversation";
    }

}

