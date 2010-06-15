// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.sprint;

public class SwitchToNextSprintServiceCall extends scrum.client.core.AServiceCall {

    public  SwitchToNextSprintServiceCall() {
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().switchToNextSprint(serviceCaller.getConversationNumber(), new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "SwitchToNextSprint";
    }

}

