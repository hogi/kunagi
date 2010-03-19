package scrum.client.collaboration;

public abstract class GChat extends scrum.client.AScrumComponent {

    protected static ilarkesto.core.logging.Log log = ilarkesto.core.logging.Log.get(Chat.class);

    protected scrum.client.admin.Auth auth;

    protected scrum.client.Dao dao;

    protected scrum.client.project.Project project;

}

