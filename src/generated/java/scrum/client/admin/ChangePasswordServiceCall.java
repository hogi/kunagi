// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.admin;

public class ChangePasswordServiceCall extends scrum.client.core.AServiceCall {

    private String newPassword;

    private String oldPassword;

    public  ChangePasswordServiceCall(String newPassword, String oldPassword) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().changePassword(serviceCaller.getConversationNumber(), newPassword, oldPassword, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "ChangePassword";
    }

}

