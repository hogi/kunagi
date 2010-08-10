// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.admin;

public class SendTestEmailServiceCall extends scrum.client.core.AServiceCall {

    public  SendTestEmailServiceCall() {
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().sendTestEmail(serviceCaller.getConversationNumber(), new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "SendTestEmail";
    }

}

