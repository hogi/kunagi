// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.admin;

public class GAuthReflector implements ilarkesto.core.scope.ComponentReflector<Auth> {

    public void injectComponents(Auth component, ilarkesto.core.scope.Scope scope) {
        component.app = (scrum.client.ScrumGwtApplication) scope.getComponent("app");
        component.dao = (scrum.client.Dao) scope.getComponent("dao");
        component.pinger = (scrum.client.communication.Pinger) scope.getComponent("pinger");
        component.ui = (scrum.client.workspace.Ui) scope.getComponent("ui");
    }

    public void callInitializationMethods(Auth component) {
    }

    public void outjectComponents(Auth component, ilarkesto.core.scope.Scope scope) {
    }

}

