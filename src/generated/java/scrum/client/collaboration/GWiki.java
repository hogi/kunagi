package scrum.client.collaboration;

public abstract class GWiki extends scrum.client.AScrumComponent {

    protected static ilarkesto.core.logging.Log log = ilarkesto.core.logging.Log.get(Wiki.class);

    public abstract void initialize();
    protected scrum.client.project.Project project;

    protected scrum.client.files.Uploader uploader;

}

