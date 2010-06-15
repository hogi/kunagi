// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.project;

public class CreateExampleProjectServiceCall implements ilarkesto.core.service.ServiceCall {

    public  CreateExampleProjectServiceCall() {
    }

    public void execute(Runnable returnHandler) {
        scrum.client.ScrumGwtApplication.get().callCreateExampleProject(returnHandler);
    }

    public void execute() {
        execute(null);
    }

}

