// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.workspace;

public class VisibleDataChangedEvent extends ilarkesto.core.event.AEvent {

    public  VisibleDataChangedEvent() {
    }

    public void tryToGetHandled(Object handler) {
        if (handler instanceof VisibleDataChangedHandler) {
            log.debug("    " + handler.getClass().getName() + ".onVisibleDataChanged(event)");
            ((VisibleDataChangedHandler)handler).onVisibleDataChanged(this);
        }
    }

}

