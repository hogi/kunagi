// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.core;

public class CreateEntityServiceCall extends scrum.client.core.AServiceCall {

    private String type;

    private java.util.Map properties;

    public  CreateEntityServiceCall(String type, java.util.Map properties) {
        this.type = type;
        this.properties = properties;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().createEntity(serviceCaller.getConversationNumber(), type, properties, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "CreateEntity";
    }

}

