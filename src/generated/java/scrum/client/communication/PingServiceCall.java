// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.communication;

public class PingServiceCall implements ilarkesto.core.service.ServiceCall {

    public  PingServiceCall() {
    }

    public void execute(Runnable returnHandler) {
        scrum.client.ScrumGwtApplication.get().callPing(returnHandler);
    }

    public void execute() {
        execute(null);
    }

}

