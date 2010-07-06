// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.project;

public class UpdateProjectHomepageServiceCall extends scrum.client.core.AServiceCall {

    public  UpdateProjectHomepageServiceCall() {
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().updateProjectHomepage(serviceCaller.getConversationNumber(), new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "UpdateProjectHomepage";
    }

}

