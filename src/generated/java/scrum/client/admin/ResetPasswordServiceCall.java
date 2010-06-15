// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.admin;

public class ResetPasswordServiceCall extends scrum.client.core.AServiceCall {

    private String userId;

    public  ResetPasswordServiceCall(String userId) {
        this.userId = userId;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().resetPassword(serviceCaller.getConversationNumber(), userId, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "ResetPassword";
    }

}

