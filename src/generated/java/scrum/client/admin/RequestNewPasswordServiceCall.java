// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.admin;

public class RequestNewPasswordServiceCall extends scrum.client.core.AServiceCall {

    private String login;

    public  RequestNewPasswordServiceCall(String login) {
        this.login = login;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().requestNewPassword(serviceCaller.getConversationNumber(), login, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "RequestNewPassword";
    }

}

