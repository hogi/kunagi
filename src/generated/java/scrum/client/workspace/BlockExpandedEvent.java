// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.workspace;

public class BlockExpandedEvent extends ilarkesto.core.event.AEvent {

    private Object object;

    public  BlockExpandedEvent(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void tryToGetHandled(Object handler) {
        if (handler instanceof BlockExpandedHandler) {
            log.debug("    " + handler.getClass().getName() + ".onBlockExpanded(event)");
            ((BlockExpandedHandler)handler).onBlockExpanded(this);
        }
    }

}

