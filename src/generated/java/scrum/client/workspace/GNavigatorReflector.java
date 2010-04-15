package scrum.client.workspace;

public class GNavigatorReflector implements ilarkesto.core.scope.ComponentReflector<Navigator> {

    public void injectComponents(Navigator component, ilarkesto.core.scope.Scope scope) {
        component.auth = (scrum.client.admin.Auth) scope.getComponent("auth");
        component.dao = (scrum.client.Dao) scope.getComponent("dao");
        component.projectWorkspaceWidgets = (ProjectWorkspaceWidgets) scope.getComponent("projectWorkspaceWidgets");
    }

    public void callInitializationMethods(Navigator component) {
        component.initialize();
    }

    public void outjectComponents(Navigator component, ilarkesto.core.scope.Scope scope) {
    }

}

