// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.admin;

public class UpdateSystemMessageServiceCall extends scrum.client.core.AServiceCall {

    private scrum.client.admin.SystemMessage systemMessage;

    public  UpdateSystemMessageServiceCall(scrum.client.admin.SystemMessage systemMessage) {
        this.systemMessage = systemMessage;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().updateSystemMessage(serviceCaller.getConversationNumber(), systemMessage, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "UpdateSystemMessage";
    }

}

