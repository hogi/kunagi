// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.admin;

public class LoginServiceCall implements ilarkesto.core.service.ServiceCall {

    private String password;

    private String username;

    public  LoginServiceCall(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public void execute(Runnable returnHandler) {
        scrum.client.ScrumGwtApplication.get().callLogin(password, username, returnHandler);
    }

    public void execute() {
        execute(null);
    }

}

