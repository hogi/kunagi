// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.admin;

public class UserLoggedInEvent extends ilarkesto.core.event.AEvent {

    public  UserLoggedInEvent() {
    }

    public void tryToGetHandled(Object handler) {
        if (handler instanceof UserLoggedInHandler) {
            log.debug("    " + handler.getClass().getName() + ".onUserLoggedIn(event)");
            ((UserLoggedInHandler)handler).onUserLoggedIn(this);
        }
    }

}

