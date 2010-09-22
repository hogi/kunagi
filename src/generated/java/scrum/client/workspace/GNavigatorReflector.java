// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.workspace;

public class GNavigatorReflector implements ilarkesto.core.scope.ComponentReflector<Navigator> {

    public void injectComponents(Navigator component, ilarkesto.core.scope.Scope scope) {
        component.app = (scrum.client.ScrumGwtApplication) scope.getComponent("app");
        component.auth = (scrum.client.admin.Auth) scope.getComponent("auth");
        component.dao = (scrum.client.Dao) scope.getComponent("dao");
    }

    public void callInitializationMethods(Navigator component) {
        component.initialize();
    }

    public void outjectComponents(Navigator component, ilarkesto.core.scope.Scope scope) {
    }

}

