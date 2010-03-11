package scrum.client.undo;

public abstract class GUndo extends scrum.client.AScrumComponent {

    protected static ilarkesto.core.logging.Log log = ilarkesto.core.logging.Log.get(Undo.class);

    public abstract void initialize();
}

