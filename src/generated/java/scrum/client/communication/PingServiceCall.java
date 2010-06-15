// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.communication;

public class PingServiceCall extends scrum.client.core.AServiceCall {

    public  PingServiceCall() {
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().ping(serviceCaller.getConversationNumber(), new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "Ping";
    }

}

