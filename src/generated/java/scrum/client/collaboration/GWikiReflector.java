package scrum.client.collaboration;

public class GWikiReflector implements ilarkesto.core.scope.ComponentReflector<Wiki> {

    public void injectComponents(Wiki component, ilarkesto.core.scope.Scope scope) {
    }

    public void callInitializationMethods(Wiki component) {
        component.initialize();
    }

    public void outjectComponents(Wiki component, ilarkesto.core.scope.Scope scope) {
    }

}

