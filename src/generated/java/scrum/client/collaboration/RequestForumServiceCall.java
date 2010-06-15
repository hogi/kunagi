// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.collaboration;

public class RequestForumServiceCall extends scrum.client.core.AServiceCall {

    public  RequestForumServiceCall() {
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().requestForum(serviceCaller.getConversationNumber(), new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "RequestForum";
    }

}

