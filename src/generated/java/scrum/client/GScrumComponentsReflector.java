package scrum.client;

public class GScrumComponentsReflector implements ilarkesto.core.scope.ComponentReflector {

    protected ilarkesto.core.scope.ComponentReflector chatReflector = createChatReflector();

    protected ilarkesto.core.scope.ComponentReflector wikiReflector = createWikiReflector();

    protected ilarkesto.core.scope.ComponentReflector pingerReflector = createPingerReflector();

    protected ilarkesto.core.scope.ComponentReflector changeHistoryManagerReflector = createChangeHistoryManagerReflector();

    public void injectComponents(Object component, ilarkesto.core.scope.Scope scope) {
        if (component instanceof scrum.client.collaboration.Chat) chatReflector.injectComponents(component, scope);
        if (component instanceof scrum.client.collaboration.Wiki) wikiReflector.injectComponents(component, scope);
        if (component instanceof scrum.client.communication.Pinger) pingerReflector.injectComponents(component, scope);
        if (component instanceof scrum.client.journal.ChangeHistoryManager) changeHistoryManagerReflector.injectComponents(component, scope);
    }

    public void callInitializationMethods(Object component) {
        if (component instanceof scrum.client.collaboration.Chat) chatReflector.callInitializationMethods(component);
        if (component instanceof scrum.client.collaboration.Wiki) wikiReflector.callInitializationMethods(component);
        if (component instanceof scrum.client.communication.Pinger) pingerReflector.callInitializationMethods(component);
        if (component instanceof scrum.client.journal.ChangeHistoryManager) changeHistoryManagerReflector.callInitializationMethods(component);
    }

    public void outjectComponents(Object component, ilarkesto.core.scope.Scope scope) {
        if (component instanceof scrum.client.collaboration.Chat) chatReflector.outjectComponents(component, scope);
        if (component instanceof scrum.client.collaboration.Wiki) wikiReflector.outjectComponents(component, scope);
        if (component instanceof scrum.client.communication.Pinger) pingerReflector.outjectComponents(component, scope);
        if (component instanceof scrum.client.journal.ChangeHistoryManager) changeHistoryManagerReflector.outjectComponents(component, scope);
    }

    public ilarkesto.core.scope.ComponentReflector createChatReflector() {
        return new scrum.client.collaboration.GChatReflector();
    }

    public ilarkesto.core.scope.ComponentReflector createWikiReflector() {
        return new scrum.client.collaboration.GWikiReflector();
    }

    public ilarkesto.core.scope.ComponentReflector createPingerReflector() {
        return new scrum.client.communication.GPingerReflector();
    }

    public ilarkesto.core.scope.ComponentReflector createChangeHistoryManagerReflector() {
        return new scrum.client.journal.GChangeHistoryManagerReflector();
    }

}

