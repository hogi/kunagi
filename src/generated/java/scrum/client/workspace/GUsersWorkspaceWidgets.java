package scrum.client.workspace;

public abstract class GUsersWorkspaceWidgets extends scrum.client.AScrumComponent {

    protected static ilarkesto.core.logging.Log log = ilarkesto.core.logging.Log.get(UsersWorkspaceWidgets.class);

    public abstract void initialize();
    protected scrum.client.admin.Auth auth;

    protected Ui ui;

}

