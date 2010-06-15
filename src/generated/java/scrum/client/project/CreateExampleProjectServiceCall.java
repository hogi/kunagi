// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.project;

public class CreateExampleProjectServiceCall extends scrum.client.core.AServiceCall {

    public  CreateExampleProjectServiceCall() {
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().createExampleProject(serviceCaller.getConversationNumber(), new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "CreateExampleProject";
    }

}

