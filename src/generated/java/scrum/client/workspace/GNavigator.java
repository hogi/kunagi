package scrum.client.workspace;

public abstract class GNavigator extends scrum.client.AScrumComponent {

    protected static ilarkesto.core.logging.Log log = ilarkesto.core.logging.Log.get(Navigator.class);

    public abstract void initialize();
    protected scrum.client.admin.Auth auth;

    protected scrum.client.Dao dao;

}

