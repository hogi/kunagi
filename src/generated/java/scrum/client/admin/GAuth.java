package scrum.client.admin;

public abstract class GAuth extends scrum.client.AScrumComponent {

    protected static ilarkesto.core.logging.Log log = ilarkesto.core.logging.Log.get(Auth.class);

    protected scrum.client.ScrumGwtApplication app;

    protected scrum.client.Dao dao;

    protected scrum.client.workspace.PublicWorkspaceWidgets publicWorkspaceWidgets;

}

