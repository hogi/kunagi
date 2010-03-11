package scrum.client.workspace;

public abstract class GPublicWorkspaceWidgets extends scrum.client.AScrumComponent {

    protected static ilarkesto.core.logging.Log log = ilarkesto.core.logging.Log.get(PublicWorkspaceWidgets.class);

    public abstract void initialize();
    protected scrum.client.ScrumGwtApplication app;

    protected Ui ui;

}

