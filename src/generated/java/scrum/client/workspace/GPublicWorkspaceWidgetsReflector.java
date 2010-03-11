package scrum.client.workspace;

public class GPublicWorkspaceWidgetsReflector implements ilarkesto.core.scope.ComponentReflector<PublicWorkspaceWidgets> {

    public void injectComponents(PublicWorkspaceWidgets component, ilarkesto.core.scope.Scope scope) {
        component.app = (scrum.client.ScrumGwtApplication) scope.getComponent("app");
        component.ui = (Ui) scope.getComponent("ui");
    }

    public void callInitializationMethods(PublicWorkspaceWidgets component) {
        component.initialize();
    }

    public void outjectComponents(PublicWorkspaceWidgets component, ilarkesto.core.scope.Scope scope) {
    }

}

