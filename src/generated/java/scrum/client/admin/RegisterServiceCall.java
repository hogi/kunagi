// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.admin;

public class RegisterServiceCall extends scrum.client.core.AServiceCall {

    private String username;

    private String password;

    private String email;

    public  RegisterServiceCall(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().register(serviceCaller.getConversationNumber(), username, password, email, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "Register";
    }

}

