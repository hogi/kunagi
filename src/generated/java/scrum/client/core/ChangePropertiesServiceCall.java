// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.core;

public class ChangePropertiesServiceCall extends scrum.client.core.AServiceCall {

    private String entityId;

    private java.util.Map properties;

    public  ChangePropertiesServiceCall(String entityId, java.util.Map properties) {
        this.entityId = entityId;
        this.properties = properties;
    }

    public void execute(Runnable returnHandler) {
        serviceCaller.onServiceCall();
        serviceCaller.getService().changeProperties(serviceCaller.getConversationNumber(), entityId, properties, new DefaultCallback(returnHandler));
    }

    @Override
    public String toString() {
        return "ChangeProperties";
    }

}

