// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.communication;

public class ServerDataReceivedEvent extends ilarkesto.core.event.AEvent implements ilarkesto.core.event.Quiet {

    private scrum.client.DataTransferObject data;

    public  ServerDataReceivedEvent(scrum.client.DataTransferObject data) {
        this.data = data;
    }

    public scrum.client.DataTransferObject getData() {
        return data;
    }

    public void tryToGetHandled(Object handler) {
        if (handler instanceof ServerDataReceivedHandler) {
            ((ServerDataReceivedHandler)handler).onServerDataReceived(this);
        }
    }

}

