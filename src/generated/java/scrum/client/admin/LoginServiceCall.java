// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.admin;

public class LoginServiceCall extends scrum.client.core.AServiceCall {

    private String username;

    private String password;

    public  LoginServiceCall(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().login(serviceCaller.getConversationNumber(), username, password, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "Login";
    }

}

