// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.collaboration;

public class RequestForumServiceCall extends scrum.client.core.AServiceCall {

    private boolean all;

    public  RequestForumServiceCall(boolean all) {
        this.all = all;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().requestForum(serviceCaller.getConversationNumber(), all, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "RequestForum";
    }

}

