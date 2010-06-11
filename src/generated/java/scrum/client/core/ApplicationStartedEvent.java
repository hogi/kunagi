// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.core;

public class ApplicationStartedEvent extends ilarkesto.core.event.AEvent {

    public  ApplicationStartedEvent() {
    }

    public void tryToGetHandled(Object handler) {
        if (handler instanceof ApplicationStartedHandler) {
            log.debug("    " + handler.getClass().getName() + ".onApplicationStarted(event)");
            ((ApplicationStartedHandler)handler).onApplicationStarted(this);
        }
    }

}

