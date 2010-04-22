package scrum.client.search;

public abstract class GSearch {

    protected static ilarkesto.core.logging.Log log = ilarkesto.core.logging.Log.get(Search.class);

    protected scrum.client.ScrumGwtApplication app;

    protected scrum.client.project.Project project;

    protected scrum.client.workspace.ProjectWorkspaceWidgets projectWorkspaceWidgets;

    @Override
    public String toString() {
        return "Search";
    }

}

