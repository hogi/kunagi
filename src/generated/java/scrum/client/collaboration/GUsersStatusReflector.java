// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.collaboration;

public class GUsersStatusReflector implements ilarkesto.core.scope.ComponentReflector<UsersStatus> {

    public void injectComponents(UsersStatus component, ilarkesto.core.scope.Scope scope) {
        component.app = (scrum.client.ScrumGwtApplication) scope.getComponent("app");
        component.auth = (scrum.client.admin.Auth) scope.getComponent("auth");
        component.project = (scrum.client.project.Project) scope.getComponent("project");
    }

    public void callInitializationMethods(UsersStatus component) {
    }

    public void outjectComponents(UsersStatus component, ilarkesto.core.scope.Scope scope) {
    }

}

