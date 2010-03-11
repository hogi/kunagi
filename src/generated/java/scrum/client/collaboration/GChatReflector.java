package scrum.client.collaboration;

public class GChatReflector implements ilarkesto.core.scope.ComponentReflector<Chat> {

    public void injectComponents(Chat component, ilarkesto.core.scope.Scope scope) {
        component.auth = (scrum.client.admin.Auth) scope.getComponent("auth");
        component.dao = (scrum.client.Dao) scope.getComponent("dao");
    }

    public void callInitializationMethods(Chat component) {
    }

    public void outjectComponents(Chat component, ilarkesto.core.scope.Scope scope) {
    }

}

