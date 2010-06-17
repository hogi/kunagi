// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.project;

public class ProjectDataReceivedEvent extends ilarkesto.core.event.AEvent {

    public  ProjectDataReceivedEvent() {
    }

    public void tryToGetHandled(Object handler) {
        if (handler instanceof ProjectDataReceivedHandler) {
            log.debug("    " + handler.getClass().getName() + ".onProjectDataReceived(event)");
            ((ProjectDataReceivedHandler)handler).onProjectDataReceived(this);
        }
    }

}

