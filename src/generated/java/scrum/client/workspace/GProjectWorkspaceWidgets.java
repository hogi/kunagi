package scrum.client.workspace;

public abstract class GProjectWorkspaceWidgets {

    protected static ilarkesto.core.logging.Log log = ilarkesto.core.logging.Log.get(ProjectWorkspaceWidgets.class);

    protected scrum.client.ScrumGwtApplication app;

    protected scrum.client.Dao dao;

    @Override
    public String toString() {
        return "ProjectWorkspaceWidgets";
    }

}

