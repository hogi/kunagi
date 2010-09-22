// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.workspace;

public abstract class GNavigator {

    protected static ilarkesto.core.logging.Log log = ilarkesto.core.logging.Log.get(Navigator.class);

    public abstract void initialize();

    protected scrum.client.ScrumGwtApplication app;

    protected scrum.client.admin.Auth auth;

    protected scrum.client.Dao dao;

    @Override
    public String toString() {
        return "Navigator";
    }

}

