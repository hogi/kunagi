// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.core;

public class SleepServiceCall extends scrum.client.core.AServiceCall {

    private long millis;

    public  SleepServiceCall(long millis) {
        this.millis = millis;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().sleep(serviceCaller.getConversationNumber(), millis, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "Sleep";
    }

}

