// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.project;

public class CloseProjectServiceCall extends scrum.client.core.AServiceCall {

    public  CloseProjectServiceCall() {
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().closeProject(serviceCaller.getConversationNumber(), new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "CloseProject";
    }

}

